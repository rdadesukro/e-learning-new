<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
    $syarat_value = addslashes(trim($_POST['syarat_value']));
		
		
        $id=addslashes(trim($_POST['id']));
		
		
		
 $random = random_word(20);
		$path = "images/before/".$random.".png";
		$name = $random.".png";

		$actualpath = "http://192.168.1.71/e_pelayanan/$path";

		
   require_once('dbConnect.php');
   
 
	

                
	    $random = random_word(20);
		$path = "images/before/".$random.".png";
		$name = $random.".png";

		$actualpath = "http://192.168.1.71/e_pelayanan/$path";


		// sesuiakan ip address laptop/pc atau URL server
 
$query1 = "UPDATE registrasi_syarats SET syarat_value='$name',status='1' WHERE id='$id'";

	          
  
		
     if(mysqli_query($con,$query1)) {
	   file_put_contents($path,base64_decode($syarat_value));
       $response["value"] = "1";
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