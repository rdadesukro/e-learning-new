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
	
$sql="SELECT id_event, nama,lokasi,tanggal,jam,lat,lng,imageUrl,(6371 * ACOS(SIN(RADIANS(lat)) * SIN(RADIANS($lat)) + COS(RADIANS(lng - $lng)) * COS(RADIANS(lat)) * COS(RADIANS($lat)))) AS jarak FROM event HAVING jarak < 6371 ORDER BY jarak ASC";


$result=mysqli_query($con,$sql);
if($result)
{
	while($row=mysqli_fetch_array($result))
	{
		$data[]=$row;
	}
	
	print(json_encode($data));
}

mysqli_close($con);

?>