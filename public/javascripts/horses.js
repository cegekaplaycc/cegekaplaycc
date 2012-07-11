
var Horses = (function($) {

	function updateTotalPrice() {
		var totalPrice = $('.totalPriceForItem').toArray().map(function(item) {
			return parseInt($(item).html());
		}).reduce(function(prev, current){
			return prev + current;
		});
		$('#totalPrice').html(totalPrice);
	}
	
	return {
		FoodMarket: {
			updateTotalPrice: updateTotalPrice
		}
	};
	
})(jQuery);
