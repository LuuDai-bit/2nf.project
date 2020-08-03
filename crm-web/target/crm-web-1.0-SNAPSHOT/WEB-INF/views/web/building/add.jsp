<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thêm/chỉnh sửa tòa nhà</title>
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
                    <h1>Thêm/chỉnh sửa tòa nhà</h1>
                </div>
                <div class="space-6"></div>

                <div class="container">

                    <div class="form-group">
                        <label for="name">Mã:</label>
                        <c:if test="${building.id >=0}">
                            <input type="text" value="${building.name}" id="name" class="form-control input-sm" placeholder="Tên"/>
                        </c:if>

                        <c:if test="${building==null}">
                            <input type="text" id="name" class="form-control input-sm" placeholder="Tên"/>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label for="code">Mã:</label>
                        <c:if test="${building.id >=0}">
                            <input type="text" value="${building.code}" id="code" class="form-control input-sm" placeholder="Mã"/>
                        </c:if>

                        <c:if test="${building==null}">
                            <input type="text" id="code" class="form-control input-sm" placeholder="Mã"/>
                        </c:if>
                    </div>

                    <div class="float-right">
                        <button type="button" class="btn btn-default" onclick="cancelAddBuilding()">Cancel</button>
                        <button type="button" class="btn btn-primary" onclick="submitBuilding()" id="submitBtn">
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