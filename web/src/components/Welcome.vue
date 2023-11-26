<template>
      <el-container>
      <div class="login_container">
        <el-form v-model="loginData" class="demo-dynamic"   ref="formRef" >
          <el-col :span="24" :offset="6" style="margin-bottom: 40px" >
            <h1>工业质检软件系统</h1>
          </el-col>
          <el-form-item  label="用户名">
            <el-col :span="24" :offset="0">
              <el-input v-model="loginData.userName" placeholder="请输入用户名" clearable />
            </el-col>
          </el-form-item>

          <el-form-item label="密码">
            <el-col :span="24" :offset="0">
              <el-input
                  v-model="loginData.password"
                  type="password"
                  placeholder="请输入密码"
                  show-password
              />
            </el-col>
          </el-form-item >
          <el-form-item prop="verifycode" label="验证码">
            <el-input v-model="Code" placeholder="请输入验证码" class="identifyinput">
            </el-input>
          </el-form-item>
          <el-form-item>
            <div class="identifybox">
              <div @click="refreshCode">
                <s-identify :identifyCode="identifyCode"></s-identify>
              </div>
              <el-button @click="refreshCode" type='text' class="textbtn">看不清，换一张</el-button>
            </div>
          </el-form-item>


          <el-row>
            <el-radio-group v-model="radio">
              <el-radio :label="3" style="color: #181818" font>用户</el-radio>
              <el-radio :label="6" style="color: #181818">管理员</el-radio>
            </el-radio-group>
          </el-row>
          <el-col :span = "24" :offset="9">
            <div id="login_btn">
              <el-button type="primary" @click="loginBtn">登录</el-button>
            </div>
          </el-col>
          <el-col :span = "24" :offset="9">
            <div id="login_btn">
              <el-button type="primary" @click="registerBtn">注册</el-button>
            </div>
          </el-col>

        </el-form>
      </div>
    </el-container>
  </template>

<script>
import {reactive} from "vue";
import {mapState,mapActions} from "vuex";
import {Message} from "element-ui";
import SIdentify from "../components/canvas.vue"



export default {
  name: "LoginPage",
  // 类似data（） vue3 语法糖
  data(){
    const validateVerifycode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'))
      } else if (value !== this.identifyCode) {
        callback(new Error('验证码不正确!'))
      } else {
        callback()
      }
    }
    return {
      identifyCodes: '1234567890abcdefghijklmnopqrstuvwxyz',
      identifyCode: '',
      Code:"",
      rules: {
        verifycode: [{
          required: true,
          trigger: 'blur',
          validator: validateVerifycode,}]
      },
      radio: 3
    }
  },
  setup(){
    const loginData = reactive({
      userName:'',
      password:'',
    })

    return {
      loginData,
      ...mapState(['isLogin']),
    }

  },
  methods:{
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    // 切换验证码
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[
            this.randomNum(0, this.identifyCodes.length)]
      }
      console.log(this.identifyCode)
      /* alert(this.identifyCode) */
    },
    ...mapActions(['login']),
    loginBtn() {
      this.$axios.post('http://localhost:8090/login',this.loginData).then(res=>res.data).then(res=> {
        console.log(res)

          if (res.code == 200) {
            if(res.data.role=="租客"&&this.radio==3)
            {
            sessionStorage.setItem("CurUser", JSON.stringify(res.data))
            this.$router.replace('/Index');
            Message({
              message: '登录成功',
              type: 'success',
            })
          }
          else if(res.data.role=="房东"&&this.radio==6) {
              sessionStorage.setItem("CurUser", JSON.stringify(res.data))
              this.$router.replace('/Index2');
              Message({
                message: '登录成功',
                type: 'success',
              })
            }
            else{
              Message({
                message: '用户类型选择错误',
                type: 'warning',
              })
            }
          } else {
            Message({
              message: '用户名密码错误',
              type: 'warning',
            })
          }
      });
    },
    registerBtn(){
      this.$router.replace('/Register');
    }
  },
  components:{
    SIdentify
  },
  mounted(){
    this.identifyCode='';
    this.makeCode(this.identifyCodes,4);
    history.pushState(null, null, document.URL);
    if (window.history && window.history.pushState) {
      $(window).on('popstate', function (){
        window.history.pushState('forward', null, '');
        window.history.forward(1);
      });
      window.history.pushState('forward', null, ''); //在IE中必须得有这两行
      window.history.forward(1);
    }
  }
}
</script>

<style scoped>

.identifybox {
  display: flex;
  justify-content: space-between;
  margin-top: 7px;
}
.el-radio.el-radio__label {

  font-size:50px !important;
}
.el-container{
  margin: 0;
  height: 100%;
  padding: 0;
  background-size:100% ;
  background-image: url("../assets/login.jpg");
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}
.login_container{
  width: 500px;
  height: 696px;
  background: rgba(223,219,219,0.2);
  border-radius: 5px;
  box-shadow: 0 25px 35px rgba(0,0,0,0.8);
  display: flex;
  align-items: center;
  justify-content: center;
}
.el-input__wrapper{
  margin-bottom: 10px;
}
#login_btn{
  width: 90px;
  margin-top: 10px;
  display: inline-grid;
}
.el-button el-button--primary{
  height: 55px !important;
}
.demo-dynamic{
  width: 80%;
  height: 80%;
}

.textbtn{
  color: white;
}

</style>