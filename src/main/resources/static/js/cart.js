"use strict";

(function ($) {
    const app = {
        init: function () {
            this.bindEvents();

        },
        bindEvents: function () {
            $(".btn-cart").on("click", (e) => {
                let id = e.target.id;
                this.addCart(id);
            });

        },
        addCart: function (id) {
            console.log("id: " + id);
            let data = '{ "product" :{ "id":' + id + '},"quantity":"' + 1 + '"}';
            $.ajax({
                url: "/buyer/cart/items",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: data,
                success: (data) => {
                    console.log("ajax: " + data);
                },
                error: (error) => {
                    console.log("Error: " + error);
                    // location.reload();
                },
                complete: () => {
                    this.updateCartNumber();
                },
            });
        },
        updateCartNumber: function () {
            $.ajax({
                url: "/buyer/cart/items/count",
                type: "GET",
                dataType: "json",
                contentType: "application/json",
                success: (data) => {
                    console.log("ajax: " + data);
                    $("#cart-item-count").text(data);
                },
                error: (error) => {
                    console.log("Error: " + error);
                }
            });
        }

    }
    app.init();

})(jQuery);
