var flagEmpty = false

const form = document.getElementById("form");
var formHTML = form.innerHTML;
form.addEventListener("submit", (e) => {
    let cpf = document.getElementById("cpf");
    let password = document.getElementById("password");

    if (cpf.value == "" || password.value == "") {
        e.preventDefault();
        if (!flagEmpty) {
            flagEmpty = true;
            form.innerHTML += "<p id='errorEmpty'>Todos os campos devem ser preenchidos</p>"
            document.getElementById("loginInner").id = "loginInnerWithError";
        }
    }
})

form.addEventListener("click", () => {
    if (flagEmpty) {
        form.innerHTML = formHTML
        flagEmpty = false;
        document.getElementById("loginInner").id = "loginInner";
    }})

function mascaraCPF(i){

    var v = i.value;

    if(isNaN(v[v.length-1])){ // impede entrar outro caractere que não seja número
        i.value = v.substring(0, v.length-1);
        return;
    }

    i.setAttribute("maxlength", "14");
    if (v.length == 3 || v.length == 7)
        i.value += ".";
    if (v.length == 11) i.value += "-";

}
