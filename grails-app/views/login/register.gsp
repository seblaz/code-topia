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
        <g:form controller="login" action="createUser" method="post">
          <div class="form-group">
              <label>Nombre</label>
              <input type="text" name="firstName" class="form-control" placeholder="Nombre">

              <label>Apellido</label>
              <input type="text" name="lastName" class="form-control" placeholder="Apellido">

              <label>Email</label>
              <input type="text" name="email" class="form-control" placeholder="some@example.com">
          </div>
          <g:hasErrors bean="${flash}">
              <div class="error">${flash.createError}</div>
          </g:hasErrors>
          <g:renderErrors  as="list" />
          <button type="submit" class="btn btn-primary">Crear</button>

        </g:form>

      </div>
    </div>
    
  </div>
  
  
</body>
</html>

