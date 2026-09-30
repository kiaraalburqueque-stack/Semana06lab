package com.alburqueque.tecsupstore.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alburqueque.tecsupstore.ui.components.TarjetaProducto
import kotlinx.coroutines.launch

data class ProductoSimple(val id: Int, val nombre: String, val precio: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavegacion() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentRoute by remember { mutableStateOf("inicio") }

    val productos = listOf(
        ProductoSimple(1, "Laptop Gamer", "S/ 3,500.00"),
        ProductoSimple(2, "Smartwatch Pro", "S/ 299.00"),
        ProductoSimple(3, "Audífonos Bluetooth", "S/ 120.00")
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawerContent(
                onDestinationSelected = { route ->
                    currentRoute = route
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("TECSUP Store") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (currentRoute) {
                    "inicio" -> {
                        LazyColumn {
                            items(productos) { p ->
                                TarjetaProducto(nombre = p.nombre, precio = p.precio)
                            }
                        }
                    }
                    "pedidos" -> Text("Pantalla: Mis pedidos", modifier = Modifier.padding(16.dp))
                    "favoritos" -> Text("Pantalla: Favoritos", modifier = Modifier.padding(16.dp))
                    "perfil" -> Text("Pantalla: Perfil de usuario", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}