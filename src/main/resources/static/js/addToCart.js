$(document).ready(function () {
    $(".add_to_cart_button").on("click", function (e) {
        productId = $(this).attr("pid");
        addToCart();
    });
});


function addToCart() {
    let contextPath = "/";

    let quantity = $("#quantity" + productId).val();

    //Path for post mapping
    let url = contextPath + "cart/add/" + productId + "/" + quantity;

    $.ajax({
        type: "POST",
        url: url,
    }).done(function (response) {
        $("#modalTitle").text("Shopping cart");
        $("#modalBody").text(response);
        $("#myModal").modal();
    }).fail(function () {
        $("#modalTitle").text("Shopping cart");
        $("#modalBody").text("You must login to add products");
        $("#myModal").modal();
    });

}