<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>
<%--<c:url var="formURL" value="/addCustomer" />--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thêm/chỉnh sửa khách hàng</title>
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
                    <h1>Thêm/chỉnh sửa khách hàng</h1>
                </div>
                <div class="space-6"></div>

                <div class="container">

                    <div class="form-group">
                        <label for="name">Tên:</label>
                        <c:if test="${customer.id >=0}">
                            <input type="text" value="${customer.name}" id="name" class="form-control input-sm" placeholder="Tên"/>
                        </c:if>

                        <c:if test="${customer==null}">
                            <input type="text" id="name" class="form-control input-sm" placeholder="Tên"/>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <c:if test="${customer.id >=0}">
                            <input type="text" value="${customer.email}" id="email" class="form-control input-sm" placeholder="Email"/>
                        </c:if>

                        <c:if test="${customer==null}">
                            <input type="text" id="email" class="form-control input-sm" placeholder="Email"/>
                        </c:if>

                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Số điện thoại:</label>
                        <c:if test="${customer.id >=0}">
                            <input type="text" value="${customer.phone}" id="phoneNumber" class="form-control input-sm" placeholder="Số điện thoại"/>
                        </c:if>

                        <c:if test="${customer==null}">
                            <input type="text" id="phoneNumber" class="form-control input-sm" placeholder="Số điện thoại"/>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="password">Mật khẩu:</label>
                        <c:if test="${customer.id >=0}">
                            <input type="text" value="${customer.password}" id="password" class="form-control input-sm" placeholder="Mật khẩu"/>
                        </c:if>

                        <c:if test="${customer==null}">
                            <input type="password" id="password" class="form-control input-sm" placeholder="Mật khẩu"/>
                        </c:if>
                    </div>
                    <div>
                        <label for="user">Người dùng</label>
                        <c:if test="${customer==null}">
                            <select id="user">
                                <c:forEach items="${users}" var="elem">
                                    <option value="${elem.id}" label="${elem.name}"></option>
                                </c:forEach>
                            </select>
                        </c:if>

                        <c:if test="${customer.id >=0}">
                            <select id="user" selected="${customer.user_id}">
                                <c:forEach items="${users}" var="elem">
                                    <option value="${elem.id}" label="${elem.code}"></option>
                                </c:forEach>
                            </select>
                        </c:if>

                    </div>

                    <div class="float-right">
                        <button type="button" class="btn btn-default" onclick="cancelAddCustomer()">Cancel</button>
                        <button type="button" class="btn btn-primary" onclick="submitCustomer()" id="submitBtn">
                            Thêm
                        </button>
                    </div>
                </div>

                <div class="space-6"></div>
            </div><!-- /.widget-main -->
        </div><!-- /.widget-body -->
    </div><!-- /.login-box -->
</div><!-- /.position-relative -->

</body>

</html>