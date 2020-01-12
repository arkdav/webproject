function getData(url, contextPath) {
    let lang = getLanguage();
    var datapost = $('#searchform').serialize();
    let sortNow = url.match(new RegExp("sort" + '=([^&=]+)'));
    let sort = sortNow == null ? "" : sortNow[1];
    let perPage = getPerPage(url);
    let pageId = getPageId(url);
    if (url.match(new RegExp("pageid" + '=([^&=]+)')) != null) {
        url = url.replace(url.match(new RegExp("pageid" + '=([^&=]+)'))[0], "pageid="+pageId);
    }
    let a = "";
    if(document.getElementById('sortdrop').innerText.trim())
    switch (sort) {
        case 'alpaz':
            a = (lang==="en")  ? 'Sort by name from A to Z' : 'Сортировать по названию от A до Z';
            break;
        case 'alpza':
            a = (lang==="en")  ? 'Sort by name from Z to A' : 'Сортировать по названию от Z до A';
            break;
        case 'priceaz':
            a = (lang==="en")  ? 'Sort by price from minimal to maximal' : 'Сортировать по цене от меньшей к большей';
            break;
        case 'priceza':
            a = (lang==="en")  ? 'Sort by price from maximal to minimal' : 'Сортировать по цене от большей к меньшей';
            break;
    }
    if (sort !== "") {
        document.getElementById('sortdrop').innerText = a.trim();
    } else if (sort === "") {
        a = document.getElementById('sortdrop').innerText.trim();
        switch (a) {
            case 'Sort by name from A to Z':
                sort = 'alpaz';
                break;
            case 'Сортировать по названию от A до Z':
                sort = 'alpaz';
                break;
            case 'Sort by name from Z to A':
                sort = 'alpza';
                break;
            case 'Сортировать по названию от Z до A':
                sort = 'alpza';
                break;
            case 'Sort by price from minimal to maximal':
                sort = 'priceaz';
                break;
            case 'Сортировать по цене от меньшей к большей':
                sort = 'priceaz';
                break;
            case 'Sort by price from maximal to minimal':
                sort = 'priceza';
                break;
            case 'Сортировать по цене от большей к меньшей':
                sort = 'priceza';
                break;
        }
        if (url.match(new RegExp("pageid" + '=([^&=]+)')) != null) {
            if (url.match(new RegExp("perpage" + '=([^&=]+)')) != null) {
                url += '&sort=' + sort;
            } else if (url.match(new RegExp("perpage" + '=([^&=]+)')) == null) {
                url += '&sort=' + sort + '&perpage=' + perPage;
            }
        } else if (url.match(new RegExp("perpage" + '=([^&=]+)')) != null) {
            url += '?sort=' + sort;
        } else if (url.match(new RegExp("perpage" + '=([^&=]+)')) == null) {
            url += '?sort=' + sort + '&perpage=' + perPage;
        }
    }
    $.ajax({
        type: "POST",
        method: "post",
        url: url,
        data: datapost,
        dataType: "json",
        success: function (data) {
            $('#row').remove();
            $('#pagination1').remove();
            $('#pagination').remove();
            let divRow = document.createElement('div');
            divRow.className = "row";
            divRow.id = "row";
            let elem = document.getElementById('catalog');
            elem.append(divRow);
            for (let i = 0; i < data.length; i++) {
                let divProduct = document.createElement('div');
                divProduct.className = "product col-4";
                divProduct.id = "product";
                divProduct.onclick=function () {
                   location.href=contextPath + "/productdata/" + data[i].productId;
                };
                let divImage = document.createElement('div');
                divImage.className = "product-img";
                divImage.id = "pr_image";
                let image = document.createElement('img');
                image.alt= (lang==="en") ? "Image not found" : "Картинка не найдена";
                image.src = contextPath + "/images/" + data[i].imageLink;
                divImage.appendChild(image);

                let divProductNamePrice = document.createElement('div');

                let hProductName = document.createElement('h2');
                hProductName.className = "product-name";
                hProductName.id="pr_name";
                hProductName.textContent=data[i].name;

                let hPrice = document.createElement('h4');
                hPrice.className = "product-price";
                hPrice.id = "pr_price";
                hPrice.textContent = data[i].price + "$";

                divProductNamePrice.appendChild(hProductName);
                divProductNamePrice.appendChild(hPrice);
                divProduct.appendChild(divImage);
                divProduct.appendChild(divProductNamePrice);

                divRow.appendChild(divProduct);
            }
            $.ajax({
                type: "POST",
                method: "post",
                async: false,
                url: contextPath + "/pages?perpage=" + perPage,
                data: datapost,
                dataType: "json",
                success: function (data) {
                    let pageId = getPageId(url);
                    if (data.length === 0) {
                        let p = document.createElement('p');
                        p.className = "no-items";
                        p.textContent = (lang==="en") ? "No such products." : "Таких продуктов не найдено.";
                        divRow.append(p);
                    } else {
                        let ulPagination = document.createElement('ul');
                        ulPagination.className = "pagination";
                        let ulPaginationUpper = ulPagination.cloneNode(true);
                        ulPagination.id = "pagination";
                        ulPaginationUpper.id = "pagination1";
                        let liPaginationPrev = document.createElement('li');
                        let firstPage = data[0].pageId.toString();
                        liPaginationPrev.className = (pageId === firstPage) ?  "page-item disabled" : "page-item";
                        let liPaginationPrevUpper = liPaginationPrev.cloneNode(true);
                        liPaginationPrev.appendChild(getAPaginationPrev(perPage, contextPath));
                        ulPagination.appendChild(liPaginationPrev);
                        liPaginationPrevUpper.appendChild(getAPaginationPrev(perPage, contextPath));
                        ulPaginationUpper.appendChild(liPaginationPrevUpper);

                        for (let j = 0; j < data.length; j++) {
                            let liPagination = document.createElement('li');
                            let pageIdNow = data[j].pageId.toString();
                            liPagination.className = (pageId === pageIdNow) ?  "page-item active" : "page-item";
                            let liPaginationUpper=liPagination.cloneNode(true);

                            liPagination.appendChild(getAPagination(data[j], perPage, contextPath));
                            ulPagination.appendChild(liPagination);
                            liPaginationUpper.appendChild(getAPagination(data[j], perPage, contextPath));
                            ulPaginationUpper.appendChild(liPaginationUpper);
                        }

                        let liPaginationNext = document.createElement('li');
                        liPaginationNext.className = (pageId === data[data.length - 1].pageId.toString()) ? "page-item disabled" : "page-item";
                        let liPaginationNextUpper = liPaginationNext.cloneNode(true);
                        liPaginationNext.appendChild(getAPaginationNext(perPage, contextPath));
                        ulPagination.appendChild(liPaginationNext);
                        liPaginationNextUpper.appendChild(getAPaginationNext(perPage, contextPath));
                        ulPaginationUpper.appendChild(liPaginationNextUpper);

                        let liPerPage = document.createElement('li');
                        liPerPage.className = "perpage";

                        let liPerPageUpper = document.createElement('li');
                        liPerPageUpper.className = "perpage";

                        let divPerPageNew = document.createElement('div');
                        divPerPageNew.className = "btn-group";

                        let divPerPageNewUpper = document.createElement('div');
                       divPerPageNewUpper.className = "btn-group";

                        let buttonNew = document.createElement("button");
                        buttonNew.className = "btn btn-outline-success dropdown-toggle";
                        buttonNew.type = "button";
                        buttonNew.setAttribute("data-toggle", "dropdown");
                        buttonNew.setAttribute("aria-haspopup", "true");
                        buttonNew.setAttribute("aria-expanded", "false");
                        buttonNew.title = (getLanguage()==="en") ? "Products per page" : "Продуктов на странице";
                        buttonNew.textContent = perPage;
                        let buttonNewUpper =buttonNew.cloneNode(true);
                        buttonNew.id="perpagedrop";
                        buttonNewUpper.id="perpagedrop1";
                        divPerPageNew.appendChild(buttonNew);
                        divPerPageNewUpper.appendChild(buttonNewUpper);

                        let divDropDownMenu = document.createElement('div');
                        divDropDownMenu.className = "dropdown-menu";
                        let divDropDownMenuUpper=divDropDownMenu.cloneNode(true);
                        divDropDownMenu.appendChild(getDropDownItem("6", contextPath));
                        divDropDownMenuUpper.appendChild(getDropDownItem("6", contextPath));
                        divDropDownMenu.appendChild(getDropDownItem("12", contextPath));
                        divDropDownMenuUpper.appendChild(getDropDownItem("12", contextPath));
                        divDropDownMenu.appendChild(getDropDownItem("18", contextPath));
                        divDropDownMenuUpper.appendChild(getDropDownItem("18", contextPath));

                        divPerPageNew.appendChild(divDropDownMenu);
                        liPerPage.appendChild(divPerPageNew);
                        ulPagination.appendChild(liPerPage);

                        divPerPageNewUpper.appendChild(divDropDownMenuUpper);
                        liPerPageUpper.appendChild(divPerPageNewUpper);
                        ulPaginationUpper.appendChild(liPerPageUpper);

                        elem.appendChild(ulPagination);
                        elem.insertBefore(ulPaginationUpper, elem.childNodes[0]);
                    }
                }
            });
        },
        error: function () {
        }
    });
}

