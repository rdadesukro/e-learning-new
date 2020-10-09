<?php
$arr = array(
    array(
        "nama_web" => "website kota jambi 1",
        "alamat" => "www.jambikota.go.id",
        "link" => "https://www.jambikota.go.id1/",

    ),
    array(
      "nama_web" => "website kota jambi 2",
        "alamat" => "www.jambikota.go.id",
      "link" => "https://www.jambikota.go.id2/",

    ),
    array(
      "nama_web" => "website kota jambi 3",
        "alamat" => "www.jambikota.go.id",
      "link" => "https://www.jambikota.go.id3/",
    ),
    array(
      "nama_web" => "website kota jambi 4",
        "alamat" => "www.jambikota.go.id",
      "link" => "https://www.jambikota.go.id4/",
    )

);

echo json_encode(array(
	"Api" => "website",
	"Deskripsi" => "list alamat website kota jambi",
  "Total" => count($arr),
"website"=>$arr)) ;

?>
