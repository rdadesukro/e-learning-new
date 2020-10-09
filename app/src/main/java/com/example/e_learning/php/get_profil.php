<?php
 require_once('koneksi.php');
 
	$sql = "select foto from donor";
	
	$res = mysqli_query($con,$sql);
	
	$result = array();
	
	while($row = mysqli_fetch_array($res)){
		array_push($result,array('url'=>$row['foto']));
	}
	
	echo json_encode(array("result"=>$result));
	
	mysqli_close($con);
 