<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:url var="formURL" value="/payment/list" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thanh toán</title>
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
                <form:form id="paymentForm" method="GET" action="${formURL}" modelAttribute="payment">
                    <div class="container">
                        <h1>Thanh toán</h1>

                        <div class="container-fluid" style="margin-bottom: 2em">
                            <div class="row">
                                <div class="col-md-4">
                                    <label>Đã trả</label>
                                </div>
                                <div class="col-md-4">
                                    <label>Có thể trả</label>
                                </div>
                            </div>
                            <div class="row">
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="amountPaid" id="searchAmountPaid"
                                                cssClass="form-control input-sm"
                                                placeholder="Đã trả"/>
                                </div>
                                <div style="margin-bottom: 1em" class="col-md-4">
                                    <form:input path="amountPayable" id="searchAmountPayable"
                                                cssClass="form-control input-sm"
                                                placeholder="Có thể trả"/>
                                </div>
                            </div>
                            <div>
                                <button style="margin:1em 0 0 1em" id="searchPayment" type="submit" class="btn btn-primary" >Tìm kiếm</button>
                            </div>
                        </div>

                        <div>
                            <button type="button" class="btn btn-primary" onclick="addPayment()">
                                <i class="flaticon-plus"></i>
                                Thêm mới</button>
                            <button id="deletePayments" type="button" onclick="deletePayment()"
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
                                    <th>Đã trả</th>
                                    <th>Có thể trả</th>
                                    <th></th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach var="r" items="${payment.listResult}">
                                    <tr>
                                        <td><input type="checkbox" id="${r.id}"
                                                   onclick="selectPayment(${r.id}, this.checked)"
                                        ></td>
                                        <td>${r.amountPaid}</td>
                                        <td>${r.amountPayable}</td>
                                        <td><button type="button" onclick="editPayment(${r.id})">Edit</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                        </div>

                        <div>
                            <div>
                                <span>Có tất cả <span id="totalItem">${payment.totalItems}</span> bản ghi</span>
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