<?php


$host='localhost';
$username='root';
$pwd='';
$db="db_kelurahan";

$con=mysqli_connect($host,$username,$pwd,$db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
	echo "Failed to Connect to Database ".mysqli_connect_error();
}
$id=$_GET['id'];
$sql="SELECT nama,instansi_id,tipe_dinas FROM instansis where id='$id'";
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