function getDropDownItem(pages, contextPath) {
    let firstA = document.createElement('a');
    firstA.className = "dropdown-item";
    firstA.onclick = function () {
        getData(contextPath + "/catalog?perpage="+pages+"&pageid=1", contextPath);
    };
    firstA.textContent = pages;
    return firstA;
}
function getAPaginationNext(perPage, contextPath){
    let aPaginationNext = document.createElement('a');
    aPaginationNext.className = "page-link";
    aPaginationNext.onclick = function () {
        getData(contextPath + "/catalog?pageid=n&perpage=" + perPage, contextPath);
    };
    aPaginationNext.textContent = (getLanguage()==="en") ? 'Next' : 'Следующая';
    return aPaginationNext;
}
function getAPagination(data, perPage, contextPath) {
    let aPagination = document.createElement('a');
    aPagination.className = "page-link";
    aPagination.onclick = function () {
        getData(contextPath + "/catalog?pageid=" + data.pageId + "&perpage=" + perPage, contextPath);
    };
    aPagination.textContent = data.pageId;
    return aPagination;
}
function getAPaginationPrev(perPage, contextPath) {
    let aPaginationPrev = document.createElement('a');
    aPaginationPrev.className = "page-link";
    aPaginationPrev.onclick = function () {
        getData(contextPath + "/catalog?pageid=p&perpage=" + perPage, contextPath);
    };
    aPaginationPrev.textContent = (getLanguage()==="en") ? 'Previous' : 'Предыдущая';
    return aPaginationPrev;
}
function getPageId(url) {
    let pageIdNow = url.match(new RegExp("pageid" + '=([^&=]+)'));
    let pageId = pageIdNow == null ? "" : pageIdNow[1];
    if (pageId === 'p') {
        pageId = parseInt(document.getElementsByClassName("page-item active")[0].textContent) - 1;
    } else if (pageId === 'n') {
        pageId = parseInt(document.getElementsByClassName("page-item active")[0].textContent) + 1;
    } else if (pageId === "") {
        pageId = "1";
    }
    return pageId;
}

