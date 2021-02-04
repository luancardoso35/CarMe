let flagEmpty = false;
let error = document.getElementById("errorEmptyDisabled")

let inputs = document.getElementsByClassName("input");
let marca = inputs[0];
let modelo = inputs[1];
let cor = inputs[2];
let cambio = inputs[3];
let assentos = inputs[4];
let ano = inputs[5];
let kms = inputs[6];
let renavam = inputs[7];
let combustivel = inputs[8];
let preco = inputs[9];
let cidade = inputs[10];
let imagem = document.getElementById("imagem");

form.addEventListener("submit", (e) => {
    if (marca.value == "" || modelo.value == "" || cor.value == "" || cambio.value == "Câmbio" || assentos.value == ""
        || ano.value == "" || kms.value == "" || renavam.value == "" || combustivel.value == "Combustível" || preco.value == ""
        || imagem.value == "" || cidade.value == "") {
        e.preventDefault();
        flagEmpty = true;
        error.id = "errorEmptyEnabled";
    }
})

form.addEventListener("click", () => {
    if (flagEmpty) {
        flagEmpty = false;
        error.id = "errorEmptyDisabled";
    }
})

let burger = document.getElementById("burger");
let active = false;
let menu = document.getElementById("menu-inactive");

burger.addEventListener("click", () => {
    if (!active) {
        burger.className = "active"
        menu.id = "menu"
        active = true;
    } else {
        burger.className = ""
        menu.id = "menu-inactive"
        active = false;
    }
})