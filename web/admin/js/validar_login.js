
function validarCorreo(parametro){

  if (/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i.test(parametro)){
        return true;
  } else {
        return false;
  }
}

function validar_login(){

    var formulario =document.login;
     
    // ================= correo electronico 
    if(formulario.correo.value==="" || /^\s+$/.test(formulario.correo.value) ){
      
        document.getElementById("alerta").innerHTML='<div class="alert alert-danger"><a href="" class="close" data-dismiss="alert">&times;</a>Ingrese el correo</div>';
        formulario.correo.value="";   
        formulario.correo.focus();
        return false;
     }else if(validarCorreo(formulario.correo.value)=== false){
        
        document.getElementById("alerta").innerHTML='<div class="alert alert-danger"><a href="" class="close" data-dismiss="alert">&times;</a>Por favor ingrese un correo electrónico valido</div>';
        formulario.correo.value="";      
        formulario.correo.focus();
        return false;
        
    }else if( formulario.correo.value.length<5 || formulario.correo.value.length>24 ) {
        document.getElementById("alerta").innerHTML='<div class="alert alert-danger"><a href="" class="close" data-dismiss="alert">&times;</a>El correo tiene un minimo de 5 caracteres y un maximo de 26 caracteres!</div>';
        formulario.correo.value="";     
        formulario.correo.focus();
        return false;
    } else{
         document.getElementById("alerta").innerHTML="";
    }
    
    // =================  clave
      if(formulario.password.value==="" || /^\s+$/.test(formulario.password.value) ){
      
        document.getElementById("alerta").innerHTML='<div class="alert alert-danger"><a href="" class="close" data-dismiss="alert">&times;</a>Ingrese su contraseña</div>';
        formulario.password.value=""; 
        formulario.password.focus();
        return false;
     }else if( formulario.password.value.length<9 || formulario.password.value.length>24 ) {
        document.getElementById("alerta").innerHTML='<div class="alert alert-danger"><a href="" class="close" data-dismiss="alert">&times;</a>La contraseña tiene un mínimo de 9 caracteres y un máximo de 26 caracteres!</div>';
        formulario.password.value="";     
        formulario.password.focus();
        return false;
    }
     else{
         document.getElementById("alerta").innerHTML="";
    }
    
    
    
    
    
    
    
}