var btnAbrirPopup = document.getElementById('btn-abrir-popup'),
    overlay = document.getElementById('overlay'),
    popup = document.getElementById('popup'),
    btnCerrarPopup = document.getElementById('btn-cerrar-popup');
    
btnAbrirPopup.addEventListener('click',function(){
   overlay.classList.add('active'); 
});

btnCerrarPopup.addEventListener('click',function(){
   overlay.classList.remove('active'); 
});

var btnAbrirPopupNuevoCliente = document.getElementById('btn-nuevo-cliente'),
    overlayNuevoCliente = document.getElementById('overlay-nuevo-cliente'),
    popupNuevoCliente = document.getElementById('popup-nuevo-cliente'),
    btnCerrarPopupNuevoCliente = document.getElementById('btn-cerrar-popup-nuevo-cliente');
    
btnAbrirPopupNuevoCliente.addEventListener('click',function(){
   overlayNuevoCliente.classList.add('active'); 
});

btnCerrarPopupNuevoCliente.addEventListener('click',function(){
   overlayNuevoCliente.classList.remove('active'); 
});