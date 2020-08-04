<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thêm/chỉnh sửa vai trò</title>
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
                    <h1>Thêm/chỉnh sửa vai trò</h1>
                </div>
                <div class="space-6"></div>

                <div class="container">

                    <div class="form-group">
                        <label for="amountPaid">Đã trả:</label>
                        <c:if test="${payment.id >=0}">
                            <input type="text" value="${payment.amountPaid}" id="amountPaid" class="form-control input-sm" placeholder="Đã trả"/>
                        </c:if>

                        <c:if test="${payment==null}">
                            <input type="text" id="amountPaid" class="form-control input-sm" placeholder="Tên"/>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="amountPayable">Có thể trả:</label>
                        <c:if test="${payment.id >=0}">
                            <input type="text" value="${payment.amountPayable}" id="amountPayable" class="form-control input-sm" placeholder="Có thể trả"/>
                        </c:if>

                        <c:if test="${payment==null}">
                            <input type="text" id="amountPayable" class="form-control input-sm" placeholder="Có thể trả"/>
                        </c:if>
                    </div>
                    <div>
                        <label for="consume">Tiêu thụ</label>
                        <c:if test="${payment==null}">
                            <select id="consume">
                                <c:forEach items="${consumes}" var="elem">
                                    <option value="${elem.id}" label="${elem.name}"></option>
                                </c:forEach>
                            </select>
                        </c:if>

                        <c:if test="${payment.id >=0}">
                            <select id="consume" selected="${payment.consume_id}">
                                <c:forEach items="${consumes}" var="elem">
                                    <option value="${elem.id}" label="${elem.code}"></option>
                                </c:forEach>
                            </select>
                        </c:if>

                    </div>
                    <div class="float-right">
                        <button type="button" class="btn btn-default" onclick="cancelAddPayment()">Cancel</button>
                        <button type="button" class="btn btn-primary" onclick="submitPayment()" id="submitBtn">
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