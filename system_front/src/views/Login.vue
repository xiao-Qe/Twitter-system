<template>
  <div :xl="6" :lg="7" class="bg-login">
    <!--标题-->
    <el-row type="flex" class="row-bg row-two" justify="center" align="middle">
      <el-col :span="6"></el-col>
      <el-col :span="6">
        <!--标题-->
        <h1 class="title">文章推荐系统</h1>
      </el-col>
      <el-col :span="6"></el-col>
    </el-row>
    <p class="status">{{ status }}</p>
    <!--form表单-->
    <el-row type="flex" class="row-bg card" justify="center" align="bottom">
      <el-col :span="7" class="login-card">
        <!--loginForm-->
        <el-form ref="loginForm" :model="loginFormData.loginForm" :rules="rules"
                  label-width="25%" class="loginForm">
          <el-form-item v-if="confirmPassword" label="用户名" prop="username" style="width: 300px">
            <el-input v-model="loginFormData.loginForm.username"></el-input>
          </el-form-item>
          <el-form-item v-else label="用户ID" prop="userId" style="width: 300px">
            <el-input v-model="loginFormData.loginForm.userId"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password" style="width: 300px">
            <el-input type="password" v-model="loginFormData.loginForm.password"></el-input>
          </el-form-item>
          <el-form-item v-if="confirmPassword" label="确认密码" prop="confirm" >
            <el-input type="password" v-model="loginFormData.loginForm.confirm"></el-input>
          </el-form-item>
          <el-form-item class="btn-ground">
            <el-button type="primary" @click="login">立即登陆</el-button>
            <el-button @click="register">
              <template v-if="!confirmPassword">注册</template>
              <template v-else>返回</template>
            </el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import router from "@/router";
import {h, reactive, ref} from "vue";
import {registerUser,loginUser} from '@/api/login/index.js'
import {ElMessage, ElMessageBox} from 'element-plus'
import {User} from '@/store/user.js'

const userInfo = User();
const status = ref('登录');
const confirmPassword = ref(false);
const loginForm = ref(null);

  let loginFormData = reactive({
    loginForm:{
      // 账户数据
      username: '',
      //用户id
      userId:'',
      // 密码数据
      password: '',
      // 记住密码
      confirm: ''
    },

  });

const validatePass2 = () => {
  return (rule, value, callback) => {
    if (value !== loginFormData.loginForm.password && loginFormData.loginForm.password !== '') {
      callback(new Error('两次输入密码不一致!'))
    } else {
      callback()
    }
  }
}
const validatePass1 = () => {
  return (rule, value, callback) => {
    if (
        value !== loginFormData.loginForm.confirm &&
        loginFormData.loginForm.confirm !== ''
    ) {
      callback(new Error('两次输入密码不一致!'))
    } else {
      callback()
    }
  }
}

  const rules = reactive( {
    // 设置账户效验规则
    username: [
      {required: true, message: '请输入账户', trigger: 'blur'},
      {min: 3, max: 20, message: '长度在 3 到 20 个字符的账户', trigger: 'blur'},
    ],
    //用户id输入规则
    userId:[
        {required:true,message:'请输入id',trigger:'blur'},
    ],
    // 设置密码效验规则
    password: [
      {required: true, message: '请输入密码', trigger: 'blur'},
      {min: 6, max: 15, message: '长度在 6 到 15 个字符的密码', trigger: 'blur'},
      {trigger: 'blur', validator: validatePass1()}
    ],
    confirm:[
        {trigger: 'blur', message: '请确认密码'},
        { min: 6, max: 15, message: '密码长度在 6 到 15 个字符', trigger: 'blur' },
        {trigger: 'blur', validator: validatePass2()}
    ]
  });

  async function login() {
    await loginForm.value.validate(async (valid) => {
      if (valid) {
        let resBean = reactive({})
        if (confirmPassword.value) {
          //注册
           resBean = await registerUser(loginFormData.loginForm.username, loginFormData.loginForm.password);
        }else {
          //登录
           resBean = await loginUser(loginFormData.loginForm.userId,loginFormData.loginForm.password);
        }
        //判断是否成功
        if(resBean.data.status === 200){
          const info = resBean.data.data;
          userInfo.$patch({
            userId:info['userInfo'].userId,
            userName:info['userInfo'].userName
          })
          if(confirmPassword.value){
            await ElMessageBox({
                title : '注册成功',
                message : h('p',null,[
                    h('h3',null,'ID号为：'+info['userInfo'].userId),
                  h('h3',null,'名字为：'+info['userInfo'].userName)
                ])
            })
          }
          window.localStorage.setItem("authorization",info['token'])
          await router.push("/user")
        }
        else {
          ElMessage.error(resBean.data.msg)
        }

      } else {
        ElMessage.error('请输入完整id和密码')
      }
    })
  }


  function register(){
    confirmPassword.value = !confirmPassword.value;
    if(confirmPassword.value){
      status.value = '注册'
    }else {
      status.value = '登录'
    }
  }

</script>

<style scoped>
.bg-login {
  height: 100vh;
  background-image: url("../assets/background.jpg");
  background-size: 100%;
}

.btn-ground {
  text-align: center;
}

.title {
  text-shadow: -3px 3px 1px #5f565e;
  text-align: center;
  margin-top: 20%;
  color: #41b9a6;
  font-size: 40px;
}

.status{
  margin-top: 0.3%;
  text-align: center;
  text-shadow: -3px 3px 1px #5f565e;
  color: #41b9a6;
  font-size: 30px;
}
.login-card {
  background-color: #ffffff;
  opacity: 0.9;
  box-shadow: 0 0 20px #ffffff;
  border-radius: 10px;
  padding: 40px 40px 30px 15px;
  width: auto;
}
</style>