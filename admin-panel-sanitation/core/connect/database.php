<?php 

try {
	$db = new PDO("mysql:host=localhost;dbname=sanitation", "YOUR-DB-UNAME", "YOUR-DB-PWD");
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}catch(PDOException $e) {
    die($e->getMessage());
}



?>
