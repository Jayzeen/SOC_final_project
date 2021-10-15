$(document).ready(function () {

    //Item quantity are decreased when minus button is pressed
    $(".minusButton").on("click", function (evt) {
        evt.preventDefault();
        decreaseQuantity($(this));
    });

    //Item quantity are increases when plus button is pressed
    $(".plusButton").on("click", function (evt) {
        evt.preventDefault();
        increaseQuantity($(this));
    });

    //Item is removed when remove icon is pressed
    $(".Link-remove").on("click", function (evt) {
        evt.preventDefault();
        removeFromCart($(this));
    })

    updateTotal();

});

//Deleting products
function removeFromCart(link) {
    url = link.attr("href");

    $.ajax({
        type: "POST",
        url: url,
    }).done(function (response) {
        $("#modalTitle").text("Shopping cart");
        if (response.includes("removed")) {
            $("#myModal").on("hide.bs.modal", function (e) {
               rowNumber = link.attr("rowNumber"); 
               removeProduct(rowNumber);
               updateTotal();
            });
        }

        $("#modalBody").text(response);
        $("#myModal").modal();
    }).fail(function () {
        $("#modalTitle").text("Shopping cart");
        $("#modalBody").text("You must login to add products");
        $("#myModal").modal();
    });
}

function removeProduct(rowNumber) {
    rowId = "row" + rowNumber;
    $("#" + rowId).remove();
}

function updateQuantity(productId, quantity) {
    let contextPath = "/";

    //Path for post mapping
    let url = contextPath + "cart/update/" + productId + "/" + quantity;

    $.ajax({
        type: "POST",
        url: url,
    }).done(function (newSubTotal) {
        updateSubTotal(newSubTotal, productId);
        updateTotal();
    }).fail(function () {
        $("#modalTitle").text("Shopping cart");
        $("#modalBody").text("You must login to add products");
        $("#myModal").modal();
    });

}

//Decreasing quantity
function decreaseQuantity(link) {
    productId = link.attr("pid");
    qtyInput = $("#quantity" + productId);

    newQty = parseInt(qtyInput.val()) - 1;
    if (newQty > 0) {
        qtyInput.val(newQty);
        updateQuantity(productId, newQty);
    }
}

//Increasing quantity
function increaseQuantity(link) {
    productId = link.attr("pid");
    qtyInput = $("#quantity" + productId);

    let newQty = parseInt(qtyInput.val()) + 1;
    if (newQty < 10) {
        qtyInput.val(newQty);
        updateQuantity(productId, newQty);
    }
}

//Updating the sub total whenever we update the cart item quantity
function updateSubTotal(newSubTotal, productId) {
    $("#subTotal" + productId).text(newSubTotal);
}

//Updating the total price of the shopping cart
function updateTotal() {
    total = 0.0;

    $(".productSubTotal").each(function (index, element) {
        total = total + parseFloat(element.innerHTML);
    });

    $(".totalAmount").text("Rs " + total);
    
}