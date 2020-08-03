$("document").ready(function(){
    let totalItems = parseInt($("#totalItem").text());
    let maxPageItems = parseInt($('#maxPageItems').find(":selected").text());
    let numberOfPage = totalItems/maxPageItems;
    autoGeneratePagingBar(numberOfPage);
})
let nop ;

function updateMaxPageItems(){
    $('#userForm').submit();
    $('#roleForm').submit();
}

function autoGeneratePagingBar(numberOfPage){

    let res = `<li class="page-item disabled" onclick="previousPage()"><a class="page-link">Previous</a> </li>`;


    for(let i =0; i < numberOfPage; i++){
        res += `<li class="page-item"><a class="page-link" onclick="changePage(this.text)">`+(i+1)+`</a></li> `;
    }


    res += `<li class="page-item disabled" onclick="nextPage()"><a class="page-link">Next</a> </li>`;

    $("#pageUL").append(res);

    nop = numberOfPage;
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
    if ($("#page").val()+1 <= nop)
        $("#page").val($("#page").val()-1);
    updateMaxPageItems();
}