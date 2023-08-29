<template>
  <div class="app-container">
	         
   <el-card class="form-card">
	    <h2>VPN申请</h2>
    <el-form :model="queryParams" ref="queryRef" :rules="rules" v-show="showSearch" :inline="true">
      <el-form-item label="申请单位名" prop="name" required>
        <el-input
          v-model="queryParams.name"
          placeholder="请输入申请单位名称"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请人姓名" prop="code" required>
        <el-input
          v-model="queryParams.code"
          placeholder="请输入申请人姓名"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
	  <el-form-item label="申请人联系电话" prop="code" required>
	    <el-input
	      v-model="queryParams.code"
	      placeholder="请输入联系电话"
	      clearable
	      style="width: 240px"
	      @keyup.enter="handleQuery"
	    />
	  </el-form-item>
	  

	  <el-form-item label="申请人邮箱" prop="name" required>
	    <el-input
	      v-model="queryParams.name"
	      placeholder="请输入邮箱"
	      clearable
	      style="width: 240px"
	      @keyup.enter="handleQuery"
	    />
	  </el-form-item>
	  
	  <el-form-item label="申请原因" prop="code" required>
	    <el-input
		 type="textarea"
		  :autosize="{ minRows: 2, maxRows: 4}"
	      v-model="queryParams.code"
	      placeholder="请输入申请原因"
	      clearable
	      style="width: 300px"
	      @keyup.enter="handleQuery"
	     />
	  </el-form-item>
	   
<el-row :gutter="10" class="mb8" justify="end">
  <el-col :span="1.5">
    <el-button type="primary" plain  @click="handleAdd" v-hasPermission="['system:role:add']"
      >提交申请</el-button
    >
  </el-col>
</el-row>
	

	  
  </el-form>
   </el-card> 
      <el-form :model="queryParams" ref="queryRef" v-show="showSearch" :inline="true">
      <el-form-item label="申请单位名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入申请单位名称"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">

      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="!single"
          @click="handleUpdate"
          v-hasPermission="['system:role:edit']"
          >修改申请</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="!multiple"
          @click="handleDelete"
          v-hasPermission="['system:role:remove']"
          >撤销申请</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="Download" @click="handleExport" v-hasPermission="['system:role:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 表格数据 -->
    <el-table v-loading="loading" :data="roleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="申请单位名称" align="center" prop="name" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="申请人姓名" align="center" prop="code" :show-overflow-tooltip="true" width="150" />
      <el-table-column label="申请原因" align="center" prop="orderNum" width="100" />
          <el-table-column prop="status" align="center" label="申请状态"  sortable width="100">
            <template #default="scope">
              <dict-tag :options="sys_status" :value="scope.row.status" />
            </template>
          </el-table-column>
      <el-table-column label="创建时间" align="center" sortable prop="createTime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-tooltip content="修改" placement="top" v-if="scope.row.id !== 1">
            <el-button
              link
              type="primary"
              icon="Edit"
              @click="handleUpdate(scope.row)"
              v-hasPermission="['system:role:edit']"
            ></el-button>
          </el-tooltip>
          <el-tooltip content="撤销" placement="top" v-if="scope.row.id !== 1">
            <el-button
              link
              type="primary"
              icon="Delete"
              @click="handleDelete(scope.row)"
              v-hasPermission="['system:role:remove']"
            ></el-button>
          </el-tooltip>

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

    <!-- 添加或修改角色配置对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="roleRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="申请单位" prop="name">
          <el-input v-model="form.name" placeholder="请输入角色名称" />
        </el-form-item>
 <el-form-item label="申请人姓名" prop="name">
   <el-input v-model="form.name" placeholder="请输入角色名称" />
 </el-form-item>
 <el-form-item label="申请人电话" prop="name">
   <el-input v-model="form.name" placeholder="请输入角色名称" />
 </el-form-item>
 <el-form-item label="申请人邮箱" prop="name">
   <el-input v-model="form.name" placeholder="请输入角色名称" />
 </el-form-item>
        <el-form-item label="申请理由">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

    <!-- 分配角色数据权限对话框 -->
    
  </div>
</template>

<script setup name="Role">
import * as roleApi from '@/api/system/roleApi';
import * as menuApi from '@/api/system/menuApi';
import * as deptApi from '@/api/system/deptApi';

const router = useRouter();
const { proxy } = getCurrentInstance();
const { sys_status } = proxy.useDict('sys_status');

