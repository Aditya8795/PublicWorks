<?php 
require ('core/init.php');
 
# if info is submitted
if (isset($_POST['submit'])) {
 
 //echo "well";
	/*if(empty($_POST['name']) || empty($_POST['vkey']) || empty($_POST['empno'])){
 
		$errors[] = 'This is required.';
 
	} */
 
	//if(empty($errors) === true){
		
		$name 	= htmlentities($_POST['name']);
		$empno 	= $_POST['empno'];
    $vkey =$_POST['vkey'];
		
  $users->addVolunteer($name,$empno,$vkey);
   echo 'Volunteer Added !';
  header('Location:./index.php');
		 exit();

	}
//}
 
//if (isset($_GET['success']) && empty($_GET['success'])) {
  //echo 'Volunteer Added !';
//}


?>




<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    

    <title>Sanitation-Admin Panel</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
  <a href="./members.php"> Click here to see Registered Volunteers </a>
    <div class="container">

      <form class="form-signin" method="POST" action="">
        <h2 class="form-signin-heading">Add Volunteers</h2>
        <label for="name" class="sr-only">Volunteer Name</label>
        <input type="text" id="name" name="name" class="form-control" placeholder="Name of Volunteer " required autofocus>
        <label for="empno" class="sr-only">Emp Number</label>
        <input type="text" id="empno" name="empno" class="form-control" placeholder="Emp Number" required>
        <label for="vkey" class="sr-only">Volunteer Key</label>
        <input type="text" id="vkey" name="vkey" class="form-control" placeholder="Volunteer key" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit" >Add</button>
      </form>
<?php 
		# if there are errors, they would be displayed here.
		if(empty($errors) === false){
			echo '<p>' . implode('</p><p>', $errors) . '</p>';
		}
 
		?>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

