const $btnImprimir = document.querySelector("#btnImprimir");

$btnImprimir.addEventListener("click", () => {
	let ocultos = document.querySelectorAll("p,strong,h1,h2,a,button,img,footer,card-body,select,label");
	ocultos.forEach(elemento => {
		elemento.classList.add("oculto-impresion");
	});
	let mostrados = document.querySelectorAll(".forzar-impresion");
	mostrados.forEach(elemento => {
		elemento.classList.remove("oculto-impresion");
	});
	window.print();
});

