"use strict";

(function($) {
    const app = {
        init: function() {
            this.bindEvents();


        },
        bindEvents: function() {
            $(".btn-follow").on("click", (e) => {
                let id = e.target.id;
                this.follow(id);
            });

            $(".btn-unfollow").on("click", (e) => {
                let id = e.target.id;
                this.unfollow(id);
            });

            $(".send").on("click", (e) => {
                let id = e.target.id;
                let message = $("#message-review").val();
                this.review(id, message);
            });
        },
        unfollow: function(id) {
            console.log("id: "+ id);
            $.ajax ({
                url: '/buyer/unfollow/'+id,
                type: "PUT",
                success: (data) => {
                    console.log("ajax: " + data);
                    location.reload();
                },
                error: (error) => {
                    console.log("Error: " + error);
                    location.reload();
                }
            });
        },

        follow: function(id) {
            console.log("id: "+ id);
            $.ajax ({
                url: '/buyer/follow/'+id,
                type: "PUT",
                success: (data) => {
                    console.log("ajax: " + data);
                    location.reload();
                },
                error: (error) => {
                    console.log("Error: " + error);
                    location.reload();
                }
            });
        },

        review: function(productId, message) {
            console.log("id: "+ productId);
            console.log("message: "+ message);
            let params = {"message" : message};
            $.ajax ({
                url: '/buyer/review/'+productId,
                type: "POST",
                data: params,
                dataType: "json",
                success: (data) => {
                    console.log("ajax: " + data);
                    location.reload();
                },
                error: (error) => {
                    console.log("Error: " + error);
                    location.reload();
                }
            });
        }

    }
    app.init();

})(jQuery);
