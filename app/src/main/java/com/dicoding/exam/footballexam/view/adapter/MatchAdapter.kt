package com.dicoding.exam.footballexam.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.view.adapter.MatchAdapter.Companion.mClickListener

import kotlinx.android.synthetic.main.item_match.view.*
import java.text.SimpleDateFormat

class MatchAdapter(private val context: Context, private val events: List<Match>, val btnlistener: BtnClickListener) : RecyclerView.Adapter<EventViewHolder>() {
    interface BtnClickListener {
        fun onBtnClick(event: Match)
    }

    companion object {
        var mClickListener: BtnClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        mClickListener = btnlistener
        holder.bindItem(events[position])
    }
}


class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    fun bindItem(event: Match) {
        itemView.item_match_date.text = convertDate(event.dateEvent!!)
        itemView.item_home_team.text = event.homeTeam
        itemView.item_away_team.text = event.awayTeam
        if (event.homeScore != null) {
            itemView.item_score.text = event.homeScore + "  vs  " + event.awayScore
        } else {
            itemView.item_score.text = "vs"
        }
        itemView.item_layout_main.setOnClickListener {
            if (mClickListener != null) {
                mClickListener?.onBtnClick(event)
            }
        }
    }


    fun convertDate(dateString: String): String {
        var format = SimpleDateFormat("yyyy-MM-dd")
        var format2 = SimpleDateFormat("EEE, dd MMM yyyy")
        var date = format.parse(dateString)
        var convertedDate = format2.format(date)
        return convertedDate
    }
}