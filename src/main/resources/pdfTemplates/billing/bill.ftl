<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>{forname} {name}</title>
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

        #avatar {
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
                <a href="/#/" class="brand-logo"><img src="http://www.qaobee.com/assets/images/logo-mini.png"
                                                      height="30px"/></a>
                <ul class="right">
                    <li><a href="https://twitter.com/Qaobee"
                           target="_blank"
                           class="white-text"><i
                            class="fa fa-twitter-square fa-2x"></i></a></li>
                </ul>

            </div>
        </div>
    </nav>
</header>
<main>
    <div id="main-container">
        <div class="row">
            <div class="col s6 offset-s3">
                <div class="row">
                    <div class="col s6">
                        <strong>${firstname!''} ${name!''}</strong>
                        <small> ${username!''}</small>
                        <br/> ${address!''}
                    </div>
                    <div class="col s6">
                    ${paidDate!''} #: ${paymentId!''}
                    </div>
                </div>
                <table class="bordered">
                    <thead>
                    <tr>
                        <th>Product</th>
                        <th>Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${plan}</td>
                        <td>${amountPaid} &euro;</td>
                    </tr>
                    </tbody>
                </table>
                <h3>Thanks,<br/>The <a href="http://www.qaobee.com" target="_blank">Qaobee</a> team.</h3>
            </div>
        </div>
    </div>
</main>
<footer class="page-footer grey">
    <div class="footer-copyright">
        <div class="container"><span class="white-text light">Copyright &copy; <b>Qaobee</b> 2015</span></div>
    </div>
</footer>
</body>
</html>