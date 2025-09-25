package com.example.lab_week_06_76847

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06_76847.model.CatModel

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<CatViewHolder>() {

    // Mutable list untuk menyimpan semua data kucing
    private val cats = mutableListOf<CatModel>()

    // Fungsi untuk mengganti data list
    fun setData(newCats: List<CatModel>) {
        cats.clear()
        cats.addAll(newCats)
        notifyDataSetChanged() // kasih tau adapter kalau ada perubahan data
    }

    // Membuat ViewHolder baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return CatViewHolder(view, imageLoader, onClickListener)
    }

    // Jumlah item dalam list
    override fun getItemCount() = cats.size

    // Bind data ke ViewHolder
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(cats[position])
    }

    // Listener dari Adapter
    interface OnClickListener {
        fun onItemClick(cat: CatModel)
    }

    // Class untuk swipe delete
    inner class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeItem(position)
        }
    }

    fun removeItem(position: Int) {
        cats.removeAt(position)
        notifyItemRemoved(position)
    }
}
