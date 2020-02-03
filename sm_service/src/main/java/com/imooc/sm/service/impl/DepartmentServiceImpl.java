package com.imooc.sm.service.impl;

import com.imooc.sm.dao.DepartmentDao;
import com.imooc.sm.entity.Department;
import com.imooc.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;
    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void delete(Integer id) {
        departmentDao.delete(id);
    }

    public Department selectById(Integer id) {
        return departmentDao.selectById(id);
    }

    public List<Department> getList() {
        return departmentDao.selectAll();
    }
}
