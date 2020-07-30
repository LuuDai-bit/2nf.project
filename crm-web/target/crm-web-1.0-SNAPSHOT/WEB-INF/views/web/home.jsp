<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url var="formURL" value="/user/list" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Người dùng</title>
</head>
<body>

<%--    <form:hidden path="/homepage"/>--%>
<div class="position-relative">

    <div id="login-box" class="login-box visible widget-box no-border">
        <div class="widget-body">
            <div class="widget-main">
                <h4 class="header blue lighter bigger">
                    <i class="ace-icon fa fa-coffee green"></i>

                </h4>
                <form:form id="userForm" method="GET" action="${formURL}" modelAttribute="users">
                <div class="container">
                    <h1>Người dùng</h1>

                    <div class="container-fluid" style="margin-bottom: 2em">
                        <div>
                            <div class="col-md-4">
                                <label>Tên</label>
                            </div>
                            <div class="col-md-4">
                                <label>Email</label>
                            </div>
                            <div class="col-md-4">
                                <label>Số diện thoại</label>
                            </div>
                        </div>
                        <div>
                            <div class="col-md-4">
                                <form:input path="name" id="searchName"
                                            cssClass="form-control input-sm"
                                            placeholder="Tên"/>
                            </div>
                            <div class="col-md-4">
                                <form:input path="email" id="searchEmail"
                                            cssClass="form-control input-sm"
                                            placeholder="Email"/>
                            </div>
                            <div class="col-md-4">
                                <form:input path="phone" id="searchPhone"
                                            cssClass="form-control input-sm"
                                            placeholder="Số điện thoại"/>
                            </div>
                        </div>
                        <div>
                            <button style="margin:1em 0 0 1em" id="searchUser" type="submit" class="btn btn-primary" >Tìm kiếm</button>
                        </div>
                    </div>

                    <div>
                        <button type="button" class="btn btn-primary" onclick="addUser()">
                            <i class="flaticon-plus"></i>
                            Thêm mới</button>
                        <button id="deleteUsers" type="button"
                                class="btn btn-danger" onclick="deleteUsers()" disabled>
                            <i class="fa fa-trash" aria-hidden="true"></i>
                            Xóa</button>
                        <div style="float:right; display:inline">
                            <select id="maxPageItems" onchange="updateMaxPageItems()">
                                <option>5</option>
                                <option>10</option>
                                <option>20</option>
                                <option>50</option>
                            </select>
                        </div>

                    </div>
                    <div>
<%--                        <table class="table table-bordered">--%>
<%--                            <thead>--%>
<%--                            <tr>--%>
<%--                                <th></th>--%>
<%--                                <th>Name</th>--%>
<%--                                <th>Email</th>--%>
<%--                                <th>Phone</th>--%>
<%--                                <th></th>--%>
<%--                            </tr>--%>
<%--                            </thead>--%>

<%--                            <tbody>--%>
<%--                                <c:forEach var="user" items="${users.listResult}">--%>
<%--                                    <tr>--%>
<%--                                        <td><input type="checkbox" id="${user.id}"--%>
<%--                                                   onclick="selectUser(${user.id}, this.checked)"--%>
<%--                                        ></td>--%>
<%--                                        <td>${user.name}</td>--%>
<%--                                        <td>${user.email}</td>--%>
<%--                                        <td>${user.phone}</td>--%>
<%--                                        <td><button id="editUser" onclick="editUser(${user.id})">Edit</button>--%>
<%--                                            <button id="deleteUser" onclick="deleteUser(${user.id})">Delete</button></td>--%>
<%--                                    </tr>--%>
<%--                                </c:forEach>--%>
<%--                            </tbody>--%>
<%--                        </table>--%>
                        <div class="table-responsive my-table">
                            <display:table name="users.listResult" cellspacing="0" cellpadding="0" requestURI="${formURL}"
                                           partialList="true" size="${users.totalItems}" id="tableList" pagesize="${users.maxPageItems}"
                                           export="false"
                                           class="table table-fcvace table-striped table-bordered table-hover dataTable no-footer "
                                           style="margin: 3em 0 1.5em;">
                                <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                headerClass="center select-cell">
                                    <fieldset>
                                        <input onclick="selectUser(${tableList.id}, this.checked)" type="checkbox" name="checkList" value="${tableList.id}" id="checkbox_${tableList.id}" class="check-box-element"/>
                                    </fieldset>
                                </display:column>
                                <display:column headerClass="text-left" property="name" title="Họ và tên"/>
                                <display:column headerClass="text-left" property="email" title="Email"/>
                                <display:column headerClass="text-left" property="phone" title="Số diện thoại"/>
                            </display:table>
                        </div>
                    </div>


                </div>
                </form:form>
                <div class="space-6"></div>


                <div class="space-6"></div>
            </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->
    </div><!-- /.login-box -->
</div><!-- /.position-relative -->

</body>
</html>