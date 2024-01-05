const toggle = document.querySelector(".menu-toggle");
const menu = document.querySelector(".menu");

function toggleMenu() {
    if (menu.classList.contains("expanded")){
        menu.classList.remove("expanded");
        toggle.querySelector('a').innerHTML = '<i id="toggle-icon" class="far fa-plus-square"></i>';
    } else {
        menu.classList.add("expanded");
        toggle.querySelector('a').innerHTML = '<i id="toggle-icon" class="far fa-minus-square"></i>';
    }
}

toggle.addEventListener("click",toggleMenu,false);


function pokazOkienko() {
    // Pobieramy referencję do okna dialogowego
    var oknoDialogowe = document.getElementById("oknoDialogowe");

    // Ustawiamy pozycję okna dialogowego na miejscu przycisku
    oknoDialogowe.style.left = (event.clientX + window.scrollX) + "px";
    oknoDialogowe.style.top = (event.clientY + window.scrollY) + "px";

    // Wyświetlamy okno dialogowe
    oknoDialogowe.style.display = "block";
}
