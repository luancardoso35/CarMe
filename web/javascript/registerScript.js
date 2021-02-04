var flagEmpty = false
var flagCPF = false;

let max = false;
const form = document.getElementById("form");


form.addEventListener("submit", (e) => {
    let name = document.getElementById("name");
    let email = document.getElementById("email")
    let cpf = document.getElementById("cpf")

    let password = document.getElementById("password")
    let tel = document.getElementById("tel")
    let imagem = document.getElementById("imagem");

    if (name.value == "" || email.value == "" || password.value == "" || cpf.value == "" || tel.value == ""
    || imagem.value == "") {
        e.preventDefault();
        if (!flagEmpty) {
            flagEmpty = true;
            if (document.getElementById("errorServlet").innerText !== "") {
                document.getElementById("loginInnerMin").id = "loginInnerMax"
                document.getElementById("errorEmptyDis").id = "errorEmptyMax"
                max = true;
            } else {
                document.getElementById("errorEmptyDis").id = "errorEmpty"
            }
        }
    } else if (!testaCPF(cpf.value)) {
        e.preventDefault();
        if (!flagCPF) {
            flagCPF = true;
            if (document.getElementById("errorServlet").innerText !== "") {
                document.getElementById("loginInnerMin").id = "loginInnerMax"
                document.getElementById("errorCPFDis").id = "errorCPFMax"
                max = true;
            } else {
                document.getElementById("errorCPFDis").id = "errorCPFMin"
            }

        }
    }

})

form.addEventListener("click", (e) => {
    if (flagCPF) {
        e.preventDefault();
        if (max) {
            document.getElementById("loginInnerMax").id = "loginInnerMin"
            document.getElementById("errorCPFMax").id = "errorCPFDis";
            max = false;
        } else {
            document.getElementById("errorCPF").id = "errorCPFDis";
        }
        flagCPF = false;
    } else if (flagEmpty) {
        e.preventDefault();
        if (max) {
            document.getElementById("loginInnerMax").id = "loginInnerMin"
            document.getElementById("errorEmptyMax").id = "errorEmptyDis";
            max = false;
        } else {
            document.getElementById("errorEmpty").id = "errorEmptyDis";
        }
        flagEmpty = false;
    }
})

function mascaraCPF(i){

    var v = i.value;

    if(isNaN(v[v.length-1])){
        i.value = v.substring(0, v.length-1);
        return;
    }

    i.setAttribute("maxlength", "14");
    if (v.length == 3 || v.length == 7)
        i.value += ".";
    if (v.length == 11) i.value += "-";

}

let olho = document.getElementById("eye");
let senha = document.getElementById("password")
olho.addEventListener("click", () => {
    if (senha.type != "text") {
        senha.type = "text"
    } else {
        senha.type = "password";
    }
})

function mascaraTel(objeto){

    let value = objeto.value

    if (isNaN(value[value.length-1])) {
        objeto.value = value.substring(0, value.length-1)
        return;
    }

    if (value.length == 1) {
        objeto.value = '(' + objeto.value;
    }

    if(value.length == 3)
        objeto.value = objeto.value + ') ';
}

function testaCPF(cpf) {
    cpf = cpf.replace(/[^\d]+/g, '');
    if (cpf == '')
        return false;
    if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999")
        return false;
    add = 0;
    for (i = 0; i < 9; i++)
        add += parseInt(cpf.charAt(i)) * (10 - i); rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(9)))
        return false; add = 0;
    for (i = 0; i < 10; i++)
        add += parseInt(cpf.charAt(i)) * (11 - i); rev = 11 - (add % 11);
    if (rev == 10 || rev == 11)
        rev = 0;
    if (rev != parseInt(cpf.charAt(10)))
        return false;
    return true;
}