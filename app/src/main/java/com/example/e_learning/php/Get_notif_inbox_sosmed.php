<?php 
require_once 'koneksi.php'; 


$id_user=$_GET['id_user'];
$id_jbt=$_GET['id_jbt'];
if($id_jbt=='5'){
	$timer='0';
	
$query = "SELECT count(id_laporan) as notif_sosmed FROM laporan LEFT JOIN masalah ON laporan.id_masalah=masalah.id_masalah 
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas 
LEFT JOIN user ON user.id_kantor_dinas=kantor_dinas.id_kantor_dinas WHERE user.id_user='$id_user' AND 
laporan.jenis_pengiriman IN('twitter','facebook','instagram') AND laporan.status_laporan IN ('baru')
 AND laporan.timer='$timer'";
}
else if($id_jbt=='2'){
	$timer='1';
	
$query = "SELECT count(id_laporan) as notif_sosmed FROM laporan LEFT JOIN masalah ON laporan.id_masalah=masalah.id_masalah 
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas 
LEFT JOIN user ON user.id_kantor_dinas=kantor_dinas.id_kantor_dinas WHERE user.id_user='$id_user' AND 
laporan.jenis_pengiriman IN('twitter','facebook','instagram') AND laporan.status_laporan IN ('baru')
 AND laporan.timer='$timer'";
}
else if($id_jbt=='1'){
$query = "SELECT count(id_laporan) as notif_sosmed from laporan where laporan.timer=2 and laporan.jenis_pengiriman IN('twitter','facebook','instagram') and laporan.status_laporan in('baru')";
}


$result = mysqli_query($konek,$query);

  print(json_encode(mysqli_fetch_assoc($result)));

