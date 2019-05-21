package com.dicoding.exam.footballexam.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.model.Match
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.view.adapter.TeamAdapter.Companion.mClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(private val context: Context, private val teams: List<Team>, val btnlistener: BtnClickListener) : RecyclerView.Adapter<TeamViewHolder>() {
    interface BtnClickListener {
        fun onBtnClick(teams: Team)
    }

    companion object {
        var mClickListener: BtnClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        mClickListener = btnlistener
        holder.bindItem(teams[position], context)
    }
}


class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    fun bindItem(teams: Team, context: Context) {
        Picasso.with(context).load(teams.teamBadge).into(itemView.team_icon)
        itemView.team_name.text = teams.teamName
        itemView.item_layout_team.setOnClickListener {
            if (mClickListener != null) {
                mClickListener?.onBtnClick(teams)
            }
        }
    }

}