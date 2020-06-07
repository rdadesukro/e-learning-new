<?php
require_once 'koneksi.php';


$sql= "SELECT laporan.id_laporan,laporan.id_user,user.nama_lengkap,kantor_dinas.kantor_dinas,masalah.masalah,laporan.saran_laporan,
laporan.status_laporan ,laporan.id_laporan,laporan.foto_laporan,laporan.foto_selesai,laporan.tanggal_lapor,laporan.alamat,laporan.judul,laporan.isi_laporan,masalah.staf,masalah.kadis,
laporan.pelapor,laporan.telp_pelapor,laporan.tanggapan_dinas,laporan.timer,laporan.tanggal_selesai,
laporan.jenis_pengiriman,laporan.lng,laporan.lat
FROM laporan
LEFT JOIN user ON laporan.id_user=user.id_user 
LEFT JOIN masalah ON masalah.id_masalah=laporan.id_masalah
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
WHERE laporan.status_laporan IN ('baru','tanggapan') AND masalah.id_kantor_dinas='17' AND laporan.jenis_pengiriman='sikesal' 
AND laporan.timer IN('0')
ORDER BY  laporan.tanggal_lapor DESC";






 // print(json_encode(mysqli_fetch_assoc($result)));
date_default_timezone_set('Asia/Jakarta');
$tanggal_sekarang = date('Y-m-d H:i:s');
$tanggal_sekarang_convert = strtotime( date('Y-m-d H:i:s'));

$result = mysqli_query($konek,$sql);
$arr = array();
while ($row = mysqli_fetch_assoc($result)) {


	$temp = array(
	"masalah" => $row["masalah"],
	"tanggal_lapor" => $row["tanggal_lapor"],
	"staf" => $row["staf"],
	"tanggal deadline" =>$cenvertedTime = date('Y-m-d H:i:s',strtotime('+'.$row["staf"].'minutes',
	(strtotime($row["tanggal_lapor"])))),
	"tanggal_sekarang" => $tanggal_sekarang,
	"selisih "=> $diff  = strtotime($cenvertedTime)-$tanggal_sekarang_convert,
	"hasilnya"=>$jam   = floor($diff / (60 * 60)*60),
	
	
);

	array_push($arr, $temp);
}

$data = json_encode($arr);
echo "$data";


?>


