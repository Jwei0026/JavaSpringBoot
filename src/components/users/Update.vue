<script setup>
import axios from "../../util/axiosInstance.ts"
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
      axios.put('/users', datas.form)
          .then((res) => {
            if (res.data == true) {
              router.push({name: "UserShow"})
            } else {
              alert("系统错误");
            }
          })
    }
    const searchById = () => {
      axios.get('/users/' + datas.form.id)
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
      <el-form-item label="客户姓名">
        <el-input v-model="datas.form.user_name"></el-input>
      </el-form-item>
      <el-form-item label="用户身份">
        <el-input v-model="datas.form.identity"></el-input>
      </el-form-item>

      <el-form-item label="联系方式">
        <el-input v-model="datas.form.contact_info"></el-input>
      </el-form-item>



      <el-form-item>
        <el-button type="primary" @click="update">保存</el-button>
      </el-form-item>

  </div>
</template>



<style scoped>
#container {
  width: 600px;
  margin: 10px auto;
}
</style>