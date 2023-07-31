<!DOCTYPE html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Code Topia</title>

    <!-- Custom fonts for this template-->
    <!-- FIXME: -->
    <link rel="stylesheet" href="${assetPath(src: 'fontawesome-free/css/all.min.css')}" type="text/css">
    <link rel="stylesheet" href="${assetPath(src: 'fontawesome-free/css/all.min.css')}" type="text/css">
    
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <!-- FIXME: -->
    <link rel="stylesheet" href="${resource(dir: 'stylesheets', file: 'login-index-min.css')}" type="text/css">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center">
                <div class="sidebar-brand-icon ">
                    <img width="80" heigth="80" src="${assetPath(src: 'CodeTopia.svg')}" style="filter: invert(100%);">
                </div>
                <div class="sidebar-brand-text mx-3">Code Topia</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item active">
                <g:link class="nav-link" controller="home" action="index">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Inicio</span></a>
                </g:link>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                    aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-cog"></i>
                    <span>Ejercicios</span>
                </a>
                <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <!--Por cada ejercicio quiero mostrar un item-->
                        <g:each in="${exerciseList}" var="exercise">
                            <g:link controller="exercise" action="index" params="[exerciseId: exercise.id]">
                                <div class="collapse-item">${exercise.title}</div>
                            </g:link>
                        </g:each>
                    </div>
                </div>
            </li>

            

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
            </div>

            <!-- Nav Item - Item -->
            <li class="nav-item">
                <g:link class="nav-link" controller="home" action="help">
                    <i class="fas fa-fw fa-greater-than"></i>
                    <span>Ayuda</span></a>
                </g:link>
                <g:link class="nav-link" controller="home" action="aboutCodeTopia">
                    <i class="fas fa-fw fa-greater-than"></i>
                    <span>Acerca de CodeTopia</span></a>
                </g:link>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            

            

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    
                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>


                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.firstName}</span>
                                <img class="img-profile rounded-circle"
                                    src="${assetPath(src: 'undraw_profile.svg')}">
                            </a>                            
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Perfil
                                </a>
                                
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Salir
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Ayuda</h1>
                    </div>

                    <!-- Content Row -->
                    <div class="row">
                        CodeTopia es una plataforma que te permite aprender a programar de manera interactiva.
                        <br>
                        Puedes elegir entre una gran variedad de ejercicios, que te permitirán aprender a programar en distintos lenguajes.
                        <br>
                        Debes completar los ejercicios para poder desbloquear nuevos y así poder seguir aprendiendo.
                        <br>
                        Si necesitas ayuda en un ejercicio, puedes pedir una pista, pero ten en cuenta que esto te restará puntos.
                        <br>
                        Puedes repetir un ejercicio que no hayas completado correctamente, una vez finalizado 100% no podrás volver a repetirlo.
                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Code Topia 2023</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Listo para salir?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Seleccioná "Salir" a continuación si quiere finalizar la sesión.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                    <a class="btn btn-primary" href="${createLink(controller: 'user', action: 'index')}">Salir</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="${assetPath(src: 'jquery-3.5.1.min.js')}"></script>    
    <script src="${assetPath(src: 'bootstrap.bundle.min.js')}"></script>

    <!-- Core plugin JavaScript-->
    <script src="${assetPath(src: 'jquery.easing.min.js')}"></script>

    

</body>

</html>