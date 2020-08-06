<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: inherit;" >

    <div class="container" style="width: inherit">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h1>Thêm/chỉnh sửa vai trò</h1>

        <div class="form-group">
            <label for="name">Tên:</label>
            <c:if test="${role.id >=0}">
                <input type="text" value="${role.name}" id="name" class="form-control input-sm" placeholder="Tên"/>
            </c:if>

            <c:if test="${role==null}">
                <input type="text" id="name" class="form-control input-sm" placeholder="Tên"/>
            </c:if>
        </div>
        <div class="form-group">
            <label for="code">Mã:</label>
            <c:if test="${role.id >=0}">
                <input type="text" value="${role.code}" id="code" class="form-control input-sm" placeholder="Mã"/>
            </c:if>

            <c:if test="${role==null}">
                <input type="text" id="code" class="form-control input-sm" placeholder="Mã"/>
            </c:if>
        </div>

        <div class="float-right">
            <button type="button" class="btn btn-default" onclick="cancelAddRole()">Cancel</button>
            <button type="button" class="btn btn-primary" onclick="submitRole()" id="submitBtn">
                Thêm
            </button>
        </div>

        <div class="space-6"></div>
    </div>
</div>
