<template>
  <h1>推荐页面</h1>
  <show-card  v-for="item in twitter" :item = "item"></show-card>
</template>

<script setup>
import {onBeforeMount, ref, toRaw} from 'vue'
  import ShowCard from "@/components/showCard";
import {getTwitterCardList} from "@/api/pubilc/getTwitterCardList";
import {ElMessage} from "element-plus";
import {getRecommend} from "@/api/recommend";

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
  const resBean = await getRecommend();
  if(resBean.data.status === 200){
    twitter.value = resBean.data.data
  }else {
    ElMessage.error(resBean.data.msg)
  }
  console.log(twitter.value)
}

onBeforeMount(()=>{
  getTwitterCards();
})
</script>

<style scoped>


</style>