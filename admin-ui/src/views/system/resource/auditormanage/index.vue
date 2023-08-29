<template>
   <div class="app-container">
      <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
         <el-form-item label="审批类型" prop="code">
			 <el-select
				v-model="queryParams.status"
				placeholder="审批类型"
				clearable
				style="width: 240px"
			 >
				<el-option
				   v-for="dict in audit_types"
				   :key="dict.value"
				   :label="dict.label"
				   :value="dict.value"
				/>
			 </el-select>
         </el-form-item>
         <el-form-item label="状态" prop="status">
            <el-select
               v-model="queryParams.status"
               placeholder="审批规则状态"
               clearable
               style="width: 240px"
            >
               <el-option
                  v-for="dict in sys_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
               />
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
         </el-form-item>
         <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
         </el-form-item>
      </el-form>

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
         <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="typeList" @selection-change="handleSelectionChange">
         <el-table-column type="selection" width="55" align="center" />
         <el-table-column label="审批类型" align="center" :show-overflow-tooltip="true" >
            <template #default="scope">
              <el-button link type="primary" @click="handleDictData(scope.row)">{{ scope.row.code }}</el-button>
            </template>
         </el-table-column>
		 
		 
		 
         <el-table-column label="状态" align="center" prop="status" width="180">
            <template #default="scope">
               <dict-tag :options="sys_status" :value="scope.row.status" />
            </template>
         </el-table-column>
         <el-table-column label="创建时间" align="center" prop="createTime" width="200">
            <template #default="scope">
               <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
         </el-table-column>
		 <el-table-column label="备注" align="center" prop="orderNum" width="300" />
		 
         <el-table-column label="操作" align="center" width="250" class-name="small-padding fixed-width">
            <template #default="scope">
<!-- 				<el-button size="small" round :type='scope.row.couponstatus==0?unuserStyle:useStyle' @click="startCounpon($event, scope.row)">{{scope.row.couponstatus==0?"启用":"禁用"}}</el-button>
 -->				
               <el-button type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermission="['system:dict:edit']">启用</el-button>
               <el-button  type="danger" icon="Delete" @click="handleDelete(scope.row)" v-hasPermission="['system:dict:remove']">删除</el-button>
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
         <el-form ref="dictRef" :model="form" :rules="rules" label-width="80px">

            <el-form-item label="审批类型" prop="code">
            			 <el-select
            				v-model="queryParams.status"
            				placeholder="审批类型"
            				clearable
            				style="width: 240px"
            			 >
            				<el-option
            				   v-for="dict in audit_types"
            				   :key="dict.value"
            				   :label="dict.label"
            				   :value="dict.value"
            				/>
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
			<el-form-item label="备注" prop="cssClass">
			   <el-input type="textarea"	v-model="form.cssClass" placeholder="请输入备注说明" />
			</el-form-item>
         </el-form>
         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitForm">确 定</el-button>
               <el-button @click="cancel">取 消</el-button>
            </div>
         </template>
      </el-dialog>

     <!-- dictData查看对话框 -->
     <dict-data ref="dictDataRef"></dict-data>
   </div>
</template>

<script setup name="Dict">
	
import { listType, getType, delType, addType, updateType } from "@/api/system/dict/type";
import dictData from "./data.vue"
import store from "@/store";

const { proxy } = getCurrentInstance();

const { sys_status } = proxy.useDict("sys_status");
const {audit_types}=["VPN","虚拟机"];
console.log(audit_types)
const typeList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: undefined,
    code: undefined,
    status: undefined
  },
  rules: {
    name: [{ required: true, message: "字典名称不能为空", trigger: "blur" }],
    code: [{ required: true, message: "字典类型不能为空", trigger: "blur" }]
  },
});

const { queryParams, form, rules } = toRefs(data);


/** 查询字典类型列表 */
function getList() {
  loading.value = true;
  listType(proxy.addTimeRange(queryParams.value, dateRange.value)).then(response => {
    typeList.value = response.records;
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
    id: undefined,
    name: undefined,
    code: undefined,
    status: 0,
    remark: undefined
  };
  proxy.resetForm("dictRef");
}
/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}
/** 重置按钮操作 */
function resetQuery() {
  dateRange.value = [];
  proxy.resetForm("queryRef");
  handleQuery();
}
/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加字典类型";
}
/** 多选框选中数据 */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}
/** 修改按钮操作 */
function handleUpdate(row) { 
	proxy.$modal.msgSuccess("启用成功");}

/** 修改按钮操作 */
function handleDictData(row) {
  proxy.$refs["dictDataRef"].show(row);
}
/** 提交按钮 */
function submitForm() {
  proxy.$refs["dictRef"].validate(valid => {
    if (valid) {
      if (form.value.id != undefined) {
        updateType(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          store.commit("CLEAN_DICT")
          getList();
        });
      } else {
        addType(form.value).then(response => {
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
  const dictIds = row.id != null ? Array.of(row.id) : ids.value;
  proxy.$modal.confirm('是否确认删除选中的数据项？').then(function() {
    return delType(dictIds);
  }).then(() => {
    store.commit("CLEAN_DICT")
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}
/** 导出按钮操作 */
function handleExport() {
  proxy.download("system/dict/export", {
    ...queryParams.value
  }, `dict_${new Date().getTime()}.xlsx`);
}

getList();
</script>
