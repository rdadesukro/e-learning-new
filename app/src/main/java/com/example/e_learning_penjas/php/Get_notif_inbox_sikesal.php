<?php
require_once 'koneksi.php';


$id_user=$_GET['id_user'];
$id_jbt=$_GET['id_jbt'];
$id_lokasi=$_GET['id_lokasi'];
if($id_jbt=='5'){
	$timer='0';
	$query = "SELECT count(id_laporan) as notif_sikesal FROM laporan LEFT JOIN masalah ON laporan.id_masalah=masalah.id_masalah
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
LEFT JOIN user ON user.id_kantor_dinas=kantor_dinas.id_kantor_dinas WHERE user.id_user='$id_user' AND
laporan.jenis_pengiriman='sikesal' AND laporan.status_laporan IN ('baru','tanggapan')
 AND laporan.timer='$timer'";
$result = mysqli_query($konek,$query);
}
else if($id_jbt=='2'){
	$timer='1';
	$query = "SELECT count(id_laporan) as notif_sikesal FROM laporan LEFT JOIN masalah ON laporan.id_masalah=masalah.id_masalah
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
LEFT JOIN user ON user.id_kantor_dinas=kantor_dinas.id_kantor_dinas WHERE user.id_user='$id_user' AND
laporan.jenis_pengiriman='sikesal' AND laporan.status_laporan IN ('baru','tanggapan')
 AND laporan.timer='$timer'";
$result = mysqli_query($konek,$query);
}
else if($id_jbt=='1'){
	$query = "SELECT count(id_laporan) as notif_sikesal from laporan where laporan.timer=2 and laporan.jenis_pengiriman='sikesal' and laporan.status_laporan in('baru','tanggapan')";
$result = mysqli_query($konek,$query);
}

else if($id_jbt=='4'){
	$query = "SELECT count(laporan.id_laporan) as notif_sikesal
	FROM laporan
	LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN masalah ON masalah.id_masalah=laporan.id_masalah
	LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	LEFT JOIN lokasi ON lokasi.id_lokasi=laporan.id_lokasi
	WHERE laporan.status_laporan IN ('baru','tanggapan','selesai') AND laporan.jenis_pengiriman='sikesal'
	AND laporan.id_lokasi='$id_lokasi'
	ORDER BY laporan.tanggal_lapor DESC";
$result = mysqli_query($konek,$query);
}


else if($id_jbt=='5'){
	$query = "SELECT count(laporan.id_laporan) as notif_sikesal
	FROM laporan
	LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN masalah ON masalah.id_masalah=laporan.id_masalah
	LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	LEFT JOIN lokasi ON lokasi.id_lokasi=laporan.id_lokasi
	WHERE laporan.status_laporan IN ('baru','tanggapan','selesai') AND laporan.jenis_pengiriman='sikesal'
	AND laporan.id_lokasi='$id_lokasi'
	ORDER BY laporan.tanggal_lapor DESC";
$result = mysqli_query($konek,$query);
}


else if($id_jbt=='3'){
	$query = "SELECT count(laporan.id_laporan) as notif_sikesal
	FROM laporan
	LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN masalah ON laporan.id_masalah=masalah.id_masalah
	LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	LEFT JOIN lokasi ON laporan.id_lokasi=lokasi.id_lokasi WHERE lokasi.id_induk='$id_lokasi'
	AND laporan.jenis_pengiriman='sikesal' ORDER BY laporan.tanggal_lapor DESC";
$result = mysqli_query($konek,$query);
}




  print(json_encode(mysqli_fetch_assoc($result)));
