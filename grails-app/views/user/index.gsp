<!-- grails-app/views/login/index.gsp -->


<html>
<head>
<link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'login.css')}" type="text/css">


<link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'bootstrap.min.css')}" type="text/css">

<script src="${assetPath(src: 'jquery-3.5.1.min.js')}"></script>
<script src="${assetPath(src: 'bootstrap.min.js')}"></script>

</head>
<body>
  <div class="sidenav">
      <div class="login-main-text">
        <img width="100" heigth="100" src="${assetPath(src: 'CodeTopia.svg')}" style="filter: invert(100%);">  
        <h1>Code Topia</h1>
        <p>Aplicación de aprendizaje.</p>
      </div>
  </div>
  
  <div class="main">
    <div class="col-md-6 col-sm-12">
      <div class="login-form">
        <g:form controller="user" action="loginUser" method="post">
          <div class="form-group">
              <label>Email</label>
              <input type="text" name="email" class="form-control" placeholder="some@example.com">
          </div>
          <button type="submit" class="btn btn-primary">Entrar</button>
        </g:form>
      </div>
    </div>
    
    <div class="col-md-6 col-sm-12">
      <g:link controller="user" action="registerUser">
        <button class="btn btn-secondary">Registrarse</button>
      </g:link>
    </div>

    <div class="col-md-6 col-sm-12">
      <g:if test="${flash.mostrarAlerta && flash.message }">
        <p>${flash.message}</p>
      </g:if>
    </div>

    <!-- Modal (oculto por defecto) -->
    <div id="myModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Error</h5>
                </div>
                <div class="modal-body">
                    <p>${flash.message}</p>
                </div>
                <div class="modal-footer justify-content-center">
                  <button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar</button>
                </div>
            </div>
        </div>
    </div>

    <g:javascript>
        $(document).ready(function() {
            var abrirModal = "${abrirModal}";
            if (abrirModal === "true") {
                $("#myModal").modal('show');
            }
        });
    </g:javascript>
    
  </div>
  
  
</body>
</html>

