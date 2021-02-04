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

function destaque(obj) {
    for (x of document.getElementsByClassName("destaque")){
        x.className = "carro";
    }

    for (x of document.getElementsByClassName("img-carro-ena")) {
        x.className = "img-carro-dis";
    }

    for (x of document.getElementsByClassName("quilometragemEna")) {
        x.className = "quilometragemDis";
    }

    for (x of document.getElementsByClassName("combustivelEna")) {
        x.className = "combustivelDis";
    }

    obj.className = "destaque"
    let children = obj.children[5].children;
    obj.children[2].className = "img-carro-ena"
    for (x of children) {
        if (x.className == "quilometragemDis") {
            x.className = "quilometragemEna"
        } else {
            x.className = "combustivelEna"
        }
    }
}