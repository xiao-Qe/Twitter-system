<template>
  <el-table :data="tableData" border style="width: 100%" >
    <el-table-column fixed prop="userId" label="用户ID" width="400" />
    <el-table-column prop="userName" label="用户名" width="270" />
    <el-table-column prop="password" label="密码" width="270" />
    <el-table-column fixed="right" label="操作" width="140">
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick"
        >查看</el-button
        >
        <el-button link type="primary" size="small">修改</el-button>
      </template>
    </el-table-column>
  </el-table>

</template>

<script setup>
import {ref} from "vue";

const handleClick = () => {
  console.log('click')
}

const tableData = ref([
  {
    userId: '2016-05-03',
    userName: 'Tom',
    password: 'California',
  },
  {
    userId: '2016-05-03',
    userName: 'Tom',
    password: 'California',
  },
  {
    userId: '2016-05-03',
    userName: 'Tom',
    password: 'California',
  },

  {
    userId: '2016-05-03',
    userName: 'Tom',
    password: 'California',
  }
])
</script>

<style scoped>

</style>