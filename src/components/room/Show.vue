<script setup lang="ts">
import axios from "../../util/axiosInstance"
import { ref, reactive, computed } from "vue";
import { useRouter } from "vue-router";
import { ElMessageBox } from "element-plus";

const router = useRouter();
const selectedId = ref(-1);
const datas = reactive({
  form: {
    room_id: null,
    room_price: null,
    is_occupied: null,
    room_image_url: null,
    room_num: null,
    room_type: "",
  },
  list: [],
});

interface forms {
  room_id: number;
  room_price: string;
  is_occupied: string;
  room_image_url: string;
  room_num: string;
  room_type: string;
}

const search = () => {
  axios.get('/roo', { params: datas.form })
      .then((res) => {
        console.log(res.data);
        datas.list = res.data;
      });
}

const selectTr = (obj: forms) => {
  ElMessageBox.alert('该行被点击', '错误', {
    confirmButtonText: '确定'
  });

  if (obj != null) {
    console.log(obj.room_id);
    selectedId.value = obj.room_id;
    console.log(selectedId.value);
  }
}

const showAdd = () => {
  router.push({ name: "RooAdd" });
}

const showUpdate = () => {
  if (selectedId.value > -1) {
    console.log('123asd');
    router.push({ name: "RooUpdate", query: { id: selectedId.value } });
  } else {
    ElMessageBox.alert('请选中数据', '警告', {
      confirmButtonText: '确定'
    });
  }
}

const deleteData = () => {
  if (selectedId.value > -1) {
    axios.delete('/roo/' + selectedId.value)
        .then((res) => {
          if (res.data == true) {
            search();
          } else {
            ElMessageBox.alert('系统错误', '错误', {
              confirmButtonText: '确定'
            });
          }
        });
  } else {
    ElMessageBox.alert('请选中数据', '警告', {
      confirmButtonText: '确定'
    });
  }
}

// 重置表单字段
const resetForm = () => {
  datas.form = {
    room_id: null,
    room_price: null,
    is_occupied: null,
    room_image_url: null,
    room_num: null,
    room_type: "",
  };
  selectedId.value = -1; // 重置选中的 ID
}

// 计算统计数据
const totalRooms = computed(() => datas.list.length); // 总房间数
const occupiedRooms = computed(() =>
    datas.list.filter(room => room.is_occupied === '1').length // 已占用房间数
);
const availableRooms = computed(() =>
    datas.list.filter(room => room.is_occupied === '0').length // 未占用房间数
);

search();
</script>

<template>
  <div id="container">
    <el-row>
      <el-form :inline="true" :model="datas.form" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="datas.form.room_id" placeholder="房间序号"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="datas.form.room_price" placeholder="房间价格"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="datas.form.is_occupied" placeholder="房间状态"></el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="datas.form.room_num" placeholder="房间编号"></el-input>
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
          <el-button @click="resetForm">重置</el-button> <!-- 重置按钮 -->
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
            prop="room_id"
            label="房间序号"
            width="300"
        >
        </el-table-column>
        <el-table-column
            prop="room_num"
            label="房间编号"
            width="200">
        </el-table-column>
        <el-table-column
            prop="room_type"
            label="房间类型"
            width="300">
        </el-table-column>
        <el-table-column
            prop="room_price"
            label="房间价格"
            width="200">
        </el-table-column>
        <el-table-column
            prop="room_image_url"
            label="房间照片"
            width="200">
          <template #default="scope">
            <el-image
                v-if="scope.row.room_image_url"
                style="width: 100px; height: 100px;"
                :src="scope.row.room_image_url"
                fit="cover">
            </el-image>
            <span v-else>无图片</span>
          </template>
        </el-table-column>
        <el-table-column
            prop="is_occupied"
            label="房间状态"
        ></el-table-column>
      </el-table>
    </el-row>

    <!-- 统计信息展示 -->
    <el-row style="margin-top: 10px; text-align: left;">
      <div>总房间数: {{ totalRooms }}</div>
      <div>已占用房间数: {{ occupiedRooms }}</div>
      <div>未占用房间数: {{ availableRooms }}</div>
    </el-row>

    <!-- 统计信息展示 -->
    <el-row gutter="20" style="margin-top: 20px; text-align: left;">
      <el-col :span="8">
        <el-card class="box-card">
          <div class="text item" style="font-weight: bold;">总房间数</div>
          <div class="number item">{{ totalRooms }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div class="text item" style="font-weight: bold;">已占用房间数</div>
          <div class="number item" style="color: #f56c6c;">{{ occupiedRooms }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <div class="text item" style="font-weight: bold;">未占用房间数</div>
          <div class="number item" style="color: #67c23a;">{{ availableRooms }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row style="margin-top:10px;text-align: left">
      <el-button type="primary" v-on:click="showAdd">新增</el-button>
      <el-button type="primary" v-on:click="showUpdate">修改</el-button>
      <el-button type="primary" v-on:click="deleteData">删除</el-button>
    </el-row>
  </div>
</template>