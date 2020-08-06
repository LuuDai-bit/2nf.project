function submitRole(){
    let role = {};
    let nameRe = /^[a-zA-Z ]+$/;

    role.name = $("#name").val().trim();
    role.code = $("#code").val().trim();

    if (role.name==''||role.name==null||!nameRe.test(role.name)){
        alert("Tên không hợp lệ");
        return;
    }

    if(isAdd){
        submitNewRole(role);
    }
    else{
        role.id = editId;
        submitEditRole(role);
    }


}

function submitNewRole(role) {
    $.ajax({
        type: "POST",
        url: "/addRole",
        data: JSON.stringify(role),
        dataType: "json",
        contentType: "application/json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        $("#name").val('');
        $("#code").val('');
    });
}

function submitEditRole(role){
    $.ajax({
        type: "POST",
        url: "/editRole/"+editId,
        data: role,
        dataType: "json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        $("#name").val('');
        $("#code").val('');
    });
}

function selectRole(roleId, isChecked){
    if(isChecked) checkedUser.push(roleId);
    else{
        checkedUser = checkedUser.filter(elem => elem != roleId);
    }

    enableRoleDeleteButton(checkedUser.length==0);
}

function enableRoleDeleteButton(haveMoreThan0){
    $("#deleteRoles")[0].disabled = haveMoreThan0;
}

function deleteRole() {
    $.ajax({
        type: "DELETE",
        url: "/deleteRoles",
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

function addRole(){
    let url = "/add/role/page?id=" + -1;
    $.get(url, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

function cancelAddRole(){
    $("#myModal").modal('hide');
    resetInput();
}

function editRole (id) {
    editId = id;
    let url = "/add/role/page?id=";
    $.get(url + id, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

$("#searchRole").click(function(){
    $('#roleForm').submit();
})