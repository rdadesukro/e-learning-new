<?php

$server		= "localhost";
$user		= "sisamsuljambikot";
$password	= "lailatulqadar";
$database	= "sisamsuljambikot_db_sisamsul";

  $con = mysqli_connect($server, $user, $password, $database);

  $lat =-1.627766;
	$lng =103.607792;
  $search = "kota";

// $page=$_GET['page'];
// if ($page==1) {
//   $kali=0;
// }
// else {
// $kali=($page-1)*5;
// }



// $arr = array(
//     array(
//         "Nama_lokasi" => "SPBU Pasti Pas Kebun Jeruk",
//         "alamat" => "Jalan Professor Dr. Soemantri, Bojonegoro, Solok Sipin, Telanaipura, Solok Sipin, Telanaipura, Kota Jambi, Jambi 36124",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipNCRjXRNToZsIaCGgZmEN2szoJnChsjKvuUrzKm=w426-h240-k-no",
// 				"langitude" => "-1.600426",
// 				"latitude" => "103.601549"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU 24 361 04",
//         "alamat" => "Jl. Pangeran Hidayat, Suka Karya, Kec. Kota Baru, Kota Jambi, Jambi 36129",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipPfCW5xK9km1_svZAMmBwPld6Fbi5iKrzf6Nigw=w426-h240-k-no",
// 				"langitude" => "-1.620503",
// 				"latitude" => "103.602641"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU Palimo",
//         "alamat" => "Jl. Lintas Sumatera No.18, Paal Lima, Kec. Kota Baru, Kota Jambi, Jambi 36129",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipMKopmsh3fPum2Rmh71rR-XgQ0XQid4qme6khVv=w408-h306-k-no",
// 				"langitude" => "-1.622377",
// 				"latitude" => "103.602781"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU 24.361.55",
//         "alamat" => "Jl. D.I Panjaitan, RT.026, Jelutung, Kebun Handil, Jelutung, Kota Jambi, Jambi 36125",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipMxtGp74PmEenlIWGwcqFwuOAdIuu3JHl3N3GTk=w408-h306-k-no",
// 				"langitude" => "-1.622122",
// 				"latitude" => "103.620023"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU 24.361.58",
//         "alamat" => "Jl. Kapt. A. Bakaruddin, Paal Lima, Kec. Kota Baru, Kota Jambi, Jambi 36129",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipNk_AfpKaecA9yhWA3nZVnxYRykqR2M1fxHZQ_X=w408-h306-k-no",
// 				"langitude" => "-1.642214",
// 				"latitude" => "103.603002"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU Pertamina Pasti Pas (SPBU 24.361.42)",
//         "alamat" => "Jl. H. Adam Malik No.29, The Hok, Jambi Sel., Kota Jambi, Jambi 36125",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipPpZ8avj9zOfxfFlZS4Sq5spHJRpoqIQk-CMFdN=w426-h240-k-no",
// 				"langitude" => "-1.630783",
// 				"latitude" => "103.624711"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU Pertamina 24.361.35",
//         "alamat" => "Jl. Sumantri Brojonegoro, Solok Sipin, Telanaipura, Kota Jambi, Jambi 36124",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipPNPccdi0-433yLUTg7ZIR31Tpnc-2q66PR52hR=w408-h306-k-no",
// 				"langitude" => "-1.603531",
// 				"latitude" => "103.601855"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU 24.361.51 | Nusa Indah",
//         "alamat" => "Jalan Kapt. A. Bakaruddin, Kota Baru, Rawa Sari, Rw. Sari, Kec. Kota Baru, Kota Jambi, Jambi 36361",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipOxy_dbGw6bBWzo61Ln8b2StvR2BFO8uToQ3FWi=w408-h306-k-no",
// 				"langitude" => "-1.619730",
// 				"latitude" => "103.579871"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU 24.36108 Pertamina Bandara Jambi",
//         "alamat" => "Jl. Soekarno-Hatta, Paal Merah, Jambi Sel., Kota Jambi, Jambi 36126",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipNBY3f2yc08c-2ycyqKJnd0tNn3nyPourzZ2qum=w408-h725-k-no",
// 				"langitude" => "-1.627475",
// 				"latitude" => "103.635018"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU 24.361.03 Pertamina IAIN Telanaipura",
//         "alamat" => "Jl. Arif Rahman Hakim, Simpang IV Sipin, Telanaipura, Kota Jambi, Jambi 36361",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipMyd_w5uByN7EFUlJhg6nzfqJJK3S7x-CFXDslN=w426-h240-k-no",
// 				"langitude" => "-1.612109",
// 				"latitude" => "103.576760"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU Talang Banjar",
//         "alamat" => "JL. Kol M Taher No.73, Pakuan Baru, Jambi Sel., Kota Jambi, Jambi",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipPP2iF_MeI7fXa-6QwG2UXmzdtvuLb0G7mbKMqq=w408-h306-k-no",
// 				"langitude" => "-1.612021",
// 				"latitude" => "103.630270"
//     ),
//     array(
//         "Nama_lokasi" => "SPBU 24.361.05 | Pasar jambi",
//         "alamat" => "Jl. Halim Perdana Kusuma, Sungai Asam, Ps. Jambi, Kota Jambi, Jambi 36123",
// 				"foto" => "https://lh5.googleusercontent.com/p/AF1QipPspqBgutUFa_1ERpmZpe7ZXYiWCaNOOHUg3pFA=w408-h725-k-no",
// 				"langitude" => "-1.599034",
// 				"latitude" => "103.618872"
//     )
// );


// $final = array_splice($arr, $kali, 100);

$query = "Select * FROM lokasi where alamat LIKE '%$search%' ORDER BY alamat ASC";


$result = mysqli_query($con,$query);

$array = array();

while ($row  = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}


echo ($result) ?
json_encode(array(
"jenis_lokasi" => "SPBU",
"Jumlah_lokasi" => count($array),
'results'=>$array)):
json_encode(array("kode" => 0, "pesan"=>"data tidak ditemukan"));

//
// echo json_encode(array(
// 	"jenis_lokasi" => "SPBU",
// 	"Jumlah_lokasi" => count($arr),
//
// "results"=>$arr)) ;

?>
