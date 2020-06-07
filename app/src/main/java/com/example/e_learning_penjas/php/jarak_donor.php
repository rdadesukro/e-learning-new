<?php


$host='localhost';
$username='root';
$pwd='';
$db="blood_skripsi";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
	echo "Failed to Connect to Database ".mysqli_connect_error();
}

$lat = $_GET['lat'];
$lng = $_GET['lng'];	
	
$sql="SELECT id_donor, nama, gol,rhesus,foto,status,no_hp,alamat,lat,lng,(6371 * ACOS(SIN(RADIANS(lat)) * SIN(RADIANS($lat)) + COS(RADIANS(lng - $lng)) * COS(RADIANS(lat)) * COS(RADIANS($lat)))) AS jarak FROM donor HAVING jarak < 6371 ORDER BY jarak ASC";
$query=mysqli_query($con,$sql);
if($query)
{
    while($row=mysqli_fetch_array($query))
  {
    $data[]=$row;
  }
    //print(json_encode($data));
	print(json_encode($data));
}else
{
  echo('Not Found ');
}
mysqli_close($con);

?>