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
  orderList: [],
})


const add = () => {
  axios.post('/order', datas.form)
      .then((res) => {
        if (res.data == true) {
          ElMessage({
            message: '保存成功',
            type: 'success',
          })
          router.push({name: "OrderShow"})
        } else {
          ElMessage({
            message: '保存失败',
            type: 'error',
          })
        }
      })
}
</script>

<template>
  <div id="container">
    <el-form ref="form" :model="datas.form" label-width="80px" style="text-align: left">
      <el-form-item label="客户姓名">
        <el-input v-model="datas.form.user_name"></el-input>
      </el-form-item>

      <el-form-item label="联系方式">
        <el-input v-model="datas.form.contact_info"></el-input>
      </el-form-item>

      <el-form-item label="房间编号">
        <el-input v-model="datas.form.room_num"></el-input>
      </el-form-item>

      <el-form-item label="入住时间">
        <el-date-picker v-model="datas.form.check_in_time" type="date" placeholder="入住日期"/>
      </el-form-item>

      <el-form-item label="房间类型">
        <el-select v-model="datas.form.room_type" placeholder="房间类型">
          <el-option label="房间类型" value=""></el-option>
          <el-option label="大床房" value="大床房"></el-option>
          <el-option label="豪华大床房" value="豪华大床房"></el-option>
          <el-option label="豪华双人房" value="豪华双人房"></el-option>
        </el-select>
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