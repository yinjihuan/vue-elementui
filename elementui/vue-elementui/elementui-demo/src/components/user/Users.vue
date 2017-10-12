<template>
  <div id="users">
    <el-form :inline="true" :model="formInline" class="demo-form-inline">

      <el-form-item label="城市">
        <el-cascader :options="options" v-model="selectedOptions"></el-cascader>
      </el-form-item>

      <el-form-item label="姓名">
        <el-input v-model="formInline.name" placeholder="姓名"></el-input>
      </el-form-item>
            
      <el-form-item label="地址">
        <el-select v-model="formInline.address" placeholder="地址">
          <el-option v-for="item in addressList" :key="item" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
            
      <el-form-item>
        <el-date-picker v-model="selectDate" type="date" placeholder="选择日期" :picker-options="pickerOptions0">
        </el-date-picker>
      </el-form-item>
            
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
        
    </el-form>
        
    <el-table :data="tableData" style="width: 100%" v-loading="loading2" element-loading-text="拼命加载中" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="name" label="姓名" width="180"></el-table-column> 
      <el-table-column prop="address" label="地址"></el-table-column> 
      <el-table-column prop="date" label="日期" width="180"></el-table-column>
      <el-table-column fixed="right" label="操作" width="140">
        <template scope="scope">
          <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          <el-button type="text" size="small"  @click="showEditDialog(scope.$index)">编辑</el-button>
          <el-button type="text" size="small"  @click="removeData(scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table> 
      
    <div class="block">
        <div class="r_btn">
          <el-button type="primary" @click="batchRemove">批量删除</el-button>
        </div>
        <div class="r_page">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="currentPage"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
    </div>
      
    <!-- Form -->
    <el-dialog title="编辑信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" auto-complete="off"></el-input>
        </el-form-item>
         <el-form-item label="地址" :label-width="formLabelWidth">
          <el-input v-model="form.address" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<style type="text/css" scoped>
  .block {
    margin-top: 20px;
    width: 100%;
  }
  .block .r_btn {
    float: left;
    width: 90px;
  }
  .block .r_page {
     float: right;
  }
</style>
<script type="text/javascript">
  import {getUserList} from '../../api'

  export default {
    data() {
      return {
        tableData: [],
        formInline: {
          name: '',
          address: '',
          id:''
        },
        currentPage:1,
        total:0,
        pageSize:10,
        addressList:["上海市普陀区金沙江路 1518 弄", "上海市普陀区金沙江路 1519 弄", "上海市普陀区金沙江路 1517 弄"],
        pickerOptions0: {
            disabledDate(time) {
              return time.getTime() < Date.now() - 8.64e7;
            }
        },
        selectDate:'',
        dialogFormVisible: false,
        formLabelWidth: '120px',
        form: {
            name: '',
            address: '',
            id:''
        },
        loading2: false,
        options: [{
          value: '湖南',
          label: '湖南',
          children: [{
            value: '长沙',
            label: '长沙'
          },
          {
            value: '株洲',
            label: '株洲'
          },
          {
            value: '湘潭',
            label: '湘潭'
          }]
        }],
        selectedOptions: [],
        multipleSelection: []
      }
    },
    mounted() {
      this.$store.dispatch('showHeader');
      this.loadData();
    },
    methods: {
      //加载数据
      loadData() {
        this.loading2 = true;
        var params = {
          page: this.currentPage, 
          pageSize: this.pageSize, 
          name: this.formInline.name, 
          address: this.formInline.address
        };
        getUserList(params).then(function(result){
          this.tableData = result.data.list;
          this.total = result.data.total;
          this.loading2 = false;
        }.bind(this)).catch(function (error) {
            this.loading2 = false;
            console.log(error);
        }.bind(this));
      },
      //查询
      onSubmit() {
        console.log(this.selectedOptions[1]);
        this.loadData();
      },
      //改变分页大小
      handleSizeChange(val) {
        this.pageSize = val;
      },
      //跳转页数
      handleCurrentChange(val) {
        this.currentPage = val;
        this.loadData();
      },
      //打开编辑窗口
      showEditDialog(row){
        var data = this.tableData[row];
        this.form.id = data.id;
        this.form.name = data.name;
        this.form.address = data.address;
        this.dialogFormVisible = true;
      },
      update(){
        if (this.form.name == "") {
          this.$message.error('姓名不能为空');
          return;
        }
        this.$message({
               message: '修改成功',
               type: 'success'
        });
        this.dialogFormVisible = false;
      },
      removeData(row) {
        var data = this.tableData[row];
        this.$confirm('确定要删除"'+data.name+'"?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      batchRemove() {
        this.multipleSelection.forEach(row => {
           console.log(row.id);
        });
       
      }
    }
  }
</script>