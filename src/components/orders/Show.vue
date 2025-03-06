<script setup lang="ts">
import axios from "../../util/axiosInstance"
import * as XLSX from 'xlsx';
import {ref, reactive} from "vue";
import {useRouter} from "vue-router";
import {ElMessageBox} from "element-plus";

const router = useRouter();
const selectedId = ref(-1);
const datas = reactive({
  form: {
    order_id:null,
    user_name: null,
    check_in_time: null,
    contact_info:null,
    room_num:null,
    room_type: "",
  },
  list: [],
})
interface forms {
  order_id: number;
  user_name: string;
  check_in_time: string;
  contact_info: string;
  room_num: string;
  room_type: string;
}

// const search = () => {
//   axios.get('/order', {params: datas.form})
//       .then((res) => {
//         datas.list = res.data;
//
//       })
// } //ok

// const search = () => {
//   axios.get('/order', {params: datas.searchForm})
//       .then((res) => {
//         datas.list = res.data.data;
//       })
// }

const search = () => {
  axios.get('/order', {params: datas.form})
      .then((res) => {
        datas.list = res.data;

      })
}


const selectTr = (obj:forms) => {
  ElMessageBox.alert('该行被点击', 'nice', {
    confirmButtonText: '确定'
  })

  if (obj != null) {
    console.log(obj.order_id)
    selectedId.value = obj.order_id;
    console.log(selectedId.value)
  }
} //ok

const showAdd = () => {
  router.push({name: "OrderAdd"})
} //ok

const showUpdate = () => {
  if (selectedId.value > -1) {
    router.push({name: "OrderUpdate", query: {id: selectedId.value}})
  } else {
    ElMessageBox.alert('请选中数据', '警告', {
      confirmButtonText: '确定'
    })
  }
}


const deleteData = () => {
  if (selectedId.value > -1) {
    axios.delete('/order/' + selectedId.value)
        .then((res) => {
          if (res.data == true) {
            search();
          } else {
            ElMessageBox.alert('系统错误', '错误', {
              confirmButtonText: '确定'
            })
          }
        })
  } else {
    ElMessageBox.alert('请选中数据', '警告', {
      confirmButtonText: '确定'
    })
  }
} //ok

const exportTableData = () => {
  // 获取表格数据
  const tableData =datas.list;

  // 将表格数据转换为工作表（worksheet）
  const ws = XLSX.utils.json_to_sheet(tableData);

  // 创建工作簿（workbook）
  const wb = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(wb, ws, '表格数据');

  // 导出为 Excel 文件
  XLSX.writeFile(wb, '表格数据.xlsx');
}


search();

</script>

<template>
  <div id="container">
    <el-row>
      <el-form :inline="true" :model="datas.form" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="datas.form.order_id" placeholder="订单编号"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="datas.form.user_name" placeholder="客户姓名"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="datas.form.contact_info" placeholder="联系方式"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="datas.form.room_num" placeholder="房间编号"></el-input>
        </el-form-item>

        <el-form-item>
          <el-date-picker v-model="datas.form.check_in_time" type="date" placeholder="入住日期"/>
        </el-form-item>

        <el-form-item style="width:120px">
          <el-select v-model="datas.form.room_type" placeholder="房间类型">
            <el-option label="房间类型" value=""></el-option>
            <el-option label="普通单人间" value="普通单人间"></el-option>
            <el-option label="豪华单人间" value="豪华单人间"></el-option>
            <el-option label="普通双人房" value="普通双人房"></el-option>
            <el-option label="豪华双人房" value="豪华双人房"></el-option>
            <el-option label="总统套房" value="总统套房"></el-option>
            <el-option label="海景套房" value="海景套房"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>

    <el-row>
      <el-table
          :data="datas.list"
          border
          style="width: 100%" highlight-current-row @current-change="selectTr"
      >
        <el-table-column
            prop="order_id"
            label="订单编号"
            width="300"
        >
        </el-table-column>
        <el-table-column
            prop="user_name"
            label="客户姓名"
            width="200">
        </el-table-column>
        <el-table-column
            prop="check_in_time"
            label="入住日期"
            width="300">
        </el-table-column>
        <el-table-column
            prop="room_num"
            label="房间编号"
            width="200">
        </el-table-column>
        <el-table-column
            prop="room_type"
            label="房间类型"
            width="200">
        </el-table-column>
        <el-table-column
            prop="contact_info"
            label="联系方式"
        ></el-table-column>

      </el-table>
    </el-row>

    <el-row style="margin-top:10px;text-align: left">
      <el-button type="primary" v-on:click="showAdd">新增</el-button>
      <el-button type="primary" v-on:click="showUpdate">修改</el-button>
      <el-button type="primary" v-on:click="deleteData">删除</el-button>
      <el-button type="primary" v-on:click="exportTableData">导出订单表</el-button>
    </el-row>

  </div>
</template>


