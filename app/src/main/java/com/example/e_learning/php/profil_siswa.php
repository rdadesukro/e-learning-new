<?php
require_once 'koneksi.php';
header("Content-type:application/json");


if(!$konek){
die('Could not connect: '.mysqli_error());
}
$nis=$_GET['nis'];
$result = mysqli_query($konek, "SELECT * FROM `siswa`,kelas WHERE siswa.id_kelas=kelas.id_kelas and siswa.nis='$nis'");


$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?

json_encode(array("kode" => 1,"search_count" => count($array), "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
?>
