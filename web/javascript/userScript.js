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