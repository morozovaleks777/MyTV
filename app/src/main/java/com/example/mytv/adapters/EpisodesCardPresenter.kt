package com.example.mytv.adapters

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.mytv.Feed
import com.example.mytv.MainActivity
import com.example.mytv.R
import com.example.mytv.fragments.Detail

class EpisodesCardPresenter : Presenter() {


    private var mDefaultCardImage: Drawable? = null


    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val icv = object : ImageCardView(parent?.context) {

        }
        mDefaultCardImage = ContextCompat.getDrawable(parent?.context!!, R.drawable.movie)
        icv.isFocusable = true
        icv.isFocusableInTouchMode = true

        icv.setOnClickListener {
            View.OnClickListener {
                it.setBackgroundColor(Color.RED)
                icv.mainImage = mDefaultCardImage
            }


            (icv.context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.container, Detail())
                .addToBackStack(null)
                .commit()
        }

        return ViewHolder(icv)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val srv = item as Feed.Sery.Season.Episode

        val icv = viewHolder?.view as ImageCardView
        Glide.with(viewHolder.view.context)
            .load(srv.thumbnail)
            .centerCrop()
            .error(mDefaultCardImage)
            .into(icv.mainImageView)
         episodeNumber=srv.episodeNumber-1

        icv.titleText = "${episodeNumber} ${srv.title} "
        icv.contentText = srv.shortDescription
        icv.setMainImageDimensions(626, 352)
        icv.infoVisibility = BaseCardView.CARD_REGION_VISIBLE_ACTIVATED
        icv.cardType = (BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA)


    }
companion object{
    var episodeNumber=0
}
    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }

}