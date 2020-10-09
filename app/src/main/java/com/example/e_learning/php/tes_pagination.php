<?phprequire_once 'koneksi.php';
$page=$_GET['page'];

if ($page==1) {
  $kali=0;
}
else {
  $kali=($page-1)*20;
}

  $query = "Select id_user,nama_lengkap From user
         LIMIT 20 OFFSET $kali";

     $result = mysqli_query($konek,$query);
$array = array();while ($row  = mysqli_fetch_assoc($result)){	$array[] = $row;}
     echo ($result) ?
json_encode(array('page'=>$page,'results'=>$array))
     :json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"))
     ;mysqli_set_charset($konek,"utf8");
