<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
      <el-form-item label="表名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入表名称"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="表描述" prop="comment">
        <el-input
          v-model="queryParams.comment"
          placeholder="请输入表描述"
          clearable
          style="width: 240px"
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
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
        <el-button type="primary" icon="Search"  @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Download"
          @click="handleGenTable"
          v-hasPermission="['tool:gen:code']"
        >生成</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="Upload"
          @click="openImportTable"
          v-hasPermission="['tool:gen:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleEditTable"
          v-hasPermission="['tool:gen:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermission="['tool:gen:remove']"
        >删除</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center" width="55"></el-table-column>
      <el-table-column label="序号" type="index" width="50" align="center">
        <template #default="scope">
          <span>{{ (queryParams.pageNum - 1) * queryParams.pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="表名称"
        align="center"
        prop="name"
        :show-overflow-tooltip="true"
        width="120"
      />
      <el-table-column
        label="表描述"
        align="center"
        prop="comment"
        :show-overflow-tooltip="true"
        width="120"
      />
      <el-table-column
        label="实体"
        align="center"
        prop="className"
        :show-overflow-tooltip="true"
        width="120"
      />
      <el-table-column label="创建时间" align="center" prop="createTime" width="160" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="160" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button
            type="text"
            icon="View"
            size="small"
            @click="handlePreview(scope.row)"
            v-hasPermission="['tool:gen:preview']"
          >预览</el-button>
          <el-button
            type="text"
            icon="Edit"
            size="small"
            @click="handleEditTable(scope.row)"
            v-hasPermission="['tool:gen:edit']"
          >编辑</el-button>
          <el-button
            type="text"
            icon="Delete"
            size="small"
            @click="handleDelete(scope.row)"
            v-hasPermission="['tool:gen:remove']"
          >删除</el-button>
          <el-button
            type="text"
            icon="Refresh"
            size="small"
            @click="handleSyncDb(scope.row)"
            v-hasPermission="['tool:gen:edit']"
          >同步</el-button>
          <el-button
            type="text"
            icon="Download"
            size="small"
            @click="handleGenTable(scope.row)"
            v-hasPermission="['tool:gen:code']"
          >生成代码</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 预览界面 -->
    <el-dialog :title="preview.title" v-model="preview.open" width="80%" top="5vh" append-to-body class="scrollbar">
      <el-tabs v-model="preview.activeName">
        <el-tab-pane
          v-for="item in preview.data"
          :label="item.templateName.substring(item.templateName.lastIndexOf('/')+1,item.templateName.indexOf('.vm'))"
          :name="item.templateName.substring(item.templateName.lastIndexOf('/')+1,item.templateName.indexOf('.vm'))"
          :key="item.templateName"
        >
          <el-link :underline="false" icon="DocumentCopy" v-copyText="item.code" v-copyText:callback="copyTextSuccess" style="float:right">&nbsp;复制</el-link>
          <pre>{{ item.code }}</pre>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
    <import-table ref="import" @ok="handleQuery" />
    <edit-table ref="editTable" @ok="handleQuery"/>
  </div>
</template>


<script>
import { listTable, previewTable, delTable, genCode, syncDb } from "@/api/tool/gen";
import importTable from "./importTable";
import editTable from "./editTable.vue";
export default {
  name: "Gen",
  components: { importTable, editTable },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 唯一标识符
      uniqueId: "",
      // 选中数组
      ids: [],
      // 选中表数组
      tableNames: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 表数据
      tableList: [],
      // 日期范围
      dateRange: "",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        tableName: undefined,
        tableComment: undefined
      },
      // 预览参数
      preview: {
        open: false,
        title: "代码预览",
        data: {},
        activeName: "domain.java"
      }
    };
  },
  created() {
    this.getList();
  },
  activated() {
    const time = this.$route.query.t;
    if (time != null && time != this.uniqueId) {
      this.uniqueId = time;
      this.queryParams.pageNum = Number(this.$route.query.pageNum);
      this.getList();
    }
  },
  methods: {
    /** 查询表集合 */
    getList() {
      this.loading = true;
      listTable(this.addTimeRange(this.queryParams, this.dateRange)).then(response => {
            this.tableList = response.records;
            this.total = response.total;
            this.loading = false;
          }
      );
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 生成代码操作 */
    handleGenTable(row) {
      const tableIds = row.id == null ? this.ids : Array.of(row.id);
      const tableNames = row.name == null ? this.tableNames : row.name;
      if (tableNames == "") {
        this.$modal.msgError("请选择要生成的数据");
        return;
      }
      if(row.genType === "1") {
        genCode(row.tableName).then(response => {
          this.$modal.msgSuccess("成功生成到自定义路径：" + row.genPath);
        });
      } else {
        this.$download.zip("/tool/gen/batchDownLoad" ,{tableIds: tableIds}, "山东邮政.zip");
      }
    },
    /** 同步数据库操作 */
    handleSyncDb(row) {
      const tableName = row.tableName;
      this.$modal.confirm('确认要强制同步"' + tableName + '"表结构吗？').then(function() {
        return synchDb(tableName);
      }).then(() => {
        this.$modal.msgSuccess("同步成功");
      }).catch(() => {});
    },
    /** 打开导入表弹窗 */
    openImportTable() {
      this.$refs.import.show();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 预览按钮 */
    handlePreview(row) {
      previewTable(row.id).then(response => {
        this.preview.data = response;
        this.preview.open = true;
        this.preview.activeName = "controller.java";
      });
    },
    /** 复制代码成功 */
    copyTextSuccess() {
      this.$modal.msgSuccess("复制成功");
    },
    /** 高亮显示 */
    highlightedCode(code, key) {
      const vmName = key.substring(key.lastIndexOf("/") + 1, key.indexOf(".vm"));
      var language = vmName.substring(vmName.indexOf(".") + 1, vmName.length);
      const result = hljs.highlight(language, code || "", true);
      return result.value || '&nbsp;';
    },
    /** 复制代码成功 */
    clipboardSuccess() {
      this.$modal.msgSuccess("复制成功");
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.tableNames = selection.map(item => item.name);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 修改按钮操作 */
    handleEditTable(row) {
      const tableId = row.id || this.ids[0];
      const tableName = row.name || this.tableNames[0];
      const params = { tableId, tableName };
      this.$refs.editTable.show(params);
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const tableIds = row.id == null ? this.ids : Array.of(row.id);
      this.$modal.confirm('是否确认删除选中的数据项？').then(function() {
        return delTable(tableIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
