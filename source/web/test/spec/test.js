/**
 * Created by Hongzhi on 2014/12/26.
 */

describe('loginController test', function () {
    beforeEach(module('app'));

    var loginCtrl;
    var $scope = {};
    var $httpBackend;

    beforeEach(inject(function (_$controller_, _$httpBackend_, _CONFIG_) {
        // The injector unwraps the underscores (_) from around the parameter names when matching
        loginCtrl = _$controller_('LoginCtrl', {$scope: $scope});
        $httpBackend = _$httpBackend_;

        $httpBackend.when('POST', _CONFIG_.SERVER_ROOT + 'o/token/')
            .respond({access_token: 'dadasdsadas'});
    }));

    it('should have loginCtrl', function () {
        expect(loginCtrl).toBeDefined();
    });

    it('should have function login in scope', function () {
        expect(typeof $scope.login == 'function').toBeTruthy();
    });

    it('should have loginData in scope', function () {
        expect($scope.loginData).toBeDefined();
    });

    it('should call login func', inject(function (CONFIG, authService) {
        $scope.loginData.username = 'zhz';
        $scope.loginData.password = 'zhz';
        $httpBackend.expectPOST(CONFIG.SERVER_ROOT + 'o/token/');

        $scope.login();

        $httpBackend.flush();

        expect(authService.authentication.isAuth).toBeTruthy();
    }));
});

