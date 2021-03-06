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

    user.role_id = role_id;

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
    if(isAdd){
        submitNewUser(user)
    }
    else{
        user.id = editId;
        submitEditUser(user);
    }
    $("#myModal").modal('hide');
}

function submitNewUser(user) {
    $.ajax({
        type: "POST",
        url: "/addUser",
        data: JSON.stringify(user),
        dataType: "json",
        contentType: "application/json"
    }).done(function (response) {
        fire_ajax_submit();
    }).fail(function (xhr, status, error) {
        fire_ajax_submit();
    });


}

function submitEditUser(user){
    $.ajax({
        type: "POST",
        url: "/editUser/"+editId,
        data: user,
        dataType: "json"
    }).done(function (response) {
        fire_ajax_submit();
    }).fail(function (xhr, status, error) {
        fire_ajax_submit();
    });
}

function selectUser(userId, isChecked){
    if(isChecked) checkedUser.push(userId);
    else{
        checkedUser = checkedUser.filter(elem => elem != userId);
    }

    enableUserDeleteButton(checkedUser.length==0);
}

function enableUserDeleteButton(haveMoreThan0){
    $("#deleteUsers")[0].disabled = haveMoreThan0;
}

$("#deleteUsers").click(function (event) {
    event.preventDefault();
})

function deleteUsers1() {
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
        enableUserDeleteButton(checkedUser.length==0);
        window.location.reload(true);
    });
}

function cancelAddUser(){
    $("#myModal").modal('hide');
}

function addUser(){
    let url = "/add/user/page?id=" + -1;
    $.get(url, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

function editUser (id) {
    editId = id;
    let url = "/add/user/page?id=";
    $.get(url + id, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
    isAdd = false;
}

function getIdFromURL(){
    let queryString = window.location.search;
    let urlParams = new URLSearchParams(queryString);
    if(urlParams.get('id')!=null) editId = urlParams.get('id');
    if(editId!=null && editId != -1) {
        isAdd = false;
        $("#submitBtn").html('Sửa');
    }
}

$("#searchUser").click(function(){
    $('#userForm').submit();
})
