"use strict";

(function($) {
    const app = {
        init: function() {
            this.bindEvents();


        },
        bindEvents: function() {
            $(".btn-approve").on("click", (e) => {
                let id = e.target.id;
                this.approveReview(id);
            });
        },
        approveReview: function(id) {
            console.log("id: "+ id);
            debugger;
            $.ajax ({
                url: '/admin/reviews/'+id,
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
        }

    }
    app.init();

})(jQuery);
