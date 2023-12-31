<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="参数名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入参数名称"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参数键名" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入参数键名"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="允许修改" prop="isAllowChange">
        <el-select v-model="queryParams.isAllowChange" placeholder="允许修改" clearable>
          <el-option v-for="dict in sys_yes_no" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" style="width: 308px">
        <el-date-picker
          v-model="dateRange"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
        <el-input v-show="false"></el-input>
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
          :disabled="single"
          @click="handleUpdate"
          v-hasPermission="['system:config:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Refresh"
          @click="handleRefreshCache"
          v-hasPermission="['system:config:remove']"
          >刷新缓存</el-button
        >
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="configList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="参数名称" align="center" prop="name" :show-overflow-tooltip="true" />
      <el-table-column label="参数键名" align="center" prop="code" :show-overflow-tooltip="true" />
      <el-table-column label="参数键值" align="center" prop="value" />
      <el-table-column label="可选值" align="center" prop="options" />
      <el-table-column label="允许修改" align="center" prop="isAllowChange" >
        <template #default="scope">
          <span>{{ scope.row.isAllowChange === true ? "是" : "否" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermission="['system:config:edit']"
            >修改</el-button
          >
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

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="configRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="参数名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入参数名称" :disabled="true" />
        </el-form-item>
        <el-form-item label="参数键名" prop="code">
          <el-input v-model="form.code" placeholder="请输入参数键名" :disabled="true" />
        </el-form-item>
        <el-form-item label="参数键值" prop="value">
          <el-select v-if="form.options.length > 0" v-model="form.value" placeholder="Select">
            <el-option v-for="item in form.options" :key="item" :label="item" :value="item" />
          </el-select>
          <el-input v-else v-model="form.value" placeholder="请输入参数键值" />
        </el-form-item>
        <el-form-item label="允许修改" prop="isAllowChange">
          <el-radio-group v-model="form.isAllowChange" :disabled="true">
            <el-radio :label="true">是</el-radio>
            <el-radio :label="false">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" :disabled="true" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Config">
// import { listConfig, getConfig, updateConfig, refreshCache } from '@/api/system/configApi';
import * as configApi from '@/api/system/configApi';

const { proxy } = getCurrentInstance();
const { sys_yes_no } = proxy.useDict('sys_yes_no');

const configList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref('');
const dateRange = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: undefined,
    code: undefined,
    configType: undefined,
  },
  rules: {
    name: [{ required: true, message: '参数名称不能为空', trigger: 'blur' }],
    code: [{ required: true, message: '参数键名不能为空', trigger: 'blur' }],
    value: [{ required: true, message: '参数键值不能为空', trigger: 'blur' }],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询参数列表 */
function getList() {
  loading.value = true;
  configApi
    .listConfig(proxy.addTimeRange(queryParams.value, dateRange.value))
    .then((response) => {
      configList.value = response.records;
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
    name: undefined,
    code: undefined,
    value: undefined,
    isAllowChange: undefined,
    options: [],
    remark: undefined,
  };
  proxy.resetForm('configRef');
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
/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.id);
  single.value = selection.length !== 1;
  multiple.value = !selection.length;
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const configId = row.id;
  configApi.getConfig(configId).then((response) => {
    response.options = response.options.length > 0 ? JSON.parse(response.options) : response.options
    form.value = response;
    open.value = true;
    title.value = '修改参数';
  });
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs.configRef.validate((valid) => {
    if (valid) {
      configApi.updateConfig(form.value).then(() => {
        proxy.$modal.msgSuccess('修改成功');
        open.value = false;
        getList();
      });
    }
  });
}

/** 刷新缓存按钮操作 */
function handleRefreshCache() {
  configApi.refreshCache().then(() => {
    proxy.$modal.msgSuccess('刷新缓存成功');
  });
}

getList();
</script>
