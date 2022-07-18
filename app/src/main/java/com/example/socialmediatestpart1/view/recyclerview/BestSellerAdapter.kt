package com.example.socialmediatestpart1.view.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.socialmediatestpart1.R
import com.example.socialmediatestpart1.model.data.BestSellerModel

class BestSellerAdapter(
    private val onListItemClickListener: OnListItemClickListener
) : RecyclerView.Adapter<BestSellerAdapter.RecyclerItemViewHolder>() {

    private var data: List<BestSellerModel> = arrayListOf()

    fun setData(data: List<BestSellerModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.best_seller_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: BestSellerModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                with(itemView) {
                    findViewById<TextView>(R.id.title).text = data.title
                    findViewById<TextView>(R.id.author).text = data.author
                    findViewById<TextView>(R.id.price).text = "${data.price}€"
                    findViewById<TextView>(R.id.score).text = data.rate.score.toString()
                    findViewById<TextView>(R.id.amount).text = "(${data.rate.amount})"

                    findViewById<ImageView>(R.id.image).load(data.image)

                    setOnClickListener { onListItemClickListener.onItemClick(data) }
                }
            }
        }
    }

    interface OnListItemClickListener {
        fun onItemClick(data: BestSellerModel)
    }
}