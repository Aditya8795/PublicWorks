
<?php
 $q=$_GET['key'];
 $mysqli=mysqli_connect('localhost','root','','sanitation') or die("Database Error");

 $my_data=mysqli_real_escape_string($mysqli,$q);
 $sql="SELECT * FROM admin WHERE login_key LIKE '%$my_data%'";
 $result = mysqli_query($mysqli,$sql) or die(mysqli_error($mysqli));
 if($result)
 {
  while($row=mysqli_fetch_array($result))
  {
   echo $row['login_key'];
   echo '#';
  }
 }
?>
