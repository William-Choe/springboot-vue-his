<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item label="挂号单信息查询">
          <el-input v-model="filters.registrationFormId" placeholder="请输入挂号单号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getPatient()">查询</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <template>
      <el-table :data="data" highlight-current-row v-loading="loading" style="width: 100%;">
        <el-table-column prop="registrationFormId" label="挂号单号" width="150"></el-table-column>

        <el-table-column prop="name" label="姓名"  width="150" sortable></el-table-column>

        <el-table-column prop="idNumber" label="身份证号"  width="150" sortable></el-table-column>

        <el-table-column prop="date" label="挂号日期"  width="180" sortable></el-table-column>

        <el-table-column prop="department" label="看诊科室"  width="150" sortable></el-table-column>

        <el-table-column prop="status" label="看诊状态"  min-width="50" sortable></el-table-column>

        <el-table-column min-width="50" >
          <el-button type="primary" v-if="patient.status === '未就诊'" @click="backRegistrate">退号</el-button>
        </el-table-column>
      </el-table>
    </template>

  </section>
</template>
<script>
  import axios from "axios";
  import qs from "qs";
  export default {
    data() {
      return {
        filters: {
          registrationFormId: ''
        },
        loading: false,
        data: [],
        patient: {
          registrationFormId: '',
          name: '',
          idNumber: '',
          date: '',
          department: '',
          status: ''
        }
      }
    },
    methods: {
      //获取用户列表
      getPatient() {
        this.loading = true;
        this.data = [];

        axios.post('http://localhost:8088/back-registrate/find-registration-form', qs.stringify({
          registrationForm_id: this.filters.registrationFormId
        })).then((response) => {
          if (response.data.result === "success") {
            setTimeout(() => {
              this.patient.registrationFormId = response.data.data.registrationForm_id;
              this.patient.name = response.data.data.patient_name;
              this.patient.idNumber = response.data.data.patient_idNumber;
              this.patient.date = response.data.data.time;
              this.patient.department = response.data.data.department;
              this.patient.status = response.data.data.status;
              this.data.push(this.patient);
              this.loading = false;
              this.$message({
                message: '已获取挂号单信息！',
                type: 'success'
              });
            }, 1500);
          } else {
            setTimeout(() => {
              this.loading = false;
              this.$message.error("无此挂号单信息！")
            }, 1500);
          }
        });
      },
      backRegistrate() {
        axios.post('http://localhost:8088/back-registrate', qs.stringify({
          registrationForm_id: this.patient.registrationFormId
        })).then((response) => {
          console.log(response.data);
          this.$message({
            message: '退号成功！',
            type: 'success'
          });
          this.data = [];
        })
      }
    }
  };
</script>
