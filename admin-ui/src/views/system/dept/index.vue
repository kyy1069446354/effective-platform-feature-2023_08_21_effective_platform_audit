<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
              v-model="deptName"
              placeholder="请输入部门名称"
              clearable
              prefix-icon="Search"
              style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree-v2
              ref="deptTreeRef"
              :data="deptOptions"
              :props="{ label: 'name', children: 'children' }"
              :filter-method="filterNode"
              :expand-on-click-node="false"
              node-key="id"
              highlight-current
              @node-click="handleNodeClick"
              :empty-text="treeEmptyText"
              :default-expanded-keys="[25000100]"
              :height="800"
          />
        </div>
      </el-col>
      <!--用户数据-->
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
          <el-form-item label="部门名称" prop="name">
            <el-input v-model="queryParams.name" placeholder="请输入部门名称" clearable @keyup.enter="handleQuery" style="width: 240px"/>
          </el-form-item>
          <el-form-item label="部门编码" prop="code">
            <el-input v-model="queryParams.code" placeholder="请输入部门编码" clearable @keyup.enter="handleQuery" style="width: 240px"/>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="queryParams.status" placeholder="部门状态" clearable>
              <el-option v-for="dict in sys_status" :key="dict.value" :label="dict.label" :value="dict.value" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermission="['system:dept:add']"
              >新增</el-button
            >
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" @click="handleClean" v-hasPermission="['system:dept:remove']"
            >清空</el-button
            >
          </el-col>
          <el-col :span="1.5">
            <el-button type="info" plain icon="Upload" @click="handleImport" v-hasPermission="['system:dept:import']"
            >导入</el-button
            >
          </el-col>
          <el-col :span="1.5">
            <el-button
                type="warning"
                plain
                icon="Download"
                @click="handleExport"
                v-hasPermission="['system:dept:export']"
            >导出</el-button>
          </el-col>

          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="deptList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column prop="name" label="部门名称" width="260"></el-table-column>
          <el-table-column prop="code" label="部门编码" width="260"></el-table-column>
          <el-table-column prop="orderNum" label="排序" width="200"></el-table-column>
          <el-table-column prop="status" label="状态" width="100">
            <template #default="scope">
              <dict-tag :options="sys_status" :value="scope.row.status" />
            </template>
          </el-table-column>
          <el-table-column label="创建时间" align="center" prop="createTime" width="200">
            <template #default="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template #default="scope">
              <el-button
                link
                type="primary"
                icon="Plus"
                @click="handleAdd(scope.row)"
                v-hasPermission="['system:dept:add']"
                >新增</el-button>
              <el-button
                link
                type="primary"
                icon="Edit"
                @click="handleUpdate(scope.row)"
                v-hasPermission="['system:dept:edit']"
                >修改</el-button>
              <el-button
                v-if="scope.row.parentId != 0"
                link
                type="primary"
                icon="Delete"
                @click="handleDelete(scope.row)"
                v-hasPermission="['system:dept:remove']"
                >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
            v-show="total > 0"
            :total="total"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="getList"
        />
      </el-col>
    </el-row>

    <!-- 添加或修改部门对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="deptRef" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24" v-if="form.parentId !== 0">
            <el-form-item label="上级部门" prop="parentId">
              <el-tree-select
                v-model="form.parentId"
                :data="deptOptions"
                :props="{ value: 'id', label: 'name', children: 'children' }"
                node-key="id"
                placeholder="选择上级部门"
                check-strictly
                :current-node-key="form.parentId"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="部门名称" prop="deptName">
              <el-input v-model="form.name" placeholder="请输入部门名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="部门编码" prop="deptName">
              <el-input v-model="form.code" placeholder="请输入部门编码" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="显示排序" prop="orderNum">
              <el-input-number v-model="form.orderNum" controls-position="right" :min="0" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="负责人" prop="leaderName">
              <el-input v-model="form.leaderName" placeholder="请输入负责人" maxlength="20" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入联系电话" maxlength="11" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="邮箱" prop="email">
              <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="部门状态">
              <el-radio-group v-model="form.status">
                <el-radio v-for="dict in sys_status" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 部门导入对话框 -->
    <el-dialog :title="upload.title" v-model="upload.open" width="400px" append-to-body>
      <el-upload
          ref="uploadRef"
          :limit="1"
          accept=".xlsx, .xls"
          :headers="upload.headers"
          :action="upload.url + '?updateSupport=' + upload.updateSupport"
          :disabled="upload.isUploading"
          :on-progress="handleFileUploadProgress"
          :on-success="handleFileSuccess"
          :auto-upload="false"
          drag
      >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <template #tip>
          <div class="el-upload__tip text-center">
            <div class="el-upload__tip"><el-checkbox v-model="upload.updateSupport" />更新已经存在的部门数据</div>
            <span>仅允许导入xls、xlsx格式文件。</span>
            <el-link
                type="primary"
                :underline="false"
                style="font-size: 12px; vertical-align: baseline"
                @click="downloadTemplate"
            >下载模板</el-link
            >
          </div>
        </template>
      </el-upload>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitFileForm">确 定</el-button>
          <el-button @click="upload.open = false">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Dept">
import { getToken } from "@/utils/token";
import * as deptApi from '@/api/system/deptApi';

const { proxy } = getCurrentInstance();
const { sys_status } = proxy.useDict('sys_status');

const deptName = ref('');
const treeEmptyText = ref('暂无数据')

