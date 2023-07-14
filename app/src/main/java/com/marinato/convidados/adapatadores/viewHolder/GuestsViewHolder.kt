package com.marinato.convidados.adapatadores.viewHolder

import android.content.DialogInterface
import android.icu.text.LocaleDisplayNames.DialectHandling
import android.view.View
import androidx.appcompat.app.AlertDialog
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

        bind.textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado")
                .setMessage("Tem certeza que deseja remover o convidado")
                .setPositiveButton("sim") { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não", null)
                .create()
                .show()
            true
        }
    }
}