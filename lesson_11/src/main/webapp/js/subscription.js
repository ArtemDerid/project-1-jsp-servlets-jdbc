function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}

var subscriptions = null;
$.get("subscriptions", function(data) {
	if (data !== '') {
		subscriptions = data;
	}
}).done(function() {
	
	var tableContent = "<tr class='header'>"+
					"<th style='width: 15%;'>Name</th>"+
					"<th style='width: 15%;'>Description</th>"+
					"<th style='width: 15%;'>Price</th>"+
					"<th style='width: 15%;'>NumberOfPages</th>"+
					"<th style='width: 15%;'>ReleaseDate</th>"+
					"<th style='width: 10%;'>Discount</th>"+
					"<th style='width: 15%;'>Options</th>"+
					"</tr>";
	
	jQuery.each(subscriptions, function(i, value) {
	
		tableContent+="<tr>"+
					  "<td>" + value.name + "</td>"+
					  "<td>" + value.description + "</td>"+
					  "<td>" + value.price + "</td>"+
					  "<td>" + value.numberOfPages + "</td>"+
					  "<td>" + value.releaseDate + "</td>"+
					  "<td><p class='premium-user'>You have 10% discount!!!</p></td>"+
					  "<td><button onclick='deleteOrderFromSubscription(" + value.subscriptionId + ")'>delete</button></td>"+
					  "</tr>"
					   
	});
	
	  $('#myTable').html(tableContent);
	
}).done(function() {
	$.get("user-status", function(data) {
		if (data !== '') {
			userStatus = data;
		}
	}).done(function() {
		if(userStatus === 'REGULAR_USER'){
			$('p.premium-user').hide();
		}
	});
});



function deleteOrderFromSubscription(subscriptionId) {	
	var customUrl = '';
	var urlContent = window.location.href.split('/');
	for (var i = 0; i < urlContent.length-1; i++) {
		customUrl+=urlContent[i]+'/'
	}
	customUrl+='subscription?subscriptionId='+subscriptionId;
	
	$.ajax({
	    url: customUrl,
	    type: 'DELETE',
	    success: function(data) {
	    	if (data == 'Success') {
	    		location.reload();
			}
	    }
	});
}