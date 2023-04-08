<template>
  <el-form class="form" ref="form" :rules="rules" :model="formData.form" label-width="120px">
    <el-form-item label="用户名：" prop="userName">
      <el-input v-model="formData.form.userName" />
    </el-form-item>
    <el-form-item label="用户Id：" >
      <el-input  disabled v-model="formData.form.userId"  />
    </el-form-item>
    <el-form-item label="用户密码：" prop="password">
      <el-input v-model="formData.form.password" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="set">提交</el-button>
      <el-button @click="changeFalse">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>

import {onBeforeMount, onBeforeUnmount, onMounted, reactive, ref, watch} from 'vue'

const props = defineProps(['id','name'])



//自定义事件
let emits = defineEmits(['change','set'])

//表单
const form = ref(null)


//表单数据
let formData =   ref({
  form:{
    userId : 0,
    userName : '',
    password : ''
  }
});

onMounted(()=>{
  formData.value.form.userId = props.id
  formData.value.form.userName = props.name
})
watch(()=>[props.id,props.name],(newValue)=>{
  formData.value.form.userId = newValue[0]
  formData.value.form.userName = newValue[1]
})


const rules = reactive( {
  // 设置账户效验规则
  userName: [
    {required: true, message: '请输入账户', trigger: 'blur'},
    {min: 3, max: 20, message: '长度在 3 到 20 个字符的账户', trigger: 'blur'},
  ],
  // 设置密码效验规则
  password: [
    {required: true, message: '请输入密码', trigger: 'blur'},
    {min: 6, max: 15, message: '长度在 6 到 15 个字符的密码', trigger: 'blur'}
 ]
});


//自定义事件触发修改isAdd值完成卸载
function changeFalse(){
  emits('change')
}

//set方法
function set(){
  form.value.validate((valid)=>{
    if(valid){
      emits('set',formData.value.form.userId,
          formData.value.form.userName,
          formData.value.form.password)
    }
  }
  )

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