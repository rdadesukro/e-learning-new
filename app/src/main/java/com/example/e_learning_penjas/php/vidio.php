
<?php
require_once 'koneksi.php';

header("Content-type:application/json");

$query = "SELECT * FROM `vidio`";


$result = mysqli_query($konek,$query);
$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?

json_encode(array("kode" => 1,"search_count" => count($array), "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
?>


