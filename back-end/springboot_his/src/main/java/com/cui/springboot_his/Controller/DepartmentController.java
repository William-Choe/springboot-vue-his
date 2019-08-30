package com.cui.springboot_his.Controller;

import com.cui.springboot_his.Dao.DepartmentDao;
import com.cui.springboot_his.Entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentDao departmentDao;

    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentDao.findOne(id);
    }

    /*查看部门列表*/
    @GetMapping("/department")
    public List<Department> departmentList() {
        return departmentDao.findAll();
    }

    /*添加一个部门*/
    @PostMapping("/department")
    public Department addDepartment(@RequestParam("name") String name) {
        Department department = new Department();
        department.setName(name);

        return departmentDao.save(department);
    }

    /*删除部门*/
    @DeleteMapping("/department/{id}")
    public void deleteDepartment(@PathVariable("id") Integer id) {
        departmentDao.delete(id);
    }
}