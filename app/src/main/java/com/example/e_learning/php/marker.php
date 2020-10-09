<?php 
	$server		= "localhost"; //sesuaikan dengan nama server
	$user		= "root"; //sesuaikan username
	$password	= ""; //sesuaikan password
	$database	= "blood_skripsi"; //sesuaikan target databese
	
	$con = mysqli_connect($server, $user, $password, $database);
	if (mysqli_connect_errno()) {
		echo "Gagal terhubung MySQL: " . mysqli_connect_error();
	}

	$query = mysqli_query($con, "SELECT * FROM donor ORDER BY nama ASC");
	
	$json = '{"donor": [';

	// bikin looping dech array yang di fetch
	while ($row = mysqli_fetch_array($query)){

	//tanda kutip dua (") tid_donorak diijinkan oleh string json, maka akan kita replace dengan karakter `
	//strip_tag berfungsi untuk menghilangkan tag-tag html pada string 
		$char ='"';

		$json .= 
		'{
			"id_donor":"'.str_replace($char,'`',strip_tags($row['id_donor'])).'", 
			"nama":"'.str_replace($char,'`',strip_tags($row['nama'])).'",
			"lat":"'.str_replace($char,'`',strip_tags($row['lat'])).'",
			"lng":"'.str_replace($char,'`',strip_tags($row['lng'])).'"
		},';
	}

	// buat menghilangkan koma diakhir array
	$json = substr($json,0,strlen($json)-1);

	$json .= ']}';

	// print json
	echo $json;
	
	mysqli_close($con);
	
?>