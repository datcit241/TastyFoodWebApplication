class Cart {
    calculate(e) {
        let cartItemId = $(e).attr("data-id");

        let attributeQuery = `[data-id='${cartItemId}']`;

        let quantity = parseInt($(".quantity" + attributeQuery).val());
        let price = parseInt($(".price" + attributeQuery).val());
        $(".total-price" + attributeQuery).text("$" + quantity * price);
    }
}