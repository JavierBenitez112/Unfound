package com.example.unfound.Presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.unfound.R


data class NavItem(
    val title: String,
    val selectedIcon: Any,
    val unselectedIcon: Any,
    val destination: Any,
)


val navigationItems = listOf(
    NavItem(
        title = "Menu",
        selectedIcon = Icons.Filled.Menu,
        unselectedIcon = Icons.Outlined.Menu,
        destination = { /* Acción de menú */ }
    ),
    NavItem(
        title = "Home",
        selectedIcon =  R.drawable.unfoundbg,
        unselectedIcon =  R.drawable.unfoundbg,
        destination = { /* Acción de menú */ }
    ),
    NavItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        destination = { /* Acción de menú */ }
    )
)