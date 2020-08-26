<?php
if($_SERVER['REQUEST_METHOD']=='POST') {


   $response = array();
   //mendapatkan data
 
		
 		$nis = addslashes(trim($_POST['nis']));
 		$foto = addslashes(trim($_POST['foto']));
 		  $random = random_word(20);
  $path = "images/profil/".$random.".png";
		$name = $random.".png";

	 $actualpath ="http://192.168.43.48/penjas/$path";
   require_once('dbConnect.php');
   //Cek npm sudah terdaftar apa belum
  

  
     $sql1 =  "UPDATE siswa SET foto='$name' WHERE nis='$nis'";
		
		
		
		
     if(mysqli_query($con,$sql1)) {
	   file_put_contents($path,base64_decode($foto));
       $response["value"] = 1;
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi ade!";
       echo json_encode($response);
     }
	 
	 
   
   // tutup database
   mysqli_close($con);
   } else {
  $response["value"] = 0;
  $response["message"] = "oops! Coba lagi! cc";
  echo json_encode($response);
}
function random_word($id = 20){
		$pool = '1234567890abcdefghijkmnpqrstuvwxyz';
		
		$word = '';
		for ($i = 0; $i < $id; $i++){
			$word .= substr($pool, mt_rand(0, strlen($pool) -1), 1);
		}
		return $word; 
	}