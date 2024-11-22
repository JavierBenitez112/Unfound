package com.example.unfound.Presentation.profile

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.Bitmap
import com.example.unfound.Data.local.DataStoreUserPrefs
import com.example.unfound.R
import kotlinx.coroutines.launch

@Composable
fun ProfileRoute(
    onBackClick: () -> Unit,
    dataStoreUserPrefs: DataStoreUserPrefs
) {
    ProfileScreen(
        onBackClick = onBackClick,
        modifier = Modifier.fillMaxSize(),
        dataStoreUserPrefs = dataStoreUserPrefs
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    profileViewModel: ProfileScreenViewModel = viewModel(),
    dataStoreUserPrefs: DataStoreUserPrefs
) {
    val visitedPlaces by profileViewModel.visitedPlaces.collectAsState()
    var userName by remember { mutableStateOf("Cambiar Nombre") }
    val isDarkTheme = isSystemInDarkTheme()
    val logoResource = if (isDarkTheme) R.drawable.unfoundbgwhite else R.drawable.unfoundbg
    var isEditing by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    var profileImage by remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data
            uri?.let {
                val inputStream = context.contentResolver.openInputStream(it)
                profileImage = BitmapFactory.decodeStream(inputStream)
            }
        }
    }
    val scope = rememberCoroutineScope()
    val nameFlow = dataStoreUserPrefs.getName().collectAsState(initial = "")

    LaunchedEffect(nameFlow.value) {
        userName = nameFlow.value ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { onBackClick() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "back")
                        }
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = logoResource),
                                contentDescription = "Logo",
                                modifier = Modifier.size(50.dp)
                            )
                        }
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(Icons.Default.Menu, contentDescription = "menu", tint = Color.Transparent)
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    profileImage?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        )
                    } ?: Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (isEditing) {
                            OutlinedTextField(
                                value = userName,
                                onValueChange = { userName = it },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(0.8f),
                                placeholder = { Text("Introduce tu nombre") },
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        scope.launch {
                                            dataStoreUserPrefs.saveName(userName)
                                            isEditing = false
                                            keyboardController?.hide()
                                        }
                                    }
                                )
                            )
                        } else {
                            Text(
                                text = userName,
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                        }
                        IconButton(
                            onClick = { isEditing = !isEditing }
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit Name")
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

//                    Button(
//                        onClick = {
//                            val intent = Intent(Intent.ACTION_PICK).apply {
//                                type = "image/*"
//                            }
//                            launcher.launch(intent)
//                        },
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = MaterialTheme.colorScheme.surface,
//                            contentColor = MaterialTheme.colorScheme.primary
//                        ),
//                        modifier = Modifier.padding(8.dp),
//                        shape = RoundedCornerShape(16.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Edit,
//                            contentDescription = "Edit Icon"
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Text(text = "Cambiar foto de perfil")
//                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Lugares visitados",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(visitedPlaces) { place ->
                    VisitedPlaceCard(
                        name = place.name,
                        description = place.address ?: "",
                        photoBitmap = place.photoBitmap
                    )
                }
            }
        }
    )
}

@Composable
fun VisitedPlaceCard(
    name: String,
    description: String,
    photoBitmap: Bitmap?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            photoBitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Place Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
        }
    }
}