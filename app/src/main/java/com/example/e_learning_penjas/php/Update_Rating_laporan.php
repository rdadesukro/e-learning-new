<?php 
 require_once 'koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$id_laporan = $_POST['id_laporan'];
 	$rating = $_POST['rating'];
 $dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7); $date 	= strtotime($dateNow."+ ".$staf." hours"); $h    = date('Y-m-d H:i:s',$date); $date2 = strtotime($h."+ ".$kadis." hours"); $g    = date('Y-m-d H:i:s',$date2);	
 	$query = "UPDATE laporan SET rating = $rating,status_laporan='selesai',tanggal_selesai='$dateNow' WHERE laporan.id_laporan = '$id_laporan'";

 	$exeQuery = mysqli_query($konek, $query); 

 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'Berhasil mengirimkan penilaian ke OPD terkait')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal diupdate'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>



 

