<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
    $syarat_value = addslashes(trim($_POST['syarat_value']));
		
		$dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
        $uuid=addslashes(trim($_POST['uuid']));
		 $uuid2=addslashes(trim($_POST['uuid2']));
        $pemohon_id=addslashes(trim($_POST['pemohon_id']));
        $layanan_id=addslashes(trim($_POST['layanan_id']));
     

 		$syarat = addslashes(trim($_POST['syarat']));
		$random = random_word(20);
		$path = "images/before/".$random.".png";
		$name = $random.".png";

		$actualpath = "http://192.168.43.48/e_pelayanan/$path";
   require_once('dbConnect.php');
   
  //$sql = "SELECT * FROM registrasis WHERE pemohon_id ='$pemohon_id' and layanan_id ='$layanan_id' ";

// $check = mysqli_fetch_array(mysqli_query($con,$sql));
 /*   if(isset($check)){
     $response["value"] = "2";
     $response["message"] = "Anda Sudah terdaftar!";
     echo json_encode($response);
   } else { */
	   $result = mysqli_query($con, "SELECT id FROM registrasis where uuid='$uuid'");
                  while($row = mysqli_fetch_assoc($result)){
                  $output=$row; 
				  $kalimat = implode(" ",$output);

}
 $result = mysqli_query($con, "SELECT layanans.`nama` FROM registrasis,layanans WHERE registrasis.uuid='$uuid' AND registrasis.`layanan_id`=layanans.`id`");
                  while($row = mysqli_fetch_assoc($result)){
                  $output=$row; 
				  $na = implode(" ",$output);

}



                
					$random = random_word(20);
		$path = "images/before/".$random.".png";
		$name = $random.".png";

		$actualpath = "http://192.168.43.48/e_pelayanan/$path";


		// sesuiakan ip address laptop/pc atau URL server
 
$query1 = "INSERT INTO registrasi_syarats (
id,
registrasi_id,
syarat_id,
syarat_value,
status,
created_at,
created_by,
updated_at,
updated_by,
uuid) VALUES (0,'$kalimat','$syarat','$name','1','1','1','1','1','$uuid2')";

	          
  
		
     if(mysqli_query($con,$query1)) {
	   file_put_contents($path,base64_decode($syarat_value));
       $response["value"] = "1";
	   $response["nama"] = $na;
	   $response["id_registrasi"] = $kalimat;
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = "0";
       $response["message"] = "oops! Coba lagi xx!";
       echo json_encode($response);
     }
	 
	
  // }
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