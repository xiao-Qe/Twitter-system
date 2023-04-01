<template>
  <el-card class="box-card" shadow="hover" @click="getTwitter(item)">
    <template #header>
      <div class="card-header">
        <span>{{ item.title }}</span>
        <span class="author">{{item.author}}</span>
      </div>
    </template>
    <div class="content">{{item.blurb}}</div>
    <div class="like">
      <el-icon><View /></el-icon>
      {{item.like}}
    </div>
    <div class="collect">
      <el-icon><Star /></el-icon>
      {{item.collect}}
    </div>
  </el-card>
</template>

<script setup>

import {twitterEntity} from "@/store/twitterEntity";
import router from "@/router";


const entity = twitterEntity();
defineProps(['item']);

function getTwitter(item){
  entity.$patch({
    title:item.title,
    author:item.author,
    blurb:item.blurb,
    content:item.content,
    like:item.like,
    collect:item.collect
  })
  router.push({
    path:"/twitter"
  })

}
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