
<?php
require_once 'koneksi.php';

define( 'API_ACCESS_KEY', 'AIzaSyBQgHf24E8okU7y2TgRfHCE7kOOsQiYYFE');

$id_masalah=$_POST['id_masalah'];
$judul = $_POST['judul'];
$name = $_POST['name'];



$query = "SELECT token,no_hp FROM `user`
LEFT JOIN masalah on masalah.id_kantor_dinas=`user`.id_kantor_dinas
WHERE id_jbt=5 AND masalah.id_masalah='$id_masalah'";

$result = mysqli_query($konek,$query);

$array = array();
while ($row  = mysqli_fetch_assoc($result))
{
    $array[] = $row['token'];
    $array[] = $row['no_hp'];
}




foreach ($array as $kirim=> $token) {


    // define( 'API_ACCESS_KEY', 'AIzaSyBQgHf24E8okU7y2TgRfHCE7kOOsQiYYFE');
         $msg = array
              (
            'body' 	=> 'Sikesal',
            'title'	=> 'Laporan baru sikesal, mohon segera ditanggapi',
        'android_channel_id'=>'Default',
        'click_action'=>'sikesal',
        'sound'=>'default');
        
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


foreach ($array as $kirim=> $no_hp) {
    ob_start();
    $apikey        = '7ce7005d62badf30942f21b591d963a2'; // api key
    $urlendpoint = 'http://sms195.xyz/sms/api_sms_reguler_send_json.php'; // url endpoint api
    
    
    $senddata = 
    array( 
    'apikey' => $apikey, 
    'datapacket'=>array());
    
    $message1='Laporan baru Sikesal, mohon segera ditanggapi '.$judul;
    array_push($senddata['datapacket'],
    array( 'number' => trim($no_hp), 
    'message' => urlencode(stripslashes(utf8_encode($message1)))));
    
    
    // send sms
     $data=json_encode($senddata); $curlHandle = curl_init($urlendpoint); curl_setopt($curlHandle, CURLOPT_CUSTOMREQUEST, "POST");
      curl_setopt($curlHandle, CURLOPT_POSTFIELDS, $data); curl_setopt($curlHandle, CURLOPT_RETURNTRANSFER, true);
      curl_setopt($curlHandle, CURLOPT_HTTPHEADER, array( 'Content-Type: application/json', 'Content-Length: ' . strlen($data)) ); curl_setopt($curlHandle, CURLOPT_TIMEOUT, 5);
       curl_setopt($curlHandle, CURLOPT_CONNECTTIMEOUT, 5); $responjson = curl_exec($curlHandle);
       curl_close($curlHandle); header('Content-Type: application/json'); echo $responjson;
 }



?>