function getPerPage(url) {
    let productsPerPage = url.match(new RegExp("perpage" + '=([^&=]+)'));
    let perPage = productsPerPage == null ? "" : productsPerPage[1];
    if (perPage === "") {
        if (document.getElementById('perpagedrop') != null) {
            perPage = document.getElementById('perpagedrop').innerText.trim();
        } else {
            perPage="6";
        }
    }
    return perPage;
}

function addToCart(url, contextPath) {
    var datapost = $('#addToCartForm').serialize();
    $.ajax({
        type:"POST",
        method: "post",
        url: url,
        data:datapost,
        dataType: "json",
        success: function(dataJson){
            $('#addToCartForm').remove();
            let elem = document.getElementById('tocart');
            if(dataJson!=null){
                var priceCart = dataJson[0];
                var amountCart=dataJson[1];
                $('exampleModalLongTitle').textContent = (getLanguage()==="en") ? "There are " + amountCart + "products in cart" :  "В вашей корзине " + amountCart + "продукта(ов)";
                let pPrice=document.createElement('p');
                pPrice.textContent=  (getLanguage()==="en") ? "Your cart price is "+priceCart+" $" : "Стоимость вашей корзины составляет "+priceCart+" $";
                elem.append(pPrice);
                let pToCatalog = document.createElement('div');
                let buttonToCatalog = document.createElement('button');
                buttonToCatalog.className = "btn btn-outline-success";
                buttonToCatalog.onclick=function () {
                    location.href = contextPath + "/catalog";
                };
                buttonToCatalog.textContent= (getLanguage()==="en") ? "Return to catalog" : "Вернуться к каталогу";
                pToCatalog.append(buttonToCatalog);
                elem.appendChild(pToCatalog);
                let pToCart = document.createElement('div');
                let buttonToCart = document.createElement('button');
                buttonToCart.className = "btn btn-outline-success";
                buttonToCart.onclick=function () {
                    location.href = contextPath + "/cart";
                };
                buttonToCart.textContent=(getLanguage()==="en") ? "Go to shopping cart" : "Перейти в корзину";
                pToCart.append(buttonToCart);
                elem.append(pToCart);
            } else {
                location.href = contextPath +"/catalog";
            }
        },
        error: function () {
            location.href = contextPath +"/catalog";
        }
    });
}

