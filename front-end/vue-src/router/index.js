import Vue from 'vue'
import Router from 'vue-router'
import Home_admin from "../components/common/admin/Home-admin";
import Home_doctor from "../components/common/doctor/Home-doctor";
import Home_operator from "../components/common/operator/Home-operator";
import Login from "../components/page/Login";
import Registration from "../components/nav_registration/Registration";
import Back_registration from "../components/nav_registration/Back_registration";
import Pay from "../components/nav_registration/Pay";
import Refund from "../components/nav_registration/Refund";
import MedicalRecord from "../components/nav_doctor/MedicalRecord";
import DispenseDrug from "../components/nav_pharmacy/DispenseDrug";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/login',
      component: Login
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/home-admin',
      component: Home_admin,
      children: [
        {
          path: '/home-admin/registration',
          component: Registration
        },
        {
          path: '/home-admin/back_registration',
          component: Back_registration
        },
        {
          path: '/home-admin/pay',
          component: Pay
        },
        {
          path: '/home-admin/refund',
          component: Refund
        },
        {
          path: '/home-admin/medicalRecord',
          component: MedicalRecord
        },
        {
          path: '/home-admin/dispenseDrug',
          component: DispenseDrug
        }
      ]
    },
    {
      path: '/home-doctor',
      component: Home_doctor,
      children: [
        {
          path: '/home-doctor/medicalRecord',
          component: MedicalRecord
        },
        {
          path: '/home-doctor/dispenseDrug',
          component: DispenseDrug
        }
      ]
    },
    {
      path: '/home-operator',
      component: Home_operator,
      children: [
        {
          path: '/home-operator/registration',
          component: Registration
        },
        {
          path: '/home-operator/back_registration',
          component: Back_registration
        },
        {
          path: '/home-operator/pay',
          component: Pay
        },
        {
          path: '/home-operator/refund',
          component: Refund
        }
      ]
    }
  ]
})
