<?php
require_once 'koneksi.php';
$kelurahan;
 if (!empty($_GET['kelurahan'])){
       $kelurahan = $_GET['kelurahan'];
	   $sql=mysqli_query($konek,"SELECT id_lokasi FROM lokasi WHERE kelurahan='$kelurahan'");
		if(mysqli_num_rows($sql)>0){
	   print(json_encode(mysqli_fetch_assoc($sql)));
   }
   else{
		print(json_encode(array("id_lokasi"=>0)));
   }
 }
?>




