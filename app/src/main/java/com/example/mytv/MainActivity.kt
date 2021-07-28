package com.example.mytv

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun getJSONFromAssets(): String? {

        var json: String? = null
        val charset: Charset = Charsets.UTF_8
        try {
            val myFeedJSONFile = assets.open("feed.json")
            val size = myFeedJSONFile.available()
            val buffer = ByteArray(size)
            myFeedJSONFile.read(buffer)
            myFeedJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}