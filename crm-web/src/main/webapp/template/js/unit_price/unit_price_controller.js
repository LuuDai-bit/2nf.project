$(function () {
    $('#datetimepicker1').datepicker({
        locale:'ru'
    });
});

function submitUnitPrice(){
    let unitPrice = {};

    unitPrice.roomPrice = $("#roomPrice").val().trim();
    unitPrice.electricityPrice = $("#electricityPrice").val().trim();
    unitPrice.waterPrice = $("#waterPrice").val().trim();
    unitPrice.effectiveDate = $("#effectiveDate").val().trim();
    unitPrice.building_id = $( "#building option:selected" ).val();

    if(isAdd){
        submitNewUnitPrice(unitPrice);
    }
    else{
        unitPrice.id = editId;
        submitEditUnitPrice(unitPrice);
    }
    $("#myModal").modal('hide');
}

function submitNewUnitPrice(unitPrice) {
    $.ajax({
        type: "POST",
        url: "/addUnitPrice",
        data: JSON.stringify(unitPrice),
        dataType: "json",
        contentType: "application/json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
    });
}

function submitEditUnitPrice(unitPrice){
    $.ajax({
        type: "POST",
        url: "/editUnitPrice/"+editId,
        data: unitPrice,
        dataType: "json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
    });
}

function selectUnitPrice(unitPriceId, isChecked){
    if(isChecked) checkedUser.push(unitPriceId);
    else{
        checkedUser = checkedUser.filter(elem => elem != unitPriceId);
    }

    enableUnitPriceDeleteButton(checkedUser.length==0);
}

function enableUnitPriceDeleteButton(haveMoreThan0){
    $("#deleteUnitPrices")[0].disabled = haveMoreThan0;
}

function deleteUnitPrice() {
    $.ajax({
        type: "DELETE",
        url: "/deleteUnitPrices",
        dataType: "json",
        data: JSON.stringify({"items":checkedUser}),
        contentType: 'application/json'
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        checkedUser = [];
        enableUnitPriceDeleteButton(checkedUser.length==0);
        window.location.reload(true);
    });
}

function addUnitPrice(){
    let url = "/add/unitPrice/page?id=" + -1;
    $.get(url, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

function cancelAddUnitPrice(){
    $("#myModal").modal('hide');
}

function editUnitPrice (id) {
    editId = id;
    let url = "/add/unitPrice/page?id=";
    $.get(url + id, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
    isAdd = false;
}

$("#searchUnitPrice").click(function(){
    $('#UnitPriceForm').submit();
});

$(function () {
    $('#effectiveDate').datepicker();
});
