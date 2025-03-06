<script setup>
import axios from "../../util/axiosInstance"
import {ref, reactive} from "vue";
import {useRouter} from "vue-router";
import {ElMessage, ElMessageBox} from "element-plus";

const router = useRouter();
const datas = reactive({
  form: {
    order_id:null,
    user_name: null,
    check_in_time: null,
    contact_info:null,
    room_num:null,
    room_type: "",
  },
  depList: [],
})


const add = () => {
  axios.post('/users', datas.form)
      .then((res) => {
        if (res.data == true) {
          ElMessage({
            message: '保存成功',
            type: 'success',
          })
          router.push({name: "UserShow"})
        } else {
          ElMessage({
            message: '保存失败',
            type: 'error',
          })
        }
      })
}
// const searchDep = () => {
//   axios.get('/dep')
//       .then((res) => {
//         datas.depList = res.data;
//         datas.form.dep.id = res.data[0].id
//       })
// }

// searchDep();

</script>

<template>
  <div id="container">
    <el-form ref="form" :model="datas.form" label-width="80px" style="text-align: left">
      <el-form-item label="用户姓名">
        <el-input v-model="datas.form.user_name"></el-input>
      </el-form-item>

      <el-form-item label="用户身份">
        <el-input v-model="datas.form.identity"></el-input>
      </el-form-item>

      <el-form-item label="联系方式">
        <el-input v-model="datas.form.contact_info"></el-input>
      </el-form-item>




      <el-form-item>
        <el-button type="primary" @click="add">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>
#container {
  width: 600px;
  margin: 10px auto;
}
</style>