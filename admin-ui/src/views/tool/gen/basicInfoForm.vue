<template>
  <el-form ref="basicInfoForm" :model="info" :rules="rules" label-width="150px">
    <el-row>
      <el-col :span="12">
        <el-form-item label="表名称" prop="name">
          <el-input placeholder="请输入表名称" v-model="info.name" readonly/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="表描述" prop="comment">
          <el-input placeholder="请输入" v-model="info.comment" readonly/>
        </el-form-item>
      </el-col>
      <el-col :span="12">
        <el-form-item label="实体类名称" prop="className">
          <el-input placeholder="请输入" v-model="info.className" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item label="作者" prop="author">
          <el-input placeholder="请输入" v-model="info.author" />
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
</template>

<script>
export default {
  props: {
    info: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      rules: {
        name: [
          { required: true, message: "请输入表名称", trigger: "blur" }
        ],
        comment: [
          { required: true, message: "请输入表描述", trigger: "blur" }
        ],
        className: [
          { required: true, message: "请输入实体类名称", trigger: "blur" }
        ],
        entityName: [
          { required: true, message: "请输入实体类的中文", trigger: "blur" }
        ],
        author: [
          { required: true, message: "请输入作者", trigger: "blur" }
        ]
      }
    };
  }
};
</script>
