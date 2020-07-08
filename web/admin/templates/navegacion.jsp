<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="#" class="brand-link">
        <img src="/Admin-JESAR/admin/img/AdminLTELogo.png"
             alt="Calzados JESAR Logo"
             class="brand-image img-circle elevation-3"
             style="opacity: .8">
        <span class="brand-text font-weight-light">Calzados JESAR</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="info">
                <p id="menuadmin">MENÚ DE ADMINISTRACIÓN</p>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
                     with font-awesome or any other icon font library -->

                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="fas fa-users nav-icon"></i>
                        <p>
                            Empleados
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="tipoDocumento.jsp" class="nav-link">
                                <i class="far fa-id-card nav-icon"></i>
                                <p>Mantener Tipo Documento</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="tipoEmpleado.jsp" class="nav-link">
                                <i class="fas fa-certificate nav-icon"></i>
                                <p>Mantener Tipo Empleado</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="empleado.jsp" class="nav-link">
                                <i class="fas fa-clipboard nav-icon"></i>
                                <p>Mantener Empleados</p>
                            </a>
                        </li>
                    </ul>
                    <ul class="nav nav-treeview">
                        <li class="nav-item has-treeview">
                            <a href="#" class="nav-link">
                                <i class="fas fa-pencil-ruler nav-icon"></i>
                                <p>
                                    Operarios
                                    <i class="right fas fa-angle-left"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="tipoOperario.jsp" class="nav-link">
                                        <i class="fas fa-certificate nav-icon"></i>
                                        <p>Mantener Tipo Operarios</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="operadores.jsp" class="nav-link">
                                        <i class="fas fa-clipboard nav-icon"></i>
                                        <p>Mantener Operarios</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>

                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="fas fa-warehouse nav-icon"></i>
                        <p>
                            Gestionar Almacén
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item has-treeview">
                            <a href="#" class="nav-link">
                                <i class="fas fa-box nav-icon"></i>
                                <p>
                                    Insumos
                                    <i class="right fas fa-angle-left"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="categoriaInsumo.jsp" class="nav-link">
                                        <i class="far fa-clipboard nav-icon"></i>
                                        <p>Mantener Categoria Insumo</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="unidadInsumo.jsp" class="nav-link">
                                        <i class="far fa-clone nav-icon"></i>
                                        <p>Mantener Unidad Insumo</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="insumos.jsp" class="nav-link">
                                        <i class="fas fa-cog nav-icon"></i>
                                        <p>Mantener Insumos</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav nav-treeview">
                        <li class="nav-item has-treeview">
                            <a href="#" class="nav-link">
                                <i class="fas fa-shoe-prints nav-icon"></i>
                                <p>
                                    Productos
                                    <i class="right fas fa-angle-left"></i>
                                </p>
                            </a>
                            <ul class="nav nav-treeview">
                                <li class="nav-item">
                                    <a href="categoriaProducto.jsp" class="nav-link">
                                        <i class="fab fa-stack-overflow nav-icon"></i>
                                        <p>Mantener Categoria Productos</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="serie.jsp" class="nav-link">
                                        <i class="fas fa-barcode nav-icon"></i>
                                        <p>Mantener Serie de Productos</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="color.jsp" class="nav-link">
                                        <i class="fas fa-palette nav-icon"></i>
                                        <p>Mantener Color de Productos</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="modeloProducto.jsp" class="nav-link">
                                        <i class="fab fa-buromobelexperte nav-icon"></i>
                                        <p>Mantener Modelos Productos</p>
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a href="productos.jsp" class="nav-link">
                                        <i class="fas fa-tag nav-icon"></i>
                                        <p>Mantener Productos</p>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>          

                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="fas fa-clipboard-list nav-icon"></i>
                        <p>
                            Clientes
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="tipoDocumento.jsp" class="nav-link">
                                <i class="far fa-id-card nav-icon"></i>
                                <p>Mantener Tipo Documento</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="controladorCliente.jsp" class="nav-link">
                                <i class="fas fa-clipboard nav-icon"></i>
                                <p>Mantener Clientes</p>
                            </a>
                        </li>
                    </ul>
                </li>
                
                <li class="nav-item has-treeview">
                    <a href="#" class="nav-link">
                        <i class="fas fa-file-alt nav-icon"></i>
                        <p>
                            Orden de Producción
                            <i class="right fas fa-angle-left"></i>
                        </p>
                    </a>
                    <ul class="nav nav-treeview">
                        <li class="nav-item">
                            <a href="registrarOrdenProduccion.jsp" class="nav-link">
                                <i class="fas fa-plus-circle nav-icon"></i>
                                <p>Registrar Orden de Producción</p>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="consultarOrdenProduccion.jsp" class="nav-link">
                                <i class="fas fa-search nav-icon"></i>
                                <p>Consultar Ordenes de Producción</p>
                            </a>
                        </li>
                    </ul>
                </li>
               
            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>