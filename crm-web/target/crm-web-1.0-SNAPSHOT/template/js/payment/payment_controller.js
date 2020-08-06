function submitPayment(){
    let payment = {};

    payment.amountPaid = $("#amountPaid").val().trim();
    payment.amountPayable = $("#amountPayable").val().trim();

    if(isAdd){
        submitNewPayment(payment);
    }
    else{
        payment.id = editId;
        submitEditPayment(payment);
    }
}

function resetInput(){
    $("input").val('');
}

function submitNewPayment(payment) {
    $.ajax({
        type: "POST",
        url: "/addPayment",
        data: JSON.stringify(payment),
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

function submitEditPayment(payment){
    $.ajax({
        type: "POST",
        url: "/editPayment/"+editId,
        data: Payment,
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

function selectPayment(paymentId, isChecked){
    if(isChecked) checkedUser.push(paymentId);
    else{
        checkedUser = checkedUser.filter(elem => elem != paymentId);
    }

    enablePaymentDeleteButton(checkedUser.length==0);
}

function enablePaymentDeleteButton(haveMoreThan0){
    $("#deletePayments")[0].disabled = haveMoreThan0;
}

function deletePayment() {
    $.ajax({
        type: "DELETE",
        url: "/deletePayments",
        dataType: "json",
        data: JSON.stringify({"items":checkedUser}),
        contentType: 'application/json'
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        checkedUser = [];
        enablePaymentDeleteButton(checkedUser.length==0);
        window.location.reload(true);
    });
}

function addPayment(){
    let url = "/add/payment/page?id=" + -1;
    $.get(url, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

function cancelAddPayment(){
    $("#myModal").modal('hide');
    resetInput();
}

function editPayment (id) {
    editId = id;
    let url = "/add/payment/page?id=";
    $.get(url + id, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

$("#searchPayment").click(function(){
    $('#PaymentForm').submit();
})