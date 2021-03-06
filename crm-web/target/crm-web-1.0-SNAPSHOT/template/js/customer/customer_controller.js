function submitCustomer(){
    let customer = {};

    customer.name = $("#name").val().trim();
    customer.name = $("#email").val().trim();
    customer.phone = $("#phoneNumber").val().trim();
    customer.password = $("#password").val().trim();
    customer.user_id = $( "#user option:selected" ).val();

    if(isAdd){
        submitNewCustomer(customer);
    }
    else{
        customer.id = editId;
        submitEditCustomer(customer);
    }
    $("#myModal").modal('hide');
}

function submitNewCustomer(customer) {
    $.ajax({
        type: "POST",
        url: "/addCustomer",
        data: JSON.stringify(customer),
        dataType: "json",
        contentType: "application/json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
    }).always(function() {
    });
}

function submitEditCustomer(customer){
    $.ajax({
        type: "POST",
        url: "/editCustomer/"+editId,
        data: customer,
        dataType: "json"
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        $("#name").val('');
        $("#code").val('');
    }).always(function () {
    });
}

function selectCustomer(customerId, isChecked){
    if(isChecked) checkedUser.push(customerId);
    else{
        checkedUser = checkedUser.filter(elem => elem != customerId);
    }

    enableCustomerDeleteButton(checkedUser.length==0);
}

function enableCustomerDeleteButton(haveMoreThan0){
    $("#deleteCustomers")[0].disabled = haveMoreThan0;
}

function deleteCustomer() {
    $.ajax({
        type: "DELETE",
        url: "/deleteCustomers",
        dataType: "json",
        data: JSON.stringify({"items":checkedUser}),
        contentType: 'application/json'
    }).done(function (response) {
        alert("Job done!!!!");
    }).fail(function (xhr, status, error) {
        alert(xhr.responseText);
        checkedUser = [];
        enableCustomerDeleteButton(checkedUser.length==0);
        window.location.reload(true);
    });
}

function addCustomer(){
    let url = "/add/customer/page?id=" + -1;
    $.get(url, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
}

function cancelAddCustomer(){
    $("#myModal").modal('hide');
}

function editCustomer (id) {
    editId = id;
    let url = "/add/customer/page?id=";
    $.get(url + id, function (data) {
        $('#edit-container').html(data);
        $('#myModal').modal('show');
    });
    isAdd = false;
}

$("#searchCustomer").click(function(){
    $('#CustomerForm').submit();
})