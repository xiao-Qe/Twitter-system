<template>
  <el-form class="form" ref="form" :disabled="false" :model="formData.form" label-width="120px">
    <el-form-item label="作品名：" prop="title">
      <el-input v-model="formData.form.title" />
    </el-form-item>
    <el-form-item label="作品类型：">
        <el-input  v-model="formData.form.superName"  />
    </el-form-item>
    <el-form-item label="具体类型：">
      <el-input v-model="formData.form.classifyName" />
    </el-form-item>
    <el-form-item label="作品类型">
      <el-radio-group v-model="formData.form.type">
        <el-radio :label= "0" >私密</el-radio>
        <el-radio :label= "1" >公开</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="作品简介" prop="blurb">
      <el-input class="blurb" v-model="formData.form.blurb" type="textarea" />
    </el-form-item>
    <el-form-item label="作品内容" prop="content">
      <el-input class="content" v-model="formData.form.content" type="textarea" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="changeFalse">关闭</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>

import {onBeforeMount, reactive, ref, watch} from 'vue'
import {User} from '@/store/user.js'
import {getAdministratorShowTwitter} from "@/api/administrator";
import {ElMessage} from "element-plus";

const props = defineProps(['twitterID'])
const user = User();


//自定义事件
const emit = defineEmits(['change','refresh'])

//表单
const form = ref(null)


//表单数据
const formData =   ref({
  form:{
    title: '111',
    superName:'111',
    classifyName: '111',
    type: 0,
    blurb: '111',
    content:'111'
  }
});

onBeforeMount(async () => {
  const resBean = await getAdministratorShowTwitter(props.twitterID)
  if(resBean.data.status === 200){
    formData.value.form = resBean.data.data
  }else {
    ElMessage.error(resBean.data.msg)
  }
})

//自定义事件触发修改isAdd值完成卸载
function changeFalse(){
  emit('change')
}


</script>

<style scoped>
.form{
  background-color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  opacity: 0.9;
  border-radius: 10px;
  padding: 40px 40px 30px 15px;
  width: auto;
}
.blurb{
  width: 800px;
  height: 70px;
}

</style>