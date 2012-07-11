
var Horses = (function($) {

	function updateTotalPrice() {
		var totalPrice = $('.totalPriceForItem').toArray().map(function(item) {
			return parseInt($(item).html());
		}).reduce(function(prev, current){
			return prev + current;
		});
		$('#totalPrice').html(totalPrice);
	}

    function showMessage(elementId, data) {
        console.dir(data);
        $(elementId).html(data).fadeIn('fast', function(){
            var me = $(this);
            setTimeout(function(){
                me.fadeOut('fast');
            }, 5000);
        });
    }
	
	return {
		FoodMarket: {
			updateTotalPrice: updateTotalPrice
		},
        Messages: {
            showSuccess: function(data){showMessage('#successMsg', data)},
            showError: function(data){showMessage('#errorMsg', data.responseText)}
        }
	};
	
})(jQuery);


