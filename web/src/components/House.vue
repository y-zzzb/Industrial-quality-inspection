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
          <el-menu-item index="House">房源中心</el-menu-item>
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
            <el-select v-model="value" @change="$forceUpdate()" filterable placeholder="请选择">
              <el-option-group
                  v-for="group in options"
                  :key="group.label"
                  :label="group.label">
                <el-option
                    v-for="item in group.options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-option-group>
            </el-select>
          <el-button @click="newt">筛选</el-button>
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
              width="300">
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
              width="120">
            <template slot-scope="scope">
              <el-button type="text" @click="dialogVisible = true">查看</el-button>
              <el-dialog
                  title="提示"
                  :visible.sync="dialogVisible"
                  :modal-append-to-body="false"
                  width="30%">
                <span>您是否确定预定这套</span>
                <span slot="footer" class="dialog-footer">
    <el-button type="primary" @click="dialogVisible = false">返回</el-button>
                  <el-button type="primary" @click="bookhouse(scope.row)">预定房屋</el-button>
  </span>
              </el-dialog>
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
      ]
    }
  },
  handleSelect(key, keyPath) {
    console.log(key, keyPath);
  },
  methods:{
    newt(){
      console.log(this.value)
      this.$axios.post('http://localhost:8090/hlistC',{province:this.value}).then(res=>res.data).then(res=> {
        console.log(this.tableData)
        this.tableData=res
      })
    },
    bookhouse(row){

      this.dialogVisible=false;
      this.tableData=row;
      this.tableData.statue=0;
      console.log(row)
      this.$axios.post('http://localhost:8090/hsaveOrMode',this.tableData).then(res=>res.data).then(res=> {
        console.log(this.tableData)
      })
      this.$axios.post('http://localhost:8090/hlistS',{statue:1}).then(res=>res.data).then(res=>{
        console.log("1234")
        console.log(res)
        this.tableData=res
      })
    },
    getGoodsList(){
      this.$axios.post('http://localhost:8090/hlistI',{statue:1,information:this.searchData.information}).then(res=>res.data).then(res=>{
        console.log(res)
        this.tableData=res
      })

    },
    PerIfoBtn(){
      this.$router.replace('/self');
    },
    loadGet(){
      this.$axios.get('http://localhost:8090/hlist').then(res=>res.data).then(res=>{
        console.log(res)
        this.tableData=res
      })
    },
    loadPost(){
      this.$axios.post('http://localhost:8090/hlistS',{statue:1}).then(res=>res.data).then(res=>{
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