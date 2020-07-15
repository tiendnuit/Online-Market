/**
 *
 */
////
function deleteProduct(){
    const productId = document.getElementById("productId").value;

    $.ajax({
        url: '/seller/product/deleteProduct/'+productId,
        type: 'GET',
        //contentType:'application/json',
        //data : productId,
        success: function(data) {
            if (data != "Ok"){
                alert("Successfully deleted");
            }},

        error: function(data){
            alert("Operation is fail" + data);
        }

    });
}

function productId(productId){
     document.getElementById("productId").value = productId;
     //jQuery(".myForm #deleteProductModal").modal();
}

