<?php 
 require_once 'koneksi.php';

 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 	$id_laporan = $_POST['id_laporan'];
 	$id_user = $_POST['id_user'];
	$isi_komentar = $_POST['isi_komentar'];
 
 $dateNow = gmdate("Y-m-d H:i:s", time()+60*60*7);
 							$date = strtotime($dateNow."+ ".$staf." hours");
$h    = date('Y-m-d H:i:s',$date);

$date2 = strtotime($h."+ ".$kadis." hours");
$g    = date('Y-m-d H:i:s',$date2);

 	$query = "INSERT INTO `komentar` (`id_komentar`, `id_user`, `id_laporan`, `isi_komentar`,tanggal) VALUES (0, $id_user, $id_laporan, '$isi_komentar','$dateNow')";

 	$exeQuery = mysqli_query($konek, $query); 

 	echo ($exeQuery) ? json_encode(array('kode' =>1, 'pesan' => 'berhasil kirim koemntar')) :  json_encode(array('kode' =>2, 'pesan' => 'data gagal diupdate'));
 }
 else
 {
 	 echo json_encode(array('kode' =>101, 'pesan' => 'request tidak valid'));
 }

 ?>


 
 