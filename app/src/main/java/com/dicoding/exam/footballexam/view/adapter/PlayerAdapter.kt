package com.dicoding.exam.footballexam.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dicoding.exam.footballexam.R
import com.dicoding.exam.footballexam.model.Player
import com.dicoding.exam.footballexam.model.Team
import com.dicoding.exam.footballexam.view.adapter.PlayerAdapter.Companion.mClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*
import kotlinx.android.synthetic.main.item_team.view.*

class PlayerAdapter(private val context: Context, private val player: List<Player>, val btnlistener: BtnClickListener) : RecyclerView.Adapter<PlayerViewHolder>() {
    interface BtnClickListener {
        fun onBtnClick(player: Player)
    }

    companion object {
        var mClickListener: BtnClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_player, parent, false))

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        mClickListener = btnlistener
        holder.bindItem(player[position], context)
    }
}


class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    fun bindItem(player: Player, context: Context) {
        Picasso.with(context).load(player.playerIcon).into(itemView.player_icon)
        itemView.player_name.text = player.playerName
        itemView.player_position.text = player.playerPosition
        itemView.item_layout_player.setOnClickListener {
            if (mClickListener != null) {
                mClickListener?.onBtnClick(player)
            }
        }
    }

}