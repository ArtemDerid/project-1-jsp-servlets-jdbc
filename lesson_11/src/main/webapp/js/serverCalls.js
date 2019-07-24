$("button.createMagazine").click(function() {

	var name = $("form.createMagazine input.magazineName").val();
	var genre = $("form.createMagazine input.magazineGenre").val();
	var description = $("form.createMagazine input.magazineDescription").val();
	var price = $("form.createMagazine input.magazinePrice").val();
	var numberOfPages = $("form.createMagazine input.numberOfPages").val();

	var magazine = {
		name : name,
		genre : genre,
		description : description,
		price : price,
		numberOfPages : numberOfPages
	};

	$.post("magazine", magazine, function(data) {
		if (data == 'Success') {
			alert('Success');
		}
	});

});