const roleList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const names = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref('');
const dateRange = ref([]);
const menuOptions = ref([]);
const menuExpand = ref(false);
const menuNodeAll = ref(false);
const deptExpand = ref(true);
const deptNodeAll = ref(false);
const deptOptions = ref([]);
const openDataScope = ref(false);
const menuRef = ref(null);
const deptRef = ref(null);

/** 数据范围选项 */
const dataScopeOptions = ref([
  { value: 1, label: '全部数据权限' },
  // { value: 2, label: '自定数据权限' },
  { value: 3, label: '本部门数据权限' },
  { value: 4, label: '本部门及以下数据权限' },
  { value: 5, label: '仅本人数据权限' },
]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: undefined,
    code: undefined,
    status: undefined,
  },
  rules: {
    name: [{ required: true, message: '角色名称不能为空', trigger: 'blur' }],
    code: [{ required: true, message: '权限字符不能为空', trigger: 'blur' }],
    orderNum: [{ required: true, message: '角色顺序不能为空', trigger: 'blur' }],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询角色列表 */
function getList() {
  loading.value = true;
  roleApi
    .listRole(proxy.addTimeRange(queryParams.value, dateRange.value))
    .then((response) => {
      roleList.value = response.records;
      total.value = response.total;
    })
    .finally(() => {
      loading.value = false;
    });
}
/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}
/** 重置按钮操作 */
function resetQuery() {
  dateRange.value = [];
  proxy.resetForm('queryRef');
  handleQuery();
}
/** 删除按钮操作 */
function handleDelete(row) {
  const roleIds = row.id != null ? Array.of(row.id) : ids.value;
  const roleNames = row.name || names.value;
  proxy.$modal
    .confirm(`是否确认删除角色为"${roleNames}"的数据项?`)
    .then(() => roleApi.deleteRole(roleIds))
    .then(() => {
      getList();
      proxy.$modal.msgSuccess('删除成功');
    })
    .catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    'system/role/export',
    {
      ...queryParams.value,
    },
    `role_${new Date().getTime()}.xlsx`,
  );
}
/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.id);
  names.value = selection.map((item) => item.name);
  single.value = selection.length === 1;
  multiple.value = selection.length;
}
/** 角色状态修改 */
function handleStatusChange(row) {
  const text = row.status === 1 ? '启用' : '停用';
  proxy.$modal
    .confirm(`确认要"${text}""${row.roleName}"角色吗?`)
    .then(() => roleApi.changeRoleStatus(row.id, row.status))
    .then(() => {
      proxy.$modal.msgSuccess(`${text}成功`);
    })
    .catch(() => {
      row.status = row.status === 0 ? 1 : 0;
    });
}
/** 更多操作 */
function handleCommand(command, row) {
  switch (command) {
    case 'handleDataScope':
      handleDataScope(row);
      break;
    case 'handleAuthUser':
      handleAuthUser(row);
      break;
    default:
      break;
  }
}
/** 分配用户 */
function handleAuthUser(row) {
  router.push(`/system/role-auth/user/${row.id}`);
}
/** 查询菜单树结构 */
function getMenuTreeSelect() {
  menuApi.getMenuSelectTree().then((response) => {
    menuOptions.value = response;
  });
}
/** 所有部门节点数据 */
function getDeptAllCheckedKeys() {
  // 目前被选中的部门节点
  const checkedKeys = deptRef.value.getCheckedKeys();
  // 半选中的部门节点
  const halfCheckedKeys = deptRef.value.getHalfCheckedKeys();
  checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
  return checkedKeys;
}
/** 重置新增的表单以及其他数据  */
function reset() {
  if (menuRef.value != undefined) {
    menuRef.value.setCheckedKeys([]);
  }
  menuExpand.value = false;
  menuNodeAll.value = false;
  deptExpand.value = true;
  deptNodeAll.value = false;
  form.value = {
    id: undefined,
    name: undefined,
    code: undefined,
    orderNum: 0,
    status: 0,
    menuIds: [],
    deptIds: [],
    menuCheckStrictly: true,
    deptCheckStrictly: true,
    remark: undefined,
  };
  proxy.resetForm('roleRef');
}
/** 添加角色 */
function handleAdd() {
  reset();
  getMenuTreeSelect();
  open.value = true;
  title.value = '添加角色';
}
/** 修改角色 */
async function handleUpdate(row) {
  reset();
  const roleId = row.id || ids.value[0];
  const roleMenu = await getRoleMenuTreeSelect(roleId);
  roleApi.getRole(roleId).then((response) => {
    form.value = response;
    open.value = true;
    const selectedMenuList = response.menuIds;
    nextTick(() => {
      selectedMenuList.forEach((v) => {
        nextTick(() => {
          menuRef.value.setChecked(v, true, false);
        });
      });
    });
    title.value = '修改角色';
  });
}
/** 根据角色ID查询菜单树结构 */
function getRoleMenuTreeSelect(roleId) {
  return menuApi.getMenuSelectTree(roleId).then((response) => {
    menuOptions.value = response;
    return response;
  });
}

