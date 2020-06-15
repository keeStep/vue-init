<template>
  <div>
    <el-form :rules="rules" ref="loginForm" :model="loginForm" class="loginContainer">
      <h3>系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" aria-autocomplete="off" aria-placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="text" v-model="loginForm.password" aria-autocomplete="off" aria-placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-checkbox class="loginRemember" v-model="checked"></el-checkbox>
      <el-button type="primary" style="width: 100%;" @click="submitLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script>

export default {
  name:"Login",
  data() {
    return {
      checked:true,
      loginForm:{
        username:'admin',
        password:'123'
      },
      rules:{
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}]
      }
    }
  },
  methods: {
    submitLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.postKeyValueRequest('/doLogin', this.loginForm).then(resp =>{
            if(resp) {
              window.sessionStorage.setItem("user", JSON.stringify(resp.obj));

              // $router 通过 $ 获取当前 vue 对象的 router 对象
              // replace() 回不到之前的页面；
              // push() 可回到之前的页面；
              this.$router.replace("/home");
            }
          })
          
        } else {
          this.$message.error('您没有输入完成哦');
          return false;
        }
      });
    }
  }

}
</script>

<style>
  .loginContainer{
    border-radius: 15px;
    background-clip: padding-box;
    background: aliceblue;
    margin-top: 180px auto;
    width: 360px;
    padding: 5px 40px 26px 40px ;
    border: 1px solid skyblue;
    box-shadow: 0 0 25px skyblue;
  }
  .loginRemember{
    margin: 0px 0px 14px 0px;
  }
</style>