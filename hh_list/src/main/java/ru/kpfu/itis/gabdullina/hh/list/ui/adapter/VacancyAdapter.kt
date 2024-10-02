package ru.kpfu.itis.gabdullina.hh.list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.gabdullina.hh.list.R
import ru.kpfu.itis.gabdullina.hh.list.databinding.ItemButtonBinding
import ru.kpfu.itis.gabdullina.hh.list.databinding.ItemVacancyMainBinding
import ru.kpfu.itis.gabdullina.hh.list.model.GeneralModel
import ru.kpfu.itis.gabdullina.hh.list.ui.mapper.toItemModel

class VacancyAdapter(
    private val onClick: (() -> Unit)
): ListAdapter<GeneralModel, RecyclerView.ViewHolder>(VacancyItemCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TYPE_VACANCY) {
            return ViewHolder(
                ItemVacancyMainBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return ButtonViewHolder(
                ItemButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> holder.bind(getItem(position) as GeneralModel.VacancyModel)
            is ButtonViewHolder -> holder.bind(getItem(position) as GeneralModel.ButtonModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is GeneralModel.VacancyModel -> VIEW_TYPE_VACANCY
            is GeneralModel.ButtonModel -> VIEW_TYPE_BUTTON
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }


    inner class ViewHolder(private val binding: ItemVacancyMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vacancy: GeneralModel.VacancyModel) {
            val item = vacancy

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

            if(vacancy.isFavorite == true) {
                binding.favoriteIv.setImageResource(R.drawable.heart_pressed)
            } else {
                binding.favoriteIv.setImageResource(R.drawable.heart_default)
            }

            binding.favoriteIv.setOnClickListener {
                if(binding.favoriteIv.imageAlpha == R.drawable.heart_pressed) {
                    binding.favoriteIv.setImageResource(R.drawable.heart_default)
                } else {
                    binding.favoriteIv.setImageResource(R.drawable.heart_pressed)
                }
            }
        }
    }

    inner class ButtonViewHolder(private val binding: ItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vacancy: GeneralModel.ButtonModel) {
            val cnt = vacancy.cnt
            if (cnt % 10 == 1) {
                binding.text.text = "Ёще $cnt вакансия"
            } else if (cnt % 10 == 2 || cnt % 10 == 3 || cnt % 10 == 4) {
                binding.text.text = "Ёще $cnt вакансии"
            } else {
                binding.text.text = "Ёще $cnt вакансий"
            }
        }
    }

    companion object {
        private const val VIEW_TYPE_VACANCY = 0
        private const val VIEW_TYPE_BUTTON = 1
    }
}

private class VacancyItemCallBack: DiffUtil.ItemCallback<GeneralModel>() {
    override fun areItemsTheSame(oldItem: GeneralModel, newItem: GeneralModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: GeneralModel, newItem: GeneralModel): Boolean {
        return oldItem == newItem
    }

}
