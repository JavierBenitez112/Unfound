package com.example.unfound.Presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavBar(
    checkItemSelected: (Any) -> Boolean,
    onNavItemClick: (Any) -> Unit
) {
    NavigationBar {
        navigationItems.forEach { navItem ->
            val isItemSelected = checkItemSelected(navItem.destination)
            NavigationBarItem(
                selected = isItemSelected,
                label = { Text(navItem.title) },
                onClick = {
                    onNavItemClick(navItem.destination)
                },
                icon = {
                    Icon(
                        imageVector = if (isItemSelected) {
                            navItem.selectedIcon as ImageVector
                        } else navItem.unselectedIcon as ImageVector,
                        contentDescription = navItem.title
                    )
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(
        checkItemSelected = { true },
        onNavItemClick = { }
    )
}