let anuncioDiv = document.getElementById("anunciosRoll");

let carro = document.getElementsByClassName("carro");
let size = 0;
if (carro[0] !== undefined) {
    size = carro[0].clientWidth;
    counter = 0;
    carro[0].className += " carro-selecionado";
}

let prevBtn = document.getElementById("prevBtn");
let nxtBtn = document.getElementById("nxtBtn");



nxtBtn.addEventListener("click", () => {
    if (counter >= carro.length -1) return;
    anuncioDiv.style.transition = "transform 0.4s ease-in-out";
    counter++;
    anuncioDiv.style.transform = "translateX(" + (-size * counter) + "px)";
    nxtBtn.style.left =  1050 + (size * counter) + "px"
    prevBtn.style.left =  95 + (size * counter) + "px"
    carro[counter-1].className = "carro"
    carro[counter].className += " carro-selecionado"
})

prevBtn.addEventListener("click", () => {
    if (counter <= 0) return;
    anuncioDiv.style.transition = "transform 0.4s ease-in-out";
    counter--;
    anuncioDiv.style.transform = "translateX(" + (-size * counter) + "px)";
    nxtBtn.style.left =  1050 + (size * counter) + "px"
    prevBtn.style.left =  85 + (size * counter) + "px"
    carro[counter+1].className = "carro"
    carro[counter].className += " carro-selecionado"
})


let valueEna = document.getElementsByClassName("input-value-ena");
let inputDis = document.getElementsByClassName("input-dis");

let valueDis = document.getElementsByClassName("input-value-dis");
let inputEna = document.getElementsByClassName("input-ena");

function mascaraTel(objeto){
    var v = objeto.value;

    if(isNaN(v[v.length-1])){ // impede entrar outro caractere que não seja número
        objeto.value = v.substring(0, v.length-1);
        return;
    }

    if(objeto.value.length == 1)
        objeto.value = '(' + objeto.value;

    if(objeto.value.length == 3)
        objeto.value = objeto.value + ') ';

    if (objeto.value.length == 14) {
        objeto.value = objeto.value.substring(0, 9) + objeto.value.substring(10,11)+
            "-" + objeto.value.substring(11, 15)
    } else if (objeto.value.length == 12) {
        objeto.value = objeto.value.substring(0, 9) + "-" + objeto.value.substring(9, 14)
    }
}

document.getElementById("editar").addEventListener("click", () => {
    for (let i = 0; i < 3; i++) {
        inputDis[0].value = valueEna[0].innerText;
        valueEna[0].className = "input-value-dis"
        inputDis[0].className = "input-ena"
    }
    document.getElementsByClassName("button-dis")[0].className = "button-ena"
})

let errorTel = false;

function show() {
    for (let i = 0; i < 3; i++) {
        document.getElementsByClassName("choiceDis")[0].className = "choiceEna"
    }
}

function hide() {
    for (let i = 0; i < 3; i++) {
        document.getElementsByClassName("choiceEna")[0].className = "choiceDis"
    }
}

function showAnuncio() {
    for (let i = 0; i < 3; i++) {
        document.getElementsByClassName("choiceADis")[0].className = "choiceAEna"
    }
}

function hideAnuncio() {
    for (let i = 0; i < 3; i++) {
        document.getElementsByClassName("choiceAEna")[0].className = "choiceADis"
    }
}

function save() {
    if (inputEna[2].value.length > 1) {
        let values = [];
        for (let i = 0; i < 3; i++) {
            values[i] = inputEna[0].value;
            valueDis[0].innerText = inputEna[0].value;
            valueDis[0].className = "input-value-ena"
            inputEna[0].className = "input-dis"
        }
        document.getElementsByClassName("button-ena")[0].className = "button-dis"
        if (errorTel)
            document.getElementById("errorTel").id = "errorTelDis"

        document.getElementById("info-user").innerHTML += "<a href='' id='disabled'></a>"
        let tel = values[2].split(" ").join("");
        document.getElementById("disabled").href = "updateProfile?nome=" + values[0] + "&email="
            + values[1] + "&tel=" + tel
        document.getElementById("disabled").click();

    } else {
        if (!errorTel) {
            document.getElementById("errorTelDis").id = "errorTel"
            errorTel = true;
        }
    }
}

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