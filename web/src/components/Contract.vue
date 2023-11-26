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
          <el-menu-item index="Index2">主页</el-menu-item>
          <el-menu-item index="/Contract">合约中心</el-menu-item>
          <el-menu-item index="MyHouse">登记中心</el-menu-item>
          <el-menu-item index="self2" @click="PerIfoBtn"><a btarget="_blank">个人中心</a></el-menu-item>
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
      </el-header>
      <el-main>
        <el-table
            :data="tableData"
            border
            style="width: 100%">
          <el-table-column
              fixed
              prop="name"
              label="房东姓名"
              width="120">
          </el-table-column>
          <el-table-column
              prop="customer"
              label="租客姓名"
              width="120">
          </el-table-column>
          <el-table-column
              label="时间(月)"
              prop="time"
              width="120">
          </el-table-column>
          <el-table-column
              prop="money"
              label="租金"
              width="270">
          </el-table-column>
          <el-table-column
              prop="no"
              label="房屋编号"
              width="120">
          </el-table-column>
          <el-table-column
              prop="statue"
              label="出租状态"
              width="120">
          </el-table-column>
          <el-table-column
              label="操作"
              width="150">
            <template slot-scope="scope">
              <el-button type="success"  size="small" @click="yes(scope.row)">同意</el-button>
              <el-button type="danger"  size="small" @click="no(scope.row)">拒绝</el-button>
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
      dialogVisible: false,
      searchData:{
        information:""
      },
      tableData: [

      ],
      contract:{

      }
    }
  },
  handleSelect(key, keyPath) {
    console.log(key, keyPath);
  },
  methods:{
    yes(row){
      this.contract=row
      this.contract.statue="同意"
      this.$axios.post('http://localhost:8090/csaveOrMode',this.contract).then(res=>res.data).then(res=> {
      })
    },
    no(row){
      this.contract=row
      this.contract.statue="拒绝"
      this.$axios.post('http://localhost:8090/csaveOrMode',this.contract).then(res=>res.data).then(res=> {
      })
    },
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
    loadGet(){
      this.$axios.get('http://localhost:8090/hlist').then(res=>res.data).then(res=>{
        console.log(res)
        this.tableData=res
      })
    },
    loadPost(){
      this.$axios.post('http://localhost:8090/clistS',{name:"刘子阳"}).then(res=>res.data).then(res=>{
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