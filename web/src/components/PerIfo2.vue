<template>
  <el-container>
    <div class="login_container">
        <el-row style="margin-top: 30px">
          <el-col :span="24">
          <h1 style="display: flex; justify-content: center; align-items: center;">个人中心</h1>
          </el-col>
        </el-row>
        <el-row type="flex" justify="center" style="margin-bottom: 20px">
            <div class="avatar"></div>
        </el-row>
      <el-form  ref="formRef" style="display: flex; justify-content: center; align-items: center;flex-direction: column;" >
        <el-form-item >
          <span slot="label">
      	<span style="color: #181818">姓名</span>
      </span>
          <el-col :span="18">
            <el-input v-model="user.name"></el-input>
          </el-col>
          <el-col :span="3">
            <el-button @click="userchange">修改</el-button>
          </el-col>
        </el-form-item>
        <el-form-item >
          <span slot="label">
      	<span style="color: #181818">年龄</span>
      </span>
          <el-col :span="18">
            <el-input v-model="user.age"></el-input>
          </el-col>
          <el-col :span="3">
            <el-button @click="userchange">修改</el-button>
          </el-col>
        </el-form-item>
        <el-form-item >
          <span slot="label">
      	<span style="color: #181818">性别</span>
      </span>
          <el-col :span="18">
            <el-input v-model="user.sex"></el-input>
          </el-col>
          <el-col :span="3">
            <el-button @click="userchange">修改</el-button>
          </el-col>
        </el-form-item>
        <el-form-item >
          <span slot="label">
      	<span style="color: #181818">手机</span>
      </span>
          <el-col :span="18">
            <el-input v-model="user.phone"></el-input>
          </el-col>
          <el-col :span="3">
            <el-button @click="userchange">修改</el-button>
          </el-col>
        </el-form-item>
        <el-form-item >
          <span slot="label">
      	<span style="color: #181818">身份证</span>
      </span>
          <el-col :span="18">
            <el-input v-model="user.roleId"></el-input>
          </el-col>
          <el-col :span="3">
            <el-button @click="userchange">修改</el-button>
          </el-col>
        </el-form-item>
      </el-form>
        <el-row style="margin-top: 30px">
          <el-col :span="12" >
          <div id="login_btn" style="margin-left: 300px">
            <el-button @click="returnBtn">返回</el-button>
          </div>
          </el-col>
          <el-col :span="12">
            <div id="login_btn" >
              <el-button @click="sureBtn">确定</el-button>
            </div>
          </el-col>
        </el-row>
    </div>
  </el-container>
</template>

<script>
import {reactive} from "vue";
import {mapState,mapActions} from "vuex";
import {Message} from "element-ui";


export default {
  name: "PerIfo",
  // 类似data（） vue3 语法糖
  data() {
    return {
      user:{
        id:JSON.parse(sessionStorage.getItem('CurUser')).id,
        userName:JSON.parse(sessionStorage.getItem('CurUser')).userName,
        password:JSON.parse(sessionStorage.getItem('CurUser')).password,
        isValid:JSON.parse(sessionStorage.getItem('CurUser')).isValid,
        role:JSON.parse(sessionStorage.getItem('CurUser')).role,
        name:JSON.parse(sessionStorage.getItem('CurUser')).name,
        age:JSON.parse(sessionStorage.getItem('CurUser')).age,
        sex:JSON.parse(sessionStorage.getItem('CurUser')).sex,
        phone:JSON.parse(sessionStorage.getItem('CurUser')).phone,
        roleId:JSON.parse(sessionStorage.getItem('CurUser')).roleId,
      },

    }
  },
  methods: {
    userchange(){
      console.log(this.user)
      this.$axios.post('http://localhost:8090/saveOrMode',this.user).then(res=>res.data).then(res=> {
        console.log(this.user)
        sessionStorage.setItem("CurUser", JSON.stringify(this.user))
      })
    },
    init(){
      this.user = JSON.parse(sessionStorage.getItem('CurUser'))
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    returnBtn() {
      this.$router.push("/self2");
    },
    loginBtn() {

    }
  }
}
</script>

<style scoped>
.el-form-item{
  width: 500px;
}
.el-button{
  margin: 5px;
  height: 35px;
}
.ifo{
  width: 25%;
  height: 25px;
}
.el-container{
  margin: 0;
  height: 100%;
  padding: 0;
  background-size:100% ;
  background-image: url("../assets/PerIfo.jpg");
  display: flex;
  justify-content: center;
  align-items: center;
  overflow: hidden;
}
.login_container{
  width: 900px;
  height: 696px;
  background: rgba(223,219,219,0.2);
  border-radius: 5px;
  box-shadow: 0 25px 35px rgba(0,0,0,0.8);
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: #ccc;
  margin-top: 50px;
}
#login_btn{
  width: 30%;
  margin-top: 10px;
  display: flex;
  align-items: center;
  justify-content: center;

}
.demo-dynamic{
  width: 80%;
  height: 60%;
}
</style>
