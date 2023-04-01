<template>
  <h1>推荐页面</h1>
  <show-card  v-for="item in twitter" :item = "item"></show-card>
</template>

<script setup>
  import {ref, toRaw} from 'vue'
  import ShowCard from "@/components/showCard";

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

</script>

<style scoped>


</style>