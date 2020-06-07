<<?php
$beamsClient = new \Pusher\PushNotifications\PushNotifications(array(
  "instanceId" => "5f39be7a3becaa7b4ce0",
  "secretKey" => "a0e66b158643c960d917",
));

$publishResponse = $beamsClient->publish(
  array("hello"),
  array("fcm" => array("notification" => array(
    "title" => "Hello",
    "body" => "Hello, World!",
  )),
));
 ?>
