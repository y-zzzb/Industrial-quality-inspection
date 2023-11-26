<template>
  <el-container style=" border: 1px solid #eee">

    <!--导航栏-->
    <el-container>
      <el-container class="login">
        <el-header>
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

      <el-header>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" router>
          <el-menu-item index="Index">主页</el-menu-item>
          <el-menu-item index="/Contract">合约中心</el-menu-item>
          <el-menu-item index="House">登记中心</el-menu-item>
          <el-menu-item index="self" @click="PerIfoBtn"><a btarget="_blank">个人中心</a></el-menu-item>
        </el-menu>
      </el-header>
      <el-header>
        <el-col :span="12">
          <el-input placeholder="请输入内容"
                    v-model="searchData.information"
                    clearable>
            <el-button
                slot="append"
                icon="el-icon-search"
                @click="getGoodsList">
            </el-button>
          </el-input>
        </el-col>
        <el-col :span="12">

          <el-button @click="newt">添加</el-button>
        </el-col>
      </el-header>
      <el-main>
        <el-table
            :data="tableData"
            border
            style="width: 100%">
          <el-table-column
              fixed
              prop="date"
              label="出租日期"
              width="120">
          </el-table-column>
          <el-table-column
              prop="name"
              label="房主姓名"
              width="120">
          </el-table-column>
          <el-table-column
              prop="province"
              label="城市"
              width="120">
          </el-table-column>
          <el-table-column
              prop="information"
              label="介绍"
              width="270">
          </el-table-column>
          <el-table-column
              prop="location"
              label="详细地址"
              width="250">
          </el-table-column>
          <el-table-column
              prop="zip"
              label="邮编"
              width="120">
          </el-table-column>
          <el-table-column
              prop="phone"
              label="联系电话"
              width="200">
          </el-table-column>
          <el-table-column
              prop="money"
              label="租金"
              width="120">
          </el-table-column>
          <el-table-column
              label="操作"
              width="150">
            <template slot-scope="scope">
              <el-button type="success"  size="small" @click="mod(scope.row)">修改</el-button>
              <el-dialog
                  title="提示"
                  :visible.sync="dialogVisible"
                  :modal-append-to-body="false"
                  width="30%">
                <el-form ref="form" :model="house" label-width="80px">
                  <el-form-item label="名字">
                    <el-input v-model="house.name"></el-input>
                  </el-form-item>
                  <el-form-item label="日期">
                    <el-input v-model="house.date"></el-input>
                  </el-form-item>
                  <el-form-item label="城市">
                    <el-input v-model="house.province"></el-input>
                  </el-form-item>
                  <el-form-item label="介绍">
                    <el-input v-model="house.information"></el-input>
                  </el-form-item>
                  <el-form-item label="位置">
                    <el-input v-model="house.location"></el-input>
                  </el-form-item>
                  <el-form-item label="电话">
                    <el-input v-model="house.phone"></el-input>
                  </el-form-item>
                  <el-form-item label="邮编">
                    <el-input v-model="house.zip"></el-input>
                  </el-form-item>
                  <el-form-item label="租金">
                    <el-input v-model="house.money"></el-input>
                  </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">返回</el-button>
                  <el-button type="primary" @click="housechange">提交</el-button>
  </span>
              </el-dialog>
              <el-button type="danger"  size="small" @click="del(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name:"House",
  data() {
    return {
      options: [{
        label: '热门城市',
        options: [{
          value: '上海',
          label: '上海'
        }, {
          value: '北京',
          label: '北京'
        }]
      }, {
        label: '城市名',
        options: [{
          value: '成都',
          label: '成都'
        }, {
          value: '深圳',
          label: '深圳'
        }, {
          value: '广州',
          label: '广州'
        }, {
          value: '大连',
          label: '大连'
        },
          {
            value: '温州',
            label: '温州'
          }]
      }],
      dialogVisible: false,
      searchData:{
        information:""
      },
      tableData: [

      ],
      house:{
        id:"",
        name:"",
        date:"",
        province:"",
        information:"",
        location:"",
        phone:"",
        zip:"",
        money:0,
        statue:1
      }
    }
  },
  handleSelect(key, keyPath) {
    console.log(key, keyPath);
  },
  methods:{
    housechange(){
      this.dialogVisible=false
      console.log(this.house)
      this.$axios.post('http://localhost:8090/hsaveOrMode',this.house).then(res=>res.data).then(res=> {
      })
      this.$router.replace('/MyHouse');
    },
    del(row){
      console.log(row.id)
      this.$axios.get('http://localhost:8090/hdelete?id='+row.id)
      this.$router.replace('/MyHouse');
    },
    mod(row){
      this.dialogVisible=true
     this.house=row
      console.log(this.house)
    },
    newt(){
      this.dialogVisible=true

    },
    getGoodsList(){
      this.$axios.post('http://localhost:8090/hlistI',{statue:1,information:this.searchData.information}).then(res=>res.data).then(res=>{
        console.log(res)
        this.tableData=res
      })

    },
    PerIfoBtn(){
      this.$router.replace('/self2');
    },
    loadPost(){
      this.$axios.post('http://localhost:8090/hlistn',{name:JSON.parse(sessionStorage.getItem('CurUser')).name}).then(res=>res.data).then(res=>{
        console.log("1234")
        console.log(res)
        this.tableData=res
      })
    }
  },
  beforeMount() {
    this.loadPost();
  }
}
</script>





<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
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