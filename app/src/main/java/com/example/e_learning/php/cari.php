<?php 
require_once 'koneksi.php'; 
$cari=$_GET['cari'];
$query = "SELECT * FROM laporan where judul LIKE '%$cari%' ORDER BY judul ASC";
 
$result = mysqli_query($konek,$query);
$array = array();
while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row; 
}
echo ($result) ? 
json_encode(array("kode" => 1, "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));





