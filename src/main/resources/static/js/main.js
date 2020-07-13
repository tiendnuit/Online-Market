function addItemToCart(id, quantity, showCart = false) {
    $.ajax ({
        url: '/cart/items',
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        data : '{ "product" :{ "id":'+ id +'},"quantity":"'+quantity+'"}',
        complete: function(responseData, status, xhttp){
            if (showCart) {
                location.href = '/cart'
            } else {
                updateCartItemCount();
            }

        }
    });
}

// function addItemToCart2(id) {
//     addItemToCart(id, $("#item-qty").val());
// }
//
// function addItemToCart3(id) {
//     addItemToCart(id, $("#item-qty").val(), true);
// }


function updateCartItemCount() {
    $.ajax ({
        url: '/cart/items/count',
        type: "GET",
        dataType: "json",
        contentType: "application/json",
        complete: function(responseData, status, xhttp){
            // $('#cart-item-count').text(responseData.responseJSON.count);
            Console.log(responseData.responseJSON.count);
        }
    });
}

function updateCartItemQuantity(id, quantity) {
    $.ajax ({
        url: '/cart/items',
        type: "PUT",
        dataType: "json",
        contentType: "application/json",
        data : '{ "product" :{ "id":'+ id +'},"quantity":"'+quantity+'"}',
        complete: function(responseData, status, xhttp){
            //updateCartItemCount();
            location.href = '/cart'
        }
    });
}

function removeItemFromCart(id) {
    $.ajax ({
        url: '/cart/items/'+id,
        type: "DELETE",
        dataType: "json",
        contentType: "application/json",
        complete: function(responseData, status, xhttp){
            //updateCartItemCount();
            location.href = '/cart'
        }
    });
}

$(document).ready(function() {
    var readURL = function(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.avatar').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $(".file-upload").on('change', function(){
        readURL(this);
    });

    $(".avatar").on('click', function() {
        $(".file-upload").click();
    });

    updateCartItemCount();
});