const deptList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const title = ref('');
const total = ref(0);
const deptOptions = ref(undefined);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
/** * 部门导入参数 */
const upload = reactive({
  // 是否显示弹出层（用户导入）
  open: false,
  // 弹出层标题（用户导入）
  title: '',
  // 是否禁用上传
  isUploading: false,
  // 是否更新已经存在的用户数据
  updateSupport: 0,
  // 设置上传的请求头部
  headers: { Authorization: `Bearer ${getToken()}` },
  // 上传的地址
  url: `${import.meta.env.VITE_APP_BASE_API}/system/dept/importData`,
});
const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    id: undefined,
    name: undefined,
    status: undefined,
    code: undefined
  },
  rules: {
    parentId: [{ required: true, message: '上级部门不能为空', trigger: 'blur' }],
    name: [{ required: true, message: '部门名称不能为空', trigger: 'blur' }],
    code: [{ required: true, message: '部门编码不能为空', trigger: 'blur' }],
    orderNum: [{ required: true, message: '显示排序不能为空', trigger: 'blur' }],
    email: [{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }],
    phone: [{ pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: '请输入正确的手机号码', trigger: 'blur' }],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 初始化部门数据 */
function initTreeData() {
  // 判断部门的数据是否存在，存在不获取，不存在则获取
  if (deptOptions.value === undefined) {
    treeEmptyText.value = "数据加载中..."
    deptApi.getDeptSelectTree().then((response) => {
      deptOptions.value = response;
      treeEmptyText.value = "暂无数据"
    });
  }
}
function refreshTreeData() {
  deptOptions.value = undefined;
  initTreeData();
}
/** 根据名称筛选部门树 */
watch(deptName, (val) => {
  proxy.$refs.deptTreeRef.filter(val);
});
/** 通过条件过滤节点  */
const filterNode = (value, data) => {
  if (!value) return true;
  return data.label.includes(value);
};
/** 节点单击事件 */
function handleNodeClick(data) {
  queryParams.value.id = data.id;
  handleQuery();
}
/** 清空按钮操作 */
function handleClean() {
  proxy.$modal
      .confirm('是否确认清空所有数据项?')
      .then(() => deptApi.deleteAll())
      .then(() => {
        refreshTreeData()
        getList();
        proxy.$modal.msgSuccess('清空成功');
      })
      .catch(() => {});
}
/** 查询部门列表 */
function getList() {
  loading.value = true;
  deptApi
    .listDept(queryParams.value)
    .then((response) => {
      deptList.value = response.records;
      total.value = response.total;
    })
    .finally(() => {
      loading.value = false;
    });
}
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}
/** 表单重置 */
function reset() {
  form.value = {
    id: undefined,
    parentId: undefined,
    name: undefined,
    code: undefined,
    orderNum: 0,
    leaderName: undefined,
    phone: undefined,
    email: undefined,
    status: 1,
  };
  proxy.resetForm('deptRef');
}
/** 搜索按钮操作 */
function handleQuery() {
  getList();
}
/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm('queryRef');
  queryParams.value.id = undefined;
  handleQuery();
}
/** 新增按钮操作 */
function handleAdd(row) {
  reset();
  if (row !== undefined) {
    form.value.parentId = row.id;
  }
  open.value = true;
  title.value = '添加部门';
}
/** 选择条数  */
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.id);
  single.value = selection.length === 1;
  multiple.value = selection.length;
}
/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  deptApi.getDept(row.id).then((response) => {
    form.value = response;
    open.value = true;
    title.value = '修改部门';
  });
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs.deptRef.validate((valid) => {
    if (valid) {
      if (form.value.id !== undefined) {
        deptApi.updateDept(form.value).then((response) => {
          proxy.$modal.msgSuccess('修改成功');
          open.value = false;
          getList();
          refreshTreeData();
        });
      } else {
        deptApi.addDept(form.value).then((response) => {
          proxy.$modal.msgSuccess('新增成功');
          open.value = false;
          getList();
          refreshTreeData();
        });
      }
    }
  });
}
/** 删除按钮操作 */
function handleDelete(row) {
  proxy.$modal
    .confirm(`是否确认删除名称为"${row.name}"的数据项?`)
    .then(() => deptApi.deleteDept(row.id))
    .then(() => {
      getList();
      refreshTreeData();
      proxy.$modal.msgSuccess('删除成功');
    })
    .catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download("system/dept/export", {
    ...queryParams.value
  }, `dept_${new Date().getTime()}.xlsx`);
}
/** 下载模板操作 */
function downloadTemplate() {
  proxy.download('system/dept/downloadTemplate', {}, `dept_template_${new Date().getTime()}.xlsx`);
}
/** 提交上传文件 */
function submitFileForm() {
  proxy.$refs.uploadRef.submit();
}
/** 文件上传中处理 */
const handleFileUploadProgress = (event, file, fileList) => {
  upload.isUploading = true;
};
/** 文件上传成功处理 */
const handleFileSuccess = (response, file, fileList) => {
  upload.open = false;
  upload.isUploading = false;
  proxy.$refs.uploadRef.clearFiles();
  proxy.$alert(
      `<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>${response.data || response.message}</div>`,
      '导入结果',
      { dangerouslyUseHTMLString: true },
  );
  getList();
  refreshTreeData()
};
/** 导入按钮操作 */
function handleImport() {
  upload.title = '部门导入';
  upload.open = true;
}
getList();
refreshTreeData()
</script>
