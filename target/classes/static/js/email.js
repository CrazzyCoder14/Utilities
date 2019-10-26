$("#email_submit").click(function() {
	$('#failure_p').hide();
	$('#success_p').hide();
	var isvalidate = true;
	if (isvalidate) {
		$.ajax({
			type : 'POST',
			contentType : "application/json",
			url : "/api/extractemail",
			data : formToJSON(),
			statusCode : {
				200 : function() {
					console.log("Success");
				},
				201 : function() {
					console.log("error");
					$('#failure_Modal').modal('show');
				}
			}
		});
	}
});

function formToJSON() {
	var emailaddress = document.getElementById("emailaddress").value;
	var password = document.getElementById("password").value;

	var eqn = JSON.stringify({
		"emailAddress" : emailaddress,
		"password" : password

	});
	console.log(emailaddress);
	console.log(password);
	console.log(eqn);
	return eqn;
	
}
