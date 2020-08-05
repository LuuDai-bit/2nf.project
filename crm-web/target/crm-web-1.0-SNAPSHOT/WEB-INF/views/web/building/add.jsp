<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h4 class="header blue lighter bigger">
    <i class="ace-icon fa fa-coffee green"></i>

</h4>
<div style="width: inherit;" >

    <div class="container" style="width: inherit">
        <h1>Thêm chỉnh sửa tòa nhà</h1>
        <div class="form-group col-md-12">
            <label for="code">Mã:</label>
            <c:if test="${building.id >=0}">
                <input type="text" value="${building.code}" id="code" class="form-control input-sm" placeholder="Tên"/>
            </c:if>

            <c:if test="${building==null}">
                <input type="text" id="code" class="form-control input-sm" placeholder="Mã"/>
            </c:if>
        </div>
        <div class="form-group col-md-4">
            <label for="district">Quận:</label>
            <c:if test="${building.id >=0}">
                <input type="text" value="${building.district}" id="district" class="form-control input-sm" placeholder="Quận"/>
            </c:if>

            <c:if test="${building==null}">
                <input type="text" id="district" class="form-control input-sm" placeholder="Quận"/>
            </c:if>
        </div>
        <div class="form-group col-md-4">
            <label for="street">Đường:</label>
            <c:if test="${building.id >=0}">
                <input type="text" value="${building.street}" id="street" class="form-control input-sm" placeholder="Đường"/>
            </c:if>

            <c:if test="${building==null}">
                <input type="text" id="street" class="form-control input-sm" placeholder="Đường"/>
            </c:if>
        </div>
        <div class="form-group col-md-4">
            <label for="Ward">Khu vực:</label>
            <c:if test="${building.id >=0}">
                <input type="text" value="${building.ward}" id="ward" class="form-control input-sm" placeholder="Khu vực"/>
            </c:if>

            <c:if test="${building==null}">
                <input type="text" id="ward" class="form-control input-sm" placeholder="Khu vực"/>
            </c:if>
        </div>
        <div class="form-group  col-md-6">
            <label for="Ward">Diện tích thuê:</label>
            <c:if test="${building.id >=0}">
                <input type="number" value="${building.leasedArea}" id="leasedArea" class="form-control input-sm" placeholder="Diện tích thuê"/>
            </c:if>

            <c:if test="${building==null}">
                <input type="number" id="leasedArea" class="form-control input-sm" placeholder="Diện tích thuê"/>
            </c:if>
        </div>
        <div class="form-group col-md-6">
            <label for="Ward">Số phòng:</label>
            <c:if test="${building.id >=0}">
                <input type="number" value="${building.roomNumber}" id="roomNumber" class="form-control input-sm" placeholder="Số phòng"/>
            </c:if>

            <c:if test="${building==null}">
                <input type="number" id="roomNumber" class="form-control input-sm" placeholder="Số phòng"/>
            </c:if>
        </div>
        <div class="form-group col-md-12">
            <label for="note">Chú thích:</label>
            <c:if test="${building.id >=0}">
                <textarea value="${building.note}" id="note" rows="10" cols="30" class="form-control input-sm"></textarea>
            </c:if>

            <c:if test="${building==null}">
                <textarea id="note" rows="10" cols="30" class="form-control input-sm"></textarea>
            </c:if>
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



