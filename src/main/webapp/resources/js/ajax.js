// $(document).ready(function(){
//         $('#searchform').submit(function(){
//             $.ajax({
//                 type:"POST",
//                 method: "post",
//                 url: "catalog",
//                 dataType: "json",
//                 data: "searchString="+$("#searchString").val(),
//                 success: function(data) {
//                     var productsList = [];
//                     for (var i=0; i<data.result.length; i++){
//                         productsList[i].productId=data.result[i].productId;
//                         productsList[i].name=data.result[i].name;
//                         productsList[i].description=data.result[i].description;
//                         productsList[i].imageLink=data.result[i].imageLink;
//                         productsList[i].price=data.result[i].price;
//                     }
//                     $('#row').html(productsList);
//                     alert('Load was performed.');
//                 }
//             });
//             return false;
//             });
//     });
