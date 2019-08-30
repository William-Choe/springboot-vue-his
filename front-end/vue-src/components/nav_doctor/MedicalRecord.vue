<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-tag style="width: 150px">患者选择:</el-tag>
        <el-button icon="el-icon-refresh" style="float: right" circle @click="refreshPatient"></el-button>
      </el-row>
      <el-row :gutter="20">
        <el-tag style="width: 100%">来诊患者</el-tag>
      </el-row>
      <el-row :gutter="20">
        <el-table :data="patientNotDiagnose" highlight-current-row width="100%" row-style="height:40px"
                  cell-style="padding:0" max-height="250px" @current-change="handleCurrentChangeNotD"
                  v-loading="loading">
          <el-table-column prop="form_id" label="挂号单号" width="143px"></el-table-column>
          <el-table-column prop="name" label="患者姓名" width="143px"></el-table-column>
        </el-table>
      </el-row>
      <el-row :gutter="20" style="margin-top: 20px">
        <el-tag style="width: 100%">已诊患者</el-tag>
      </el-row>
      <el-row :gutter="20">
        <el-table :data="patientDiagnosed" highlight-current-row width="100%" row-style="height:40px"
                  cell-style="padding:0" max-height="250px" @current-change="handleCurrentChangeD" v-loading="loading">
          <el-table-column prop="form_id" label="挂号单号" width="143px"></el-table-column>
          <el-table-column prop="name" label="患者姓名" width="143px"></el-table-column>
        </el-table>
      </el-row>
    </el-col>
    <el-col :span="17" style="margin-left: 30px">
      <el-row :gutter="20" style="margin-bottom: 10px">
        <el-tag style="width: 330px">
          患者姓名：{{patientMedicalRecord.name}}&nbsp&nbsp&nbsp&nbsp病历号：{{patientMedicalRecord.medicalRecord_id}}&nbsp&nbsp&nbsp&nbsp
          年龄：{{patientMedicalRecord.age}}&nbsp&nbsp&nbsp&nbsp性别：{{patientMedicalRecord.sex}}
        </el-tag>
        <el-button type="primary" style="float: right" @click="diagnose" v-if="!patientMedicalRecord.isDiagnosed">提交诊断
        </el-button>
      </el-row>
      <el-tabs type="border-card">
        <el-tab-pane label="病历首页">
          <el-row :gutter="20">
            <el-tag>病史内容:</el-tag>
            <el-form label-width="100px" style="margin-top: 15px">
              <el-form-item label="主诉">
                <el-input type="textarea" v-model="patientMedicalRecord.zs"></el-input>
              </el-form-item>
              <el-form-item label="现病史">
                <el-input type="textarea" v-model="patientMedicalRecord.xbs"></el-input>
              </el-form-item>
              <el-form-item label="现病治疗情况">
                <el-input type="textarea" v-model="patientMedicalRecord.xbzlqk"></el-input>
              </el-form-item>
              <el-form-item label="既往史">
                <el-input type="textarea" v-model="patientMedicalRecord.jws"></el-input>
              </el-form-item>
              <el-form-item label="过敏史">
                <el-input type="textarea" v-model="patientMedicalRecord.gms"></el-input>
              </el-form-item>
              <el-form-item label="体格检查">
                <el-input type="textarea" v-model="patientMedicalRecord.tgjc"></el-input>
              </el-form-item>
            </el-form>
            <el-tag>评估/判断:</el-tag>
            <el-form ref="form" :model="form" label-width="100px" style="margin-top: 15px">
              <el-form-item label="初步诊断">
                <el-input type="textarea" v-model="patientMedicalRecord.cbzd"></el-input>
              </el-form-item>
            </el-form>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="检查申请">检查申请</el-tab-pane>
        <el-tab-pane label="检验申请">检验申请</el-tab-pane>
        <el-tab-pane label="门诊确诊">门诊确诊</el-tab-pane>
        <el-tab-pane label="处置申请">处置申请</el-tab-pane>
        <el-tab-pane label="成药处方">
          <el-row :gutter="20">
            <el-row>
              <el-tag>门诊诊断:</el-tag>
              <el-tag type="info" v-if="this.patientMedicalRecord.cbzd!=null">{{this.patientMedicalRecord.cbzd}}</el-tag>
              <el-button type="primary" style="float:right;margin-left:10px;margin-right:10px" size="small"
                         @click="multiDelete">删药
              </el-button>
              <el-button type="primary" style="float:right;margin-left:10px" size="small"
                         @click="dialogFormVisible = true">增药
              </el-button>
              <!--点击增药按钮，弹出对话框-->
              <el-dialog title="添加药品" :visible.sync="dialogFormVisible" width="400px">
                <el-form :model="drugForm">
                  <el-form-item label="药品名称：" :label-width="formLabelWidth">
                    <el-select v-model="drugForm.drug_id" placeholder="请选择药品">
                      <el-option v-for="(item, index) in medicineList" :value="item.drug_id" :label="item.name"
                                 :key="index"></el-option>
                    </el-select>
                  </el-form-item>
                  <el-form-item label="药品数量：" :label-width="formLabelWidth">
                    <el-input v-model="drugForm.number" style="width: 50px"></el-input>
                  </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                  <el-button @click="dialogFormVisible = false">取 消</el-button>
                  <el-button type="primary" @click="drugFormClick">确 定</el-button>
                </div>
              </el-dialog>
            </el-row>
            <el-row>
              <el-table ref="multipleTable" :data="prescriptionList" tooltip-effect="dark" style="width: 100%"
                        @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55"></el-table-column>
                <el-table-column prop="name" label="药品名称" width="200px"></el-table-column>
                <el-table-column prop="unit" label="规格" width="180px"></el-table-column>
                <el-table-column prop="price" label="单价" width="150px"></el-table-column>
                <el-table-column prop="number" label="数量" width="150px"></el-table-column>
              </el-table>
            </el-row>
            <el-row style="margin-top: 20px">
              <el-tag style="width: 110px">本处方金额合计：</el-tag>
              <el-tag style="width: 70px" type="warning">{{this.totalPrice}}元</el-tag>
              <el-button type="primary" style="float: right;margin-right: 20px" @click="prescribe" size="medium">提交处方
              </el-button>
            </el-row>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="草药处方">草药处方</el-tab-pane>
        <el-tab-pane label="费用查询">费用查询</el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</template>
