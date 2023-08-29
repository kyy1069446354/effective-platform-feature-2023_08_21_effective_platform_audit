<template>
  <el-dialog title="审批流程详情" v-model="visible" width="80%" top="5vh" append-to-body>
      

      <el-row :gutter="10" class="mb8">
         <el-col :span="1.5">
            <el-button
               type="primary"
               plain
               icon="Plus"
               @click="handleAdd"
               v-hasPermission="['system:dict:add']"
            >新增</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="success"
               plain
               icon="Edit"
               :disabled="single"
               @click="handleUpdate"
               v-hasPermission="['system:dict:edit']"
            >修改</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="danger"
               plain
               icon="Delete"
               :disabled="multiple"
               @click="handleDelete"
               v-hasPermission="['system:dict:remove']"
            >删除</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="warning"
               plain
               icon="Download"
               @click="handleExport"
               v-hasPermission="['system:dict:export']"
            >导出</el-button>
         </el-col>
         <el-col :span="1.5">
            <el-button
               type="warning"
               plain
               icon="Close"
               @click="handleClose"
            >关闭</el-button>
         </el-col>
         <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
         <el-table-column type="selection" width="55" align="center" />
         <el-table-column label="审批人姓名" align="center" prop="dictCode" />
		 <el-table-column label="审批类型" align="center" prop="remark" :show-overflow-tooltip="true" />
		 
         <el-table-column label="审批顺序" align="center" prop="label">
            <template #default="scope">
               <span v-if="scope.row.listClass == '' || scope.row.listClass == 'default'">{{ scope.row.label }}</span>
               <el-tag v-else :type="scope.row.listClass == 'primary' ? '' : scope.row.listClass">{{ scope.row.label }}</el-tag>
            </template>
         </el-table-column>
         <el-table-column label="部门" align="center" prop="value" />
         <el-table-column label="联系方式" align="center" prop="orderNum" />
         <el-table-column label="状态" align="center" prop="status" width="100">
            <template #default="scope">
               <dict-tag :options="sys_status" :value="scope.row.status" />
            </template>
         </el-table-column>
         <el-table-column label="创建时间" align="center" prop="createTime" width="180">
            <template #default="scope">
               <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
         </el-table-column>
         <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
            <template #default="scope">
				<el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermission="['system:dict:edit']">停用</el-button>
				
               <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermission="['system:dict:edit']">修改</el-button>
               <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermission="['system:dict:remove']">删除</el-button>
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
         <el-form ref="dataRef" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="字典类型">
               <el-input v-model="form.dictCode" :disabled="true" />
            </el-form-item>
            <el-form-item label="工号" prop="dictLabel">
               <el-input v-model="form.label" placeholder="请输入数据标签" />
            </el-form-item>
            <el-form-item label="姓名" prop="dictValue">
               <el-input v-model="form.value" placeholder="请输入数据键值" />
            </el-form-item>
            <el-form-item label="部门" prop="cssClass">
               <el-input v-model="form.cssClass" placeholder="请输入样式属性" />
            </el-form-item>
            <el-form-item label="联系方式" prop="cssClass">
               <el-input v-model="form.cssClass" placeholder="请输入样式属性" />
            </el-form-item>

            <el-form-item label="审批顺序" prop="listClass">
               <el-select v-model="form.listClass">
                  <el-option
                     v-for="item in listClassOptions"
                     :key="item.value"
                     :label="item.label + '(' + item.value + ')'"
                     :value="item.value"
                  ></el-option>
               </el-select>
            </el-form-item>
            <el-form-item label="状态" prop="status">
               <el-radio-group v-model="form.status">
                  <el-radio
                     v-for="dict in sys_status"
                     :key="dict.value"
                     :label="dict.value"
                  >{{ dict.label }}</el-radio>
               </el-radio-group>
            </el-form-item>
            <el-form-item label="备注" prop="remark">
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
  </el-dialog>
</template>

<script setup name="Data">
import { optionselect as getDictOptionselect} from "@/api/system/dict/type";
import { listData, getData, delData, addData, updateData } from "@/api/system/dict/data";
import store from "@/store";


const { proxy } = getCurrentInstance();
const { sys_status } = proxy.useDict("sys_status");

const visible = ref(false)
const dataList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const defaultDictType = ref("");
const typeOptions = ref([]);
const route = useRoute();
// 数据标签回显样式
const listClassOptions = ref([
  { value: "default", label: "默认" },
  { value: "primary", label: "主要" },
  { value: "success", label: "成功" },
  { value: "info", label: "信息" },
  { value: "warning", label: "警告" },
  { value: "danger", label: "危险" }
]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    dictCode: undefined,
  },
  rules: {
    label: [{ required: true, message: "数据标签不能为空", trigger: "blur" }],
    value: [{ required: true, message: "数据键值不能为空", trigger: "blur" }],
    orderNum: [{ required: true, message: "数据顺序不能为空", trigger: "blur" }]
  }
});

const { queryParams, form, rules } = toRefs(data);


/** 查询字典类型列表 */
function getTypeList() {
  getDictOptionselect().then(response => {
    typeOptions.value = response;
  });
}
/** 查询字典数据列表 */
function getList() {
  loading.value = true;
  listData(queryParams.value).then(response => {
    dataList.value = response.records;
    total.value = response.total;
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
    dictCode: undefined,
    label: undefined,
    value: undefined,
    cssClass: undefined,
    listClass: "default",
    orderNum: 0,
    status: 1,
    remark: undefined
  };
  proxy.resetForm("dataRef");
}
/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}
/** 返回按钮操作 */
function handleClose() {
  visible.value = false;
}
/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}
/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加字典数据";
  form.value.dictCode = queryParams.value.dictCode;
}
/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}
/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const id = row.id || ids.value[0];
  getData(id).then(response => {
    form.value = response;
    open.value = true;
    title.value = "修改字典数据";
  });
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["dataRef"].validate(valid => {
    if (valid) {
      if (form.value.id != undefined) {
        updateData(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          store.commit("CLEAN_DICT")
          getList();
        });
      } else {
        addData(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          store.commit("CLEAN_DICT")
          getList();
        });
      }
    }
  });
}
/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id != null ? Array.of(row.id) : ids.value;
  proxy.$modal.confirm('是否确认删除选中的数据项？').then(function() {
    return delData(_ids);
  }).then(() => {
    store.commit("CLEAN_DICT")
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download("system/dictData/export", {
    ...queryParams.value
  }, `dict_data_${new Date().getTime()}.xlsx`);
}
// 显示弹框
function show(row) {
  queryParams.value.dictCode = row.code;
  getList();
  visible.value = true
}

defineExpose({
  show
})
getTypeList();
</script>
