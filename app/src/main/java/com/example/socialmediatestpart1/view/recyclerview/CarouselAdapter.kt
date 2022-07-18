package com.example.socialmediatestpart1.view.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.socialmediatestpart1.R
import com.example.socialmediatestpart1.model.data.CarouselModel


class CarouselAdapter(
) : RecyclerView.Adapter<CarouselAdapter.RecyclerItemCarouselViewHolder>() {

    private var data: List<CarouselModel> = arrayListOf()

    fun setData(data: List<CarouselModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselAdapter.RecyclerItemCarouselViewHolder {
        return RecyclerItemCarouselViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.carousel_item, parent, false) as View
        )
    }

    override fun onBindViewHolder(
        holder: CarouselAdapter.RecyclerItemCarouselViewHolder,
        position: Int
    ) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemCarouselViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: CarouselModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<ImageView>(R.id.image).load(data.image)
            }
        }
    }
}