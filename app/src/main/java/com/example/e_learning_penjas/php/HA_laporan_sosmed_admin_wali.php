<?php 
require_once 'koneksi.php'; 



$id_kantor_dinas=$_GET['id_kantor_dinas'];
$timer=$_GET['timer'];

$query = "SELECT laporan.id_laporan,laporan.id_user,user.nama_lengkap,kantor_dinas.kantor_dinas,masalah.masalah,laporan.saran_laporan,
laporan.status_laporan ,laporan.id_laporan,laporan.foto_laporan,laporan.foto_selesai,laporan.tanggal_lapor,laporan.alamat,laporan.judul,laporan.isi_laporan,masalah.staf,masalah.kadis,
laporan.pelapor,laporan.telp_pelapor,laporan.tanggapan_dinas,laporan.timer,laporan.tanggal_selesai,
laporan.jenis_pengiriman,laporan.lng,laporan.lat
FROM laporan
LEFT JOIN user ON laporan.id_user=user.id_user 
LEFT JOIN masalah ON masalah.id_masalah=laporan.id_masalah
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
WHERE laporan.status_laporan IN ('baru') AND laporan.jenis_pengiriman IN('facebook','instagram','twitter')
AND laporan.timer IN('2')
ORDER BY  laporan.tanggal_lapor DESC";

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