<script>
  import axios from "axios";
  import qs from "qs";

  export default {
    data() {
      return {
        filter: {
          doctor_id: ''
        },
        patientNotDiagnose: [],
        patientDiagnosed: [],
        currentRow: null,
        patientMedicalRecord: {
          registrationForm_id: '',
          medicalRecord_id: '',
          patient_id: '',
          name: '',
          sex: '',
          age: '',
          zs: '',
          xbs: '',
          xbzlqk: '',
          jws: '',
          gms: '',
          tgjc: '',
          cbzd: '无',
          isDiagnosed: true
        },
        loading: false,
        medicineList: [],
        prescriptionList: [],
        drugForm: {
          drug_id: '',
          name: '',
          unit: '',
          price: '',
          number: 0
        },
        dialogFormVisible: false,
        formLabelWidth: '100px',
        drugMultipleSelection: [],
        totalPrice: 0
      }
    },
    created() {
      /*页面创建时获取未诊、待诊患者数据，获取药品信息*/
      this.getDoctorId();
      this.findPatientNotDiagnose();
      this.findPatientDiagnosed();
      this.getMedicineInfo();
      this.refreshPatient();
    },
    methods: {
      getDoctorId() {
        axios.post('http://localhost:8088/login/get-doctor-id-by-user', qs.stringify({
          user_id: sessionStorage.getItem("user_id")
        })).then((response) => {
          this.filter.doctor_id = response.data.data;
        });
      },
      /*获取来诊患者数据*/
      findPatientNotDiagnose() {
        axios.post('http://localhost:8088/medical-record/find-patient-not-diagnose', qs.stringify({
          doctor_id: this.filter.doctor_id
        })).then((response) => {
          this.patientNotDiagnose = response.data.data;
        });
      },

      /*获取已诊患者数据*/
      findPatientDiagnosed() {
        axios.post('http://localhost:8088/medical-record/find-patient-diagnosed', qs.stringify({
          doctor_id: this.filter.doctor_id
        })).then((response) => {
          this.patientDiagnosed = response.data.data;
        });
      },

      /*刷新患者数据*/
      refreshPatient() {
        this.loading = true;
        setTimeout(() => {
          this.findPatientNotDiagnose();
          this.findPatientDiagnosed();
          this.loading = false;
          this.$message({
            message: '刷新成功！',
            type: 'success'
          });
        }, 1500);
      },

      /*获取未诊患者表点击“行”数据*/
      handleCurrentChangeNotD(val) {
        this.currentRow = val;
        this.patientMedicalRecord.isDiagnosed = false;
        this.patientMedicalRecord.registrationForm_id = this.currentRow.form_id;
        this.findPatientMedicalRecord();
      },

      /*获取已诊患者表点击“行”数据*/
      handleCurrentChangeD(val) {
        this.currentRow = val;
        this.patientMedicalRecord.isDiagnosed = true;
        this.findPatientMedicalRecord();
      },

      /*获取病人病历单信息*/
      findPatientMedicalRecord() {
        axios.post('http://localhost:8088/medical-record/find-patient-record', qs.stringify({
          patient_id: this.currentRow.patient_id
        })).then((response) => {
          this.patientMedicalRecord.medicalRecord_id = response.data.data.medicalRecord_id;
          this.patientMedicalRecord.patient_id = response.data.data.patient_id;
          this.patientMedicalRecord.name = response.data.data.name;
          this.patientMedicalRecord.sex = response.data.data.sex;
          this.patientMedicalRecord.age = response.data.data.age;
          this.patientMedicalRecord.zs = response.data.data.zs;
          this.patientMedicalRecord.xbs = response.data.data.xbs;
          this.patientMedicalRecord.xbzlqk = response.data.data.xbzlqk;
          this.patientMedicalRecord.jws = response.data.data.jws;
          this.patientMedicalRecord.gms = response.data.data.gms;
          this.patientMedicalRecord.tgjc = response.data.data.tgjc;
          this.patientMedicalRecord.cbzd = response.data.data.cbzd;
        });
      },

      /*诊断*/
      diagnose() {
        this.$confirm('请确认是否提交诊断信息', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.post('http://localhost:8088/medical-record/diagnose', qs.stringify({
            registrationForm_id: this.patientMedicalRecord.registrationForm_id,
            zs: this.patientMedicalRecord.zs,
            xbs: this.patientMedicalRecord.xbs,
            xbzlqk: this.patientMedicalRecord.xbzlqk,
            jws: this.patientMedicalRecord.jws,
            gms: this.patientMedicalRecord.gms,
            tgjc: this.patientMedicalRecord.tgjc,
            cbzd: this.patientMedicalRecord.cbzd
          })).then((response) => {
            if (response.data.result === "success") {
              this.$message({
                message: '诊断成功！',
                type: 'success'
              });
              this.refreshPatient();
              this.$refs[this.data.patientMedicalRecord].resetFields();
            }
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消提交诊断信息'
          });
        });
      },

      /*获取药品信息*/
      getMedicineInfo() {
        axios.get('http://localhost:8088/prescription/get-medicine-info')
          .then((response) => {
            this.medicineList = response.data.data;
          });
      },

      /*点击药品添加表单确认事件*/
      drugFormClick() {
        this.dialogFormVisible = false;
        this.medicineList.forEach(item => {
          if (this.drugForm.drug_id === item.drug_id) {
            this.drugForm.name = item.name;
            this.drugForm.price = item.price;
            this.drugForm.unit = item.unit;
            this.totalPrice = parseFloat(this.totalPrice) + (parseFloat(item.price) * this.drugForm.number).toFixed(2);
          }
        });
        this.prescriptionList.push(this.drugForm);
        this.drugForm = {
          drug_id: '',
          name: '',
          unit: '',
          price: '',
          number: ''
        };
        this.$message({
          message: '添加成功！',
          type: 'success'
        });
      },

      /*药品表多选*/
      handleSelectionChange(val) {
        this.drugMultipleSelection = val;
      },

      /*药品表多项删除*/
      multiDelete() {
        let newArr = [];
        this.totalPrice = 0;

        //遍历处方列表和待删除多选列表，若处方列表中的对象在删除多选列表中，
        //设置flag值为true，只有当flag为false时，处方列表对象添加至新的厨房里表中
        this.prescriptionList.forEach(item => {
          let flag = false;
          this.drugMultipleSelection.forEach(delItem => {
            if (item.drug_id === delItem.drug_id) {
              flag = true;
            }
          });
          if (flag === false) {
            newArr.push(item);
            this.totalPrice += (parseFloat(item.price) * parseInt(item.number)).toFixed(2);
          }
          flag = false;
        });
        this.prescriptionList = newArr;
        this.$message.error("删除成功！")
      },

      /*开处方*/
      prescribe() {
        let drugStr = "";
        let tempPrescribeName = this.patientMedicalRecord.cbzd + "-成药处方";
        this.prescriptionList.forEach(item => {
          drugStr += item.drug_id + "-" + item.number + ",";
        });
        this.$confirm('请确认是否提交处方信息', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.post('http://localhost:8088/prescription/prescribe', qs.stringify({
            medicalRecord_id: this.patientMedicalRecord.medicalRecord_id,
            prescribe_name: tempPrescribeName,
            drug_str: drugStr
          })).then(() => {
            this.$message({
              message: '提交成功！',
              type: 'success'
            });
          });
        });
      }
    }
  };
</script>
