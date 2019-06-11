/**
 * 
 */

function crearInput () {
	let parent = document.getElementById("nuevoInsert");//busca en donde agregar el INPUT
	
	//Crear tags
	let p =document.createElement("p");
	parent.appendChild(p);
	let nombre = document.createTextNode("Nombre");
	p.appendChild(nombre);
	
	//crear INPUT NOMBRE
	let f = document.createElement("input");//crea un nuevo INPUT
	f.type= "text";	//tipo de INPUT
	f.name="nombre";//nombre de INPUT
	parent.appendChild(f);//agrega el INPUT

	//Crear tags
	let p2 =document.createElement("p");
	parent.appendChild(p2);
	let usuario = document.createTextNode("Nombre de Usuario");
	p2.appendChild(usuario);
	//crea INPUT USUARIO
	let f2 = document.createElement("input");//crea un nuevo INPUT
	f2.type= "text";	//tipo de INPUT
	f2.name="usuario";//nombre de INPUT
	parent.appendChild(f2);//agrega el INPUT
	
	//Crear Tags
	let p3 =document.createElement("p");
	parent.appendChild(p3);
	let email = document.createTextNode("Email");
	p3.appendChild(email);
	//Crear INPUT EMAIL
	let f3 = document.createElement("input");//crea un nuevo INPUT
	f3.type= "text";	//tipo de INPUT
	f3.name="email";//nombre de INPUT
	parent.appendChild(f3);//agrega el INPUT
	
	//Crear Tags
	let p4 =document.createElement("p");
	parent.appendChild(p4);
	let ID = document.createTextNode("ID");
	p4.appendChild(ID);
	//Crear INPUT EMAIL
	let f4 = document.createElement("input");//crea un nuevo INPUT
	f4.type= "number";	//tipo de INPUT
	f4.name="ID";//nombre de INPUT
	f4.value="1";
	f4.min="1";
	f4.max="10";
	parent.appendChild(f4);//agrega el INPUT
	
	

	let br=document.createElement("br");
	parent.appendChild(br);


}

function eliminarInput(){
	let parent = document.getElementById("padrenuevoinsert");
	let child= document.getElementById("nuevoInsert");
	parent.removeChild(child);
}

//function redireccionar(){
//	  window.location.href="MostrarBD";
//	} 
//	setTimeout ("redireccionar()", 2000); //tiempo expresado en milisegundos
