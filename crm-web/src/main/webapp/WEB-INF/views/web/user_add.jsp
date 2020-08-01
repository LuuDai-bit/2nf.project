<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>
<%--<c:url var="formURL" value="/addUser" />--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thêm/chỉnh sửa người dùng</title>
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
                    <h1>Thêm/chỉnh sửa người dùng</h1>
                </div>
                <div class="space-6"></div>

<%--                <form:form id="addUserForm" commandName="users">--%>
                <div class="container">
                    <div class="form-group">
                        <label for="name">Tên người dùng:</label>
<%--                        <form:input path="name" id="name"--%>
<%--                                    cssClass="form-control input-sm"--%>
<%--                                    placeholder="Tên"/>--%>
                        <input type="text" id="name" class="form-control input-sm" placeholder="Tên"/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
<%--                        <form:input path="email" id="email"--%>
<%--                                    cssClass="form-control input-sm"--%>
<%--                                    placeholder="Email"/>--%>

                        <input type="text" id="email" class="form-control input-sm" placeholder="Email"/>
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Số điện thoại:</label>
<%--                        <form:input path="phone" id="phoneNumber"--%>
<%--                                    cssClass="form-control input-sm"--%>
<%--                                    placeholder="Số điện thoại"/>--%>
                        <input type="text" id="phoneNumber" class="form-control input-sm" placeholder="Số điện thoại"/>
                    </div>
                    <div>
                        <label for="role">Vai trò</label>
<%--                        <select class="form-control form-control-sm"--%>
<%--                                data-textxml="${roles}" id="role">--%>
<%--                            <c:forEach var="role" items="${roles}">--%>
<%--                                <option value=${role.id}>${role.code}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </select>--%>
                        <select id="role">
                            <c:forEach items="${roles}" var="role">
                                <option value="${role.id}" label="${role.code}"></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div style="margin-top: 1em" class="form-group">
                        <form method = "POST" enctype="multipart/form-data" id="fileUploadForm">
                            <input id="imgfile" type="file" name="files" accept="image/png, image/jpeg" multiple/><br/><br/>
                        </form>
                    </div>

                    <div class="float-right">
                        <button type="button" class="btn btn-default" onclick="cancelAddUser()">Cancel</button>
                        <button type="button" class="btn btn-primary" onclick="submitUser()">Thêm</button>
                    </div>
                </div>
<%--                </form:form>--%>
                <div class="space-6"></div>
            </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->
    </div><!-- /.login-box -->
</div><!-- /.position-relative -->

</body>

</html>