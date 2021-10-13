package com.example.mytv.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.example.mytv.Feed
import com.example.mytv.R
import com.example.mytv.adapters.CardPresenter
import com.example.mytv.adapters.SeriesCardPresenter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException
import java.nio.charset.Charset

class MainFragment : BrowseSupportFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val obj = getJSONFromAssets()
        val jsonadapter: JsonAdapter<Feed> = Moshi.Builder().build().adapter(Feed::class.java)
        feed = obj?.let { jsonadapter.fromJson(it) }

        loadRows()
        setUI()


    }

    private fun loadRows() {

        val category1 = HeaderItem(0, "movies")
        val category2 = HeaderItem(1, "series")
        val rows1Adapter = ArrayObjectAdapter(CardPresenter())
        val rows2Adapter = ArrayObjectAdapter(SeriesCardPresenter())

        for (i in 0 until feed?.series?.size!!) {
           // if (i != 0) {
               for(j in 0 until 9) {
                   rows1Adapter.addAll(0, feed?.movies)
                   rows2Adapter.addAll(0, feed?.series)
               }
          //  }
        }
        val windowAdapter = ArrayObjectAdapter(ListRowPresenter())
        windowAdapter.add(0, ListRow(category1, rows1Adapter))
        windowAdapter.add(0, ListRow(category2, rows2Adapter))
        adapter = windowAdapter

    }


    private fun setUI() {

        title = feed?.providerName

        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireActivity(), R.color.background_gradient_end)
        searchAffordanceColor = ContextCompat.getColor(requireActivity(), R.color.search_opaque)


    }

    private fun getJSONFromAssets(): String? {

        val json: String?
        val charset: Charset = Charsets.UTF_8
        try {
            val myFeedJSONFile = context?.assets?.open("feed.json")
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

    companion object {
        var feed: Feed? = null
    }

}