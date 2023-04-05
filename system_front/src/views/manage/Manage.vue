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
import {onBeforeMount, reactive, ref, watch} from "vue";
import {useRoute} from "vue-router/dist/vue-router";
import {getAuditList} from "@/api/administrator";
import {ElMessage} from "element-plus";

const handleClick = () => {
  console.log('click')
}

let isAudit = ref(false);
let route = reactive(useRoute())

const tableData = ref([
  {
    title: '',
    twitterId: 0,
    author: '',
    classifyName: '',
    type: '',
    state: '',
    creatTime: '',
  }
])

onBeforeMount(async () => {
  await getList()
})

//获取列表方法
async function getList() {
  const resBean = await getAuditList();
  if (resBean.data.status === 200) {
    const data = resBean.data.data
    data.forEach((AuditTwitter) => {
          //将数据替换将Long替换为String
          const date = new Date(AuditTwitter.creatTime * 1000)
          AuditTwitter.creatTime = date.toLocaleDateString();

          //替换类型
          if (AuditTwitter.type === 0) {
            AuditTwitter.type = '私密'
          } else {
            AuditTwitter.type = '公开'
          }

          //替换状态
          if (AuditTwitter.state === 0) {
            AuditTwitter.state = '待审核'
          } else if (AuditTwitter.state === 3) {
            AuditTwitter.state = '下架'
          }
        }
    )
    tableData.value = data
  } else {
    ElMessage.error(resBean.data.msg)
  }
}

function resetData() {
  isAudit.value = route.path === '/administrator/audit';
}

resetData();

watch(() => route.path, () => {
  resetData()
})
</script>

<style scoped>

</style>