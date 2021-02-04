<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<%--
  Created by IntelliJ IDEA.
  User: Luan
  Date: 27/01/2021
  Time: 01:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pt-br">
<head>

    <title>CarMe | Perfil</title>
    <link rel="icon" href="imagens/carLogoIcon.png">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="headerStyle.css">
    <link rel="stylesheet" href="userProfileStyle.css">
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

<form action="/deleteProfile" method="GET" class="formDelete">
    <button class="delete" onclick="show()" type="button">Excluir perfil</button>
    <p class="choiceDis">Tem certeza que deseja excluir seu perfil?</p>
    <button class="choiceDis" type="submit">Sim</button>
    <button class="choiceDis" onclick="hide()" type="button">Não</button>
</form>

<div id="container">
    <span>Perfil</span>
    <hr>

    <div id="info-user">

        <div id="name-photo">
            <img id="user-img" src="/carDetail?img=${user.getImageName()}">
            <p id="name">${nome}</p>
            <button id="editar">Editar</button>
        </div>

        <div id="nome-completo" class="infos">
            <p>
                <img class="img-info" src="imagens/userProfilePage/name.png">
                <span class="title">Nome Completo</span>
                <input type="text" class="input-dis">
            </p>
            <p class="input-value-ena">${user.getNome()}</p>
        </div>

        <div id="cpf" class="infos">
            <p>
                <img class="img-info" src="imagens/userProfilePage/page.png">
                <span class="title">CPF</span>
            </p>
            <p>${user.getCPF()}</p>
        </div>

        <div id="email" class="infos">
            <p>
                <img class="img-info" src="imagens/aboutUsPage/email.png">
                <span class="title">Email</span>
                <input type="email" class="input-dis" id="email-input">

            </p>
            <p class="input-value-ena">${user.getEmail()}</p>
        </div>

        <div id="telefone" class="infos">
            <p>
                <img class="img-info" src="imagens/userProfilePage/telephone.png">
                <span class="title">Telefone</span>
                <input type="text" class="input-dis" onkeypress="mascaraTel(this)" minlength="10" maxlength="15">
            </p>
            <p class="input-value-ena">${user.getTelefone()}</p>
            <p id='errorTelDis'>Número inválido</p>
        </div>

        <button class="button-dis" onclick="save()">Salvar</button>
    </div>

    <span>Anúncios</span>
    <hr>

    <div id="anuncios">
        <div id="anunciosRoll">

            <%  String error = (String) request.getAttribute("error");
                String btnClass = "btn";
                if (error != null) {
                    btnClass = "btn-dis";
                }
                pageContext.setAttribute("btn", btnClass);
            %>

            <img id="nxtBtn" class="${btn}" src="imagens/userProfilePage/right-arrow.png">
            <img id="prevBtn" class="${btn}" src="imagens/userProfilePage/left-arrow.png">

            <div>
                <p id="error">${error}</p>
            </div>

            <c:forEach var="carro" items="${carMap}">

            <div class="carro">
                <img class="car-img" src="/catalogue?img=<c:out value="${carro.value.getImageName()}"/>">
                <p class="car-name">
                    <c:out value="${carro.value.getMarca()}"/> <c:out value="${carro.value.getModelo()}"/>
                </p>

                <div class="info-carro">
                    <div class="info-car">
                        <img class="info-car-img" src="imagens/cataloguePage/seat.png">
                        <p class="info-value"><c:out value="${carro.value.getAssentos()}"/> lugares</p>
                    </div>

                    <div class="info-car">
                        <img class="info-car-img" src="imagens/cataloguePage/gearshift.png">
                        <p class="info-value"><c:out value="${carro.value.getCambio()}"/></p>
                    </div>

                    <div class="info-car">
                        <img class="info-car-img" src="imagens/cataloguePage/new-year.png">
                        <p class="info-value"><c:out value="${carro.value.getAnoFabricacao()}"/></p>
                    </div>

                    <div class="info-car">
                        <img class="info-car-img" src="imagens/cataloguePage/speedometer.png">
                        <p class="info-value"><c:out value="${carro.value.getQuilometragem()}"/> Km</p>
                    </div>

                    <div class="info-car">
                        <img class="info-car-img" src="imagens/cataloguePage/fuel-station.png">
                        <p class="info-value"><c:out value="${carro.value.getCombustivel()}"/></p>
                    </div>

                    <div class="info-car">
                        <img class="info-car-img" src="imagens/cataloguePage/tool.png">
                        <p class="info-value"><c:out value="${carro.value.getCidade()}"/></p>
                    </div>

                    <div class="info-car">
                        <img class="info-car-img" src="imagens/carDetailPage/real.png">
                        <p class="info-value">
                            <span>R$</span>
                            <span id="preco"><c:out value="${carro.value.getPreco()}"/></span>
                        </p>
                    </div>
                </div>

                <form action="/deleteCar" method="GET" class="formDelete">
                    <button class="delete" onclick="showAnuncio()" type="button">Excluir anúncio</button>
                    <p class="choiceADis">Tem certeza que deseja excluir esse anúncio?</p>
                    <button class="choiceADis" type="submit">Sim</button>
                    <input type="hidden" name="carro" value="${carro.key}">
                    <button class="choiceADis" onclick="hideAnuncio()" type="button">Não</button>
                </form>
            </div>
            </c:forEach>
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

<script src="javascript/userProfileScript.js"></script>
</body>
</html>