<template>
  <div class = "category-column">
  <el-scrollbar v-model="classify">
    <div class="scrollbar-flex-content" >
      <p v-for="item in classify" :key="item.classifyId" class="scrollbar-demo-item"
         :class="{'checked':activeId === item.classifyId}"
          @click="checked(item.classifyId)">
        {{ item.className }}
      </p>
    </div>
  </el-scrollbar>
  </div>
<h1>分类展示</h1>
  <h1>{{title}}</h1>
  <show-card  v-for="item in twitter" :item = "item"></show-card>
</template>

<script setup>
import { reactive, ref, watch} from 'vue'
import {useRoute} from "vue-router/dist/vue-router";
import ShowCard from "@/components/showCard";
import {getClassifyBySuperId} from "@/api/twitter/addTwitter";
import {ElMessage} from "element-plus";

  //选中的id
  let activeId = ref(0);
  let title = ref('');
  let classify = ref([{
    classifyId: undefined,
    className : ''
  }]);
  let route = reactive(useRoute())


let  twitter = ref([
  {
    title: '标题1',
    author: '作者',
    blurb: '简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1' +
        '简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1简介1',
    content: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容' +
        '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容\n -内容内容内容内容内容内容内容内容内容' +
        '内容内容内容内容内容\n内容内容内容内容内容',
    like: 20,
    collect: 30
  },
  {
    title: '标题2',
    author: '作者1',
    blurb: '简介2',
    content: '内容',
    like: 18,
    collect: 32
  },
  {
    title: '标题3',
    author: '作者2',
    blurb: '简介3',
    content: '内容',
    like: 290,
    collect: 80
  }
]);

  resetData();

  //选中改变样式
  function checked(Id){
    activeId.value = Id;
  }


  function resetData () {
      // 根据不同路由地址，返回不同数据
      if(route.path === '/user/athletics'){
        title.value = '体育'
        getClassify(1000)
      }else if (route.path === '/user/amusement'){
        title.value = '娱乐'
        getClassify(2000)
      }else if(route.path === '/user/study'){
        title.value = '学习'
        getClassify(4000)
      }else if (route.path === '/user/technology'){
        title.value = '科学'
        getClassify(3000)
      }else if(route.path === '/user/currentEvents'){
        title.value = '时事'
        getClassify(5000)
      }
    }

    async function getClassify(superId) {
     const resBean = await getClassifyBySuperId(superId)
      if(resBean.data.status === 200){
        classify.value = resBean.data.data
      }else {
        ElMessage.error(resBean.data.msg)
      }
      activeId.value = classify.value[0].classifyId
    }

  watch(() => route.path,()=>{resetData()})
</script>

<style scoped>
.category-column{
  height: 80px;
}
.scrollbar-flex-content {
  display: flex;
}
.scrollbar-demo-item {
  cursor: pointer;
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 170px;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary)
}
.checked{
  background: #FFCC99;
}
</style>