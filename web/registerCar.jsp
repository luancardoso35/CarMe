<%--
  Created by IntelliJ IDEA.
  User: Luan
  Date: 08/01/2021
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <link rel="stylesheet" href="registerCarStyle.css">
    <link rel="stylesheet" href="headerStyle.css">
    <link rel="icon" href="imagens/carLogoIcon.png">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CarMe | Anúncio</title>
</head>
<body>
<header>

    <a id="logo" href="index.jsp">
        <h1>CarMe</h1>
    </a>

    <div id="buttons">
                <span class="buttons">
                <a class="links-header" href="catalogue">Comprar</a>
                </span>
        <span class="buttons">
                <a class="links-header" href="registerCar.jsp">Vender</a>
                </span>
        <span class="buttons">
                    <a class="links-header" href="aboutUs.jsp">Sobre Nós</a>
                </span>
    </div>

        <div id="user">
            <img id="userImg" src="/carDetail?img=${imageName}">
            <span id="username">${nome}</span>
            <div id="burger">
                <div id="line1"></div>
                <div id="line2"></div>
                <div id="line3"></div>
            </div>
        </div>

</header>

<section>
<div id="texto">
    <h1 id="h1">Anunciar seu carro nunca foi tão fácil!</h1>
    <p id="textp">Preencha os campos do formulário abaixo e anuncie agora</p>
</div>

<div id="container">


    <div id="form">
        <form action="/registerCar" method="POST" enctype="multipart/form-data">
            <div>
                <input type="text" name="marca" class="input" placeholder="Marca">
                <input type="text" name="modelo" class="input" placeholder="Modelo">
            </div>
            <input type="text" name="cor" class="input" placeholder="Cor">

            <select class="input" name="cambio" id="filtro-cambio">
                <option selected disabled hidden>Câmbio</option>
                <option value="Automático">Automático</option>
                <option value="Manual">Manual</option>
            </select>

            <input type="number" name="assentos" class="input" placeholder="Lugares">
            <input type="text" name="ano" class="input" placeholder="Ano de Fabricação">

            <input type="text" name="kms" class="input" placeholder="Quilometragem">
            <input type="text" name="renavam" class="input" minlength="11" maxlength="11" placeholder="Renavam">

            <select class="input" name="combustivel" id="combustivel">
                <option selected disabled hidden>Combustível</option>
                <option value="Gasolina">Gasolina</option>
                <option value="Álcool">Álcool</option>
                <option value="Diesel">Diesel</option>
                <option value="Flex">Flex</option>
            </select>

            <input type="number" name="preco" class="input" placeholder="Preço" min="0.00" step="0.01">

            <input type="text" name="cidade" class="input cidade" placeholder="Cidade">

            <input type="file" name="imagem" id="imagem">

            <p id="errorEmptyDisabled">Todos os campos devem ser preenchidos</p>

            <p id="errorServlet">${error}</p>

            <input type="submit" value="Cadastrar Veículo" id="botao">
        </form>
    </div>
</div>
</section>

<div id="menu-inactive">
    <a href="/userProfile" class="option">Meu Perfil</a>
    <a href="/logout" id="logout" class="option">
        <img src="imagens/log-out.png">
        <span>Sair</span>
    </a>
</div>

<script src="javascript/registerCarScript.js"></script>
<script src="javascript/userScript.js"></script>
</body>
</html>