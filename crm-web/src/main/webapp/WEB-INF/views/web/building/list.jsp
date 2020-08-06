<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url var="formURL" value="/building/list" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tòa nhà</title>
</head>
<body>

<%--    <form:hidden path="/homepage"/>--%>
<div class="position-relative" id="outside">

    <div id="login-box" class="login-box visible widget-box no-border">
        <div class="widget-body">
            <div class="widget-main">
                <h4 class="header blue lighter bigger">
                    <i class="ace-icon fa fa-coffee green"></i>

                </h4>
                <form:form id="BuildingForm" method="GET" action="${formURL}" modelAttribute="building">
                    <div class="container">
                        <h1>Tòa nhà</h1>

                        <div class="container-fluid" style="margin-bottom: 2em">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Mã</label>
                                </div>
                                <div class="col-md-4">
                                    <label>Quận</label>
                                </div>
                                <div class="col-md-4">
                                    <label>Đường</label>
                                </div>
                            </div>
                            <div class="row">
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="code" id="searchCode"
                                                cssClass="form-control input-sm"
                                                placeholder="Mã"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="district" id="searchCode"
                                                cssClass="form-control input-sm"
                                                placeholder="Quận"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="street" id="searchCode"
                                                cssClass="form-control input-sm"
                                                placeholder="Đường"/>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-4">
                                    <label>Khu vực</label>
                                </div>
                                <div class="col-md-4">
                                    <label>Diện tích thuê</label>
                                </div>
                                <div class="col-md-4">
                                    <label>Số phòng</label>
                                </div>
                            </div>
                            <div class="row">
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="ward" id="searchCode"
                                                cssClass="form-control input-sm"
                                                placeholder="Khu vực"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="leasedArea" id="searchCode"
                                                cssClass="form-control input-sm"
                                                placeholder="Diện tích thuê"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="roomNumber" id="searchCode"
                                                cssClass="form-control input-sm"
                                                placeholder="Số phòng"/>
                                </div>
                            </div>
                            <div>
                                <button style="margin:1em 0 0 1em" id="searchBuilding" type="submit" class="btn btn-primary" >Tìm kiếm</button>
                            </div>
                        </div>

                        <div>

                            <button type="button" class="btn btn-primary" data-toggle="modal" onclick="addBuilding()"
                                    data-target=".bd-example-modal-lg">Thêm mới</button>
                            <button id="deleteBuildings" type="button" onclick="deleteBuilding()"
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
                                    <th>Mã</th>
                                    <th>Quận</th>
                                    <th>Đường</th>
                                    <th>Khu vực</th>
                                    <th>Leased Area</th>
                                    <th>Số phòng</th>
                                    <th>Chú thích</th>
                                    <th></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="r" items="${building.listResult}">
                                    <tr>
                                        <td><input type="checkbox" id="${r.id}"
                                                   onclick="selectBuilding(${r.id}, this.checked)"
                                        ></td>
                                        <td>${r.code}</td>
                                        <td>${r.district}</td>
                                        <td>${r.street}</td>
                                        <td>${r.ward}</td>
                                        <td>${r.leasedArea}</td>
                                        <td>${r.roomNumber}</td>
                                        <td>${r.note}</td>
                                        <td><button type="button" onclick="editBuilding(${r.id})">Edit</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <a style="float:right" href="/building/export">Export CSV</a>
                        </div>

                        <div>
                            <div>
                                <span>Có tất cả <span id="totalItem">${building.totalItems}</span> bản ghi</span>
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