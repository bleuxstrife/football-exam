package com.dicoding.exam.footballexam.view.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.dicoding.exam.footballexam.R
import java.util.ArrayList

class MainPagerAdapter internal constructor(fm: FragmentManager, mFragments: ArrayList<Fragment>, private val mTitles: Array<String>, internal var c: Context) : FragmentPagerAdapter(fm) {
    private var mFragments = ArrayList<Fragment>()

    init {
        this.mFragments = mFragments
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    fun getTabView(position: Int): View {
        val v = LayoutInflater.from(c).inflate(R.layout.tab_custom, null)
        val title = v.findViewById(R.id.tab_name) as TextView
        title.setText(mTitles[position])
        return v
    }
}