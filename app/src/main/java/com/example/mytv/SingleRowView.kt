package com.example.mytv

import android.graphics.drawable.Drawable
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

data class SingleRowView(
     val name:String?=null,
     val image: Drawable? = null)
val main1=MainActivity()
val obj= main1.getJSONFromAssets()
val moshi: Moshi = Moshi.Builder().build()
val jsonadapter: JsonAdapter<Feed> = moshi.adapter(Feed::class.java)
val feed = jsonadapter.fromJson(obj)
