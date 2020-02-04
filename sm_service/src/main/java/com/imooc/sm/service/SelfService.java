package com.imooc.sm.service;

import com.imooc.sm.entity.Staff;

public interface SelfService {
    /**
     * 登陆
     * @param account
     * @param password
     */
    public Staff login(String account, String password);

    /**
     * 忘记密码
     * @param id   员工id
     * @param password   新密码
     */
    public void changePassword(Integer id,String password);

}
