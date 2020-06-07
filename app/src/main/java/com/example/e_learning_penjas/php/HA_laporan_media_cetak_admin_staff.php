
<?php 
require_once 'koneksi.php'; 

$id_kantor_dinas=$_GET['id_kantor_dinas'];


 // print(json_encode(mysqli_fetch_assoc($result)));
date_default_timezone_set('Asia/Jakarta');
$tanggal_sekarang = date('Y-m-d H:i:s');
$tanggal_sekarang_convert = strtotime( date('Y-m-d H:i:s'));

 $query = "SELECT laporan.id_laporan,laporan.id_user,user.nama_lengkap,kantor_dinas.kantor_dinas,masalah.masalah,laporan.saran_laporan,
laporan.status_laporan ,laporan.id_laporan,laporan.foto_laporan,laporan.tanggal_lapor,laporan.alamat,laporan.judul,laporan.isi_laporan,masalah.staf,masalah.kadis,
laporan.pelapor,laporan.telp_pelapor,laporan.status_laporan,laporan.timer,laporan.tanggal_selesai,laporan.foto_selesai,
laporan.jenis_pengiriman,laporan.lng,laporan.lat
FROM laporan
LEFT JOIN user ON laporan.id_user=user.id_user 
LEFT JOIN masalah ON masalah.id_masalah=laporan.id_masalah
LEFT JOIN kantor_dinas ON masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
WHERE laporan.jenis_pengiriman IN ('media cetak','koran') AND laporan.status_laporan IN 
('baru') AND masalah.id_kantor_dinas='$id_kantor_dinas' AND laporan.timer IN ('0')
ORDER BY  laporan.tanggal_lapor DESC";
$result = mysqli_query($konek,$query);

 // print(json_encode(mysqli_fetch_assoc($result)));


$result = mysqli_query($konek,$query);
$arr = array();
while ($row = mysqli_fetch_assoc($result)) {


	$temp = array(
	"id_laporan" => $row["id_laporan"],
	"id_user" => $row["id_user"],
	"nama_lengkap" => $row["nama_lengkap"],	
	"kantor_dinas" => $row["kantor_dinas"],
	"masalah" => $row["masalah"],	
	"saran_laporan" => $row["saran_laporan"],
	"status_laporan" => $row["status_laporan"],
	"foto_laporan" => $row["foto_laporan"],
	"foto_selesai" => $row["foto_selesai"],
	"tanggal_lapor" => $row["tanggal_lapor"],
	"tanggal_lapor" => $row["tanggal_lapor"],
	"alamat" => $row["alamat"],	
	"isi_laporan" => $row["isi_laporan"],
	"jenis_pengiriman" => $row["jenis_pengiriman"],
	"judul" => $row["judul"],	
	"lng" => $row["lng"],	
	"lat" => $row["lat"],
	"pelapor" => $row["pelapor"],
	"telp_pelapor" => $row["telp_pelapor"],
	"tanggapan_dinas" => $row["tanggapan_dinas"],
	 "timer" => $row["timer"], 
	 "tanggal_selesai" => $row["tanggal_selesai"],
	
	"tanggal deadline" =>$cenvertedTime = date('Y-m-d H:i:s',strtotime('+'.$row["staf"].'minutes',
	(strtotime($row["tanggal_lapor"])))),
	"tanggal_sekarang" => $tanggal_sekarang,
	"selisih "=> $diff  = strtotime($cenvertedTime)-$tanggal_sekarang_convert,
	"hasilnya"=>$jam   = floor($diff / (60 * 60)*60),
	"deadline" =>floor($diff / (60 * 60)*60),
	"staf" =>$row["staf"],
	"kadis" =>$row["kadis"]
	
	
);

	array_push($arr, $temp);
}

$data = json_encode(array("kode" => 1, "result"=>$arr));
echo "$data";


?>
