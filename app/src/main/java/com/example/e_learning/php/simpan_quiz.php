<?php
if($_SERVER['REQUEST_METHOD']=='POST') {


   $response = array();
   //mendapatkan data
    $nis = addslashes(trim($_POST['nis']));
	$nama = addslashes(trim($_POST['nama']));
 	$nilai = addslashes(trim($_POST['nilai']));
 	$id_guru = addslashes(trim($_POST['id_guru']));
 	$id_kelas = addslashes(trim($_POST['id_kelas']));
 	$quiz = addslashes(trim($_POST['quiz']));
 $dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
   require_once('dbConnect.php');


  $sql2 =  "iNSERT INTO nilai (
     id_nilai,
     nis,
     nama,
     status,
     waktu,
    id_guru,
    id_kelas,
    quiz,
    nilai) VALUES (0,
'$nis',
'$nama',
'1',
'$dateNow',
'$id_guru',
'$id_kelas',
'$quiz',
'$nilai')";


     if(mysqli_query($con,$sql2)) {
	 
       $response["value"] = 1;
       $response["message"] =  "berhasil";
       echo json_encode($response);

     } else {
       $response["value"] = 0;
       $response["message"] = "oops! gagal up pemohons";
       echo json_encode($response);
     }
 
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