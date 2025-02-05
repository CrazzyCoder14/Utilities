<!DOCTYPE html>
<html lang="en">
<head>
<title>Utility Wizard</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
<link rel="stylesheet" href="css/email.css">
<script defer src="js/email.js"></script>

</head>
<body>
	<br>
	<br>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="home" style="color: #1a4791">Utility Wizard</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="home">Home</a></li>
				<li><a href="contactus">Contact Us</a></li>
				
			</ul>
		</div>
	</nav>
	<div class="container">	
		<div class="incontainer">
			<form method="post" id="csvform">
				<label for="filename">File Name</label> <input type="text"
					id="filename" name="filename" required>
				<label for="inputfile">File to upload</label> <input type="file"
					id="inputfile" name="inputfile" required>	
				<input type="reset" value="Reset" id="reset"> <input
					type="button" value="Submit" id="csv_submit">
			</form>
		</div>
	</div>
	<div id="failure_Modal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Ooops!! Username or Password does not match :(</h4>
				</div>
				<div class="modal-footer">
					<input type="button" data-dismiss="modal" value="close">
				</div>
			</div>
		</div>
	</div>
</body>
</html>