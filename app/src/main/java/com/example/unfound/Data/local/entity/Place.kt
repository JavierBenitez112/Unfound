package com.example.unfound.Presentation.profile

import android.graphics.Bitmap

data class VisitedPlace(
    val id: String,
    val name: String,
    val address: String?,
    val photoBitmap: Bitmap?
)