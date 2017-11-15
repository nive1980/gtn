//----------------------------------------------------------------
// store (contains the products)
//
// NOTE: nutritional info from http://www.cspinet.org/images/fruitcha.jpg
// score legend:
// 0: below 5% of daily value (DV)
// 1: 5-10% DV
// 2: 10-20% DV
// 3: 20-40% DV
// 4: above 40% DV
//
function store() {
    this.products = [];
    
}
store.prototype.getProduct = function (sku) {
    for (var i = 0; i < this.products.length; i++) {
        if (this.products[i].sku == sku)
            return this.products[i];
    }
    return null;
}
