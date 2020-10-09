<?php
include 'koneksi.php';
global $connect;
    $id_donor = $_POST["id_donor"];

    $query = "SELECT *FROM donor WHERE donor.id_user ='$id_donor'";

    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);

    $response = array();

    if($number_of_rows > 0) {
        while($row = mysqli_fetch_assoc($result)) {
            $response[] = $row;
        }
    }

    header('Content-Type: application/json');
    echo json_encode(array("donor"=>$response));
    mysqli_close($connect);
	?>