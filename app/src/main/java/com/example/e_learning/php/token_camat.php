<?php
require_once 'koneksi.php';
$id_lokasi=$_POST['id_lokasi'];

  $query = "SELECT lokasi.id_induk,lokasi.kelurahan FROM `user`
  LEFT JOIN lokasi ON lokasi.id_lokasi=user
  .id_lokasi_jabatan WHERE `user`.id_lokasi_jabatan='$id_lokasi'";
  $result = mysqli_query($konek,$query);
  $row = mysqli_fetch_assoc($result);
  $id_induk = $row['id_induk'];


// print(json_encode(mysqli_fetch_assoc($result2)));
define( 'API_ACCESS_KEY', 'AIzaSyBQgHf24E8okU7y2TgRfHCE7kOOsQiYYFE');

     $msg = array
          (
		'body' 	=> 'Sikesal',
		'title'	=> 'Laporan baru sikesal',
    'android_channel_id'=>'Default',
    'click_action'=>'sikesal_camat',
    'sound'=>'default'
          );

$query2 = "SELECT `user`.nama_lengkap,`user`.token FROM `user` WHERE `user`.id_lokasi_jabatan='$id_induk'";
$result2 = mysqli_query($konek,$query2);
$row2 = mysqli_fetch_assoc($result2);
$token = $row2['token'];

	$fields = array
			(
				'to'		=> $row2['token'],
				'notification'	=> $msg,
      );


	$headers = array
			(
				'Authorization: key=' . API_ACCESS_KEY,
				'Content-Type: application/json'
			);
#Send Reponse To FireBase Server
		$ch = curl_init();
		curl_setopt( $ch,CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send' );
		curl_setopt( $ch,CURLOPT_POST, true );
		curl_setopt( $ch,CURLOPT_HTTPHEADER, $headers );
		curl_setopt( $ch,CURLOPT_RETURNTRANSFER, true );
		curl_setopt( $ch,CURLOPT_SSL_VERIFYPEER, false );
		curl_setopt( $ch,CURLOPT_POSTFIELDS, json_encode( $fields ) );
		$result = curl_exec($ch );
		echo $result;
		curl_close( $ch );


?>
