<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${firstname!''} ${name!''}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css"/>

    <style>
        body {
            display: flex;
            min-height: 100vh;
            flex-direction: column;
        }

        main {
            flex: 1 0 auto;
        }

        .logo {
            width: 177px;
            height: auto;
            margin: 10px;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<header>
    <nav class="top-nav green darken-1" role="navigation">
        <div class="container">
            <div class="nav-wrapper">
                <a href="/#/" class="brand-logo">
                    <div class="media logo" data-src="https://www.qaobee.com/assets/images/logo-mini.png"></div></a>
            </div>
        </div>
    </nav>
</header>
<main>
    <div class="main-container">
        <div class="row">
            <div class="row">
                <strong>${firstname!''} ${name!''}</strong>
                <small> ${username!''}</small>
                <br/> ${address!''}
            </div>
        </div>
        <table class="bordered">
            <thead>
            <tr>
                <th>Product</th>
                <th>Date</th>
                <th>Id</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${plan}</td>
                <td>${paidDate!''} </td>
                <td>${paymentId!''}</td>
                <td>${amountPaid} &euro;</td>
            </tr>
            </tbody>
        </table>
        <h3>Thanks,<br/>The <a href="https://www.qaobee.com" target="_blank">Qaobee</a> team.</h3>
    </div>
</main>
<footer class="page-footer grey">
    <div class="footer-copyright">
        <div class="container"><span class="white-text light">Copyright &copy; <b>Qaobee</b> 2015</span></div>
    </div>
</footer>
</body>
</html>