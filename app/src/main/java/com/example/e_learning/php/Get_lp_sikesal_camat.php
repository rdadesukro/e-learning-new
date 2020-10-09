<?php
require_once 'koneksi.php';


$id_lokasi=$_GET['id_lokasi'];



 $query = "
 SELECT laporan.id_laporan,laporan.id_user,user.nama_lengkap,kantor_dinas.kantor_dinas,masalah.masalah,
laporan.saran_laporan,laporan.status_laporan,laporan.foto_laporan,laporan.foto_selesai,laporan.tanggal_lapor,laporan.tanggal_selesai,
laporan.alamat,laporan.judul,laporan.isi_laporan,laporan.pelapor,laporan.telp_pelapor,laporan.tanggapan_dinas,laporan.timer,laporan.jenis_pengiriman,
laporan.lng,laporan.lat
FROM laporan
LEFT JOIN user ON laporan.id_user=user.id_user
LEFT JOIN masalah ON laporan.id_masalah=masalah.id_masalah
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
LEFT JOIN lokasi ON laporan.id_lokasi=lokasi.id_lokasi WHERE lokasi.id_induk='$id_lokasi'
AND laporan.jenis_pengiriman='sikesal' ORDER BY laporan.tanggal_lapor DESC";
$result = mysqli_query($konek,$query);

 // print(json_encode(mysqli_fetch_assoc($result)));


$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?
json_encode(array("kode" => 1, "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));

// $hijri="lian";


// $myObj->id_masalah = $hijri;
// $myObj->id_kantor_dinas = 30;


// $myJSON = json_encode($myObj);

// echo $myJSON;
