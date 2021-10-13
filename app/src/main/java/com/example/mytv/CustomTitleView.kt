package com.example.mytv

import android.content.Context
import android.text.Layout
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.TitleViewAdapter
import com.example.mytv.R.layout


class CustomTitleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), TitleViewAdapter.Provider {

    private val titleViewAdapter = object : TitleViewAdapter() {
        override fun getSearchAffordanceView(): View? {
            return null
        }

        override fun setTitle(titleText: CharSequence?) {
            this@CustomTitleView.setTitle(titleText)
        }
    }


    init {
        View.inflate(context, layout.view_custom_title, this)
    }

    fun setTitle(titleText: CharSequence?) {
        findViewById<TextView>(R.id.vTitle).text = "LOGO"
        findViewById<TextView>(R.id.vTitle1).text = titleText

    }

    override fun getTitleViewAdapter(): TitleViewAdapter {
        return titleViewAdapter
    }
}