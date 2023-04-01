<template>
  <hr />
  <h5>总共有4条评论</h5>
  <hr />
    <div class = "speaker">
      <el-icon size="100px"><UserFilled /></el-icon>
    </div>
    <h6  class="commentator-name">匿名用户</h6>
    <p>一级评论1</p>
    <div class="commentAssess">
      <el-icon><Flag /></el-icon>喜欢
      <div class="secondComments" @click="showSecondLevelComment = !showSecondLevelComment">
        <el-icon ><ChatLineRound /></el-icon>2&nbsp;&nbsp;
      </div>
    </div>

  <div v-if="showSecondLevelComment" class = "secondComment" >
       二级评论列表 start
        <div class="speaker">
          <el-icon size="50px"><UserFilled /></el-icon>
        </div>
        <h6  class="commentator-name">匿名用户</h6>
        <p>二级评论1</p>
       二级评论 end
      <hr />
      <textarea placeholder="请输入评论内容......"></textarea>
      <button class="pull-right" >提交</button>
    </div>
</template>

<script setup>
import {ref} from "vue";

let showSecondLevelComment = ref(false);

</script>

<style scoped>
.commentator-name {

  font-weight: bold;
  text-align: left;
}
.speaker{
  float:left;
}
.secondComment{
  padding: 0.1% 10%;
}
.secondComments{
  float: left;
  cursor: pointer;
}
textarea {
  margin: 10px 0;
}
</style>