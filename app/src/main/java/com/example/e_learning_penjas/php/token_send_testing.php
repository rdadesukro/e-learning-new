<?php
require_once 'koneksi.php';

define( 'API_ACCESS_KEY', 'AIzaSyBQgHf24E8okU7y2TgRfHCE7kOOsQiYYFE');
$id_masalah=$_POST['id_masalah'];
$query = "SELECT token FROM `user`
LEFT JOIN masalah on masalah.id_kantor_dinas=`user`.id_kantor_dinas
WHERE id_jbt=5 AND masalah.id_masalah='$id_masalah'";

$result = mysqli_query($konek,$query);

$array = array();
while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row['token'];
}

  foreach ($array as $kirim=> $token) {
  // define( 'API_ACCESS_KEY', 'AIzaSyBQgHf24E8okU7y2TgRfHCE7kOOsQiYYFE');
       $msg = array
            (
  		'body' 	=> 'Sikesal',
  		'title'	=> 'Laporan baru sikesal, mohon segera ditanggapi',
      'android_channel_id'=>'Default',
      'click_action'=>'sikesal',
      'sound'=>'default'

            );
  	$fields = array
  			(
  				'to'		=> $token,
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



}




?>
