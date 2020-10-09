<?php
require_once 'koneksi.php';

$nis;
$password;
 if (!empty(addslashes(trim($_GET['nis'])))){
       $nis = $_GET['nis'];
	    $password =md5( $_GET['password']);
	   $sql1= "SELECT nis FROM siswa WHERE nis='$nis'";
		$sql= "SELECT pemohons.`instansi_id`,instansis.`instansi_id`AS id_ambil, pemohons.`nis`,pemohons.`nama`,pemohons.`alamat`,pemohons.`jk`,pemohons.`tempat_lahir`,pemohons.`pekerjaan`,pemohons.`tanggal_lahir`,pemohons.`no_hp` FROM pemohons,instansis where pemohons.nis='$nis' AND pemohons.`instansi_id`=instansis.`id`";
		$sql2= "SELECT nis FROM user WHERE nis LIKE '$nis'";
		$result=mysqli_query($konek,$sql1);
		$result2=mysqli_query($konek,$sql2);
			if(mysqli_num_rows($result)>0){
				if(mysqli_num_rows($result2)>0){
					 print(json_encode(array("nik" => 2)));
				}
				else{
				
				
					 
		$sql3 =  "INSERT INTO user (
		id,
		nis,
		password,
		token,
		status)
		VALUES (NULL,'$nis','$password','1','siswa')";
		if(mysqli_query($konek,$sql3)) {
	 print(json_encode(array("nik" => 3)));
     } else {
       
     }
	 
		
				
				}
				}



		else{
	  print(json_encode(array("nik" => 0)));
		}

	}else{
	   echo "Data nis belum di input";
   }
?>
