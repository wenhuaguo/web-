<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2017/1/13
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <shiro:hasRole name="role_admin">
                <li class="header">设置模块</li>
                <li class="tree-view">
                    <a href="#">
                        <i class="fa fa-dashboard"></i> <span>Dashboard</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                </li>
                <ul class="treeview-menu ">
                    <li class="${param.menu == 'sys_accounts'? 'active':''}"><a href="/user"><i class="fa fa-circle-o"></i>用户管理</a></li>
                    <li class="${param.menu == 'sys_device'?'active':''}"><a href="/setting/device"><i class="fa fa-circle-o"></i>设备管理</a></li>
                </ul>
                <li class="treeview ${fn:startsWith(param.menu,'sys_') ? 'active':''}">
                    <a href="#">
                        <i class="fa fa-gears"></i> <span>系统设置</span> <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu ">
                        <li class="${param.menu == 'sys_accounts'? 'active':''}"><a href="/user"><i class="fa fa-circle-o"></i>用户管理</a></li>
                        <li class="${param.menu == 'sys_device'?'active':''}"><a href="/setting/device"><i class="fa fa-circle-o"></i>设备管理</a></li>
                    </ul>
                </li>
            </shiro:hasRole>
           <li class="header">业务模块</li>
            <shiro:hasRole name="role_finance">
                <li class="treeview  ${fn:startsWith(param.menu,'business_finance_') ? 'active':''}">
                    <a href="#">
                        <i class="fa fa-pie-chart"></i>
                        <span>财务报表</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li class="${param.menu == 'business_finance_dayReport' ? 'active': ''}"><a href="/finance/report/day"><i class="fa fa-circle-o"></i> 日报</a></li>
                        <li class="${param.menu == 'business_finance_monthReport' ? 'active': ''}"><a href="/finance/report/month"><i class="fa fa-circle-o"></i>月报</a></li>
                        <li class="${param.menu == 'business_finance_yearReport' ? 'active': ''}"><a href="/finance/report/year"><i class="fa fa-circle-o"></i> 年报</a></li>
                    </ul>
                </li>
            </shiro:hasRole>
            <shiro:hasRole name="role_market">
                <li class="treeview ${fn:startsWith(param.menu,"business_" ) ? 'active':''}">
                    <a href="#">
                        <i class="fa fa-edit"></i> <span>业务</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu ">
                        <li class="${fn:startsWith(param.menu,'business_deviceRent_')? 'active':''}"><a href="#"><i class="fa fa-circle-o"></i>设备租赁
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                            <ul class="treeview-menu">
                                <li class="${param.menu == 'business_deviceRent_out'? 'active':''}"><a href="/business/device/assembly"><i class="fa fa-circle-o"></i> 业务流水</a></li>
                                <li class="${param.menu == 'business_deviceRent_add'? 'active':''}"><a href="/business/device/addRentDevice"><i class="fa fa-plus"></i>新增流水</a></li>
                            </ul>
                        </li>
                        <li class="${fn:startsWith(param.menu,'business_deviceManager_') ? 'active':''}"><a href="#"><i class="fa fa-circle-o"></i>设备管理
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                            <ul class="treeview-menu">
                                <li class="${param.menu == 'business_deviceManager_inventory'? 'active':''}"><a href="/business/device/inventory"><i class="fa fa-circle-o"></i> 设备库存</a></li>
                                <li class="${param.menu == 'business_deviceManager_new'? 'active':''}"> <a href="/setting/device/new"><i class="fa fa-plus"></i> 新增设备 </a></li>
                            </ul>
                        </li>
                        <li class="${fn:startsWith(param.menu,'business_labour_')? 'active':''}"><a href="/user"><i class="fa fa-circle-o"></i>劳务外包
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                            <ul class="treeview-menu">
                                <li class="${param.menu == 'business_labour_business'?'active':''}"><a href="/labour/businessAssembly"><i class="fa fa-circle-o"></i> 业务流水</a></li>
                                <li class="${param.menu == 'business_labour_add'?'active' : ''}">
                                    <a href="/labour/addAssembly"><i class="fa fa-plus"></i> 新增流水 <i class="fa fa-angle-left pull-right"></i></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </shiro:hasRole>
            <li class="treeview ${param.menu == 'networkDisk'? 'active' : ''}">
                <a href="/pan/list">
                    <i class="fa fa-folder"></i>
                    <span>公司网盘</span>
                </a>

            </li>
            <li class="header">用户模块</li>
            <li class="treeview">
                <a href="/loginout">
                    <i class="fa fa-gears"></i> <span>安全退出</span> <i class="fa fa-angle-left pull-right"></i>
                </a>
            </li>
        </ul>

    </section>
    <!-- /.sidebar -->
</aside>
