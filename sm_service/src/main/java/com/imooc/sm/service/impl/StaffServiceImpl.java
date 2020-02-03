package com.imooc.sm.service.impl;

import com.imooc.sm.dao.StaffDao;
import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("staffService")
public class StaffServiceImpl implements StaffService {
   @Autowired
   private StaffDao staffDao;

    public void add(Staff staff) {
        staff.setPassword("123456");
        staff.setWorkTime(new Date());
        staff.setStatus("正常");
        staffDao.insert(staff);
    }

    public void edit(Staff staff) {
        staffDao.update(staff);
    }

    public void delete(Integer id) {
        staffDao.delete(id);
    }

    public Staff selectById(Integer id) {
        return staffDao.selectById(id);
    }

    public List<Staff> getList() {
        return staffDao.selectAll();
    }
}
