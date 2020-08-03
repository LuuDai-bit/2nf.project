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

    // if (building.code==''||building.name==null||!nameRe.test(Building.name)){
    //     alert("Tên không hợp lệ");
    //     return;
    // }

    if(isAdd){
        submitNewBuilding(building);
    }
    else{
        building.id = editId;
        submitEditBuilding(building);
    }


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
        $("#name").val('');
        $("#code").val('');
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
    });
}

function selectBuilding(buildingId, isChecked){
    if(isChecked) checkedUser.push(buildingId);
    else{
        checkedUser = checkedUser.filter(elem => elem != buildingId);
    }

    enableDeleteButton(checkedUser.length==0);
}

function enableDeleteButton(haveMoreThan0){
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
        enableDeleteButton(checkedUser.length==0);
        window.location.reload(true);
    });
}

function addBuilding(){
    window.open("/building/add/page?id="+-1);
}

function cancelAddBuilding(){
    window.close();
}

function editBuilding (id) {
    editId = id;
    window.open("/building/add/page?id="+id);
    isAdd = false;
}

$("#searchBuilding").click(function(){
    $('#buildingForm').submit();
})