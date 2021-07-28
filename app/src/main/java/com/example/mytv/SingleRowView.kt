package com.example.mytv

import android.graphics.drawable.Drawable
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

data class SingleRowView(
     val name:String?=null,
     val image: Drawable? = null)

