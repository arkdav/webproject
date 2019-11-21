// вызов функции по завершению загрузки страницы
$(document).ready(function() {
    // вызов функции после потери полем 'productAmount' фокуса
    $('#productAmount').blur(function() {
        $.ajax({
            url : 'CartOrderController',     // URL - сервлет
            data : {                 // передаваемые сервлету данные
                productAmount : $('#productAmount').val(),
                productId : $('#productId').val()
            },
            success : function(response) {
                // обработка ответа от сервера
                $('#ajaxCartOrderResponse').text(response);
            }
        });
    });
});