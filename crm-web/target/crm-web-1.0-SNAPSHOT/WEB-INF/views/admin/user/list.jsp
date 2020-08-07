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
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:select path="role_id" id="searchRoles" cssClass="form-control custom-select my-select" multiple="false">
                                        <form:option value="" label="Vai trò"></form:option>
                                        <c:forEach items="${roles}" var="r">
                                            <form:option value="${r.id}" label="${r.code}"></form:option>
                                        </c:forEach>
                                    </form:select>
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
                            <button id="deleteUsers" type="button" onclick="deleteUsers1()"
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
                                <c:forEach var="user" items="${users.listResult}">
                                    <tr>
                                        <td><input type="checkbox" id="${user.id}"
                                                   onclick="selectUser(${user.id}, this.checked)"
                                        ></td>
                                        <td>${user.name}</td>
                                        <td>${user.email}</td>
                                        <td>${user.phone}</td>
                                        <td><button type="button" onclick="editUser(${user.id})">Edit</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                            <button style="float:right" type="button" class="btn btn-link" onclick="exportCSV('user')">Xuất CSV</button>
                        </div>

                        <div>
                            <div>
                                <span>Có tất cả <span id="totalItem">${users.totalItems}</span> bản ghi</span>
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

<%--Modal--%>
<div style="margin: 0 auto" id="myModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="margin: 15em auto">
        <div class="modal-content" id="edit-container">

        </div>
    </div>
</div>

<%--End Modal--%>
</body>
</html>