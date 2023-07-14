package com.marinato.convidados.adapatadores.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.marinato.convidados.adapatadores.listener.OnGuestListener
import com.marinato.convidados.databinding.RowGuestsBinding
import com.marinato.convidados.model.GuestModel

class GuestsViewHolder(private val bind: RowGuestsBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {
    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener {
            listener.onClick(guest.id)

        }

    }

}