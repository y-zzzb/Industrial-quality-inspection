<template>
  <el-container>
    <div class="login_container">
      <el-row style="margin-top: 30px">
        <el-col :span="24">
          <h1 style="display: flex; justify-content: center; align-items: center;">个人中心</h1>
        </el-col>
      </el-row>

      <el-descriptions class="margin-top"  :column="2" :size="size" border style="margin-top: 100px">

        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            姓名
          </template>
          {{user.name}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            年龄
          </template>
          {{user.age}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-coordinate"></i>
            性别
          </template>
          <el-tag :type="user.sex==='1' ? 'primary' : 'danger'"
                  disable-transitions><i :class="user.sex==1?'el-icon-male':'el-icon-female'"></i>{{user.sex===1?"男":"女"}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-phone"></i>
            电话
          </template>
          {{user.phone}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>
            身份证
          </template>
          {{user.roleId}}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-custom"></i>
            用户身份
          </template>
          {{user.role}}
        </el-descriptions-item>
      </el-descriptions>

      <el-row style="margin-top: 30px">
        <el-col :span="12" >
          <div id="login_btn" style="margin-left: 300px">
            <el-button @click="returnBtn">返回主界面</el-button>
          </div>
        </el-col>
        <el-col :span="12">
          <div id="login_btn" >
            <el-button @click="changeBtn">修改</el-button>
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
      dialogVisible: false,
      user:{}
    }
  },
  methods: {
    init(){
      this.user=JSON.parse(sessionStorage.getItem("CurUser"))
      },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
    },
    returnBtn() {
      this.$router.push("/Index");
    },
    changeBtn() {
      this.$router.push("/PerIfo");
    }
  },
  beforeMount() {
    this.init()
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
