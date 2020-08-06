function popThisUp() {
    // $("#buildingModal").modal('show');
}
function submitBuilding(){
    let building = {};
    let nameRe = /^[a-zA-Z ]+$/;

    building.code = $("#code").val().trim();
    building.district =  $("#district").val().trim();
    building.street = $("#street").val().trim();
    building.ward =$("#ward").val().trim();
    building.leasedArea =$("#leasedArea").val().trim();
    building.roomNumber =$("#roomNumber").val().trim();
    building.note = $("#note").val().trim();

    building.avatar = "";
    let avatar = $("#imgfile")[0].files;
    for(let i = 0; i < avatar.length; i++){
        building.avatar += avatar[i].name + ',';
    }
    if(building.avatar.length>1) building.avatar = building.avatar.slice(0, -1);

    if(isAdd){
        submitNewBuilding(building);
    }
    else{
        building.id = editId;
        submitEditBuilding(building);
    }


}

function resetInput(){
    $("input").val('');
}

function submitNewBuilding(building) {
    $.ajax({
        type: "POST",
        url: "/addBuilding",
        data: JSON.stringify(building),
        dataType: "json",
        contentType: "application/json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        if(xhr.responseText == "success") {
            fire_ajax_submit();
        }
    }).always(function() {

    });
}

function submitEditBuilding(building){
    $.ajax({
        type: "POST",
        url: "/editBuilding/"+editId,
        data: building,
        dataType: "json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        $("#name").val('');
        $("#code").val('');
    }).always(function () {
        fire_ajax_submit();
    });
}

function selectBuilding(buildingId, isChecked){
    if(isChecked) checkedUser.push(buildingId);
    else{
        checkedUser = checkedUser.filter(elem => elem != buildingId);
    }

    enableBuildingDeleteButton(checkedUser.length==0);
}

function enableBuildingDeleteButton(haveMoreThan0){
    $("#deleteBuildings")[0].disabled = haveMoreThan0;
}

function deleteBuilding() {
    $.ajax({
        type: "DELETE",
        url: "/deleteBuildings",
        dataType: "json",
        data: JSON.stringify({"items":checkedUser}),
        contentType: 'application/json'
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        checkedUser = [];
        enableBuildingDeleteButton(checkedUser.length==0);
        window.location.reload(true);
    });
}

function addBuilding(){
    let url = "/add/building/page?id=" + -1;
    $.get(url, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

function cancelAddBuilding(){
    $("#myModal").modal('hide');
    resetInput();
}

function editBuilding (id) {
    editId = id;
    let url = "/add/building/page?id=";
    $.get(url + id, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

$("#searchBuilding").click(function(){
    $('#buildingForm').submit();
})
