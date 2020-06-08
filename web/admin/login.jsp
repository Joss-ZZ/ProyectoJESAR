<%-- 
    Document   : login
    Created on : 08-jun-2020, 14:32:49
    Author     : PCGAMING
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="css/all.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <div class="card" style="width: 400px">
                    <div class="card-body">
                        <form name="login" action="Validar" method="POST" >
                        <br>
                        <div class="form-group" align="center">
                            <h3>Login</h3>
                            <img src="imagenes/logo.jpg"  >
                            <p>Bienvenido al sistema</p>
                             </div>    
                                <div class="col-md-10">
                                    <div class="input-group mb-4">
                                         <div class="input-group-prepend">
                                             <div class="input-group-text"><i class="fas fa-user"></i></div>
                                         </div>
                                             <input type="text"  name="usuario"  class="form-control" id="inlineFormInputGroup" placeholder="Usuario" 
                                               data-toggle="popover" 
                                               data-trigger="focus" 
                                               title="Usuario" 
                                               data-content="Ingrese sus usuario">
                                         </div><!---->

                                     <div class="input-group mb-4">
                                         <div class="input-group-prepend">
                                             <div class="input-group-text"><i class="fas fa-unlock-alt"></i></div>
                                         </div>
                                             <input type="password" name="password" class="form-control" id="inlineFormInputGroup" placeholder="Contraseña" 
                                               data-toggle="popover" 
                                               data-trigger="focus" 
                                               title="Contraseña" 
                                               data-content="Ingrese su password ">
                                    </div>
                                </div> <!--div-->
                        
                        
                        <input type="submit" value="Ingresar" style="width: 100%" class="form-control btn btn-primary text white"><br><br> 
                    </form>
                    </div>
                </div>
            </div>
            <div class="col-sm-4"></div>
        </div>
 
    </body>
    
</html>
