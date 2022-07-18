package com.example.socialmediatestpart1.view.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.socialmediatestpart1.R
import com.example.socialmediatestpart1.model.data.SimilarModel

class SimilarAdapter(
) : RecyclerView.Adapter<SimilarAdapter.RecyclerItemCarouselViewHolder>() {

    private var data: List<SimilarModel> = arrayListOf()

    fun setData(data: List<SimilarModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimilarAdapter.RecyclerItemCarouselViewHolder {
        return RecyclerItemCarouselViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.similar_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(
        holder: SimilarAdapter.RecyclerItemCarouselViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemCarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: SimilarModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<ImageView>(R.id.image).load(data.image)
            }
        }
    }
}