$(document).ready(function() {
    $(window).keydown(function(event){
        if(event.keyCode === 13) {
            event.preventDefault();
            return false;
        }
    });
});

function getUsersFor(type){
    let arrName = getUsersCheckboxArray('login');
    let arrStatus=getUsersCheckboxArray('status');
    $("#"+type+"-for-now").remove();
    let elem = document.getElementById(type+"-users");
    if(arrName.length!==0){
        let divForNow=document.createElement('div');
        divForNow.id=type+'-for-now';
        for (var i=0; i<arrName.length; i++) {
            let p = document.createElement('p');
            if(type==="change"){
                p.textContent=arrName[i]+" - "+arrStatus[i];
            } else {
                p.textContent=arrName[i];
            }
            divForNow.appendChild(p);
        }
        elem.append(divForNow);
    } else {
        document.location.reload();
    }
}

function getProductsForBusinessUser(type){
    let arrId = getBusinessCheckboxArray('id');
    let arrName=getBusinessCheckboxArray('name');
    let arrStatus=getBusinessCheckboxArray('status');
    $("#"+type+"-for-now").remove();
    let elem = document.getElementById(type+"-products");
    if(arrId.length!==0){
        let divForNow=document.createElement('div');
        divForNow.id=type+'-for-now';
        for (var i=0; i<arrId.length; i++) {
            let p = document.createElement('p');
            if(type==="delete"){
                p.textContent=arrId[i]+" - "+arrName[i];
            } else {
                p.textContent=arrId[i]+" - "+arrName[i]+" - "+arrStatus[i];
            }
            divForNow.appendChild(p);
        }
        elem.append(divForNow);
    } else {
        document.location.reload();
    }
}

function doGetBusiness(url) {
    let arr = getBusinessCheckboxArray('id');
    var datapost="stringArray="+arr.join();
    $.ajax({
        type: "GET",
        method: "get",
        async:false,
        url: url,
        data:datapost,
        dataType: "text",
        success: function(text){
            if(text!==""){
                getLanguage()==="en" ? alert("Everything ended successfully.") : alert("Все завершено успешно.");
            }
            document.location.reload();
        },
        error: function () {
            getLanguage()==="en" ? alert("Something went wrong.") : alert("Что-то пошло не так.");
            document.location.reload();
        }
    });
}

function selectAllRadios() {
    $('input[type=checkbox]').each(function() {
           this.checked=true;
    });
}

function unselectAllRadios() {
    $('input[type=checkbox]').each(function() {
        this.checked=false;
    });
}

function getUsersCheckboxArray(type) {
    let arr = [];
    $('input[type=checkbox]').each(function() {
        if (this.checked) {
            var splited = this.value.split(",");
            if(type==="login"){
                arr.push(splited[0]);
            } else if (type==="status"){
                arr.push(splited[1]);
            }
        }
    });
    return arr;
}
function getBusinessCheckboxArray(type) {
    let arr = [];
    $('input[type=checkbox]').each(function() {
        if (this.checked) {
            var splited = this.value.split(",");
            if(type==="id"){
                arr.push(splited[0]);
            } else if (type==="name"){
                arr.push(splited[1]);
            } else if (type==="status"){
                arr.push(splited[2]);
            }
        }
    });
    return arr;
}

function doGetAdmin(url, type) {
    let arr = getUsersCheckboxArray('login');
    var datapost="stringArray="+arr.join();
    $.ajax({
        type: "GET",
        method: "get",
        async:false,
        url: url,
        data:datapost,
        dataType: "text",
        success: function(text){
            if(text!==""){
                getLanguage()==="en" ? alert("Everything ended successfully.") :alert("Все завершено успешно.");
            }
            document.location.reload();
        },
        error: function () {
            getLanguage()==="en" ? alert("Something went wrong.") :alert("Что-то пошло не так.");
            document.location.reload();
        }
    });
}

function getLanguage() {
    var lang="en";
    if(document.getElementById('footer-lang-en').innerText.trim()==="English"){
        lang="en";
    } else if (document.getElementById('footer-lang-en').innerText.trim()==="Английский"){
        lang="ru";
    }
    return lang;
}