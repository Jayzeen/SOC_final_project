$(document).ready(function () {
    //Item quantity are decreased when minus button is pressed
    $(".minusButton").on("click", function (evt) {
        evt.preventDefault();
         productId = $(this).attr("pid");
         qtyInput = $("#quantity" + productId);

         newQty = parseInt(qtyInput.val()) - 1;
        if (newQty > 0) qtyInput.val(newQty);
    });

    //Item quantity are increases when plus button is pressed
    $(".plusButton").on("click", function (evt) {
        evt.preventDefault();
        productId = $(this).attr("pid");
        qtyInput = $("#quantity" + productId);

        newQty = parseInt(qtyInput.val()) + 1;
        if (newQty < 10) qtyInput.val(newQty);
    });
});