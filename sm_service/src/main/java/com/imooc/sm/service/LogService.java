package com.imooc.sm.service;


import com.imooc.sm.entity.Log;

import java.util.List;

public interface LogService {

    /**
     * 记录日志
     * @param log
     */
    void addSystemLog(Log log);
    void addLoginLog(Log log);
    void addOperateLog(Log log);

    /**
     * 获取日志
     * @return
     */
    List<Log> getSystemLog();
    List<Log> getLoginLog();
    List<Log> getOperateLog();
}
