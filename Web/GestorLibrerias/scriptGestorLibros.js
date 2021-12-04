var libroBorrado;

var listaLibrosjq = $("#listaLibros");
console.log(listaLibrosjq);

var ocultos = false;
function cambiarVisibilidad(){
	if(ocultos){
		//mostrar los textos
		listaLibrosjq.show();
		ocultos = false;
	}else{
		//ocultar los textos
		listaLibrosjq.hide();
		ocultos = true;
	}
}

function mostrarPanel(){
    $("#panel").show();
}

function mostrarBotonBorrar(){
    $("#btnBorrar").show();
}

function ocultarBotonBorrar(){
    $("#btnBorrar").hide();
}

//La función find permite buscar otros elementos por id, clase o etiqueta, dentro del elemento sobre el que se aplica
$("#panel-content").find("span").click(function(){
	$("#panel").hide();
});

/*FUNCIONALIDAD DEL FORMULARIO*/

//Verificar si ya se hay datos guardados, si no, crearlos
var datos = localStorage.getItem("data");
if(!datos){
	//Si no hay datos se crea la estructura y se guarda
	datos = {
		"recargas":0,
		"libros":[]
	};
	//guardar los datos convertidos en string JSON
	localStorage.setItem("data",JSON.stringify(datos));
}else{
	//Si ya había datos, decodificarlos
	datos = JSON.parse(datos);
}

//Incrementar el contador de recargas y mostrarlo en la página
datos.recargas++;
$("#recargas").text(datos.recargas);
localStorage.setItem("data",JSON.stringify(datos));
//localStorage.clear(); PARA LIMPIAR TANTO LAS RECARGAS COMO LOS DATOS!! -----------------------------------
//Mostrar los datos de los visitantes
mostrarDatos();

//Lectura de datos del formulario
function registrarLibro(){
	//Leer los datos del formulario y dejar de nuevo los campos vacíos
	var titulo = $("#input-titulo").val();
	$("#input-titulo").val("");

	var autor = $("#input-autor").val();
	$("#input-autor").val("");

	var publicacion = $("#input-publicacion").val();
	$("#input-publicacion").val("");

	var sinopsis = $("#input-sinopsis").val();
	$("#input-sinopsis").val("");

	var calificacion = $("#input-calificacion").val();
	$("#input-calificacion").val("");

	var notas = $("#input-notas").val();
	$("#input-notas").val("");

	//Crear el objeto de los libros
	var listaLibros = {"titulo":titulo,"autor":autor,"publicacion":publicacion,"sinopsis":sinopsis,"calificacion":calificacion,"notas":notas};
	
	//Añadirlo a la lista y guardarla
	if(libroBorrado > -1){
		datos.libros[libroBorrado] = listaLibros;
		libroBorrado = -1;
	}else{
		datos.libros.push(listaLibros);
	}
	
	localStorage.setItem("data",JSON.stringify(datos));

	//Cerrar la vista del formulario y recargar el listado
	$("#panel").hide();
	mostrarDatos();
}

//Función para cargar los datos en la lista
function mostrarDatos(){
	//Limpiar la lista de datos previos
	$("#listaLibros").empty();

	var filtro = $("#filtro").val();

	//Recorrer la lista de libros
	$.each(datos.libros,function(index,value){

		//Si hay filtro, ver si coincide
		var titulo = value.titulo.toLowerCase();
		if(titulo.includes(filtro.toLowerCase())|| !filtro){

			//Crear un elemento de la lista con los datos de los libros y su índice del array
			//indice del array
			var tr = $("<tr></tr>");
			var td1 = $("<td></td>").text(value.titulo);
			var td2 = $("<td></td>").text(value.autor);
			var td3 = $("<td></td>").text(value.publicacion);
			var td4 = $("<td></td>").text(value.calificacion);
			var td5 = $("<td></td>");
			var btnEdit = $("<button data-id="+index+"></button>").text("Editar");
			$("#listaLibros").append(tr);
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			tr.append(td5);
			td5.append(btnEdit);
			btnEdit.click(function(){
				mostrarPanel(true,$(this).data('id'));
			});
		}
	});
}

//Función para abrir panel de editar
function mostrarPanel(editar, id){

	var titulo = $("#input-titulo").val();
	var autor = $("#input-autor").val();
	var publicacion = $("#input-publicacion").val();
	var sinopsis = $("#input-sinopsis").val();
	var calificacion = $("#input-calificacion").val();
	var notas = $("#input-notas").val();
	if(editar){
		//cargar los datos de la fila en las celdad/inputs, los que ya están puestos
		$("#input-titulo").val(datos.libros[id].titulo);
		$("#input-autor").val(datos.libros[id].autor);
		$("#input-publicacion").val(datos.libros[id].publicacion);
		$("#input-sinopsis").val(datos.libros[id].sinopsis);
		$("#input-calificacion").val(datos.libros[id].calificacion);
		$("#input-notas").val(datos.libros[id].notas);
		
		console.log(datos.libros[id]);
		//Mostrar el botón de borrar
		mostrarBotonBorrar();
		//añadir un parametro/data o guardar la variablev
		libroBorrado = id;
		//si pongo aqui, el var libro, lo pierdo.
		} else{
			ocultarBotonBorrar();
			ocultarPanel();
		}
		$("#panel").show();
}

function borrarLibro(){
	//buscar el indice de la visita/libro a editar
	var response = confirm("¿Realmente quieres borrar el libro?");
	if(response){
		//borrar la visita del array con la funcion split
		datos.libros.splice(libroBorrado,1);
		
		localStorage.setItem("data",JSON.stringify(datos));
		ocultarPanel();
		mostrarDatos();
	}
}
	
function editar(){
		//buscar el indice de la visita/libro a editar
}

//con esto se oculta el panel despues de borrar, y se borran los datos predeterminados
function ocultarPanel(){
	$("#input-titulo").val("");
	$("#input-autor").val("");
	$("#input-publicacion").val("");
	$("#input-sinopsis").val("");
	$("#input-calificacion").val("");
	$("#input-notas").val("");
	$("#panel").hide();
}

//Cambiar calificacion por estrellas
$('.starrr').starrr({
	rating: 3
});

//Ordenación
$("th").click(function(){
	ordenar($(this).data('campo'));
	
});


function ordenar(campo){
	console.log(campo)
	datos.libros.sort(function (a, b){
		console.log(campo) 
		//obtener el campo por el que se filtra
		var valor1, valor2;
		switch(campo){
			case "titulo":
				valor1 = a.titulo;
				valor2 = b.titulo;
				break

			case "autor":
				valor1 = a.autor;
				valor2 = b.autor;
				break

			case "publicacion":
				valor1 = a.publicacion;
				valor2 = b.publicacion;
				break

			case "calificacion":
				valor1 = a.calificacion;
				valor2 = b.calificacion;
				break
		}

		if (valor1 > valor2) {
			return 1;
		}
		if (valor1 < valor2) {
			return -1;
		}
		return 0;
	});
	mostrarDatos();
};	

		 
	