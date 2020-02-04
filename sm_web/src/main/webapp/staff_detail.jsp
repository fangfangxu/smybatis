<%--
  Created by IntelliJ IDEA.
  User: 47284
  Date: 2020/2/4
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>员工信息</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/thems.css">
    <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //自适应屏幕宽度
            window.onresize=function(){ location=location };

            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');

            var main_w = $(window).width();
            $('.xjhy').css('width',main_w-40+'px');

        });
    </script>
</head>

<body onLoad="Resize();">
<div id="right_ctn">
    <div class="right_m">
        <div class="hy_list">
            <div class="box_t">
                <span class="name">员工信息</span>
            </div>
            <div class="space_hx">&nbsp;</div>
            <div class="xjhy">
                <!--高级配置-->
                <ul class="hypz gjpz clearfix">
                    <li class="clearfix">
                        <span class="title">账户名：</span>
                        <div class="li_r">${STAFF.account}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">状态：</span>
                        <div class="li_r">${STAFF.status}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">部门：</span>
                        <div class="li_r">${STAFF.department.name}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">姓名：</span>
                        <div class="li_r">${STAFF.name}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">性别：</span>
                        <div class="li_r">${STAFF.sex}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">身份证号：</span>
                        <div class="li_r">${STAFF.idNumber}</div>
                    </li>
                    <li class="clearfix">
                        <span class="title">出生日期：</span>
                        <div class="li_r">
                            <fmt:formatDate value="${STAFF.bornDate}" pattern="yyyy-MM-dd"/>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="title">入职时间：</span>
                        <div class="li_r">
                            <fmt:formatDate value="${STAFF.workTime}" pattern="yyyy-MM-dd hh:mm"/>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="title">离职时间：</span>
                        <div class="li_r">
                            <fmt:formatDate value="${STAFF.leaveTime}" pattern="yyyy-MM-dd hh:mm"/>
                        </div>
                    </li>
                    <li class="clearfix">
                        <span class="title">备注：</span>
                        <div class="li_r">${STAFF.info}</div>
                    </li>
                    <li class="tj_btn">
                        <a href="javascript:history.go(-1);" class="back">返回</a>
                    </li>
                </ul>
                <!--高级配置-->
            </div>
        </div>
    </div>
</div>
</body>
</html>
