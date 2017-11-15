'use strict';
/* App Module */
angular.module('sharedModule', []).factory("DataService", function () {

    // create store
    var myStore = new store();

    // create shopping cart
    var myCart = new shoppingCart("AngularStore");

   
    // return data object with store and cart
    return {
        store: myStore,
        cart: myCart
    };
});
/* App Module */

/* App Module */

var gtnApp = angular.module('gtn', ['ngRoute','restControllers','nvd3','sharedModule', 'ngMaterial', 'mdDataTable']);

gtnApp.config(['$routeProvider',
           	function($routeProvider) {
           		$routeProvider.
           			when('/',{
           				templateUrl: '/gtn/index-detail.html',
           				controller: 'HomeCtrl'
           			}).when('/index.html',{
           				templateUrl: '/index.html',
           				controller: 'HomeCtrl'
           			}).when('/forgotpassword',{
           				templateUrl: '/gtn/forgot_password.html',
           				controller: 'HomeCtrl'
           			}).when('/profile', {
           				templateUrl: '/gtn/profile.html',
           				controller: 'ProfileCtrl'
           			}).when('/security', {
           				templateUrl: '/gtn/security.html',
           				controller: 'SecurityCtrl'
           			}).when('/login',{
           				templateUrl: '/gtn/login.html',
           				controller: 'HomeCtrl'
           			}).when('/usersList', {
           				templateUrl : '/gtn/users-list.html',
           				controller : 'UsersCtrl'
           			}).when('/subscription',{
           				templateUrl: '/gtn/user-subscription.html',
           				controller: 'SubCtrl'
           			}).when('/subscriptionDetails', {
           				templateUrl : '/gtn/subscription-details.html',
           				controller : 'SubCtrl'
           			}).when('/payment', {
           				templateUrl: '/gtn/user_payment.html',
           				controller: 'PaymentGatewayCtrl'
           			}).when('/adduser', {
           				templateUrl : '/gtn/user.html',
           				controller : 'SubCtrl'
           			}).when('/store', {
           				templateUrl : '/gtn/store.htm',
           				controller : 'storeCtrl'
           			}).when('/products/:productSku', {
           				templateUrl : '/gtn/product.htm',
           				controller : 'storeCtrl'
           			}).when('/cart', {
           				templateUrl : '/gtn/shoppingCart.htm',
           				controller : 'storeCtrl'
           			}).when('/adduser', {
           				templateUrl : '/gtn/user.html',
           				controller : 'SubCtrl'
           			}).when('/billing',{
           				templateUrl: '/gtn/billing.html',
           				controller: 'BillingCtrl'
           			}).when('/publication',{
           				templateUrl: '/gtn/publication.html',
           				controller: 'PublicationCtrl'
           			}).when('/CaseMgt', {
           				templateUrl : '/gtn/user_support_tickets.html',
           				controller : 'caseMgtCtrl'
           			}).when('/activity', {
           				templateUrl: '/gtn/user-activity.html',
           				controller: 'activityCtlr'
           			}).otherwise({
                           redirectTo: '/'
                       });
           	}
]);
gtnApp.directive('fileModel', ['$parse', function ($parse) {
	    return {
	        restrict: 'A',
	        link: function(scope, element, attrs) {
	            var model = $parse(attrs.fileModel);
	            var modelSetter = model.assign;
	            
	            element.bind('change', function(){
	                scope.$apply(function(){
	                    modelSetter(scope, element[0].files[0]);
	                });
	            });
	        }
	    };
	}]);


var dashboardApp = angular.module('gtnDashboard', ['ngRoute', 'ngSanitize', 'controller', 'screeningController', 'ngResource', 'nvd3', 
                                                   'restControllers', 'ui.bootstrap', 'ngMaterial','sharedModule', 'toggle-switch', 'mdDataTable']);

