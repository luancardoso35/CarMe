<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Luan
  Date: 15/01/2021
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="imagens/carLogoIcon.png">
    <link rel="stylesheet" href="aboutUsStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CarMe | Sobre Nós</title>
</head>
<body>
<header>
    <a id="logo" href="index.jsp">
        <h1>CarMe</h1>
    </a>

        <% String s = (String)request.getSession().getAttribute("nome");
            String compra = "register.jsp?method=compra";
            String venda = "register.jsp?method=venda";
            boolean logged = false;

            if (s != null) {
                compra = "catalogue";
                venda = "registerCar.jsp";
                logged = true;
            }

            pageContext.setAttribute("compra", compra);
            pageContext.setAttribute("venda", venda);
            pageContext.setAttribute("logged", logged);
        %>

    <div id="buttons">
            <span class="buttons">
                <a class="links-header" href="${compra}">Comprar</a>
            </span>
        <span class="buttons">
                <a class="links-header" href="${venda}">Vender</a>
            </span>
        <span class="buttons">
                <a class="links-header" href="aboutUs.jsp">Sobre Nós</a>
            </span>
    </div>

    <div id="user">
        <c:choose>
            <c:when test="${logged}">
                <img id="userImg" src="/carDetail?img=${imageName}">
                <span id="username">${nome}</span>
                <div id="burger">
                    <div id="line1"></div>
                    <div id="line2"></div>
                    <div id="line3"></div>
                </div>
            </c:when>
            <c:otherwise>
                <img id="userImgDefault" src="imagens/userDefault.png">
                <a href="register.jsp?method=about"><span id="userDefault">Login</span></a>
            </c:otherwise>
        </c:choose>
    </div>
</header>

<section id="text-container">
    <h2>Revolução no conceito de venda de automóveis!</h2>
    <p>A <span id="carMe">CarMe</span> é uma empresa virtual que mudou a forma como automóveis são vendidos.</p>
    <p>Nossa maior preocupação é o conforto e a segurança do cliente, e para garantirmos isso, possuímos:</p>

    <div id="cliente">
        <img src="imagens/aboutUsPage/community.png">
        <span>Prioridade ao conforto e satisfação dos clientes</span>
    </div>

    <div id="seguranca">
        <img src="imagens/aboutUsPage/verified.png">
        <span>Garantia de segurança através da conferência dos dados diariamente</span>
    </div>

    <div id="telefone">
        <img src="imagens/aboutUsPage/phone-call.png">
        <span>Atendimento 24 horas</span>
    </div>
</section>

<section id="contato">

    <div class="contact">
        <a href="mailto:carMe@contato.gmail.com">
            <img class="img-con" src="imagens/aboutUsPage/email.png">
            <p class="link">carMe@contato.gmail.com</p>
        </a>
    </div>

    <div class="contact">
        <a href="tel:+5501670707070">
            <img class="img-con" src="imagens/aboutUsPage/phone-call.png">
            <p class="link">+55 16 7070-7070</p>
        </a>
    </div>

    <div class="contact">
        <a href="https://g.page/chase-center-san-francisco?share">
            <img class="img-con" src="imagens/aboutUsPage/map.png">
            <p class="link">1 Warriors Way, San Francisco, CA 94158, United States</p>
        </a>
    </div>
</section>


<div id="menu-inactive">
    <a href="" class="option">Meu Perfil</a>
    <a href="/logout" id="logout" class="option">
        <img src="imagens/log-out.png">
        <span>Sair</span>
    </a>
</div>

<script src="javascript/userScript.js"></script>
</body>
</html>
