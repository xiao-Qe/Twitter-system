<template>
  <el-container class="layout-container-demo" style="height: 100vh">
    <el-aside width="200px" class="side">
      <el-menu
               router>
        <el-menu-item index="/user/recommend">
          <template #title><el-icon><House /></el-icon>
            推荐
          </template>
        </el-menu-item>
        <el-sub-menu index="classify">
          <template #title>
            <el-icon><Grid /></el-icon>分类
          </template>
          <el-menu-item index="/user/athletics">
            <template #title>体育</template>
          </el-menu-item>
          <el-menu-item index="/user/amusement">
            <template #title>娱乐</template>
          </el-menu-item>
          <el-menu-item index="/user/study">
            <template #title>学习</template>
          </el-menu-item>
          <el-menu-item index="/user/technology">
            <template #title>科学</template>
          </el-menu-item>
          <el-menu-item index="/user/currentEvents">
            <template #title>时事</template>
          </el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="myself">
          <template #title><el-icon><User /></el-icon>
          我的
          </template>
          <el-menu-item index = "/user/essay">
            <template #title>作品</template>
          </el-menu-item>
          <el-menu-item index="/user/like">
            <template #title>喜欢</template>
          </el-menu-item>
          <el-menu-item index="/user/collect">
            <template #title>收藏</template>
          </el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: left; font-size: 1px">
        <input class = "search">
        <div class="toolbar">

            <el-icon style="margin-right: 8px; margin-top: 5px" size="20px">
              <Search />
            </el-icon>
          <span>搜索</span>
        </div>
      </el-header>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import router from "../router/index.js";

export default {
  name: "Administrator",
  date: {}
}
</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 80%;
  left: 10px;
}

.search{
  width: 93%;
  height: 50%;
}
</style>