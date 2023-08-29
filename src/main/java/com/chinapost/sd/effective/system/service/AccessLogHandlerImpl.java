package com.chinapost.sd.effective.system.service;

import cn.hutool.core.util.StrUtil;
import com.chinapost.sd.effective.system.constant.OperationStatusEnum;
import com.chinapost.sd.effective.system.domain.po.SysOperationLogPO;
import com.chinapost.sd.effective.system.mapper.SysOperationLogMapper;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import com.chinapost.sd.boot.commons.utils.json.JacksonUtil;
import com.chinapost.sd.boot.infrastructure.annotations.AccessLog;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.spi.AccessLogHandler;
import com.chinapost.sd.boot.infrastructure.thread.AsyncExecutor;
import com.chinapost.sd.boot.infrastructure.utils.AuthenticationUtils;
import com.chinapost.sd.boot.infrastructure.utils.IPUtils;
import com.chinapost.sd.boot.infrastructure.utils.ServletHolderUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/20
 */
@Slf4j
@Component
public class AccessLogHandlerImpl implements AccessLogHandler {
    @Autowired
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public void handleLog(JoinPoint joinPoint, AccessLog accessLog, Exception e, Object jsonResult) {
        try {
            SysOperationLogPO po = new SysOperationLogPO();
            po.setId(IdGenerator.generate());
            HttpServletRequest request = ServletHolderUtils.getRequest();
            String ip = IPUtils.getIpAddr(request);
            po.setOperatorIp(ip);
            LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
            if (loginUser != null) {
                po.setUsername(loginUser.getUsername());
                po.setUserId(loginUser.getUserId());
                po.setDeptId(loginUser.getDeptId());
            }
            po.setOperationTime(LocalDateTime.now());
            po.setRequestUrl(request.getRequestURI());
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            String methodFormat = StrUtil.format("{}.{}()", className, methodName);

            po.setCalledMethod(methodFormat);
            po.setRequestMethod(request.getMethod());

            // 是否需要保存request，参数和值
            if (accessLog.isSaveRequestData()) {
                po.setOperationParam(argsArrayToString(joinPoint.getArgs()));
            }else {
                po.setOperationParam("");
            }
            // 是否需要保存response，参数和值
            if (accessLog.isSaveResponseData() && jsonResult != null) {
                po.setOperationResult(JacksonUtil.toString(jsonResult));
            }else {
                po.setOperationResult("");
            }
            if (e != null) {
                po.setStatus(OperationStatusEnum.FAIL.getValue());
                po.setErrorStack(JacksonUtil.toString(e));
            } else {
                po.setStatus(OperationStatusEnum.SUCCESS.getValue());
                po.setErrorStack("");
            }
            // 设置action动作
            po.setBusinessType(accessLog.businessType().ordinal());
            // 设置标题
            po.setTitle(accessLog.title());
            // 设置操作人类别
            po.setOperatorType(accessLog.operatorType().ordinal());
            AsyncExecutor.execute(() -> sysOperationLogMapper.save(po));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("生成操作日志异常，异常信息:{}", exp.getMessage(), exp);
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        if (paramsArray == null || paramsArray.length == 0){
            return "";
        }
        List<Object> paramsList = Arrays.stream(paramsArray)
                .filter(param -> !this.isFilterObject(param))
                .collect(Collectors.toList());
        if (paramsList.isEmpty()){
            return "";
        }

        return JacksonUtil.toString(paramsList);
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o)
    {
        Class<?> clazz = o.getClass();
        if (clazz.isArray())
        {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        }
        else if (Collection.class.isAssignableFrom(clazz))
        {
            Collection collection = (Collection) o;
            for (Object value : collection)
            {
                return value instanceof MultipartFile;
            }
        }
        else if (Map.class.isAssignableFrom(clazz))
        {
            Map map = (Map) o;
            for (Object value : map.entrySet())
            {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
                || o instanceof BindingResult;
    }
}
