<template>
  <el-container style=" border: 1px solid #eee">

    <!--导航栏-->
    <el-container>
      <el-container class="login">
        <el-header style="background-color: #B3C0D1;
  color: #333;
  line-height: 60px;">
          <div style="width:100%;height:60px;
        position: fixed !important;top: 0px;left: 0px;">
            <el-page-header
                @back="goBack"
                content="租房客"
            >
            </el-page-header>
          </div>
        </el-header>
      </el-container>

      <el-header style="background-color: #B3C0D1;
  color: #333;
  line-height: 60px;">
        <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect" :default-active="$route.path" router>
          <el-menu-item index="/Index">主页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">合约管理</template>
            <el-menu-item>
              <template slot-scope="scope">
                <el-button type="text" @click="dialogVisible = true">合约1</el-button>
                <el-dialog
                    title="提示"
                    :visible.sync="dialogVisible"
                    :modal-append-to-body="false"
                    width="30%">
                  <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">返回</el-button>
  </span>
                </el-dialog>
              </template>
            </el-menu-item>
            <el-menu-item><template slot-scope="scope">
              <el-button type="text" @click="dialogVisible = true">合约2</el-button>
              <el-dialog
                  title="提示"
                  :visible.sync="dialogVisible"
                  :modal-append-to-body="false"
                  width="30%">
                <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">返回</el-button>
  </span>
              </el-dialog>
            </template></el-menu-item>
            <el-menu-item><template slot-scope="scope">
              <el-button type="text" @click="dialogVisible = true">合约3</el-button>
              <el-dialog
                  title="提示"
                  :visible.sync="dialogVisible"
                  :modal-append-to-body="false"
                  width="30%">
                <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">返回</el-button>
  </span>
              </el-dialog>
            </template></el-menu-item>
          </el-submenu>
          <el-menu-item index="/House">房源中心</el-menu-item>
          <el-menu-item index="/self"><a btarget="_blank">个人中心</a></el-menu-item>
        </el-menu>
      </el-header>
      <el-header style="text-align: center">
        <p style="font-size:30px">租房客：全世界最优秀的房屋租赁平台</p>
        <p>这里汇聚了最丰富以及优质的房源信息</p>
        <p>您可以在这里租到您向往的的所有风格类型</p>
        <p>希望您可以感受到我们的诚挚与热情</p>
      </el-header>
      <el-header style="margin-top: 160px">
        <template>
          <el-carousel :interval="4000" type="card" height="350px" >
            <el-carousel-item v-for="item in list" v-bind:key="item">
              <img :src="item.img"/>
            </el-carousel-item>
          </el-carousel>
        </template>
      </el-header>
      <el-header style="margin-top: 340px">
        <el-row>
          <el-col :span="4" :offset="6">联系电话：15888729620</el-col>
          <el-col :span="5">公司名称：浙江省租房客网络有限公司</el-col>
          <el-col :span="6">公司地址：浙江省温州市龙湾区智联大厦1103室</el-col>
        </el-row>
      </el-header>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name:"Index",
  data() {
    return {
      list:[
        {img:require('../assets/a.jpg')},
        {img:require('../assets/b.jpg')},
        {img:require('../assets/c.jpg')},
        {img:require('../assets/d.jpg')},
        {img:require('../assets/e.jpg')}
          ],
      dialogVisible : false
    }
  },
  handleSelect(key, keyPath) {
    console.log(key, keyPath);
  },
  methods:{
    goback(){
      this.$router.replace('');
    },
    PerIfoBtn(){
      this.$router.replace('/self');
    },
    loadGet(){
      this.$axios.get('http://localhost:8090/list').then(res=>res.data).then(res=>{
        console.log(res)
      })
    },
    loadPost(){
      this.$axios.post('http://localhost:8090/listP',{name:"叶"}).then(res=>res.data).then(res=>{
        console.log(res)
       // this.tableData=res
      })
    }
  },
  beforeMount() {
    this.loadPost();
  }
}
</script>





<style scoped>


.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.el-aside {
  color: #333;
}

.login .el-page-header {

  line-height: 60px;
  margin-left: 20px;
}
.login .el-page-header__content {

}

</style>