/**
 * Created by Hongzhi on 2014/12/27.
 */
describe("Task List", function(){
   it('should display list of tasks', function(){
       expect(repeater('tr.item').count()).toBe(3);
   }) ;
});