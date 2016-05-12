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

        .avatar {
            width: 177px;
            height: auto;
            margin: 10px;
            vertical-align: middle;
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
                    <div class="media logo" data-src="https://www.qaobee.com/assets/images/logo-mini.png"/></a>
            </div>
        </div>
    </nav>
</header>
<main>
    <div class="main-container">
        <h1><#if avatar??>
            <div class="media avatar" data-src="${avatar}"/></#if> ${firstname!''} ${name!''}
            <small>${username!''}</small>
        </h1>
        <hr/> ${birthdate!''}
        <br/> ${address!''}
        <br/> ${phoneNumber!''} / ${email!''}
    </div>
</main>
<footer class="page-footer grey">
    <div class="footer-copyright">
        <div class="container"><span class="white-text light">Copyright &copy; <b>Qaobee</b> 2015</span></div>
    </div>
</footer>
</body>
</html>