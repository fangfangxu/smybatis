package com.imooc.sm.service;

import com.imooc.sm.entity.Department;

import java.util.List;

public interface DepartmentService {

   public void add(Department department);
   public void edit(Department department);
   public void delete(Integer id);
   public Department selectById(Integer id);
   public List<Department> getList();
}
