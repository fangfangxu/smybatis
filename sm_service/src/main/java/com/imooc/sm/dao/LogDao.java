package com.imooc.sm.dao;

import com.imooc.sm.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("logDao")
public interface LogDao {
    void insert(Log log);

    /**
     * 系统日志/登录日志/业务日志
     * @param type
     * @return
     */
    List<Log> selectByType(String type);

}
