<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>

<%--
  Created by IntelliJ IDEA.
  User: Luan
  Date: 07/01/2021
  Time: 02:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imagens/carLogoIcon.png">
    <link rel="stylesheet" href="catalogueStyle.css">
    <link rel="stylesheet" href="headerStyle.css">
    <title>CarMe | Catálogo</title>

</head>
<body>

<% String s = (String)request.getSession().getAttribute("nome");
    boolean logged = false;
    String venda = "register.jsp?method=venda";

    if (s != null) {
        logged = true;
        venda = "registerCar.jsp";
    }

    pageContext.setAttribute("logged", logged);
    pageContext.setAttribute("venda", venda);
    %>

<header>

    <a id="logo" href="index.jsp">
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
                    <a href="register.jsp?method=compra"><span id="userDefault">Login</span></a>
                </c:otherwise>
            </c:choose>
        </div>

</header>

<div id="text-container">
    <div id="titulo">
        <h1>Catálogo de carros</h1>
        <p>Explore os carros que você pode gostar!</p>
    </div>

    <div id="filtros">
        <form action="/catalogue" method="get">
            <input class="filtro" type="text" placeholder="    Marca" id="filtro-marca" name="marca">
            <input class="filtro" type="text" placeholder="    Modelo" id="filtro-modelo" name="modelo">

            <select class="filtro" name="cambio" id="filtro-cambio">
                <option selected disabled hidden>Câmbio</option>
                <option value="automatico">Automático</option>
                <option value="manual">Manual</option>
            </select>

            <input id="filtro-ano" class="filtro" type="number" min="1960" max="2021" step="1" placeholder="   Ano" name="ano">

            <input class="filtro" type="number" name="assentos" id="filtro-assentos" placeholder="    Assentos">

            <input class="buttonsFilter" type="submit" value="Aplicar Filtros">
            <button class="buttonsFilter" formaction="/catalogue">Limpar Filtros</button>
        </form>
    </div>
</div>

<% String errorCpfID = "errorCPFDis";
    String errorCpf = (String)request.getAttribute("errorCPF");
    if (errorCpf != null) {
        errorCpfID = "errorCPFEna";
    }
    pageContext.setAttribute("idErrorCPF", errorCpfID);
%>

<div id=${idErrorCPF}>${errorCPF}</div>

<div id="info">Clique nos cards para saber mais sobre os carros!</div>


<div id="catalogo-carros">


    <c:forEach var="carro" items="${carMap}">
        <div class="carro" onclick="destaque(this)">
            <p class="marca-modelo"><c:out value="${carro.value.getMarca()}"/>
                <c:out value="${carro.value.getModelo()}"/></p>

            <div class="preco-total">
                <span class="moeda">R$</span>
                <span class="preco"><c:out value="${carro.value.getPreco()}"/></span>

            </div>
            <img class="img-carro-dis" src="/catalogue?img=<c:out value="${carro.value.getImageName()}"/>"/>
            <div class="info">
                <div class="cambio">
                    <img src="imagens/cataloguePage/gearshift.png">
                    <p><c:out value="${carro.value.getCambio()}"/></p>
                </div>
                <div class="assento">
                    <img src="imagens/cataloguePage/seat.png">
                    <p><c:out value="${carro.value.getAssentos()}"/> lugares</p>
                </div>
                <div class="ano">
                    <img src="imagens/cataloguePage/new-year.png">
                    <p><c:out value="${carro.value.getAnoFabricacao()}"/></p>
                </div>
            </div>

            <div class="local">
                <img src="imagens/cataloguePage/tool.png">
                <span><c:out value="${carro.value.getCidade()}"/></span>
            </div>

            <div id="optional">
                <div class="quilometragemDis">
                    <img src="imagens/cataloguePage/speedometer.png">
                    <span><c:out value="${carro.value.getQuilometragem()}"/> quilômetros</span>
                </div>

                <div class="combustivelDis">
                    <img src="imagens/cataloguePage/fuel-station.png">
                    <span><c:out value="${carro.value.getCombustivel()}"/></span>
                </div>
            </div>

            <div class="button-div">
                <button class="button">
                    <a href="/carDetail?carro=<c:out value="${carro.key}"/>" class="link">Comprar</a>
                </button>
            </div>
        </div>
    </c:forEach>
        <%
           String error = (String)request.getAttribute("error");
           String displayError;
           if(error != null) {
               displayError = "visible";
           } else {
               displayError = "none";
           }
           pageContext.setAttribute("displayError", displayError);
            %>
        <span id="error" style="display:${displayError}">${error}</span>

        <div id="menu-inactive">
            <a href="/userProfile" class="option">Meu Perfil</a>
            <a href="/logout" id="logout" class="option">
                <img src="imagens/log-out.png">
                <span>Sair</span>
            </a>
        </div>
        <script src="javascript/catalogueScript.js"></script>
</body>
</html>