<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="loginStyle.css">
    <link rel="stylesheet" href="headerStyle.css">
    <link rel="icon" href="imagens/carLogoIcon.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CarMe | Entrar</title>

</head>
<body id="body">
<header>
    <a href="index.jsp" id="logo"><h1>CarMe</h1></a>


    <div id="buttons">
                <span class="buttons">
                    <a class="links-header" href="/catalogue">Comprar</a>
                </span>
        <span class="buttons">
                    <a class="links-header" href="register.jsp?method=venda">Vender</a>
                </span>
        <span class="buttons">
                    <a class="links-header" href="aboutUs.jsp">Sobre Nós</a>
        </span>
    </div>

    <div id="user">
        <img id="userImgDefault" src="imagens/userDefault.png">
        <a href="register.jsp?method=none"><span id="userDefault">Login</span></a>
    </div>
</header>

<div id="container">
    <div id="login">
        <div id="loginInner">
            <h1>Entrar</h1>

            <% String method = request.getParameter("method");
                String renavam = request.getParameter("carro");
            %>

            <form id="form" action="/login?carro=<%=renavam%>" method="POST">


                <input id="method" name="method" type="text" value=<%=method%>>

                <label class="label">CPF</label>
                <input id="cpf" class="input" type="text" name="cpf" oninput="mascaraCPF(this)">

                <label class="label">Senha</label>
                <input id="password" class="input" type="password" name="password">

                <span id="errorServlet">${error}</span>

                <input id="button" type="submit" value="Entrar">

            </form>
        </div>
    </div>

    <div id="image">
        <img id="carro" src="imagens/aboutUsPage/2015_jaguar_f-type_r_coupe_27_1920x1080.jpg">
    </div>
</div>

<script src="javascript/loginScript.js"></script>
</body>
</html>
