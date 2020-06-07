<?php
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 $id = $_GET['id'];
 $sql = "select * from coba where id = '$id'";
 require_once('koneksi.php');
 
 $r = mysqli_query($con,$sql);
 
 $result = mysqli_fetch_array($r);
 
 header('content-type: Image/jpeg');
 
 echo base64_decode($result['foto']);
 
 mysqli_close($con);
 
 }else{
 echo "Error";
 }