<template>
  <el-table :data="tableData" border style="width: 100%" >
    <el-table-column fixed prop="userId" label="用户ID" width="400" />
    <el-table-column prop="userName" label="用户名" width="270" />
    <el-table-column prop="password" label="密码" width="270" />
    <el-table-column fixed="right" label="操作" width="140">
      <template v-slot = "scope">
        <el-button link type="primary" size="small" @click="Delete(scope.row.userId)"
        >删除</el-button
        >
        <el-button link type="primary" size="small" @click="updateUser(scope.row.userId,scope.row.userName)">修改</el-button>
      </template>
    </el-table-column>
  </el-table>

  <teleport to="body" preview-teleported="true">
    <div v-show="isShow" class = "mask" >
      <SetUser :id = "id" :name="name" @set="setUserInfo" @change = "changIsShow"></SetUser>
    </div>
  </teleport>
</template>

<script setup>
import {onBeforeMount, ref} from "vue";
import {deleteUser, getUserList, setUser} from "@/api/administrator";
import {ElMessage, ElMessageBox} from "element-plus";
import SetUser from "@/components/SetUser";

let isShow = ref(false)

let id = ref(0)
let name = ref('')

let tableData = ref([
  {
    userId: 0,
    userName: 'Tom',
    password: '',
  }
])

onBeforeMount(async () => {
  const resBean = await getUserList()
  if(resBean.data.status === 200){
    tableData.value = resBean.data.data
  }else {
    ElMessage.error(resBean.data.msg)
  }
})

//删除方法
async function Delete(userId) {
  await ElMessageBox.confirm(
      '确认删除该用户？',
      '提醒',
      {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(async () => {
            const resBean = await deleteUser(userId)
            if (resBean.data.status === 200) {
              const res = await getUserList()
              tableData.value = res.data.data
            } else {
              ElMessage.error(resBean.data.msg)
            }
          }
      ).catch(()=>{
            ElMessage({
              type: 'info',
              message: '取消删除',
            })
              })


}

function updateUser(userId,userName){
  id.value = userId
  name.value = userName
  isShow.value = true
}

function changIsShow(){
  isShow.value = false
}

//修改用户方法
async function setUserInfo(userId, userName, password) {
  const resBean = await setUser(userId, userName, password)
  if(resBean.data.status === 200){
    const res = await getUserList()
    tableData.value = res.data.data
  }else {
    ElMessage.error(resBean.data.msg)
  }
  isShow.value = false
}
</script>

<style scoped>
.mask{
  position: fixed;
  z-index: 999;
  top: 0;bottom: 0;left: 0;right: 0;
  background-color: rgba(0,0,0,0.5);
}
</style>