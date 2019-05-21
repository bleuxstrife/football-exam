package com.dicoding.exam.footballexam.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.view.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.fragment_matches_main.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class MatchesFragment: Fragment(){
    private lateinit var mainPagerAdapter: MainPagerAdapter
    private var mFragments: ArrayList<Fragment> = ArrayList()
    private val mTitles = arrayOf("Prev", "Next")
    private lateinit var c: Context
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        c = activity as AppCompatActivity
        initFragments()
        initViewPager()
        initTabLayout()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view  =  inflater.inflate(R.layout.fragment_matches_main, container, false)
        return view
    }

    private fun initFragments() {
        mFragments = ArrayList()
        for (title in mTitles) {
            mFragments.add(MatchesSubFragment().getInstance(title))
        }
    }

    private fun initViewPager() {
        mainPagerAdapter = MainPagerAdapter(childFragmentManager, mFragments, mTitles, c)
        viewpager.adapter = mainPagerAdapter
        viewpager.offscreenPageLimit = mTitles.size
    }

    private fun initTabLayout() {
        tabs.setupWithViewPager(viewpager)
        for (i in mTitles.indices) {
            val tab = tabs.getTabAt(i)
            tab!!.setCustomView(mainPagerAdapter.getTabView(i))
        }
    }
}
