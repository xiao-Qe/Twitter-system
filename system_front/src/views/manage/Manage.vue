<template>
  <el-table :data="tableData" style="width: 100%" border>
    <el-table-column fixed prop="title" label="标题" width="400" />
    <el-table-column prop="twitterId" label="文章ID" width="120" />
    <el-table-column prop="author" label="作者" width="120" />
    <el-table-column prop="type" label="作品类型" width="120" />
    <el-table-column prop="state" label="作品状态" width="120" />
    <el-table-column prop="creatTime" label="创建时间" width="120" />
    <el-table-column fixed="right" label="操作" width="120">
      <template #default>
        <el-button link type="primary" size="small" @click="handleClick"
        >查看</el-button
        >
        <el-button v-if="isAudit" link type="primary" size="small">通过</el-button>
        <el-button v-else link type="primary" size="small">下架</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import {reactive, ref, watch} from "vue";
import {useRoute} from "vue-router/dist/vue-router";

const handleClick = () => {
  console.log('click')
}

let isAudit = ref(false);
let route = reactive(useRoute())

const tableData = ref([
  {
    title: '2016-05-03',
    twitterId: 'Tom',
    author: 'California',
    classifyId: 'Los Angeles',
    type: 'No. 189, Grove St, Los Angeles',
    state: 'CA 90036',
    creatTime: 'Home',
  },
  {
    title: '2016-05-03',
    twitterId: 'Tom',
    author: 'California',
    classifyId: 'Los Angeles',
    type: 'No. 189, Grove St, Los Angeles',
    state: 'CA 90036',
    creatTime: 'Home',
  },
  {
    title: '2016-05-03',
    twitterId: 'Tom',
    author: 'California',
    classifyId: 'Los Angeles',
    type: 'No. 189, Grove St, Los Angeles',
    state: 'CA 90036',
    creatTime: 'Home',
  },

  {
    title: '2016-05-03',
    twitterId: 'Tom',
    author: 'California',
    classifyId: 'Los Angeles',
    type: 'No. 189, Grove St, Los Angeles',
    state: 'CA 90036',
    creatTime: 'Home',
  }
])


function resetData() {
  if (route.path === '/administrator/audit') {
    isAudit.value = true
  } else {
    isAudit.value = false
  }
}

resetData();

watch(() => route.path, () => {
  resetData()
})
</script>

<style scoped>

</style>