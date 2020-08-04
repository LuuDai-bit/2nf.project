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


}

function resetInput(){
    $("input").val('');
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
        if(xhr.responseText == "success") resetInput();
    }).always(function() {
        fire_ajax_submit();
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
        $("#name").val('');
        $("#code").val('');
    }).always(function () {
        fire_ajax_submit();
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
    window.open("/unitPrice/add/page?id="+-1);
}

function cancelAddUnitPrice(){
    window.close();
}

function editUnitPrice (id) {
    editId = id;
    window.open("/unitPrice/add/page?id="+id);
    isAdd = false;
}

$("#searchUnitPrice").click(function(){
    $('#UnitPriceForm').submit();
});
