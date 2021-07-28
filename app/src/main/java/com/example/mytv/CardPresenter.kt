package com.example.mytv

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide


class CardPresenter: Presenter(){
    private var mDefaultCardImage: Drawable? = null


    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val icv = object : ImageCardView(parent?.context) {
            override fun setSelected(selected: Boolean) {
                //updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }
        mDefaultCardImage = ContextCompat.getDrawable(parent?.context!!, R.drawable.movie)
        icv.isFocusable = true
        icv.isFocusableInTouchMode = true
        //updateCardBackgroundColor(cardView, false)
        return Presenter.ViewHolder(icv)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
      val srv= item as Feed.Movy
        val icv= viewHolder?.view as ImageCardView
        Glide.with(viewHolder.view.context)
            .load(srv.thumbnail)
            .centerCrop()
            .error(mDefaultCardImage)
            .into(icv.mainImageView)

        icv.titleText=srv?.title
        icv.contentText="movie description"
        icv.setMainImageDimensions(313,176)
        icv.infoVisibility= BaseCardView.CARD_REGION_VISIBLE_ACTIVATED
        icv.cardType=(BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA)

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        TODO("Not yet implemented")
    }

}
