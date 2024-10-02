package ru.kpfu.itis.gabdullina.hh.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.gabdullina.hh.api.model.Offer
import ru.kpfu.itis.gabdullina.hh.list.R
import ru.kpfu.itis.gabdullina.hh.list.databinding.ItemOfferBinding

class OfferAdapter(): ListAdapter<Offer, OfferAdapter.ViewHolder>(OfferItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ItemOfferBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: Offer) {

            binding.title.text = offer.title

            val button = offer.button
            if(button != null) {
                binding.button.text = button.text
            } else {
                binding.button.isVisible = false
            }

            val id = offer.id?: ""
            if(id == "near_vacancies") {

            } else if (id == "level_up_resume") {
                binding.icon.setImageResource(R.drawable.star_icon)
                binding.icon.setBackgroundResource(R.drawable.black_green_back)
            } else if (id == "temporary_job") {
                binding.icon.setImageResource(R.drawable.temp_icon)
                binding.icon.setBackgroundResource(R.drawable.black_green_back)
            } else {
                binding.icon.isVisible = false
            }

        }
    }
}

private class OfferItemCallBack: DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

}
