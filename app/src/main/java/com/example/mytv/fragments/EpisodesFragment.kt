package com.example.mytv.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.example.mytv.fragments.MainFragment.Companion.feed
import com.example.mytv.R
import com.example.mytv.adapters.EpisodesCardPresenter


class EpisodesFragment : BrowseSupportFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadRows()
        setUI()


    }


    private fun loadRows() {
        val category = mutableListOf<HeaderItem>()
        for (i in 0 until feed?.series?.get(0)?.seasons?.size!!)
            category.add(
                (HeaderItem(
                    0,
                    "season ${feed?.series?.get(0)?.seasons?.get(i )?.seasonNumber}"
                ))
            )

        val rows1Adapter = ArrayObjectAdapter(EpisodesCardPresenter())


        for (i in 0 until category.size) {
            rows1Adapter.addAll(0, feed?.series?.get(0)?.seasons?.get(i)?.episodes)
        }


        val windowAdapter = ArrayObjectAdapter(ListRowPresenter())
        for (i in 0 until category.size) {
            windowAdapter.add(0, ListRow(category[i], rows1Adapter))

        }
        adapter = windowAdapter


    }


    private fun setUI() {

        title = feed?.providerName

        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireActivity(), R.color.background_gradient_end)
        searchAffordanceColor = ContextCompat.getColor(requireActivity(), R.color.search_opaque)


    }

}