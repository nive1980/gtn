restControllers.service('productService',function($http,$cookies){
	this.getAllProducts = function() {
		return $http(
				{
					method : 'POST',
					url : '/gtn/fetchProductList'				
       })
	};
	
});