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
    let res = "<li class=\"page-item disabled\">\n" +
        "                                        <a class=\"page-link\" href=\"#\" tabindex=\"-1\">Previous</a>\n" +
        "                                    </li>";
    for(let i =0; i < numberOfPage; i++){
        res += `<li class="page-item"><a class="page-link" onclick="changePage(this.text)">`+(i+1)+`</a></li> `;
    }
    res += "<li class=\"page-item\">\n" +
        "                                        <a class=\"page-link\" href=\"#\">Next</a>\n" +
        "                                    </li>";

    $("#pageUL").append(res);
}

function changePage(_page){

    $("#page").val(_page);
    updateMaxPageItems();
}