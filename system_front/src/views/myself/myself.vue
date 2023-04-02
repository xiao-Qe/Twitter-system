<template>
  <h1>我的信息</h1>
  <div class="speaker">
    <el-icon size="70px"><UserFilled /></el-icon>
  </div>
  <div class="name">{{user.name}}</div>
  <div class="userId">id号：{{user.userId}}</div>
  <div class = "tags">
    兴趣：</div>
    <div class="tag" v-for="tag in user.tag">{{tag}}</div>

  <br>
  <h1>{{ title }}</h1>
  <hr>
  <el-card @click="isAdd = true" class="box-card" shadow="hover" v-if="title==='作品'">
    <div class="add">
      <el-icon  size="180px"><Plus /></el-icon>
    </div>
  </el-card>

  <teleport to="body">
    <div v-if="isAdd" class = "mask">
      <AddTwitter @refresh="refresh" @change = "changeIsAdd"></AddTwitter>
    </div>
  </teleport>

  <show-card  v-if="twitter !== undefined || twitter.length > 0"  v-for="item in twitter" :item="item"></show-card>
</template>

<script setup>

import {onBeforeMount, reactive, ref, watch} from "vue";
import {useRoute} from "vue-router/dist/vue-router";
import ShowCard from "@/components/showCard";
import AddTwitter from "@/components/AddTwitter";
import {User} from '@/store/user.js';
import {getTwitterCardList} from "@/api/pubilc/getTwitterCardList";
import {ElMessage} from "element-plus";

//是否是添加
const isAdd = ref(false);
//页面标题
let title = ref('');
let route = reactive(useRoute())
const userIdAndName = User();


let  twitter = ref([
  {
    id : 0,
    title: '标题1',
    author: '作者',
    blurb: '简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1' +
        '简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1',
    view : 0,
    like: 20,
    collect: 30
  }
]);

//请求展示卡片
async function getTwitterCards() {
  const resBean = await getTwitterCardList();
  if(resBean.data.status === 200){
    twitter.value = resBean.data.data
  }else {
    ElMessage.error(resBean.data.msg)
  }
}

onBeforeMount(()=>{
  getTwitterCards();
})

let user = reactive({
  name:'',
  userId:0,
  tag:['篮球','美术','动漫','游戏']
})
user.name = userIdAndName.userName;
user.userId = userIdAndName.userId;

//关闭addTwitter
function changeIsAdd(){
  isAdd.value = false;
}

//关闭addTwitter并且刷新页面
function refresh(){
  isAdd.value = false;
  //刷新页面

}



function resetData() {
  if (route.path === '/user/essay') {
    title.value = '作品'
  } else if (route.path === '/user/like') {
    title.value = '喜欢'
  } else if (route.path === '/user/collect') {
    title.value = '收藏'
  }
}

resetData();

watch(() => route.path, () => {
  resetData()
})
</script>

<style scoped>
.mask{
  position: absolute;
  top: 0;bottom: 0;left: 0;right: 0;
  background-color: rgba(0,0,0,0.5);
}
.speaker{
  float: left;
}
.name{
  font-size: 25px;
}
.userId{
  font-size: 5px;
}
.tags{
  float: left;
}
.tag{
  background-color: bisque;
  float: left;
  margin-left: 5px;

}
.add{
  position: absolute;
  margin-left: 50px;
  margin-right: auto;
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