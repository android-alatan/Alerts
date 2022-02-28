package io.androidalatan.dialog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.androidalatan.alertdialog.R

class SimpleTextItemAdapter(
    private val items: List<String>,
    private val itemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<SimpleTextItemAdapter.TextViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_common_dialog_content_list, parent, false)
        val textViewHolder = TextViewHolder(itemView)
        itemView.setOnClickListener {
            itemClickListener.invoke(textViewHolder.adapterPosition)
        }

        return textViewHolder
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView by lazy { itemView.findViewById<TextView>(R.id.tv_item_dialog_content_list) }
        fun bind(text: String) {
            textView.text = text
        }
    }
}