/** 根据角色ID查询部门树结构 */
function getRoleDeptTreeSelect(roleId) {
  return deptApi.getDeptSelectTree(roleId).then((response) => {
    deptOptions.value = response;
    return response;
  });
}

/** 树权限（展开/折叠） */
function handleCheckedTreeExpand(value, type) {
  if (type == 'menu') {
    for (let i = 0; i < menuOptions.value.length; i++) {
      menuRef.value.store.nodesMap[menuOptions.value[i].menuId].expanded = value;
    }
  } else if (type == 'dept') {
    for (let i = 0; i < deptOptions.value.length; i++) {
      deptRef.value.store.nodesMap[deptOptions.value[i].id].expanded = value;
    }
  }
}
/** 树权限（全选/全不选） */
function handleCheckedTreeNodeAll(value, type) {
  if (type == 'menu') {
    menuRef.value.setCheckedNodes(value ? menuOptions.value : []);
  } else if (type == 'dept') {
    deptRef.value.setCheckedNodes(value ? deptOptions.value : []);
  }
}
/** 树权限（父子联动） */
function handleCheckedTreeConnect(value, type) {
  if (type == 'menu') {
    form.value.menuCheckStrictly = !!value;
  } else if (type == 'dept') {
    form.value.deptCheckStrictly = !!value;
  }
}
/** 所有菜单节点数据 */
function getMenuAllCheckedKeys() {
  // 目前被选中的菜单节点
  const checkedKeys = menuRef.value.getCheckedKeys();
  // 半选中的菜单节点
  const halfCheckedKeys = menuRef.value.getHalfCheckedKeys();
  checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
  return checkedKeys;
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs.roleRef.validate((valid) => {
    if (valid) {
      if (form.value.id != undefined) {
        form.value.menuIds = getMenuAllCheckedKeys();
        roleApi.updateRole(form.value).then((response) => {
          proxy.$modal.msgSuccess('修改成功');
          open.value = false;
          getList();
        });
      } else {
        form.value.menuIds = getMenuAllCheckedKeys();
        roleApi.addRole(form.value).then((response) => {
          proxy.$modal.msgSuccess('新增成功');
          open.value = false;
          getList();
        });
      }
    }
  });
}
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
}
/** 选择角色权限范围触发 */
function dataScopeSelectChange(value) {
  if (value !== '2') {
    deptRef.value.setCheckedKeys([]);
  }
}
/** 分配数据权限操作 */
async function handleDataScope(row) {
  reset();
  //const roleDeptTreeResponse = await getRoleDeptTreeSelect(row.id);

  roleApi.getRole(row.id).then((response) => {
    form.value = response;
    openDataScope.value = true;
    nextTick(() => {
      if (deptRef.value) {
        deptRef.value.setCheckedKeys(response.deptIds);
      }
    });
  });
  title.value = '分配数据权限';
}

/** 提交按钮（数据权限） */
function submitDataScope() {
  if (form.value.id !== undefined) {
    form.value.deptIds = getDeptAllCheckedKeys();
    roleApi.changeDataScope(form.value).then((response) => {
      proxy.$modal.msgSuccess('修改成功');
      openDataScope.value = false;
      getList();
    });
  }
}
/** 取消按钮（数据权限） */
function cancelDataScope() {
  openDataScope.value = false;
  reset();
}

getList();
single.value = 0;
multiple.value = 0;
</script>
<style scoped>
.vpn-application {
  max-width: 800px;
  margin: auto;
  padding: 20px;
}

.form-card {
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  padding: 20px;
}

.el-form-item__label {
  font-weight: bold;
}

.el-button {
  margin-right: 10px;
}

.el-table {
  width: 100%;
}
</style>