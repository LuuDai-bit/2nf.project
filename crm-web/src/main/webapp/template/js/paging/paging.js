$("document").ready(function(){
    autoGeneratePagingBar();
})

function updateMaxPageItems(){
    $('#userForm').submit();
}

function autoGeneratePagingBar(){
    let totalItems = parseInt($("#totalItem").text());
    let maxPageItems = parseInt($('#maxPageItems').find(":selected").text());
    let numberOfPage = totalItems/maxPageItems;
    let res = `<li class="page-item disabled" onclick="previousPage()"><a class="page-link">Previous</a> </li>`;


    for(let i =0; i < numberOfPage; i++){
        res += `<li class="page-item"><a class="page-link" onclick="changePage(this.text)">`+(i+1)+`</a></li> `;
    }


    res += `<li class="page-item disabled" onclick="nextPage()"><a class="page-link">Next</a> </li>`;

    $("#pageUL").append(res);
}

function changePage(_page){

    $("#page").val(_page);
    updateMaxPageItems();
}

function previousPage() {
    if ($("#page").val()-1 > 0)
        $("#page").val($("#page").val()-1);
    updateMaxPageItems();
}

function nextPage() {
    let totalItems = parseInt($("#totalItem").text());
    let maxPageItems = parseInt($('#maxPageItems').find(":selected").text());
    let numberOfPage = totalItems/maxPageItems;
    if ($("#page").val()+1 <= numberOfPage)
        $("#page").val($("#page").val()-1);
    updateMaxPageItems();
}