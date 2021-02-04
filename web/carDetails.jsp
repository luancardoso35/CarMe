<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Luan
  Date: 20/01/2021
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="imagens/carLogoIcon.png">
    <link rel="stylesheet" href="carDetailStyle.css">
    <link rel="stylesheet" href="headerStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CarMe | <c:out value="${carro.getMarca()} ${carro.getModelo()} "/></title>
</head>

<% String s = (String)request.getSession().getAttribute("nome");
    String sout = "";
    String venda = "register.jsp?method=venda";

    if (s != null) {
        sout = "Olá, " + s;
        venda = "registerCar.jsp";
    }

    pageContext.setAttribute("sout", sout);
    pageContext.setAttribute("venda", venda);
%>

<body>
<header>
    <a href="index.jsp" id="logo">
        <h1>CarMe</h1>
    </a>

    <div id="buttons">
                <span class="buttons">
                    <a class="links-header" href="catalogue">Comprar</a>
                </span>
        <span class="buttons">
                    <a class="links-header" href="${venda}">Vender</a>
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

    <div id="container">
        <div id="imagem">
            <img src="/catalogue?img=${carro.getImageName()}">
            <div id="detail-fixed">
                <p id="name">${carro.getMarca()} ${carro.getModelo()}</p>
                <p id="price">R$ ${carro.getPreco()}</p>
            </div>
        </div>

        <div id="car-name">
            <div id="full-name">
                <span>${carro.getMarca()}</span>
                <span>${carro.getModelo()}</span>
            </div>
            <div id="car-detail">
                <p>Detalhes do carro</p>
                <div class="detail">
                    <span class="title">Marca</span>
                    <span class="value">${carro.getMarca()}</span>
                </div>

                <hr>

                <div class="detail">
                    <span class="title">Modelo</span>
                    <span class="value">${carro.getModelo()}</span>
                </div>

                <hr>

                <div class="img-detail">
                    <img src="imagens/cataloguePage/gearshift.png">
                    <span class="title">Câmbio</span>
                    <span class="value">${carro.getCambio()}</span>
                </div>

                <hr>

                <div class="img-detail">
                    <img src="imagens/cataloguePage/seat.png">
                    <span class="title">Lugares</span>
                    <span class="value">${carro.getAssentos()} lugares</span>
                </div>

                <hr>

                <div class="img-detail">
                    <img src="imagens/cataloguePage/new-year.png">
                    <span class="title">Ano de Fabricação</span>
                    <span class="value">${carro.getAnoFabricacao()}</span>
                </div>

                <hr>

                <div class="img-detail">
                    <img src="imagens/cataloguePage/fuel-station.png">
                    <span class="title">Combustível</span>
                    <span class="value">${carro.getCombustivel()}</span>
                </div>

                <hr>

                <div class="img-detail">
                    <img src="imagens/cataloguePage/speedometer.png">
                    <span class="title">Quilometragem</span>
                    <span class="value">${carro.getQuilometragem()} quilômetros</span>
                </div>

                <hr>

                <div class="img-detail">
                    <img src="imagens/cataloguePage/tool.png">
                    <span class="title">Local</span>
                    <span class="value">${carro.getCidade()}</span>
                </div>

                <hr>

                <div class="img-detail">
                    <img src="imagens/carDetailPage/real.png">
                    <span class="title">Preço</span>
                    <span class="value">
                            <span>R$</span>
                            <span>${carro.getPreco()}</span>
                        </span>
                </div>
            </div>
        </div>

        <div id="pessoa-container">
            <p>Detalhes do anunciante</p>
            <div id="img-name">
                <img src="/carDetail?img=${user.getImageName()}">
                <span>${user.getNome()}</span>
            </div>

            <div class="email-tel">
                <img src="imagens/carDetailPage/email (1).png">
                <span class="title">Email</span>
                <span class="value">${user.getEmail()}</span>
            </div>

            <hr>

            <div class="email-tel">
                <img src="imagens/carDetailPage/telephone (1).png">
                <span class="title">Telefone</span>
                <span class="value">${user.getTelefone()}</span>
            </div>
        </div>
    </div>

<div id="menu-inactive">
    <a href="/userProfile" class="option">Meu Perfil</a>
    <a href="/logout" id="logout" class="option">
        <img src="imagens/log-out.png">
        <span>Sair</span>
    </a>
</div>

<script src="javascript/userScript.js"></script>
</body>
</html>
