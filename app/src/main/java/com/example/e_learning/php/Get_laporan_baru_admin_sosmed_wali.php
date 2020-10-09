
<?php 
require_once 'koneksi.php'; 

$timer=$_GET['timer'];

 $query = "SELECT laporan.id_laporan,laporan.id_user,user.nama_lengkap,kantor_dinas.kantor_dinas,masalah.masalah,laporan.saran_laporan,
laporan.status_laporan ,laporan.id_laporan,laporan.foto_laporan,laporan.tanggal_lapor,laporan.alamat,laporan.judul,laporan.isi_laporan,masalah.staf,masalah.kadis,
laporan.pelapor,laporan.telp_pelapor,laporan.status_laporan,laporan.timer,laporan.tanggal_selesai,
laporan.jenis_pengiriman,laporan.lng,laporan.lat
FROM laporan
LEFT JOIN user ON laporan.id_user=user.id_user 
LEFT JOIN masalah ON masalah.id_masalah=laporan.id_masalah
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
WHERE laporan.jenis_pengiriman IN ('twitter','instagram','facebook') AND laporan.status_laporan IN ('baru','tanggapan','selesai') AND laporan.timer='$timer'
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







