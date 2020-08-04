<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url var="formURL" value="/customer/list" />

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
                <form:form id="customerForm" method="GET" action="${formURL}" modelAttribute="customer">
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
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="name" id="searchName"
                                                cssClass="form-control input-sm"
                                                placeholder="Tên"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="email" id="searchEmail"
                                                cssClass="form-control input-sm"
                                                placeholder="Email"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="phone" id="searchPhone"
                                                cssClass="form-control input-sm"
                                                placeholder="Số điện thoại"/>
                                </div>

                            </div>
                            <div>
                                <button style="margin:1em 0 0 1em" id="searchCustomer" type="submit" class="btn btn-primary" >Tìm kiếm</button>
                            </div>
                        </div>

                        <div>
                            <button type="button" class="btn btn-primary" onclick="addCustomer()">
                                <i class="flaticon-plus"></i>
                                Thêm mới</button>
                            <button id="deleteCustomer" type="button" onclick="deleteCustomer()"
                                    class="btn btn-danger" disabled>
                                <i class="fa fa-trash" aria-hidden="true"></i>
                                Xóa</button>
                            <div style="float:right; display:inline">
                                <form:select path="maxPageItems" cssClass="my-select" onchange="updateMaxPageItems()">
                                    <form:option value="5">5</form:option>
                                    <form:option value="10">10</form:option>
                                    <form:option value="20">20</form:option>
                                    <form:option value="50">50</form:option>
                                </form:select>
                            </div>

                        </div>
                        <div>
                            <table class="table table-bordered my-table">
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
                                <c:forEach var="elem" items="${customer.listResult}">
                                    <tr>
                                        <td><input type="checkbox" id="${elem.id}"
                                                   onclick="selectCustomer(${elem.id}, this.checked)"
                                        ></td>
                                        <td>${elem.name}</td>
                                        <td>${elem.email}</td>
                                        <td>${elem.phone}</td>
                                        <td><button type="button" onclick="editCustomer(${elem.id})">Edit</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                        </div>

                        <div>
                            <div>
                                <span>Có tất cả <span id="totalItem">${elem.totalItems}</span> bản ghi</span>
                                <span>Bản ghi x - y</span>
                            </div>
                            <div>
                                <nav aria-label="...">
                                    <ul class="pagination" id="pageUL">
                                    </ul>
                                </nav>
                            </div>
                            <form:hidden path="page" value="${page}" id="page"></form:hidden>
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