dashboardApp.config(['$routeProvider', '$locationProvider', '$qProvider', 
   	function($routeProvider, $locationProvider, $qProvider) {
	
		//$httpProvider.interceptors.push('httpResponeInterceptor');
	
		$locationProvider.hashPrefix('');
		$qProvider.errorOnUnhandledRejections(false);
		
		$routeProvider.
   			when('/', {
				templateUrl: 'views/dashboard.html',
				controller: 'controller'
			}).
   			when('/createEntityScreening', {
   				templateUrl: 'views/gtc/entityScreening.html',
   				controller: 'entityScreeningCtlr'
   			}).
   			when('/resultEntityScreening', {
   				templateUrl: 'views/gtc/resultEntityScreening.html',
   				controller: 'entityScreeningResultCtlr'
   			}).
   			when('/createLicenseScreening', {
   				templateUrl: 'views/gtc/licenseScreening.html',
   				controller: 'licenseScreeningCtlr'
   			}).
   			when('/resultLicenseScreening', {
   				templateUrl: 'views/gtc/resultLicenseScreeningNew.html',
   				controller: 'licenseScreeningResultCtlr'
   			}).
   			when('/createBusinessIntelligence', {
   				templateUrl: 'views/bi/businessIntelligence.html',
   				controller: 'businessIntelligenceCtlr'
   			}).
   			when('/createShipmentWizard', {
   				templateUrl: 'views/export/outer.html',
   				controller: 'exportOperationCtlr'
   			}).
   			when('/createNewShipment', {
   				templateUrl: 'views/export/outer.html',
   				controller: 'exportOperationCtlr'
   			}).
   			when('/createExporter', {
   				templateUrl: 'views/export/exporter.html',
   				controller: 'exportOperationCtlr'
   			}).
   			when('/utlimateConsignee', {
   				templateUrl: 'views/export/ultimateConsignee.html',
   				controller: 'exportOperationCtlr'
   			}).
   			when('/intermediateConsignee', {
   				templateUrl: 'views/export/intermediateConsignee.html',
   				controller: 'exportOperationCtlr'
   			}).
   			when('/freightForwarder', {
   				templateUrl: 'views/export/freightForwarder.html',
   				controller: 'exportOperationCtlr'
   			}).
   			when('/shipmentBilling', {
   				templateUrl: 'views/export/shipmentBilling.html',
   				controller: 'exportOperationCtlr'
   			}).
   			when('/listItemCartons', {
   				templateUrl: 'views/export/listItemCartons.html',
   				controller: 'itemCartonController'
   			}).
   			when('/createAddItem', {
   				templateUrl: 'views/export/createAddItem.html',
   				controller: 'itemCartonController'
   			}).
   			when('/createAddCarton', {
   				templateUrl: 'views/export/createAddCarton.html',
   				controller: 'itemCartonController'
   			}).
   			when('/bookingCustomFiling', {
   				templateUrl: 'views/export/bookingCustomFiling.html',
   				controller: 'exportOperationCtlr'
   			}).   			
   			when('/shipmentDocument', {
   				templateUrl: 'views/export/shipmentDocument.html',
   				controller: 'exportOperationCtlr'
   			}).when('/viewPdf', {
   				templateUrl: 'views/publication/pdf_viewer.html',
   				controller: 'PublicationCtrl'
   			}).when('/publication',{
   				templateUrl: 'views/publication/publication.html',
   				controller: 'PublicationCtrl'
   			}).when('/publicationSub',{
   				templateUrl: 'views/publication/publicationSub.html',
   				controller: 'PublicationCtrl'
   			}).when('/createSearchSbu',{
   				templateUrl: 'views/admin/searchSbu.html',
   				controller: 'adminController'
   			}).when('/sbuParams',{
   				templateUrl: 'views/admin/searchGlobalParam.html',
   				controller: 'adminController'
   			}).when('/resultSbuConfig',{
   				templateUrl: 'views/admin/resultGlobalParam.html',
   				controller: 'adminController'
   			}).when('/createSbuConfig',{
   				templateUrl: 'views/admin/createGlobalParam.html',
   				controller: 'adminController'
   			}).when('/createSearchExporter',{
   				templateUrl: 'views/masters/searchExporter.html',
   				controller: 'masterCtlr'
   			}).when('/createAddExporter',{
   				templateUrl: 'views/masters/createAddExporter.html',
   				controller: 'masterCtlr'
   			}).when('/resultSearchExporter',{
   				templateUrl: 'views/masters/resultSearchExporter.html',
   				controller: 'masterCtlr'
   			}).when('/themes',{
   				templateUrl: 'views/user/theme.html',
   				controller: 'UsersCtrl'
   			}).when('/createSearchProduct',{
   				templateUrl: 'views/masters/searchProducts.html',
   				controller: 'masterCtlr'
   			}).when('/createAddProduct',{
   				templateUrl: 'views/masters/createAddProduct.html',
   				controller: 'masterCtlr'
   			}).when('/resultSearchProducts',{
   				templateUrl: 'views/masters/resultSearchProducts.html',
   				controller: 'masterCtlr'
   			}).when('/resultSearchProductManuf',{
   				templateUrl: 'views/masters/resultSearchProductManuf.html',
   				controller: 'masterCtlr'
   			}).when('/createAddProductManufacture',{
   				templateUrl: 'views/masters/createAddProductManufacture.html',
   				controller: 'masterCtlr'
   			}).when('/createSearchConsignee',{
   				templateUrl: 'views/masters/searchConsignee.html',
   				controller: 'masterCtlr'
   			}).when('/resultSearchConsignee',{
   				templateUrl: 'views/masters/resultSearchConsignee.html',
   				controller: 'masterCtlr'
   			}).when('/createAddConsignee',{
   				templateUrl: 'views/masters/createAddConsignee.html',
   				controller: 'masterCtlr'
   			}).when('/createSchedularAES',{
   				templateUrl: 'views/admin/createSchedularAES.html',
   				controller: 'adminController'
   			}).when('/createSearchScheduledJob',{
   				templateUrl: 'views/admin/createSearchScheduledJob.html',
   				controller: 'adminController'
   			}).when('/resultJobSchedular',{
   				templateUrl: 'views/admin/resultJobSchedular.html',
   				controller: 'adminController'
   			});	
   	}
]);