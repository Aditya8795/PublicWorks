<?php

$key=$_GET['key'];
$con=mysqli_connect("localhost","root","","sanitation");

if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

$check="SELECT * FROM admin WHERE login_key = '$key'";
$rs = mysqli_query($con,$check);
if (!$rs) {
    printf("Error: %s\n", mysqli_error($con));
    exit();
}
$data = mysqli_fetch_array($rs, MYSQLI_NUM);
if($data[0] > 1) {
    echo "1";
}
else
	echo "0";


