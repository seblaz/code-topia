<!-- grails-app/views/login/index.gsp -->

<html>
<head>
<link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'login.css')}" type="text/css">


<link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'bootstrap.min.css')}" type="text/css">

<script src="${assetPath(src: 'bootstrap.min.js')}"></script>
<script src="${assetPath(src: 'jquery-3.5.1.min.js')}"></script>

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
    
  </div>
  
  
</body>
</html>

