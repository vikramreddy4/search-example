angular
	.module('myApp', ['ngResource'])
	.service('ReportService', function ($log, $resource) {
        return {
	    	getSitters : function () {
	        	var reportResource = $resource('sitter/all', {}, {
	            	get: {
	            		method: 'GET',
	        			params: {}, 
	            		isArray: false
	            	}
	            });
	            return reportResource.get();
	        }
        }
    })
    .controller('ReportController', function ($scope, $log, ReportService) {
		$scope.message = "";
		$scope.error = "";
		$scope.minRating = "0";
    	$scope.getSitters = function(version, myE) {
    		$scope.error = "";
    		$scope.partnerTxs = ReportService.getSitters();
        };
    });
