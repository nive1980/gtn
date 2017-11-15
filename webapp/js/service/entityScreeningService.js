dashboardApp.service('entityScreeningService', function () {
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
