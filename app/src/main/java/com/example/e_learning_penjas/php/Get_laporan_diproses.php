<?php 
require_once 'koneksi.php'; 

$id_user=$_GET['id_user'];
$page=$_GET[page];
$status_laporan=$_GET['status_laporan'];

 $query = "SELECT laporan.id_laporan,masalah.masalah,user.id_user,user.nama_lengkap,laporan.judul,laporan.isi_laporan,laporan.saran_laporan,laporan.jenis_pengiriman,laporan.tanggal_lapor,laporan.lang,laporan.lat,laporan.foto_laporan,laporan.alamatFROM laporan,user,masalahWHERE user.id_user=laporan.id_user AND laporan.id_masalah=masalah.id_masalah AND laporan.id_user='$id_user'AND status_laporan='belum selesai' ORDER BY id_laporan DESC"; 
 
 
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


// $myObj->id_masalah = $hijri;
// $myObj->id_kantor_dinas = 30;


// $myJSON = json_encode($myObj);

// echo $myJSON;






