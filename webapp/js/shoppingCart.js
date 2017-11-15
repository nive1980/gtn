
function shoppingCart(cartName) {
    this.cartName = cartName;
    this.clearCart = false;
    this.checkoutParameters = {};
    this.items = [];

    // load items from local storage when initializing
    this.loadItems();

    // save items to local storage when unloading
    var self = this;
    $(window).unload(function () {
       });
}
function getCookie(name) {
    var cookieValue = null;
    if (document.cookie && document.cookie != '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = jQuery.trim(cookies[i]);
            // Does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) == (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}
shoppingCart.prototype.shopCart= function(totlPrice) {

var csrftoken = getCookie('XSRF-TOKEN');
		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
			}
var dataToBeSent = {
			"items" : this.items,
				"userId":$.cookie('username')
		
		}
	
this.clearItems();

var that = this;
$.ajax({
			type : 'POST',
beforeSend : function(xhr) {
			              xhr.setRequestHeader("X-XSRF-TOKEN", csrftoken);
	},
			url : '/gtn/shopTheCart',
			responseType : 'json',
			data: JSON.stringify(dataToBeSent),
			 contentType: "application/json; charset=utf-8",
			headers : JSON.stringify(headerTobeSent),
			success : function(r) {

    that.clearItems();
//localStorage.setItem('billingamount',totlPrice);
//location.href = '/gtn/usermgt-index.html#/payment';
//location.reload();

			},
			error : function(r) {

			}
		});

}
shoppingCart.prototype.updatePublicationStatus= function() {
var csrftoken = getCookie('XSRF-TOKEN');
		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
			}
var dataToBeSent = {
			"items" : this.items,
				"userId":$.cookie('username')
		
		}
this.clearItems();

var that = this;
$.ajax({
			type : 'POST',
beforeSend : function(xhr) {
			              xhr.setRequestHeader("X-XSRF-TOKEN", csrftoken);
	},
			url : '/gtn/updatePublicationStatus',
			responseType : 'json',
			data: JSON.stringify(dataToBeSent),
			 contentType: "application/json; charset=utf-8",
			headers : JSON.stringify(headerTobeSent),
			success : function(r) {

    that.clearItems();
//localStorage.setItem('billingamount',totlPrice);
//location.href = '/gtn/usermgt-index.html#/payment';
//location.reload();

			},
			error : function(r) {

			}
		});

}


// load items from local storage
shoppingCart.prototype.loadItems = function () {
    var items = localStorage != null ? localStorage[this.cartName + "_items"] : null;

if (items != null && JSON != null) {


        try {
            var items = JSON.parse(items);

            for (var i = 0; i < items.length; i++) {
                var item = items[i];

                if (item.sku != null && item.name != null && item.price != null && item.quantity != null) {

                                    
item = new cartItem(item.sku,item.type, item.name, item.price, item.quantity,item.itemId);
			
  this.items.push(item);
                }
            }
        }
        catch (err) {
            // ignore errors while loading...
        }
    }
}

// save items to local storage
shoppingCart.prototype.saveItems = function () {
    if (localStorage != null && JSON != null) {
        localStorage[this.cartName + "_items"] = JSON.stringify(this.items);
    }
}

// adds an item to the cart
shoppingCart.prototype.addItem = function (itemId,sku,type, name, price, quantity, currency) {
    quantity = this.toNumber(quantity);


    if (quantity != 0) {

        // update quantity for existing item
        var found = false;
        for (var i = 0; i < this.items.length && !found; i++) {
            var item = this.items[i];
            if (item.sku == sku) {
                found = true;
                item.quantity = quantity;
                if (item.quantity <= 0) {
                    this.items.splice(i, 1);
                }
            }
        }

        // new item, add now
        if (!found) {
            var item = new cartItem(sku,type, name, price, quantity,itemId, currency);
            this.items.push(item);
        }

        // save changes
        this.saveItems();
    }
}

// removes an item to the cart
shoppingCart.prototype.removeItem = function (itemId,sku,type, name, price, quantity) {

    quantity = this.toNumber(quantity);
    if (quantity != 0) {

        // update quantity for existing item
        var found = false;
        for (var i = 0; i < this.items.length && !found; i++) {
            var item = this.items[i];
            if (item.sku == sku) {
                found = true;
                var totalcost=localStorage.getItem('pubTotalPriceToPass');
                totalcost=parseFloat(totalcost)-parseFloat(price);
                localStorage.setItem('pubTotalPriceToPass',totalcost);
                    this.items.splice(i, 1);
             }
        }


        // save changes
        this.saveItems();
      var csrftoken = getCookie('XSRF-TOKEN');
		var headerTobeSent = {
				'Authorization' : 'Basic ' + btoa($.cookie('username') + ':' + $.cookie('password'))
			}
var dataToBeSent = {
			"id" : itemId,
				"userId":$.cookie('username')
		
		}

var that = this;
$.ajax({
			type : 'POST',
beforeSend : function(xhr) {
			              xhr.setRequestHeader("X-XSRF-TOKEN", csrftoken);
	},
			url : '/gtn/unmapPublication',
			responseType : 'json',
			data: JSON.stringify(dataToBeSent),
			 contentType: "application/json; charset=utf-8",
			headers : JSON.stringify(headerTobeSent),
			success : function(r) {

//localStorage.setItem('billingamount',totlPrice);
//location.href = '/gtn/usermgt-index.html#/payment';
location.reload();

			},
			error : function(r) {

			}
		});

  
    }
location.reload();

}
// get the total price for all items currently in the cart
shoppingCart.prototype.getTotalPrice = function (sku) {
    var total = 0;
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        if (sku == null || item.sku == sku) {
            total += this.toNumber(item.quantity * item.price);
        }
    }
    return total;
}

// get the total price for all items currently in the cart
shoppingCart.prototype.getTotalCount = function (sku) {
    var count = 0;
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        if (sku == null || item.sku == sku) {
            count += this.toNumber(item.quantity);
        }
    }
    return count;
}

// clear the cart
shoppingCart.prototype.clearItems = function () {

this.items = [];
    this.saveItems();
    
}

// define checkout parameters
shoppingCart.prototype.addCheckoutParameters = function (serviceName, merchantID, options) {

    // check parameters
    if (serviceName != "PayPal" && serviceName != "Google") {
        throw "serviceName must be 'PayPal' or 'Google'.";
    }
    if (merchantID == null) {
        throw "A merchantID is required in order to checkout.";
    }

    // save parameters
    this.checkoutParameters[serviceName] = new checkoutParameters(serviceName, merchantID, options);
}

// check out
shoppingCart.prototype.checkout = function (serviceName,totalPrice,currency) {

 }


// utility methods
shoppingCart.prototype.addFormFields = function (form, data) {
    if (data != null) {
        $.each(data, function (name, value) {
            if (value != null) {
                var input = $("<input></input>").attr("type", "hidden").attr("name", name).val(value);
                form.append(input);
            }
        });
    }
}
shoppingCart.prototype.toNumber = function (value) {
    value = value * 1;
    return isNaN(value) ? 0 : value;
}

//----------------------------------------------------------------
// checkout parameters (one per supported payment service)
//
function checkoutParameters(serviceName, merchantID, options) {
    this.serviceName = serviceName;
    this.merchantID = merchantID;
    this.options = options;
}

//----------------------------------------------------------------
// items in the cart
//
function cartItem(sku,type, name, price, quantity,itemId, currency) {
    this.sku = sku;
this.itemId=itemId;

this.type=type;
	    this.name = name;
    this.price = price * 1;
    this.quantity = quantity * 1;
    this.currency = currency;
}
