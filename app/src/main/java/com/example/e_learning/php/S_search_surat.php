<?php 
require_once 'koneksi.php'; 
  $search = $_GET['search'];
$query = "SELECT * FROM kantor_dinas where kantor_dinas LIKE '%$search%' ORDER BY kantor_dinas ASC";
if($search==''){
	$result = mysqli_query($konek,$query);
$array = array();
while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row; 
}
echo ($result) ? 
json_encode(array("kode" => 1, "result"=>"")) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));
}
else{
$result = mysqli_query($konek,$query);
$array = array();
while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row; 
}
echo ($result) ? 
json_encode(array("kode" => 1, "result"=>$array)) :
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));

}

