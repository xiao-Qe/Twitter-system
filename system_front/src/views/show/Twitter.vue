<template>
  <div class="back">
    <div class="main">
      <!-- 文章标题 -->
      <h2 class="title">{{ twitter.title }}</h2>
      <!-- 文章描述 -->
      <h6 class="description">
        <el-icon><Timer /></el-icon>&nbsp;{{time}}
        &nbsp;&nbsp;
        <el-icon>
          <Avatar/>
        </el-icon>&nbsp;{{ twitter.author }}
        &nbsp;&nbsp;
        <el-icon>
          <View/>
        </el-icon>&nbsp;{{ twitter.like }}
        &nbsp;&nbsp;
        <el-icon>
          <Star/>
        </el-icon>&nbsp;{{ twitter.collect }}
      </h6>
      <!-- 文章内容 -->
      <div class="text" v-html="twitter.content"></div>
      <!-- 对应点击事件 -->
      <div class="tag-box">
        <div class="like">
          <el-icon>
            <View/>
          </el-icon>
          {{ twitter.like }}&nbsp;&nbsp;
        </div>
        <div class="collect">
          <el-icon><Star/></el-icon>{{ twitter.collect }}&nbsp;&nbsp;
        </div>
        <div class = "comment" @click = "showComment = !showComment">
          <el-icon >
          <ChatLineRound/>
          </el-icon>
          评论
        </div>
      </div>
      <Comment v-if = "showComment"></Comment>
    </div>
  </div>
</template>

<script setup>
import {twitterIndex} from "@/store/twitterIndex";
import Comment from "@/components/Comment";
import {onBeforeMount, reactive, ref} from "vue";
import {getTwitterDisplay} from "@/api/twitter";
import {ElMessage} from "element-plus";

const twitterID = twitterIndex();
let time = ref('')
const showComment = ref(false);
let twitter = ref({
  id : 0,
  creatTime : 0,
  title : '',
  blurb : '',
  author : '',
  like : 0,
  collect : 0,
  content : '',
  view : 0
})
onBeforeMount(async () => {

  const resBean = await getTwitterDisplay(twitterID.id)
  if (resBean.data.status === 200) {
    twitter.value = resBean.data.data
  } else {
    ElMessage.error(resBean.data.msg)
  }

  const date = new Date(twitter.value.creatTime * 1000)

  time = date.toLocaleDateString();
})





</script>

<style scoped>
.back {
  background-color: #B3C0D1;
  color: #333;
  height: 100vh;
}

.main {
  margin: 0.1% 10% 0.1% 10%;
  border-radius: 5px;
  background-color: aliceblue;
  /* 防止当文章内容较少的时候，出现上下颜色不一致的问题 */
  /*min-height: 100%;*/
  height: 100vh;
  /* 解决元素浮动后从父元素溢出的问题 */
  overflow: auto;
}

.title,
.description {
  text-align: center;
}

.text {
  line-height: 30px;
  white-space: pre-wrap;
}

.tag-box {
  margin-right: 10px;
  display: flex;
  flex-direction: row;
  justify-content: right;
}
.comment{
  cursor: pointer;
}
</style>