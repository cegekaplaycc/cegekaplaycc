describe("Messages", function() {


	beforeEach(function() {
		loadFixtures("messages.html");
        jasmine.Clock.useMock();
        jQuery.fx.off = true;
	});


    it("SuccessMessage gets displayed", function() {
        Horses.Messages.showSuccess("blabla");
        expect($('#successMsg')).toBeVisible();
        expect($('#successMsg')).toHaveHtml("blabla");
    });

    it("SuccessMessage hides after 5 secs", function() {
        Horses.Messages.showSuccess("blabla");
        jasmine.Clock.tick(5001);
        expect($('#successMsg')).toBeHidden();
        expect($('#successMsg')).toHaveHtml("blabla");
    });

});