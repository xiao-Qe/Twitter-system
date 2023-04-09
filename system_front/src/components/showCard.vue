<template>

  <el-card class="box-card" shadow="hover" @click="check(item)">
    <template #header>
      <div class="card-header">
        <span>{{ item.title }}</span>
        <span class="author">{{item.author}}</span>
      </div>
    </template>
    <div class="content">{{item.blurb}}</div>
    <div v-if="item.state !== null" class="state">
        <div v-if="item.state === 0">
          <el-icon  style="color: burlywood;"><WarningFilled /></el-icon>待审核
        </div>
        <div v-if="item.state === 1">
          <el-icon  style="color: greenyellow;"><SuccessFilled /></el-icon>通过
        </div>
        <div v-if="item.state === 2">
          <el-icon  style="color: crimson;"><CircleCloseFilled /></el-icon>已下架
        </div>
    </div>
    <div class="like">
      <el-icon><View /></el-icon>
      {{item.like}}
    </div>
    <div class="collect">
      <el-icon><Star /></el-icon>
      {{item.collect}}
    </div>
  </el-card>

  <el-dialog
      v-model="visible"
      title="请选择操作"
      width="30%"
  >
    <span>请选择操作</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="visible = false">关闭</el-button>
        <el-button type="primary" @click="updateTwitter">修改</el-button>
        <el-button type="primary" v-if="item.state === 2" @click="getCauseByTwitterId">查看原因</el-button>
        <el-button type="primary" @click="getTwitter(item)">
          查看
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>

import {twitterIndex} from "@/store/twitterIndex";
import router from "@/router";
import {ref} from "vue";
import {getCause} from "@/api/myself/myself";
import {ElMessage, ElMessageBox} from "element-plus";

const entity = twitterIndex();
const props = defineProps(['item']);

const emits = defineEmits(['update'])

//点击事件
function check(item) {
  if(item.state !== null){
    visible.value = true
  }else {
    getTwitter(item)
  }
}

//修改事件
function updateTwitter(){
  emits('update', props.item.id)
  visible.value = false
}

//查看原因
async function getCauseByTwitterId() {
  console.log(props.item.id)
  const resBean = await getCause(props.item.id)
  if(resBean.data.status === 200){
    console.log(resBean.data.data)
    await ElMessageBox.alert(resBean.data.data, '下架原因',{
      confirmButtonText: '关闭'
    })
  }else {
    ElMessage.error(resBean.data.msg)
  }
}

//跳转查看
function getTwitter(item){
  entity.$patch({
    id:item.id
  })
  router.push({
    path:"/twitter"
  })

}

let visible = ref(false)
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.content{
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  overflow: hidden;
}
.state{
  position:absolute;
  right: 90px;
  bottom:0;
}
.like{
  position:absolute;
  right: 50px;
  bottom:0;
}
.collect{
  position:absolute;
  right: 5px;
  bottom:0;
}
.box-card {
  cursor: pointer;
  position:relative;
  float: left;
  width: 330px;
  height: 205px;
  margin: 10px;
}
</style>