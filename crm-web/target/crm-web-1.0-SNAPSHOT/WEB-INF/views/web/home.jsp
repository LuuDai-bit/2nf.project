<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Người dùng</title>
</head>
<body>
<div class="position-relative">
    <div id="login-box" class="login-box visible widget-box no-border">
        <div class="widget-body">
            <div class="widget-main">
                <h4 class="header blue lighter bigger">
                    <i class="ace-icon fa fa-coffee green"></i>

                </h4>
                <div class="container">
                    <h1>Người dùng</h1>
                    <div>
                        <button type="button" class="btn btn-primary" onclick="addUser()">Thêm mới</button>
                        <button id="deleteUsers" type="button"
                                class="btn btn-danger" onclick="deleteUsers()" disabled>Xóa</button>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th></th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th></th>
                        </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="user" items="${users}">
                                <tr>
                                    <td><input type="checkbox" id="${user.id}"
                                               onclick="selectUser(${user.id}, this.checked)"
                                    ></td>
                                    <td>${user.name}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phone}</td>
                                    <td><button id="editUser" onclick="editUser(${user.id})">Edit</button>
                                        <button id="deleteUser" onclick="deleteUser(${user.id})">Delete</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

<%--                    <div class="table-responsive">--%>
<%--                        <display:table name="model.listResult" cellspacing="0" cellpadding="0" requestURI="$formUrl"--%>
<%--                                  partialList="true" size="${model.totallItems}" id="tableList" pagesize="${model.maxPageItems}"--%>
<%--                                  export="false"--%>
<%--                                       class="table table-fcvace table-striped table-bordered table-hover dataTable no-footer"--%>
<%--                                       style="margin: 3em 0 1.5em;">--%>
<%--                        <display:column headerClass="text-left" property="name" title="Họ và tên"/>--%>
<%--                        <display:column headerClass="text-left" property="email" title="Email"/>--%>
<%--                        <display:column headerClass="text-left" property="phone" title="Số diện thoại"/>--%>
<%--                        </display:table>--%>
<%--                    </div>--%>
                </div>
                <div class="space-6"></div>


                <div class="space-6"></div>
            </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->
    </div><!-- /.login-box -->
</div><!-- /.position-relative -->
</body>
</html>