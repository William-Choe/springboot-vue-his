<template>
  <el-form :inline="true" :model="form" :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
    <el-form-item label="病历号" prop="medicalRecord">
      <el-input v-model="form.medicalRecord" placeholder="病历号" @change="getPatientInfo(form.medicalRecord)"></el-input>
    </el-form-item>

    <el-form-item label="姓名" prop="name">
      <el-input v-model="form.name" placeholder="姓名"></el-input>
    </el-form-item>

    <el-form-item label="性别" prop="sex">
      <el-select v-model="form.sex" placeholder="性别">
        <el-option label="男" value="男"></el-option>
        <el-option label="女" value="女"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="年龄" prop="age">
      <el-input v-model="form.age" placeholder="年龄"></el-input>
    </el-form-item>

    <el-form-item label="出生日期" prop="birth">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.birth" style="width: 100%;"
                          value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>
    </el-form-item>

    <el-form-item label="身份证号" prop="idNumber">
      <el-input v-model="form.idNumber" placeholder="身份证号"></el-input>
    </el-form-item>

    <el-form-item label="家庭住址">
      <el-input v-model="form.address" placeholder="家庭住址"></el-input>
    </el-form-item>

    <el-form-item label="看诊日期" prop="date">
          <el-date-picker type="date" placeholder="选择日期" v-model="form.date" style="width: 100%;"
                          value-format="yyyy-MM-dd" format="yyyy-MM-dd"></el-date-picker>
    </el-form-item>

    <el-form-item label="科室" prop="department">
      <el-select v-model="form.department" placeholder="科室" >
        <el-option v-for="(item, index) in departmentList" :value="item.id" :label="item.name" :key="index"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="号别" prop="registeredLevel">
      <el-select v-model="form.registeredLevel" placeholder="号别" @change="setMoney(form.registeredLevel)">
        <el-option v-for="(item, index) in registeredLevelList" :value="item.id" :label="item.level" :key="index"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="看诊医生" prop="doctor">
      <el-select v-model="form.doctor" placeholder="看诊医生">
        <el-option v-for="(item, index) in doctorList" :value="item.doctor_id" :label="item.doctor" :key="index"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="应收金额" prop="money">
      <el-input v-model="form.money" placeholder="应收金额"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('form')">挂号</el-button>
      <el-button @click="resetForm('form')">清空</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import axios from "axios";
  import qs from "qs";

  export default {
    data() {
      return {
        registeredLevelList: '',
        departmentList: '',
        doctorList: [],
        havePatient: false,
        form: {
          medicalRecord: '',
          name: '',
          sex: '',
          age: '',
          birth: '',
          idNumber: '',
          address: '',
          registeredLevel: '',
          date: '',
          department: '',
          doctor: '',
          money: ''
        },
        rules: {
          medicalRecord: [
            {required: true, message: '请输入病历号', trigger: 'blur'}
          ],
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' },
          ],
          sex: [
            { required: true, message: '请选择性别', trigger: 'change' }
          ],
          age: [
            { required: true, message: '请输入年龄', trigger: 'blur' },
          ],
          birth: [
            { type: 'string', required: true, message: '请选择您的出生日期', trigger: 'blur' }
          ],
          date: [
            { type: 'string', required: true, message: '请选择看诊日期', trigger: 'blur' }
          ],
          department: [
            { required: true, message: '请选择挂号科室', trigger: 'blur' }
          ],
          registeredLevel: [
            { required: true, message: '请选择号别', trigger: 'blur' }
          ],
          doctor: [
            { required: true, message: '请选择看诊医生', trigger: 'blur' }
          ]
        }
      };
    },
    created: function(){
      this.getData();
    },
    methods: {
      /*提交挂号*/
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            /*看是否是第一次挂号，两种挂号方式接口不同*/
            if (!this.havePatient) {
              axios.post('http://localhost:8088/registrate', qs.stringify({
                name: this.form.name,
                sex: this.form.sex,
                age: this.form.age,
                birth: this.form.birth,
                occupation: "无",
                idNumber: this.form.idNumber,
                department_id: this.form.department,
                registeredLevel_id: this.form.registeredLevel,
                doctor_id: this.form.doctor,
                phone: "无"
              })).then(response => {
                if (response.data.result === "success") {
                  this.$message({
                    message: '挂号成功！',
                    type: 'success'
                  });
                  this.$refs[formName].resetFields();
                }
              });
            } else {
              axios.post('http://localhost:8088/registrate-by-medical-record-id', qs.stringify({
                medicalRecord_id: this.form.medicalRecord,
                department_id: this.form.department,
                registeredLevel_id: this.form.registeredLevel,
                doctor_id: this.form.doctor,
              })).then(response => {
                if (response.data.result === "success") {
                  this.$message({
                    message: '挂号成功！',
                    type: 'success'
                  });
                  this.$refs[formName].resetFields();
                }
              });
            }
          } else {
            this.$message.error('请检查挂号信息是否正确！');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },

      /*获取可用病历号，挂号级别，科室*/
      getData(){
        axios.get('http://localhost:8088/registrate/find-medical-record-id').then(response => {
          this.form.medicalRecord = response.data.data;
        });
        axios.get('http://localhost:8088/registrate/get-registered-level').then(response => {
          this.registeredLevelList = response.data.data;
        });
        axios.get('http://localhost:8088/registrate/get-department').then(response => {
          this.departmentList = response.data.data;
        });
      },

      /*获取挂号级别后自动显示应缴费金额*/
      setMoney(levelId) {
        this.registeredLevelList.forEach(item => {
          if (item.id === levelId) {
            this.form.money = item.fee;
          }
        });
        this.$options.methods.getScheduleDoctor(this.form.department, this.form.registeredLevel,
          this.form.date, this.doctorList);
      },

      /*根据科室和挂号级别找出排班表中的医生*/
      getScheduleDoctor(department_id, registeredLevel, date, doctors) {
        axios.post('http://localhost:8088/registrate/find-schedule-doctor', qs.stringify({
          department_id: department_id,
          registeredLevel_id: registeredLevel
        })).then(response => {
          let temp = response.data.data;
          temp.forEach(item => {
            if (date === item.time) {
              doctors.push(item);
            }
          });
        });
      },

      /*若病历号改变，拉取此病历号的患者信息*/
      getPatientInfo(medicalRecord) {
        axios.post('http://localhost:8088/registrate/get-patient-info', qs.stringify({
          medicalRecord_id: medicalRecord
        })).then((response) => {
          this.form.sex = response.data.data.sex;
          this.form.age = response.data.data.age;
          this.form.birth = response.data.data.birth;
          this.form.idNumber = response.data.data.idNumber;
          this.form.address = response.data.data.address;
          this.form.name = response.data.data.name;

          this.havePatient = true;

          this.$message({
            message: '已获取病人信息！',
            type: 'success'
          });
          console.log(this.form);
        });
      }
    }
  }
</script>
