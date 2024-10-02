package ru.kpfu.itis.gabdullina.hh.favourites.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.gabdullina.hh.api.model.Vacancy
import ru.kpfu.itis.gabdullina.hh.favourites.databinding.ItemVacancyBinding
import ru.kpfu.itis.gabdullina.hh.favourites.mapper.toItemModel

class FavouritesAdapter(): ListAdapter<Vacancy, FavouritesAdapter.ViewHolder>(VacancyItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemVacancyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ItemVacancyBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(vacancy: Vacancy) {
                val item = vacancy.toItemModel()

                if(item.lookingNumber == "") {
                    binding.lookingNumber.isVisible = false
                } else {
                    binding.lookingNumber.text = item.lookingNumber
                }

                if(item.name == "") {
                    binding.name.isVisible = false
                } else {
                    binding.name.text = item.name
                }

                if(item.city == "") {
                    binding.city.isVisible = false
                } else {
                    binding.city.text = item.city
                }

                if(item.company == "") {
                    binding.company.isVisible = false
                } else {
                    binding.company.text = item.company
                }

                if(item.experience == "") {
                    binding.experience.isVisible = false
                } else {
                    binding.experience.text = item.experience
                }

                if(item.date == "") {
                    binding.date.isVisible = false
                } else {
                    binding.date.text = item.date
                }
            }
    }
}

private class VacancyItemCallBack: DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

}
