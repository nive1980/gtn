dashboardApp.directive('bindHtmlCompile', ['$compile', function ($compile) {
        return {
            restrict: 'A',
            link: function (scope, element, attrs) {
                scope.$watch(function () {
                    return scope.$eval(attrs.bindHtmlCompile);
                }, function (value) {
                    element.html(value);
                    $compile(element.contents())(scope);
                });
            }
        };
    }]);


dashboardApp.directive( 'compileData', function ( $compile ) {
	  return {
	    scope: true,
	    link: function ( scope, element, attrs ) {

	      var elmnt;

	      attrs.$observe( 'template', function ( myTemplate ) {
	        if ( angular.isDefined( myTemplate ) ) {
	          // compile the provided template against the current scope
	          elmnt = $compile( myTemplate )( scope );

	            element.html(""); // dummy "clear"

	          element.append( elmnt );
	        }
	      });
	    }
	  };
	});