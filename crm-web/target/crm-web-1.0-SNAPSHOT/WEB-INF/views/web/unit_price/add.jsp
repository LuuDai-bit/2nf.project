<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thêm/chỉnh sửa giá</title>
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
                    <h1>Thêm/chỉnh sửa giá</h1>
                </div>
                <div class="space-6"></div>

                <div class="container">

                    <div class="form-group col-md-6">
                        <label for="roomPrice">Giá phòng:</label>
                        <c:if test="${unitPrice.id >=0}">
                            <input type="number" value="${unitPrice.roomPrice}" id="roomPrice" class="form-control input-sm" placeholder="Giá phòng"/>
                        </c:if>

                        <c:if test="${unitPrice==null}">
                            <input type="number" id="roomPrice" class="form-control input-sm" placeholder="Giá phòng"/>
                        </c:if>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="electricityPrice">Giá điện:</label>
                        <c:if test="${unitPrice.id >=0}">
                            <input type="number" value="${unitPrice.electricityPrice}" id="electricityPrice" class="form-control input-sm" placeholder="Giá điện"/>
                        </c:if>

                        <c:if test="${unitPrice==null}">
                            <input type="number" id="electricityPrice" class="form-control input-sm" placeholder="Giá điện"/>
                        </c:if>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="electricityPrice">Giá nước:</label>
                        <c:if test="${unitPrice.id >=0}">
                            <input type="number" value="${unitPrice.waterPrice}" id="waterPrice" class="form-control input-sm" placeholder="Giá nước"/>
                        </c:if>

                        <c:if test="${unitPrice==null}">
                            <input type="number" id="waterPrice" class="form-control input-sm" placeholder="Giá nước"/>
                        </c:if>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="electricityPrice">Ngày hiệu lực:</label>
                        <div class='input-group date' id='datetimepicker1'>
                        <c:if test="${unitPrice.id >=0}">
                            <input type="text" value="${unitPrice.effectiveDate}" id="effectiveDate" class="form-control input-sm" placeholder="Ngày hiệu lực"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </c:if>

                        <c:if test="${unitPrice==null}">
                            <input type="text" id="effectiveDate" class="form-control input-sm" placeholder="Ngày hiệu lực"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </c:if>
                        </div>
                    </div>
                    <div>
                        <label for="building">Tòa nhà</label>
                        <c:if test="${unitPrice==null}">
                            <select id="building">
                                <c:forEach items="${buildings}" var="elem">
                                    <option value="${elem.id}" label="${elem.code}"></option>
                                </c:forEach>
                            </select>
                        </c:if>

                        <c:if test="${unitPrice.id >=0}">
                            <select id="building" selected="${unitPrice.building_id}">
                                <c:forEach items="${buildings}" var="elem">
                                    <option value="${elem.id}" label="${elem.code}"></option>
                                </c:forEach>
                            </select>
                        </c:if>

                    </div>

                    <div class="float-right">
                        <button type="button" class="btn btn-default" onclick="cancelAddUnitPrice()">Cancel</button>
                        <button type="button" class="btn btn-primary" onclick="submitUnitPrice()" id="submitBtn">
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