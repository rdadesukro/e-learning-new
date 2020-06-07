<?php
require_once 'koneksi.php';

$id_user;
 if (!empty($_GET['id_user'])){
       $id_user = $_GET['id_user'];
		$sql= "SELECT photo FROM db_sikesal.user where id_user='$id_user'";
		$result=mysqli_query($konek,$sql);
			if(mysqli_num_rows($result)>0){

					print(json_encode(mysqli_fetch_assoc($result)));

				}



		else{
	  print(json_encode(array("id_user" => 0)));
		}

	}else{
	   echo "Data id_user belum di input";
   }
?>
