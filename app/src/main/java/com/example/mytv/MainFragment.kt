package com.example.mytv


import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException
import java.nio.charset.Charset
import java.util.*



class MainFragment : BrowseSupportFragment() {

    var feed: Feed?=null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val obj=getJSONFromAssets()
        val jsonadapter: JsonAdapter<Feed> = Moshi.Builder().build().adapter(Feed::class.java)
         feed = obj?.let {  jsonadapter.fromJson(it)}
        setUI()
        loadRows()
    }

    private fun loadRows() {

        val category1 = HeaderItem(0, "movies")
        val category2 = HeaderItem(1, "series")
        val rows1Adapter = ArrayObjectAdapter(CardPresenter())
        val rows2Adapter = ArrayObjectAdapter(SeriesCardPresenter())


        for (i in 0 until 10) {
            if (i != 0) {
                rows1Adapter.addAll(0,feed?.movies)
                rows2Adapter.addAll(0,feed?.series)
            }}
        val windowAdapter=ArrayObjectAdapter(ListRowPresenter())
            windowAdapter.add( 0,ListRow(category1,rows1Adapter),)
            windowAdapter.add( 1,ListRow(category2,rows2Adapter))
            adapter = windowAdapter
    }



    private fun setUI() {
        title = getString(R.string.browse_title)
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireActivity(), R.color.background_gradient_end)
        searchAffordanceColor = ContextCompat.getColor(requireActivity(), R.color.search_opaque)
    }

    fun getJSONFromAssets(): String? {

        var json: String?=null
        val charset: Charset = Charsets.UTF_8
        try {
            val myFeedJSONFile =context?.assets?.open("feed.json")
            val size = myFeedJSONFile?.available()
            val buffer = size?.let { ByteArray(it) }
            myFeedJSONFile?.read(buffer)
            myFeedJSONFile?.close()
            json = buffer?.let { String(it, charset) }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

}