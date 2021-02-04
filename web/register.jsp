<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="registerStyle.css">
    <link rel="stylesheet" href="headerStyle.css">
    <link rel="icon" href="imagens/carLogoIcon.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CarMe | Cadastro</title>

</head>
<body>
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
        <div id="loginInnerMin">
            <h1 id="title">Crie sua conta</h1>

            <span id="errorServlet">${error}</span>

            <form id="form"
                  action="/register"
                  method="POST" enctype="multipart/form-data">

                <%
                    String method = request.getParameter("method");
                    String renavam = (String)request.getAttribute("carro");
                %>


                <input id="method" name="method" type="text" value=<%=method%>>
                <label class="label">Nome Completo</label>
                <input id="name" class="input" type="text" name="nome">

                <label class="label">CPF</label>
                <input id="cpf" class="input" type="text" oninput="mascaraCPF(this)" name="cpf">

                <label class="label">Email</label>
                <input id="email" class="input" type="email" name="email">

                <label class="label">Telefone</label>
                <input id="tel" class="input" type="text" oninput="mascaraTel(this)" name="telefone"
                       minlength="13" maxlength="14">

                <label class="label">Senha</label>
                <img id="eye" src="imagens/registerPage/view.png">
                <input id="password" class="input" type="password" name="senha">

                <label class="label">Foto de usuário</label>
                <input type="file" id="imagem" name="imagem">

                <label class="label" id="cadastrado">Já é cadastrado?
                    <a id="entrar" href="login.jsp?method=<%=method%>&carro=<%=renavam%>">Entrar</a>
                </label>

                <input id="button" type="submit" value="Criar Conta">


                <span id='errorEmptyDis'>Preencha todos os campos e selecione uma foto</span>
                <span id='errorCPFDis'>CPF inválido.</span>
            </form>
        </div>
    </div>

    <div id="image">
        <img id="carro" src="imagens/aboutUsPage/2015_jaguar_f-type_r_coupe_27_1920x1080.jpg">
    </div>
</div>

<script type="text/javascript" src="javascript/registerScript.js"></script>
</body>
</html>