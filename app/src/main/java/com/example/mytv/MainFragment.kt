package com.example.mytv

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import androidx.core.content.ContextCompat
import androidx.leanback.app.BackgroundManager
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

import java.util.*



class MainFragment : BrowseSupportFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUI()
        loadRows()
    }

    private fun loadRows() {
        val category1 = HeaderItem(0, "movies")
        val category2 = HeaderItem(1, "series")

        val rows1Adapter = ArrayObjectAdapter(CardPresenter())
        val rows2Adapter = ArrayObjectAdapter(CardPresenter())

        rows1Adapter.add(SingleRowView("movie",Drawable.createFromPath("drawable/movie.png")))
        rows1Adapter.add(SingleRowView("moviemovie",Drawable.createFromPath("drawable/movie.png")))
        rows1Adapter.add(SingleRowView("moviemoviemovie",Drawable.createFromPath("drawable/movie.png")))
        rows2Adapter.add(SingleRowView("series",Drawable.createFromPath("drawable/movie.png")))
        rows2Adapter.add(SingleRowView("seriesseries",Drawable.createFromPath("drawable/movie.png")))
        rows2Adapter.add(SingleRowView("seriesseriesseries",Drawable.createFromPath("drawable/movie.png")))



val windowAdapter=ArrayObjectAdapter(ListRowPresenter())
        windowAdapter.add(0, ListRow(category1,rows1Adapter))
        windowAdapter.add(1, ListRow(category2,rows2Adapter))
        adapter = windowAdapter
    }



    private fun setUI() {
        title = getString(R.string.browse_title)
        title
        headersState = BrowseSupportFragment.HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled =true
        brandColor = ContextCompat.getColor(requireActivity(), R.color.background_gradient_end)

    }


}