<?php
require_once 'koneksi.php';



$query="select (select COUNT(id_laporan) FROM laporan ) AS lap_total,(select COUNT(id_laporan) FROM laporan WHERE status_laporan='selesai') AS lap_selesai,
(select COUNT(id_laporan) FROM laporan WHERE status_laporan='baru' OR status_laporan='tanggapan') AS lap_proses";

$result = mysqli_query($konek,$query);
$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?

json_encode(array("kode" => 1, "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
mysqli_set_charset($konek,"utf8");
