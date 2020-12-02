package id.kharozim.phonebookmvvm.views

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kharozim.phonebookmvvm.databinding.ItemListContactBinding
import id.kharozim.phonebookmvvm.repository.remote.responses.ContactResponse

class ContactAdapter (private val context: Context, private val listener: ListenerContact) :
RecyclerView.Adapter<ContactAdapter.Viewholder>() {

    var listContact = mutableListOf<ContactResponse>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    fun deleteContactById(id: Long) {
        val index = listContact.indexOfFirst { it.id == id }
        if (index != -1){
            listContact = listContact.filter { it.id != id }.toMutableList()
            notifyItemRemoved(index)
        }
    }

    inner class Viewholder(
        private val binding: ItemListContactBinding,
        private val listener: ListenerContact
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun binData(contactResponse: ContactResponse) {
            binding.run {
                tvName.text = contactResponse.name
                tvPhone.text = contactResponse.phone
                btDel.setOnClickListener { listener.onDelete(contactResponse.id) }
            }
        }
    }

    interface ListenerContact {
        fun onDelete(id: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            ItemListContactBinding.inflate(LayoutInflater.from(context), parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binData(listContact[position])
    }

    override fun getItemCount(): Int = listContact.size
}