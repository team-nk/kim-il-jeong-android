package com.gram.kimiljeong.presentation.view.introduction

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gram.kimiljeong.R

class IntroductionAdapter(
    private val mContext: Context,
) : PagerAdapter() {

    private val layoutInflater by lazy {
        mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    private val images = intArrayOf(
        R.drawable.img_introduction_1,
        R.drawable.img_introduction_2,
        R.drawable.img_introduction_3,
        R.drawable.img_introduction_4,
    )

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    @SuppressLint("MissingInflatedId", "InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.item_introduction, null)
        val image = view.findViewById<ImageView>(R.id.image_item_introduction_pager_item)
        val viewpager = container as ViewPager

        image.setImageResource(this.images[position])
        viewpager.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewpager = container as ViewPager
        val view = `object` as View
        viewpager.removeView(view)
    }
}
