dashboardApp.service('licenseScreeningService', function () {
    var response = {};

    return {
        getResponse: function () {
            return response;
        },
        setResponse: function(value) {
        	response = value;
        }
    };
});
