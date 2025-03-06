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
        dep: {id: null}
      },
      depList: []
    })

    const update = () => {
      axios.put('/roo', datas.form)
          .then((res) => {
            if (res.data == true) {
              router.push({name: "RooShow"})
            } else {
              alert("系统错误");
            }
          })
    }
    const searchById = () => {
      axios.get('/roo/' + datas.form.id)
          .then((res) => {
            datas.form=res.data;
            if (datas.form.dep == null) {
              datas.form.dep = {id: datas.depList[0].id};
            }
          })
    }
    // const searchDep = () => {
    //   axios.get('/dep')
    //       .then((res) => {
    //         datas.depList=res.data;
    //         datas.form.dep.id = datas.depList[0].id
    //
    //       })
    // }


    datas.form.id = route.query.id;
    // searchDep();
    searchById();

</script>
<template>
  <div id="container">
    <el-form ref="form" :model="datas.form" label-width="80px" style="text-align: left">
      <el-form-item label="房间价格">
        <el-input v-model="datas.form.room_price"></el-input>
      </el-form-item>

      <el-form-item label="房间状态">
        <el-input v-model="datas.form.is_occupied"></el-input>
      </el-form-item>

      <el-form-item label="房间照片">
        <el-input v-model="datas.form.room_image_url"></el-input>
      </el-form-item>

      <el-form-item label="房间编号">
        <el-input v-model="datas.form.room_num"></el-input>
      </el-form-item>

      <el-form-item label="房间类型">
        <el-select v-model="datas.form.room_type" placeholder="房间类型">
          <el-option label="房间类型" value=""></el-option>
          <el-option label="普通单人间" value="普通单人间"></el-option>
          <el-option label="豪华单人间" value="豪华单人间"></el-option>
          <el-option label="普通双人房" value="普通双人房"></el-option>
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