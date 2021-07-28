package com.example.mytv

import android.view.ViewGroup
import androidx.leanback.widget.BaseCardView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter

class CardPresenter: Presenter(){
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val icv = object : ImageCardView(parent?.context) {
            override fun setSelected(selected: Boolean) {
                //updateCardBackgroundColor(this, selected)
                super.setSelected(selected)
            }
        }

        icv.isFocusable = true
        icv.isFocusableInTouchMode = true
        //updateCardBackgroundColor(cardView, false)
        return Presenter.ViewHolder(icv)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
      val srv= item as SingleRowView?
        val icv= viewHolder?.view as ImageCardView
        icv.mainImage=srv?.image
        icv.titleText=srv?.name
        icv.contentText="movie description"
        icv.setMainImageDimensions(313,176)
        icv.infoVisibility= BaseCardView.CARD_REGION_VISIBLE_ACTIVATED
        icv.cardType=(BaseCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA)

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        TODO("Not yet implemented")
    }

}
