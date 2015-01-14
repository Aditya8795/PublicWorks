<?php
//require ('core/init.php');
mysql_connect('localhost','YOUR DB UNAME','YOUR DB PASSWORD');
mysql_select_db('sanitation');
$query="SELECT * FROM admin";
$result=mysql_query($query);

if(!$result){
  echo mysql_error();
 
}




echo "<table>";

while($row=mysql_fetch_assoc($result)){

    echo "<tr>
    <td>" .$row['name']. "</td>
    <td>" .$row['login_key']. "</td>
    <td>" .$row['emp_number']. "</td>
    </tr>";

}
echo "</table>";
echo "<br>";
echo "<a href='./index.php' style='float:right'>Admin-Panel</a>";




?>
