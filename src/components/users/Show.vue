<script setup lang="ts">
import axios from "../../util/axiosInstance";
import { ref, reactive, onMounted } from "vue";
import { useRouter } from "vue-router";
import { ElMessageBox } from "element-plus";
import * as echarts from 'echarts';

const router = useRouter();
const selectedId = ref(-1);
const datas = reactive({
  form: {
    user_id: null,
    user_name: null,
    identity: null,
    contact_info: null,
  },
  list: [],
  // 修改 identityStats 类型声明，允许任意字符串作为键
  identityStats: reactive<{ [key: string]: number }>({
    客户: 0,
    管理员: 0,
    经理: 0,
  }),
});
interface forms {
  user_id: number;
  user_name: string;
  identity: string;
  contact_info: string;
}

// 获取数据
const search = () => {
  axios.get('/users', { params: datas.form }).then((res) => {
    console.log(res.data);
    datas.list = res.data;
    // 更新身份统计
    updateIdentityStats();
    renderChart();
  });
}

// 更新身份统计
const updateIdentityStats = () => {
  // 重置身份统计
  datas.identityStats = { 客户: 0, 管理员: 0, 经理: 0,员工: 0 };

  datas.list.forEach((user: forms) => {
    if (datas.identityStats[user.identity] !== undefined) {
      datas.identityStats[user.identity]++;
    }
  });
}

// 渲染图表
const renderChart = () => {
  const chartContainer = document.getElementById('identityChart') as HTMLDivElement;
  if (chartContainer) {
    const myChart = echarts.init(chartContainer);
    const option = {
      title: {
        text: '用户身份分布',
        subtext: '用户身份统计',
        left: 'center',
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b}: {c} ({d}%)',
      },
      series: [
        {
          name: '用户身份',
          type: 'pie',
          radius: '55%',
          data: [
            { value: datas.identityStats['客户'], name: '客户' },
            { value: datas.identityStats['管理员'], name: '管理员' },
            { value: datas.identityStats['经理'], name: '经理' },
            { value: datas.identityStats['员工'], name: '员工' },
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)',
            },
          },
        },
      ],
    };
    myChart.setOption(option);
  }
}

// 选择行
const selectTr = (obj: forms) => {
  ElMessageBox.alert('该行被点击', '错误', {
    confirmButtonText: '确定',
  });

  if (obj != null) {
    console.log(obj.user_id);
    selectedId.value = obj.user_id;
    console.log(selectedId.value);
  }
}

// 跳转到新增页面
const showAdd = () => {
  router.push({ name: "UserAdd" });
}

// 跳转到修改页面
const showUpdate = () => {
  if (selectedId.value > -1) {
    router.push({ name: "UserUpdate", query: { id: selectedId.value } });
  } else {
    ElMessageBox.alert('请选中数据', '警告', {
      confirmButtonText: '确定',
    });
  }
}

// 删除数据
const deleteData = () => {
  if (selectedId.value > -1) {
    axios.delete('/users/' + selectedId.value).then((res) => {
      if (res.data == true) {
        search();
      } else {
        ElMessageBox.alert('系统错误', '错误', {
          confirmButtonText: '确定',
        });
      }
    });
  } else {
    ElMessageBox.alert('请选中数据', '警告', {
      confirmButtonText: '确定',
    });
  }
}

// 初次加载时搜索
onMounted(() => {
  search();
});
</script>

<template>
  <div id="container">
    <el-row>
      <el-form :inline="true" :model="datas.form" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="datas.form.user_id" placeholder="用户ID"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="datas.form.user_name" placeholder="用户姓名"></el-input>
        </el-form-item>

        <el-form-item style="width:120px">
          <el-select v-model="datas.form.identity" placeholder="用户身份">
            <el-option label="用户身份" value=""></el-option>
            <el-option label="客户" value="客户"></el-option>
            <el-option label="管理员" value="管理员"></el-option>
            <el-option label="经理" value="经理"></el-option>
            <el-option label="员工" value="员工"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-input v-model="datas.form.contact_info" placeholder="联系方式"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="search">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>

    <!-- 用户列表 -->
    <el-row>
      <el-table
          :data="datas.list"
          border
          style="width: 100%"
          highlight-current-row
          @current-change="selectTr"
      >
        <el-table-column prop="user_id" label="用户ID" width="300"></el-table-column>
        <el-table-column prop="user_name" label="用户姓名" width="200"></el-table-column>
        <el-table-column prop="identity" label="用户身份" width="300"></el-table-column>
        <el-table-column prop="contact_info" label="联系方式"></el-table-column>
      </el-table>
    </el-row>

    <!-- 数据可视化图表 -->
    <el-row style="margin-top: 20px;">
      <div id="identityChart" style="width: 100%; height: 400px;"></div>
    </el-row>

    <!-- 操作按钮 -->
    <el-row style="margin-top:10px;text-align: left">
      <el-button type="primary" v-on:click="showAdd">新增</el-button>
      <el-button type="primary" v-on:click="showUpdate">修改</el-button>
      <el-button type="primary" v-on:click="deleteData">删除</el-button>
    </el-row>
  </div>
</template>

<style scoped>
#identityChart {
  width: 100%;
  height: 400px;
  margin-top: 20px;
}


</style>