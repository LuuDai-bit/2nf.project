$(document).ready(function () {

});
let checkedUser = [];
let isAdd = true;
let editId = -1;
function fire_ajax_submit() {

    let form = $('#fileUploadForm')[0];

    let data = new FormData(form);
    if(data == null){
        console.log("Khong lay duoc");
        return;
    }
    // fdata.append("extraField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: "multipart/form-data",
        url: "/api/upload/multi",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (result) {
            console.log("SUCCESS : ", result);
        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });


}

function submitUser(){
    let user = {};
    let phoneNumberRe = /^\d+$/;
    let emailRe = /^[a-z][a-z0-9_\.]{5,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/;
    let nameRe = /^[a-zA-Z ]+$/;

    user.name = $("#name").val().trim();
    user.email = $("#email").val().trim();
    user.phone = $("#phoneNumber").val().trim();
    let avatar = $("#imgfile")[0].files;

    let role_id = $( "#role option:selected" ).val();
    user.role = {};
    user.role.id = role_id;

    user.avatar = "";
    for(let i = 0; i < avatar.length; i++){
        user.avatar += avatar[i].name + ',';
    }
    if(user.avatar.length>1) user.avatar = user.avatar.slice(0, -1);
    let e =  $( "#role option:selected" ).text();

    if (user.name==''||user.name==null||!nameRe.test(user.name)){
        alert("Tên không hợp lệ");
        return;
    }

    if (user.email==''||user.email==null||!emailRe.test(user.email)){
        alert("Địa chỉ email này không hợp lệ");
        return;
    }

    if (user.phone==''||user.phone==null||!phoneNumberRe.test(user.phone)||user.phone.length>11||user.phone.length<9){
        alert("Số điện thoại không hợp lệ");
        return;
    }
    console.log(user);
    getIdFromURL();
    if(isAdd){
        submitNewUser(user)
    }
    else{
        user.id = editId;
        submitEditUser(user);
    }

    // $("#name").val("");
    // $("#email").val("");
    // $("#phoneNumber").val("");
    // $("#imgfile").val("");

}

function submitNewUser(user) {
    // console.log(data);
    // $('#addUserForm').submit();
    $.ajax({
        type: "POST",
        url: "/addUser",
        data: JSON.stringify(user),
        dataType: "json",
        contentType: "application/json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
    });

    fire_ajax_submit();
}

function submitEditUser(user){
    $.ajax({
        type: "POST",
        url: "/editUser/"+editId,
        data: user,
        dataType: "json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
    });
}

function deleteUser(userId){
    $.ajax({
        type: "DELETE",
        url: "/deleteUser/"+userId,
        dataType: "json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        window.location.reload(true);
    });

}

function selectUser(userId, isChecked){
    if(isChecked) checkedUser.push(userId);
    else{
        checkedUser = checkedUser.filter(elem => elem != userId);
    }
    enableDeleteButton(checkedUser.length==0);
}

function enableDeleteButton(haveMoreThan0){
    $("#deleteUsers")[0].disabled = haveMoreThan0;
}

function deleteUsers(){

    $.ajax({
        type: "DELETE",
        url: "/deleteUsers",
        dataType: "json",
        data: JSON.stringify({"items":checkedUser}),
        contentType: 'application/json'

    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        checkedUser = [];
        enableDeleteButton(checkedUser.length==0);
        window.location.reload(true);
    });

}

function editUser() {

}

function cancelAddUser(){
    window.close();
}

function addUser(){
    window.open("/adduserpage");
}

function editUser (id) {
    editId = id;
    window.open("/adduserpage?id="+id);
    isAdd = false;
}

function getIdFromURL(){
    let queryString = window.location.search;
    // console.log(queryString);
    let urlParams = new URLSearchParams(queryString);
    if(urlParams.get('id')!=null) editId = urlParams.get('id');
    if(editId!=null && editId != -1) isAdd = false;
}

$("#searchUser").click(function(){
    $('#userForm').submit();
})
