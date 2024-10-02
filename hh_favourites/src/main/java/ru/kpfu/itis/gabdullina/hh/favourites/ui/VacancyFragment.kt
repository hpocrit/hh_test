package ru.kpfu.itis.gabdullina.hh.favourites.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.kpfu.itis.gabdullina.hh.favourites.R
import ru.kpfu.itis.gabdullina.hh.favourites.databinding.FragmentFavouritesBinding
import ru.kpfu.itis.gabdullina.hh.favourites.mapper.toCntOfVacancies
import ru.kpfu.itis.gabdullina.hh.favourites.ui.adapter.FavouritesAdapter
import javax.inject.Inject

class VacancyFragment : Fragment(R.layout.fragment_favourites) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val favouritesViewModel: FavouritesViewModel by viewModels { factory }
    private val componentViewModel: FavouritesComponentViewModel by viewModels()
    private var adapter: FavouritesAdapter? = null

    private var binding: FragmentFavouritesBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.favouritesComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouritesBinding.bind(view)

        favouritesViewModel.getVacancies()

        binding?.run{
            adapter = FavouritesAdapter()
            vacanciesRv.layoutManager = LinearLayoutManager(context)
            vacanciesRv.adapter = adapter
        }
        observerData()
    }

    private fun observerData() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                favouritesViewModel.vacancyList.onEach { vacancies ->
                    binding?.let {
                        adapter?.submitList(vacancies)
                        if (vacancies != null) {
                            it.cntOfVacancyTv.text = vacancies.size.toCntOfVacancies()
                        } else {
                            it.cntOfVacancyTv.text = 0.toCntOfVacancies()
                        }
                    }
                }.collect()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}
