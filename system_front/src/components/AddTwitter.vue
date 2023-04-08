<template>
  <el-form class="form" ref="form" :model="formData.form" :rules="rules" label-width="120px">
    <el-form-item label="作品名：" prop="title">
      <el-input v-model="formData.form.title" />
    </el-form-item>
    <el-form-item label="作品类型：">
      <el-select v-model="classify" placeholder="请选择作品分类">
        <el-option v-for="classify in superClassify"  :label="classify.className" :value="classify.classifyId"  />
      </el-select>
    </el-form-item>
    <el-form-item label="具体类型：">
      <el-select v-model="formData.form.classify" placeholder="请选择作品分类">
        <el-option v-for="classify in specificClassification"  :label="classify.className" :value="classify.classifyId"  />
      </el-select>
    </el-form-item>
    <el-form-item label="作品类型">
      <el-radio-group v-model="formData.form.type">
        <el-radio label="0" >私密</el-radio>
        <el-radio label="1" >公开</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="作品简介" prop="blurb">
      <el-input class="blurb" v-model="formData.form.blurb" type="textarea" />
    </el-form-item>
    <el-form-item label="作品内容" prop="content">
      <el-input class="content" v-model="formData.form.content" type="textarea" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="goSubmit">提交</el-button>
      <el-button @click="changeFalse">取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script setup>

import {onBeforeMount, reactive, ref, watch} from 'vue'
import {getClassifyBySuperId, getSupperId,addTwitter} from "@/api/myself/addTwitter";
import {ElMessage} from "element-plus";
import {User} from '@/store/user.js'

const user = User();


//自定义事件
const emit = defineEmits(['change','refresh'])

//表单
const form = ref(null)
//大类分类
let superClassify = ref([
  {classifyId:0,
  className:''}
]);
//选中的大类
const classify = ref(undefined)
//具体分类
let specificClassification = ref([
  {classifyId:0,
    className:''}
]);

//表单数据
  const formData =   reactive({
      form:{
        title: '',
        classify: undefined,
        type: 0,
        blurb: '',
        content:''
      }
  });

  //表单验证规则
  const rules = reactive({
    //标题验证规则
    title:[
        {required: true, message: '请输入标题', trigger: 'blur'},
        {min: 3, max: 50, message: '标题长度应在 3 到 50 个字符', trigger: 'blur'}
    ],
    //简介验证规则
    blurb:[
      {required: true, message: '请输入简介', trigger: 'blur'},
      {max:150, message: '长度最多150字', trigger: 'blur'}
    ],
    //内容验证规则
    content:[
      {required: true, message: '请输入内容', trigger: 'blur'}
    ]
  })

  onBeforeMount(async () => {
    const resBean = await getSupperId();
    if(resBean.data.status === 200){
      superClassify.value = resBean.data.data;
    }else {
      ElMessage.error(resBean.data.msg)
    }
  })

function goSubmit(){
    form.value.validate(async (valid) => {
      console.log(formData.form)
      //表单验证是否通过
      if (valid) {
        const resBean =  await addTwitter(user.userId,formData.form.title,formData.form.classify,formData.form.type,formData.form.blurb,formData.form.content)
        //判断是否添加成功
        if(resBean.data.status === 200){
          ElMessage.success('添加成功')
          //触发事件，让父类重新刷新页面
          emit('refresh')
        }else {
          //出错报警
          ElMessage.error(resBean.data.msg)
        }
      } else {
        ElMessage.error('请完整输入内容')
      }

    })
}

  //自定义事件触发修改isAdd值完成卸载
function changeFalse(){
emit('change')
}

//事件监听获取superId
watch(classify,async (newValue) => {
  const resBean = await getClassifyBySuperId(newValue)
  if(resBean.data.status === 200){
    specificClassification.value = resBean.data.data
  }else {
    ElMessage.error(resBean.data.msg)
  }
})

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