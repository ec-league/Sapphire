/**
 * Created by yun.li on 2015/11/25.
 */
var common = angular.module("app.common", []);
//显示列表
var commonCtrl = function($scope, $http, $q, $filter) {
	/*$http({
	    method: "POST",
	    url: "../Vendor/HotelPubDBVendor.ashx",
	    params: {
	        f: "GetVendorData"
	    },
	    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
	}).success(function (data) {
	    $scope.Vendorlist = data;
	}).error(function (reason) {
	    alert(reason);
	});*/
	$scope.Vendorlist = [{
		Type : "Bug",
		Priority : 'High',
		Subject : '无法返回主页',
		Deadline : "2015-12-01",
		Description : "这个问题很严重呀",
		Status : "Fixing"
	}];

}
