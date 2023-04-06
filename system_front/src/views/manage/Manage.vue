<template>
  <el-table :data="tableData" style="width: 100%" border>
    <el-table-column prop="title" label="标题" width="380" />
    <el-table-column prop="twitterId" label="文章ID" width="140" />
    <el-table-column prop="author" label="作者" width="120" />
    <el-table-column prop="type" label="作品类型" width="120" />
    <el-table-column prop="state" label="作品状态" width="100" />
    <el-table-column prop="creatTime" label="创建时间" width="120" />
    <el-table-column fixed="right" label="操作" width="140">
      <template v-slot = "scope">
        <el-button link type="primary" size="small" @click="showTwitter(scope.row.twitterId)"
        >查看</el-button
        >
        <el-button v-if="isAudit" link type="primary" size="small" @click="pass(scope.row.twitterId)">通过</el-button>
        <el-button link type="primary" size="small" @click="undercarriageTwitter(scope.row.twitterId)">下架</el-button>
      </template>
    </el-table-column>
  </el-table>

  <teleport to="body" preview-teleported="true">
    <div v-if="isShow" class = "mask" >
      <ShowTwitter :twitterID = "twitterID" @change = "changIsShow"></ShowTwitter>
    </div>
  </teleport>
</template>

<script setup>
import {onBeforeMount, reactive, ref, watch} from "vue";
import {useRoute} from "vue-router/dist/vue-router";
import {getAuditList, setTwitterPass, setTwitterUndercarriage} from "@/api/administrator";
import {ElMessage, ElMessageBox} from "element-plus";
import ShowTwitter from "@/components/showTwitter"

let isAudit = ref(false);
let route = reactive(useRoute())
let twitterID = ref(0);
let undercarriageCause = ref('')

let isShow = ref(false);

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

//通过方法
async function pass(twitterId) {
  const resBean = await setTwitterPass(twitterId)
  //设置成功
  if(resBean.data.status === 200){
    //重新请求列表
    await getList()
  }else {
    ElMessage.error(resBean.data.msg)
  }
}

//下架twitter方法
async function undercarriageTwitter(twitterId){
  ElMessageBox.prompt('请输入下架原因','原因',{
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    inputPattern: /[^\x00-\xff]/,
    inputErrorMessage: '请输入下架原因',
  }).then(async ({value}) => {
    undercarriageCause.value = value
    const resBean = await setTwitterUndercarriage(twitterId,undercarriageCause.value)
    if(resBean.data.status === 200){
      //重新请求列表
      await getList()
    }else {
      ElMessage.error(resBean.data.msg)
    }
  })
}

function changIsShow(){
  isShow.value = false
}

function showTwitter(twitterId){
  isShow.value = true
  twitterID.value = twitterId
}

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
.mask{
  position: fixed;
  z-index: 999;
  top: 0;bottom: 0;left: 0;right: 0;
  background-color: rgba(0,0,0,0.5);
}
</style>