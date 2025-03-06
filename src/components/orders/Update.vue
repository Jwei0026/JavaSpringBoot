<script setup>
import axios from "../../util/axiosInstance"
import { ref, reactive} from "vue";
import {useRoute, useRouter} from "vue-router";

    const router = useRouter();
    const route = useRoute()
    const datas = reactive({
      form: {
        id: null,
        number: null,
        name: null,
        gender: null,
        age: null,
        order: {id: null}
      },
      orderList: []
    })

    const update = () => {
      axios.put('/order', datas.form)
          .then((res) => {
            if (res.data == true) {
              router.push({name: "OrderShow"})
            } else {
              alert("系统错误");
            }
          })
    }
    const searchById = () => {
      axios.get('/order/' + datas.form.id)
          .then((res) => {
            datas.form=res.data;
            if (datas.form.order == null) {
              datas.form.order = {id: datas.orderList[0].id};
            }
          })
    }

    datas.form.id = route.query.id;
    // searchDep();
    searchById();

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
        <el-button type="primary" @click="update">保存</el-button>
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