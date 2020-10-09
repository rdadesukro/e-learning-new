<?php
require_once 'koneksi.php';
$search = $_POST['search'];


$status_laporan=$_GET['status_laporan'];

	$query = "SELECT laporan.id_laporan,user.id_user,user.nama_lengkap,masalah.masalah,kantor_dinas.kantor_dinas,laporan.tanggapan_dinas,
	laporan.alamat,laporan.judul,laporan.isi_laporan,laporan.saran_laporan,laporan.jenis_pengiriman,laporan.tanggal_lapor,
	laporan.tanggal_selesai, laporan.lng,laporan.lat,laporan.foto_laporan,laporan.foto_selesai,laporan.status_laporan,
	laporan.rating,user.photo,COUNT(komentar.id_komentar) AS jumlah_komen FROM laporan LEFT JOIN komentar
	USING(id_laporan) LEFT JOIN masalah USING(id_masalah)LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN kantor_dinas ON laporan.id_masalah=masalah.id_masalah AND masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	WHERE laporan.judul LIKE '%$search%' AND laporan.status_laporan='selesai' AND laporan.jenis_pengiriman='sikesal'
	GROUP BY id_laporan ORDER BY laporan.view DESC";



$result = mysqli_query($konek,$query);

 // print(json_encode(mysqli_fetch_assoc($result)));


$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?

json_encode(array("kode" => 1,"search_count" => count($array), "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
?>

