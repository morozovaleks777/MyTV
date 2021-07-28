package com.example.mytv


import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.io.IOException
import java.nio.charset.Charset


class MainFragment : BrowseSupportFragment() {
    lateinit var movie: Feed.Movy
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
      //  val main1=MainActivity()
        val obj=getJSONFromAssets()
        val moshi: Moshi = Moshi.Builder().build()
        val jsonadapter: JsonAdapter<Feed.Movy> = moshi.adapter(Feed.Movy::class.java)
         movie = jsonadapter.fromJson(obj)!!
        setUI()
        loadRows()
    }

    private fun loadRows() {
        val category1 = HeaderItem(0, "movies")
        val category2 = HeaderItem(1, "series")

        val rows1Adapter = ArrayObjectAdapter(CardPresenter())
//
//        rows1Adapter.add(SingleRowView("movie",Drawable.createFromPath("drawable/movie.png")))
//        rows1Adapter.add(SingleRowView("moviemovie",Drawable.createFromPath("drawable/movie.png")))
//        rows1Adapter.add(SingleRowView("moviemoviemovie",Drawable.createFromPath("drawable/movie.png")))
rows1Adapter.add(listOf(movie))


val windowAdapter=ArrayObjectAdapter(ListRowPresenter())
        windowAdapter.add( ListRow(category1,rows1Adapter))
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

        var json: String? = null
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