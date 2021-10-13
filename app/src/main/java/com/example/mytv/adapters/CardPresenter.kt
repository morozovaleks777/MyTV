package com.example.mytv.adapters

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.mytv.fragments.EpisodesFragment
import com.example.mytv.Feed
import com.example.mytv.MainActivity
import com.example.mytv.R
import com.example.mytv.fragments.Detail


class CardPresenter : Presenter() {
    private var mDefaultCardImage: Drawable? = null

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val icv = object : ImageCardView(parent?.context) {
            override fun setSelected(selected: Boolean) {

                updateCardBackgroundColor(this)
                super.setSelected(selected)

            }

        }
        icv.setOnClickListener {
            View.OnClickListener {
                it.setBackgroundColor(Color.RED)
                icv.mainImage = mDefaultCardImage
            }
            (icv.context as MainActivity).supportFragmentManager.beginTransaction()
                // .setReorderingAllowed(false)
                .replace(R.id.container, Detail())
                .addToBackStack(null)
                .commit()
        }



        mDefaultCardImage = ContextCompat.getDrawable(parent?.context!!, R.drawable.movie)
        icv.isFocusableInTouchMode = true
        //updateCardBackgroundColor(cardView, false)


        return ViewHolder(icv)
    }

    private fun updateCardBackgroundColor(cardView: ImageCardView) {
        cardView.setBackgroundColor(Color.GREEN)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val srv = item as Feed.Movy
        val icv = viewHolder?.view as ImageCardView
        Glide.with(viewHolder.view.context)
            .load(srv.thumbnail)
            .centerCrop()
            .error(mDefaultCardImage)
            .into(icv.mainImageView)

        icv.titleText = srv.title
        icv.contentText = "movie description"
        icv.setMainImageDimensions(626, 352)
        icv.infoVisibility = BaseCardView.CARD_REGION_VISIBLE_ACTIVATED
        icv.cardType = (BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA)

        icv.isClickable = true


    }


    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {

    }


}