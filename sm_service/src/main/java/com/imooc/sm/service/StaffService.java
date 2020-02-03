package com.imooc.sm.service;

import com.imooc.sm.entity.Staff;

import java.util.List;

public interface StaffService {

    public void add(  Staff staff);
    public void edit( Staff staff);
    public void delete(Integer id);
    public Staff selectById(Integer id);
    public List<Staff> getList();
}
