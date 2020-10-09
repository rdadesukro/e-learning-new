<?php
require_once 'koneksi.php';


$id_lokasi=$_GET['id_lokasi'];

 $query = "
 SELECT laporan.id_laporan,laporan.id_user,user.nama_lengkap,kantor_dinas.kantor_dinas,masalah.masalah,laporan.saran_laporan,
laporan.status_laporan ,laporan.id_laporan,laporan.foto_laporan,laporan.foto_selesai,laporan.tanggal_lapor,laporan.alamat,laporan.judul,laporan.isi_laporan,masalah.staf,masalah.kadis,
laporan.pelapor,laporan.telp_pelapor,laporan.tanggapan_dinas,laporan.timer,laporan.tanggal_selesai,
laporan.jenis_pengiriman,laporan.lng,laporan.lat
FROM laporan
LEFT JOIN user ON laporan.id_user=user.id_user
LEFT JOIN masalah ON masalah.id_masalah=laporan.id_masalah
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
LEFT JOIN lokasi ON lokasi.id_lokasi=laporan.id_lokasi
WHERE laporan.status_laporan IN ('baru','tanggapan','selesai') AND laporan.jenis_pengiriman IN ('koran','media cetak')
AND laporan.id_lokasi='$id_lokasi'
ORDER BY laporan.id_laporan DESC";
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
