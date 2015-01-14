<?php

class Users{
 
 private $db;

 public function __construct($database){
  
   $this->db=$database;

}

 

 
public function addVolunteer($name,$empno,$vkey){
	

 
	$query 	= $this->db->prepare("INSERT INTO `admin` (name,emp_number,login_key) VALUES ('$name','$empno','$vkey') ");
 
	

	try{
	$query->execute();
     
	
	}catch(PDOException $e){
		die($e->getMessage());
	}	
}


  
 

/*
public function members($id) {
 
	$query = $this->db->prepare("SELECT * FROM `admin` WHERE `Id`= ?");
	$query->bindValue(1, $id);
 
	try{
 
		$query->execute();
 
		return $query->fetch();
 
	} catch(PDOException $e){
 
		die($e->getMessage());
	}
}
*/

}
?>
