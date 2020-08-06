<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div style="width: inherit;" >

    <div class="container" style="width: inherit">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h1>Thêm chỉnh sửa tòa nhà</h1>
        <div class="form-group col-md-12">
            <label for="code">Mã:</label>
            <c:choose>
                <c:when test="${building.id >=0}">
                    <input type="text" value="${building.code}" id="code" class="form-control input-sm" placeholder="Tên"/>
                </c:when>

                <c:otherwise>
                    <input type="text" id="code" class="form-control input-sm" placeholder="Mã"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group col-md-4">
            <label for="district">Quận:</label>
            <c:choose>
                <c:when test="${building.id >=0}">
                    <input type="text" value="${building.district}" id="district" class="form-control input-sm" placeholder="Quận"/>
                </c:when>

                <c:otherwise>
                    <input type="text" id="district" class="form-control input-sm" placeholder="Quận"/>
                </c:otherwise>
            </c:choose>

        </div>
        <div class="form-group col-md-4">
            <label for="street">Đường:</label>
            <c:choose>
                <c:when test="${building.id >=0}">
                    <input type="text" value="${building.street}" id="street" class="form-control input-sm" placeholder="Đường"/>
                </c:when>

                <c:otherwise>
                    <input type="text" id="street" class="form-control input-sm" placeholder="Đường"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group col-md-4">
            <label for="Ward">Khu vực:</label>
            <c:choose>
                <c:when test="${building.id >=0}">
                    <input type="text" value="${building.ward}" id="ward" class="form-control input-sm" placeholder="Khu vực"/>
                </c:when>

                <c:otherwise>
                    <input type="text" id="ward" class="form-control input-sm" placeholder="Khu vực"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="form-group  col-md-6">
            <label for="Ward">Diện tích thuê:</label>
            <c:choose>
                <c:when test="${building.id >=0}">
                    <input type="number" value="${building.leasedArea}" id="leasedArea" class="form-control input-sm" placeholder="Diện tích thuê"/>
                </c:when>

                <c:otherwise>
                    <input type="number" id="leasedArea" class="form-control input-sm" placeholder="Diện tích thuê"/>
                </c:otherwise>
            </c:choose>

        </div>
        <div class="form-group col-md-6">
            <label for="Ward">Số phòng:</label>
            <c:choose>
                <c:when test="${building.id >=0}">
                    <input type="number" value="${building.roomNumber}" id="roomNumber" class="form-control input-sm" placeholder="Số phòng"/>
                </c:when>

                <c:otherwise>
                    <input type="number" id="roomNumber" class="form-control input-sm" placeholder="Số phòng"/>
                </c:otherwise>
            </c:choose>

        </div>
        <div class="form-group col-md-12">
            <label for="note">Chú thích:</label>
            <c:choose>
                <c:when test="${building.id >=0}">
                    <textarea value="${building.note}" id="note" rows="10" cols="30" class="form-control input-sm"></textarea>
                </c:when>

                <c:otherwise>
                    <textarea id="note" rows="10" cols="30" class="form-control input-sm"></textarea>
                </c:otherwise>
            </c:choose>

        </div>
        <%--                    Display image--%>
        <c:if test="${building.id >=0}">
            <div>
                <c:if test="${not empty building.avatar}">
                    <c:forEach var="ava" items="${building.avatar}">
                        <%--                                    <c:set var="image" value="E://pictures//${ava}"/>--%>
                        <img style="width: 200px;padding-top: 20px;; height:200px" alt="Building Pic" src='<c:url value='/template/pictures/${ava}'/>'
                        />

                    </c:forEach>

                </c:if>
            </div>
        </c:if>
        <%--                            End display image--%>
        <div style="margin-top: 1em" class="form-group">
            <form method = "POST" enctype="multipart/form-data" id="fileUploadForm">
                <label for="imgfile">Ảnh đại diện:</label>
                <input id="imgfile" type="file" name="files" accept="image/png, image/jpeg" multiple/><br/><br/>
            </form>
        </div>
        <div class="float-right">
            <button type="button" class="btn btn-default" onclick="cancelAddBuilding()">Cancel</button>
            <button type="button" class="btn btn-primary" onclick="submitBuilding()" id="submitBtn">
                Thêm
            </button>
        </div>
    </div>
    <div class="space-6"></div>
</div>



