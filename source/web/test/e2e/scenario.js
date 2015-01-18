/**
 * Created by Hongzhi on 2014/12/27.
 */
describe('scenario', function () {
    browser.get('/');

    it('should have nourriture in title', function () {
        expect(browser.getTitle()).toContain('Nourriture');
    });

    describe('login before other operations', function () {

        browser.get('#/login/');

        it('login in to the system', function () {
            element(by.model('loginData.username')).sendKeys('hongzhi');
            element(by.model('loginData.password')).sendKeys('123456');
            element(by.id('submit')).click();

            expect(browser.getLocationAbsUrl()).toContain('ingredient');
        });
    });
});