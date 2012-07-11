
describe("FoodMarket", function() {
	
	beforeEach(function() {
		loadFixtures("foodMarket.html");
	});
	
	it("TotalPrice should be updated with sum of individual amounts", function() {
		$('#totalPrice_1').html(5);
		$('#totalPrice_2').html(7);
		Horses.FoodMarket.updateTotalPrice();
		expect($('#totalPrice')).toHaveHtml(12);
	});
	
});