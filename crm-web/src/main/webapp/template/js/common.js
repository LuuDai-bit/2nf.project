$(document).ready(function () {
    getIdFromURL();
});
let checkedUser = [];
let isAdd = true;
let editId = -1;

function fire_ajax_submit() {

    let form = $('#fileUploadForm')[0];

    let data = new FormData(form);
    if(data == null){
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
            alert("SUCCESS : "+ result);
            window.location.reload(true);
        },
        error: function (e) {
            alert('')
            $("#btnSubmit").prop("disabled", false);
        }
    });
}

function validateNumberInput(num){
    return (num != null && num > 0);
}

function validateStringInput(str) {
    return (str != null && str.trim() != '');
}

function validateDateInput(date) {

}


function exportCSV(category){
    console.log('zo');
    let paramObj = {};
    $.each($('#buildingForm').serializeArray(), function(_, kv) {
        paramObj[kv.name] = kv.value;
    });
    let params = jQuery.param(paramObj);
    let url = '/'+category+'/export?'+ params;
    window.open(url, '_blank');
}