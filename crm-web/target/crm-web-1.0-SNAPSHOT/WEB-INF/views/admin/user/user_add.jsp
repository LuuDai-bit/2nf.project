<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>
<%--<c:url var="formURL" value="/addUser" />--%>
<div style="width: inherit;" >

    <div class="container" style="width: inherit">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h1>Thêm/chỉnh sửa người dùng</h1>

            <div class="form-group">
                <label for="name">Tên người dùng:</label>
                <c:if test="${users.id >=0}">
                    <input type="text" value="${users.name}" id="name" class="form-control input-sm" placeholder="Tên"/>
                </c:if>

                <c:if test="${users==null}">
                    <input type="text" id="name" class="form-control input-sm" placeholder="Tên"/>
                </c:if>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <c:if test="${users.id >=0}">
                    <input type="text" value="${users.email}" id="email" class="form-control input-sm" placeholder="Email"/>
                </c:if>

                <c:if test="${users==null}">
                    <input type="text" id="email" class="form-control input-sm" placeholder="Email"/>
                </c:if>

            </div>
            <div class="form-group">
                <label for="phoneNumber">Số điện thoại:</label>
                <c:if test="${users.id >=0}">
                    <input type="text" value="${users.phone}" id="phoneNumber" class="form-control input-sm" placeholder="Số điện thoại"/>
                </c:if>

                <c:if test="${users==null}">
                    <input type="text" id="phoneNumber" class="form-control input-sm" placeholder="Số điện thoại"/>
                </c:if>

            </div>
            <div>
                <label for="role">Vai trò</label>
                <c:if test="${users==null}">
                    <select id="role">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}" label="${role.code}"></option>
                        </c:forEach>
                    </select>
                </c:if>

                <c:if test="${users.id >=0}">
                    <select id="role" selected="${users.role_id}">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}" label="${role.code}"></option>
                        </c:forEach>
                    </select>
                </c:if>

            </div>

            <div style="margin-top: 1em" class="form-group">
                <label>Ảnh đại diện</label>
                <form method = "POST" enctype="multipart/form-data" id="fileUploadForm">
                    <input id="imgfile" type="file" name="files" accept="image/png, image/jpeg" multiple/><br/><br/>
                </form>
            </div>

            <%--                    Display image--%>
            <c:if test="${users.id >=0}">
                <div>
                    <c:if test="${not empty users.avatar}">
                        <c:forEach var="ava" items="${users.avatar}">
                            <%--                                    <c:set var="image" value="E://pictures//${ava}"/>--%>
                            <img style="width: 200px;padding-top: 20px; height:200px" alt="users Pic" src='<c:url value='/image-response-entity?ava=${ava}'/>'
                            />

                        </c:forEach>

                    </c:if>
                </div>
            </c:if>
            <%--                            End display image--%>

            <div class="float-right">
                <button type="button" class="btn btn-default" onclick="cancelAddUser()">Cancel</button>
                <button type="button" class="btn btn-primary" onclick="submitUser()" id="submitBtn">
                    Thêm
                </button>
            </div>

        <div class="space-6"></div>
    </div>
</div>