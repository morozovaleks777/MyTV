package com.example.mytv

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

import java.util.*



class MainFragment : BrowseSupportFragment() {
    lateinit var movy: Feed.Movy
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val main1=MainActivity()
        val obj= main1.getJSONFromAssets()
        val moshi: Moshi = Moshi.Builder().build()
        val jsonadapter: JsonAdapter<Feed.Movy> = moshi.adapter(Feed.Movy::class.java)
        val movy = jsonadapter.fromJson(obj)
        setUI()
        loadRows()
    }

    private fun loadRows() {
        val category1 = HeaderItem(0, "movies")
        val category2 = HeaderItem(1, "series")

        val rows1Adapter = ArrayObjectAdapter(CardPresenter())

        rows1Adapter.add(SingleRowView("movie",Drawable.createFromPath("drawable/movie.png")))
        rows1Adapter.add(SingleRowView("moviemovie",Drawable.createFromPath("drawable/movie.png")))
        rows1Adapter.add(SingleRowView("moviemoviemovie",Drawable.createFromPath("drawable/movie.png")))
rows1Adapter.add(listOf(movy))


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



}