<?php
require_once 'koneksi.php';

define( 'API_ACCESS_KEY', 'AIzaSyBQgHf24E8okU7y2TgRfHCE7kOOsQiYYFE');

     $msg = array
          (
		'body' 	=> 'Sikesal',
		'title'	=> 'Maaf Anda tidak boleh lagi melapor',
    'android_channel_id'=>'Default',
    'click_action'=>'notif_user',
    'sound'=>'default'





          );
	$fields = array
			(
				'to'		=> $_REQUEST['token'],
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
