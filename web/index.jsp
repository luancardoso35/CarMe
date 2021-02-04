<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="indexStyle.css">
    <link rel="stylesheet" href="headerStyle.css">
    <link rel="icon" href="imagens/carLogoIcon.png">
    <title>CarMe</title>
</head>
<body>
<header>
    <% String s = (String)request.getSession().getAttribute("nome");
        String venda = "register.jsp?method=venda";
        boolean logged = false;

        if (s != null) {
            logged = true;
            venda = "registerCar.jsp";
        }

        pageContext.setAttribute("venda", venda);
        pageContext.setAttribute("logged", logged);
    %>

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
                    <a href="register.jsp?method=none"><span id="userDefault">Login</span></a>
                </c:otherwise>
            </c:choose>
        </div>
</header>

<div id="principal">
    <div class="text">
        <p>CarMe é plataforma que revolucionou a forma que as pessoas compram e vendem automóveis.</p>
        <p>Só aqui você encontra uma experiência inesquecível na troca do seu veículo.</p>
        <p>Comece Agora:</p>

        <div id="buttons-principal">
            <button>
                <a href="catalogue">Comprar</a>
            </button>
            <button>
                <a href="${venda}">Vender</a>
            </button>
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