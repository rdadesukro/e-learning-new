<?php
require_once 'koneksi.php';

$nip;
$password;
 if (!empty(addslashes(trim($_GET['nip'])))){
       $nip = $_GET['nip'];
	    $password =md5( $_GET['password']);
	   $sql1= "SELECT nip FROM guru WHERE nip='$nip'";
		$sql= "SELECT pemohons.`instansi_id`,instansis.`instansi_id`AS id_ambil, pemohons.`nip`,pemohons.`nama`,pemohons.`alamat`,pemohons.`jk`,pemohons.`tempat_lahir`,pemohons.`pekerjaan`,pemohons.`tanggal_lahir`,pemohons.`no_hp` FROM pemohons,instansis where pemohons.nip='$nip' AND pemohons.`instansi_id`=instansis.`id`";
		$sql2= "SELECT nis FROM user WHERE nis LIKE '$nip'";
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
		VALUES (NULL,'$nip','$password','1','guru')";
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
	   echo "Data nip belum di input";
   }
?>
