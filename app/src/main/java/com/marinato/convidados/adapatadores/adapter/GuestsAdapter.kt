package com.marinato.convidados.adapatadores.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marinato.convidados.adapatadores.viewHolder.GuestsViewHolder
import com.marinato.convidados.databinding.RowGuestsBinding
import com.marinato.convidados.model.GuestModel

class GuestsAdapter : RecyclerView.Adapter<GuestsViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val item = RowGuestsBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return GuestsViewHolder(item)
    }

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updatedGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()

    }
}