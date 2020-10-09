<?php
require_once 'koneksi.php';

$jenis_pengiriman=$_GET['jenis_pengiriman'];
//$page=$_GET[page];
$status_laporan=$_GET['status_laporan'];
if($jenis_pengiriman=="Sikesal" || $jenis_pengiriman=="sikesal"){
	$query = "SELECT laporan.id_laporan,user.id_user,user.nama_lengkap,masalah.masalah,kantor_dinas.kantor_dinas,laporan.tanggapan_dinas,
	laporan.alamat,laporan.judul,laporan.isi_laporan,laporan.saran_laporan,laporan.jenis_pengiriman,laporan.tanggal_lapor,
	laporan.tanggal_selesai, laporan.lng,laporan.lat,laporan.foto_laporan,laporan.foto_selesai,laporan.status_laporan,
	laporan.rating,user.photo,COUNT(komentar.id_komentar) AS jumlah_komen FROM laporan LEFT JOIN komentar
	USING(id_laporan) LEFT JOIN masalah USING(id_masalah)LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN kantor_dinas ON laporan.id_masalah=masalah.id_masalah AND masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	WHERE laporan.status_laporan='selesai' AND laporan.jenis_pengiriman='sikesal'
	GROUP BY id_laporan ORDER BY laporan.view DESC";
}
 else if($jenis_pengiriman=="Sosial Media"){
	$query = "SELECT laporan.id_laporan,user.id_user,user.nama_lengkap,masalah.masalah,kantor_dinas.kantor_dinas,laporan.tanggapan_dinas,
	laporan.alamat,laporan.judul,laporan.isi_laporan,laporan.saran_laporan,laporan.jenis_pengiriman,laporan.tanggal_lapor,
	laporan.tanggal_selesai, laporan.lng,laporan.lat,laporan.foto_laporan,laporan.foto_selesai,laporan.status_laporan,
	laporan.rating,user.photo,COUNT(komentar.id_komentar) AS jumlah_komen FROM laporan LEFT JOIN komentar
	USING(id_laporan) LEFT JOIN masalah USING(id_masalah)LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN kantor_dinas ON laporan.id_masalah=masalah.id_masalah AND masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	WHERE laporan.status_laporan='selesai' AND laporan.jenis_pengiriman IN('facebook','instagram','twitter')
	GROUP BY id_laporan ORDER BY laporan.tanggal_selesai DESC";
}
 else if($jenis_pengiriman=="Media cetak"){
	$query = "SELECT laporan.id_laporan,user.id_user,user.nama_lengkap,masalah.masalah,kantor_dinas.kantor_dinas,laporan.tanggapan_dinas,
	laporan.alamat,laporan.judul,laporan.isi_laporan,laporan.saran_laporan,laporan.jenis_pengiriman,laporan.tanggal_lapor,
	laporan.tanggal_selesai, laporan.lng,laporan.lat,laporan.foto_laporan,laporan.foto_selesai,laporan.status_laporan,
	laporan.rating,user.photo,COUNT(komentar.id_komentar) AS jumlah_komen FROM laporan LEFT JOIN komentar
	USING(id_laporan) LEFT JOIN masalah USING(id_masalah)LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN kantor_dinas ON laporan.id_masalah=masalah.id_masalah AND masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	WHERE laporan.status_laporan='selesai' AND laporan.jenis_pengiriman='koran'
	GROUP BY id_laporan ORDER BY laporan.tanggal_selesai DESC";
}

else{
	$query = "SELECT laporan.id_laporan,user.id_user,user.nama_lengkap,masalah.masalah,kantor_dinas.kantor_dinas,laporan.tanggapan_dinas,
	laporan.alamat,laporan.judul,laporan.isi_laporan,laporan.saran_laporan,laporan.jenis_pengiriman,laporan.tanggal_lapor,
	laporan.tanggal_selesai, laporan.lng,laporan.lat,laporan.foto_laporan,laporan.foto_selesai,laporan.status_laporan,
	laporan.rating,user.photo,COUNT(komentar.id_komentar) AS jumlah_komen FROM laporan LEFT JOIN komentar
	USING(id_laporan) LEFT JOIN masalah USING(id_masalah)LEFT JOIN user ON laporan.id_user=user.id_user
	LEFT JOIN kantor_dinas ON laporan.id_masalah=masalah.id_masalah AND masalah.id_kantor_dinas=kantor_dinas.id_kantor_dinas
	WHERE laporan.status_laporan='selesai' AND laporan.jenis_pengiriman='sms'
	GROUP BY id_laporan ORDER BY laporan.tanggal_selesai DESC";
}







//  $query = "SELECT user.id_user,user.nama_lengkap,laporan.judul FROM laporan,user
 //WHERE user.id_user=laporan.id_user AND laporan.id_user='$id_user'
 //AND status_laporan='$status_laporan' ORDER BY id_laporan DESC & LIMIT $page";


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

mysqli_set_charset($konek,"utf8");

// $myObj->id_masalah = $hijri;
// $myObj->id_kantor_dinas = 30;


// $myJSON = json_encode($myObj);

// echo $myJSON;
