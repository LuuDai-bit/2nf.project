<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url var="formURL" value="/unitPrice/list" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đơn vị giá</title>
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
                <form:form id="unitPriceForm" method="GET" action="${formURL}" modelAttribute="unitPrice">
                    <div class="container">
                        <h1>Đơn vị giá</h1>

                        <div class="container-fluid" style="margin-bottom: 2em">
                            <div class="row">
                                <div class="col-md-3">
                                    <label>Giá phòng</label>
                                </div>
                                <div class="col-md-3">
                                    <label>Giá điện</label>
                                </div>
                                <div class="col-md-3">
                                    <label>Giá nước</label>
                                </div>
                                <div class="col-md-3">
                                    <label>Ngày hiệu lực</label>
                                </div>
                            </div>
                            <div class="row">
                                <div style="margin-bottom: 1em" class="col-md-3">
                                    <form:input path="roomPrice" id="searchRoomPrice"
                                                cssClass="form-control input-sm"
                                                placeholder="Giá phòng"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-3">
                                    <form:input path="electricityPrice" id="searchElectricityPrice"
                                                cssClass="form-control input-sm"
                                                placeholder="Giá điện"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-3">
                                    <form:input path="waterPrice" id="searchWaterPrice"
                                                cssClass="form-control input-sm"
                                                placeholder="Giá nước"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-3">
                                    <form:input path="effectiveDate" id="searchEffectiveDate"
                                                cssClass="form-control input-sm"
                                                placeholder="Ngày hiệu lực"/>
                                </div>
                            </div>
                            <div>
                                <button style="margin:1em 0 0 1em" id="searchUnitPrice" type="submit" class="btn btn-primary" >Tìm kiếm</button>
                            </div>
                        </div>

                        <div>
                            <button type="button" class="btn btn-primary" onclick="addUnitPrice()">
                                <i class="flaticon-plus"></i>
                                Thêm mới</button>
                            <button id="deleteUnitPrices" type="button" onclick="deleteUnitPrice()"
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
                                    <th>Code</th>
                                    <th></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="r" items="${unitPrice.listResult}">
                                    <tr>
                                        <td><input type="checkbox" id="${r.id}"
                                                   onclick="selectUnitPrice(${r.id}, this.checked)"
                                        ></td>
                                        <td>${r.name}</td>
                                        <td>${r.code}</td>
                                        <td><button type="button" onclick="editUnitPrice(${r.id})">Edit</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
<%--                            <a style="float:right" href="/unitPrice/export">Export CSV</a>--%>
                        </div>

                        <div>
                            <div>
                                <span>Có tất cả <span id="totalItem">${unitPrice.totalItems}</span> bản ghi</span>
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
    <div class="modal-dialog modal-lg">
        <div class="modal-content" id="edit-container" style="margin: 15em auto">

        </div>
    </div>
</div>

<%--End Modal--%>

</body>
</html>