var app = angular.module('salaryCalculatorApp', ['ngSanitize', 'pascalprecht.translate']);

app.config(function ($translateProvider) {
	
	$translateProvider.translations('pl', {
	    CALCULATE_VALUE_DESCRIPTION: 'Oblicz miesięczną kwotę po odliczeniu kosztów',
		VALUE_PER_DAY_NET: 'Wartość dniówki netto',
		MONTH_VALUE: 'Miesięczna kwota',
		CALCULATE_MONTH_VALUE: 'Wylicz miesięczną kwotę',
		CHOOSE_COUNTRY: 'Wybierz kraj',
		POLAND: 'Polska',
		GERMANY: 'Niemcy',
		UK: 'Anglia',
        CHOOSE_COUNTRY_MEASSAGE: 'wybierz kraj',
        INSERT_NUMERIC_VALUE: 'wprowadź kwotę'
	});
	
	$translateProvider.preferredLanguage('pl');
	
	$translateProvider.useSanitizeValueStrategy('sanitizeParameters');
	
});

app.controller('monthSalaryCalculatorCtrl', function($scope, $translate, calculateMonthValueService, countriesService) {

	$scope.onlyNumbers = '/^\d+$/';

    $scope.dayNetValueInput = '';
    $scope.monthValue = '';
	$scope.countries = {};
	$scope.selectedCountry = '';

	countriesService.get().then(
        function(result) {
            $scope.countries = result.data;
            $scope.selectedCountry = '';
        }
    );

	$scope.calculate = function() {
	    var error = $scope.checkArguments($scope.selectedCurrency, $scope.dayNetValueInput);
	    if(!error) {
            calculateMonthValueService.calculate($scope.dayNetValueInput, $scope.selectedCountry).then(
                function(result) {
                    $scope.monthValue = result.data.value;
                }
            );
        }
    }

    $scope.changeCountry = function() {
        for(var index in $scope.countries) {
            if($scope.countries[index].name == $scope.selectedCountry) {
                $scope.selectedCurrency = $scope.countries[index].currencyCode;
            }
        }
    }

    $scope.checkArguments = function(currency, dayNetValue) {
        var error = null;
        $scope.isWarning = "";

        if(dayNetValue == undefined || dayNetValue == '' || !$scope.isNumber(dayNetValue)) {
            $translate('INSERT_NUMERIC_VALUE').then(function (translation) {
                $scope.monthValue = translation;
            }, function (translationId) {

            });
            error = "error";
            $scope.isWarning = "red";
        }
        if(currency == undefined || currency == '') {
            $translate('CHOOSE_COUNTRY_MEASSAGE').then(function (translation) {
                $scope.monthValue = translation;
            }, function (translationId) {

            });
            error = "error";
            $scope.isWarning = "red";
        }

        return error;
    }

    $scope.isNumber = function(n) {
      return !isNaN(parseFloat(n)) && isFinite(n);
    }

});

app.service('calculateMonthValueService', function($http) {
    this.calculate = function (dayNetValue, country) {
        var promise = $http({url: '/getMonthValue/' + country, method: 'GET', params: {dayNetValue: dayNetValue}}).then(function(result) {
            return result;
        });
        return promise;
    }
});

app.service('countriesService', function($http) {
    this.get = function() {
        var promise = $http.get('/getCountries').then(function(result) {
            return result;
        });
        return promise;
    }
});