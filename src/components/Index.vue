<template>
  <el-container>
    <el-header style="height:120px">
      <div id="top">
        <div id="logo">小鑫民宿管理系统</div>
      </div>
    </el-header>
    <el-container>

      <el-aside width="200px">
        <el-menu>

          <el-menu-item>
            <el-icon>
              <Ship/>
            </el-icon>
            <span><router-link to="/log">登录页面</router-link></span>
          </el-menu-item>

          <el-menu-item index="employee">
            <el-icon>
              <Star/>
            </el-icon>
            <span><router-link to="/users/show">用户管理</router-link></span>
          </el-menu-item>

          <el-menu-item index="employee">
            <el-icon>
              <Star/>
            </el-icon>
            <span><router-link to="/roo/show">房间管理</router-link></span>
          </el-menu-item>

          <el-menu-item index="employee">
            <el-icon>
              <Star/>
            </el-icon>
            <span><router-link to="/order/show">订单管理</router-link></span>
          </el-menu-item>


        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>

    </el-container>
    <el-footer style="height:120px">详细咨询请致电：10010</el-footer>
  </el-container>
</template>


<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  background-image: url('https://q4.itc.cn/images01/20240112/c273680a507c4777b288ee395868e80d.jpeg'); /* 设置背景图片 */
  background-size: cover; /* 使图片覆盖整个区域 */
  background-position: center center; /* 居中显示图片 */
  background-repeat: no-repeat; /* 不重复背景图片 */
}


.el-main {
  background-color: #fff;
  color: #333;
  height: 760px;
  text-align: center;
}

#top {
  clear: both;
  height: 80px;
  text-align: left;
}

#top #logo {
  text-align: center;
  color: #fff;
  font-size: 30px;
  font-weight: bold;
  padding: 15px 0 0 40px;
}


.el-aside {
  background-color: #f5f7fa; /* 侧边栏背景颜色 */
  border-right: 1px solid #ffffff; /* 右边框 */
  height: 760px;
}



.el-menu-item:hover {
  background-color: rgba(236, 159, 15, 0.99); /* 鼠标悬停时的背景色 */
}

</style>
<script setup>
</script>