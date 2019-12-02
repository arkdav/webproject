function getData(url, contextPath) {
    var datapost = $('#searchform').serialize();
    $.ajax({
        type:"POST",
        method: "post",
        url: url,
        data: datapost,
        dataType: "json",
        success: function(data) {
            $('#row').remove();
            $('#pagination').remove();
            let divRow = document.createElement('div');
            divRow.className="row";
            divRow.id="row";
            let elem= document.getElementById('catalog');
            elem.append(divRow);
                for (let i=0; i<data.length; i++) {
                    let divProduct = document.createElement('div');
                    divProduct.className="product col-4";
                    divProduct.id="product";

                    let divImage=document.createElement('div');
                    divImage.className="product-img";
                    divImage.id="pr_image";
                    let image=document.createElement('img');
                    image.src=contextPath+"/images/"+data[i].imageLink;
                    divImage.appendChild(image);

                    let divProductNamePrice=document.createElement('div');

                    let hProductName=document.createElement('h2');
                    hProductName.className="product-name";
                    let aProductName=document.createElement('a');
                    aProductName.id="pr_name";
                    aProductName.href=contextPath+"/productdata/"+data[i].productId;
                    aProductName.textContent=data[i].name;
                    hProductName.appendChild(aProductName);

                    let hPrice=document.createElement('h4');
                    hPrice.className="product-price";
                    hPrice.id="pr_price";
                    hPrice.textContent=data[i].price+"$";

                    divProductNamePrice.appendChild(hProductName);
                    divProductNamePrice.appendChild(hPrice);
                    divProduct.appendChild(divImage);
                    divProduct.appendChild(divProductNamePrice);
                    divRow.appendChild(divProduct);
            }
            let ulPagination = document.createElement('ul');
            ulPagination.className="pagination";
            ulPagination.id="pagination";

            $.ajax({
                type:"POST",
                method: "post",
                async: false,
                url: contextPath+"/pages",
                data: datapost,
                dataType: "json",
                success: function(data) {
                    if(data.length===0){
                        let p = document.createElement('p');
                        p.textContent="No products.";
                        divRow.append(p);
                    }
                    for (let j=0; j<data.length; j++) {
                        let liPagination = document.createElement('li');
                        liPagination.className="page-item";
                        let aPagination = document.createElement('a');
                        aPagination.className="page-link";
                        aPagination.onclick=function(){
                            getData(contextPath+"/catalog?pageid="+data[j].pageId, contextPath);
                        }
                        aPagination.textContent=data[j].pageId;
                        liPagination.appendChild(aPagination);
                        ulPagination.appendChild(liPagination);
                    }
                    elem.append(ulPagination);
                }
            });
        },
        error: function () {
        }
    });
}
