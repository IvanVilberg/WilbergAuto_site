let menu = document.querySelector('#menu-icon');
let navigation_bar = document.querySelector('.navigation-bar');

menu.onclick = () => {
    menu.classList.toggle('bx-x');
    navigation_bar.classList.toggle('open');
}