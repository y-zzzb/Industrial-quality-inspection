<template>
  <el-container>
    <div class="login_container">
      <el-form v-model="loginData" class="demo-dynamic"   ref="formRef" >
        <el-col :span="24" :offset="6" style="margin-bottom: 40px" >
          <h1>房屋租赁系统注册</h1>
        </el-col>
        <el-form-item>
          <el-row style="font-weight: bold">用户名:</el-row>
          <el-col :span="24" :offset="0">
            <el-input v-model="loginData.userName" placeholder="请输入用户名" clearable />
          </el-col>
        </el-form-item>

        <el-form-item >
          <el-row style="font-weight: bold">密码:</el-row>
          <el-col :span="24" :offset="0">
            <el-input
                v-model="loginData.password"
                type="password"
                placeholder="请输入密码"
                show-password
            />
          </el-col>
        </el-form-item>
        <el-row>
          <el-radio-group v-model="radio">
            <el-radio :label="3" style="color: #181818" font>用户</el-radio>
            <el-radio :label="6" style="color: #181818">房东</el-radio>
          </el-radio-group>
        </el-row>
        <el-col :span = "24" :offset="9">
          <div id="login_btn">
            <el-button type="primary" @click="loginBtn">完成注册</el-button>
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


export default {
  name: "Register",
  // 类似data（） vue3 语法糖
  data(){
    return {
      radio: 3
    };
  },
  setup(){
    const loginData = reactive({
      userName:'',
      password:'',
    })

    return {
      trole:"",
      loginData,
    }

  },
  methods: {
    loginBtn() {
      this.$axios.post('http://localhost:8090/logint', {
        userName: this.loginData.userName
      }).then(res => res.data).then(res => {
        console.log(res)
        if (this.radio === 3) {
          this.trole = "租客"
        } else {
          this.trole = "房东"
        }
        console.log(this.trole)
        if (res.code === 400) {
          this.$axios.post('http://localhost:8090/save', {
            userName: this.loginData.userName,
            password: this.loginData.password,
            role:this.trole
          })
          this.$router.replace('/');
        } else {
          Message({
            message: '用户名已注册',
            type: 'warning',
          })
        }
      });
    }
  }
}
</script>

<style scoped>
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
</style>