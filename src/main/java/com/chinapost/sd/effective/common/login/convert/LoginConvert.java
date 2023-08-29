package com.chinapost.sd.effective.common.login.convert;

import com.chinapost.sd.effective.common.login.vo.LoginUserInfoVO;
import com.chinapost.sd.effective.common.login.vo.RouterModel;
import com.chinapost.sd.effective.system.domain.dto.SysUser;
import com.chinapost.sd.effective.system.domain.po.SysMenuPO;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/7
 */
@Mapper
public interface LoginConvert {
    LoginConvert INSTANCE = Mappers.getMapper(LoginConvert.class);

    LoginUserInfoVO convert(LoginUserInfo dto);

    LoginUserInfo convert(SysUser dto);

    RouterModel convert(SysMenuPO po);

}
