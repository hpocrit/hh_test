package ru.kpfu.itis.gabdullina.hh.list.ui.screen.main

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
import ru.kpfu.itis.gabdullina.hh.list.R
import ru.kpfu.itis.gabdullina.hh.list.databinding.FragmentMainBinding
import ru.kpfu.itis.gabdullina.hh.list.model.GeneralModel
import ru.kpfu.itis.gabdullina.hh.list.ui.adapter.OfferAdapter
import ru.kpfu.itis.gabdullina.hh.list.ui.adapter.VacancyAdapter
import ru.kpfu.itis.gabdullina.hh.list.ui.mapper.toVacancyModel
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.MainComponentViewModel
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.all.AllFragment
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by viewModels { factory }
    private val componentViewModel: MainComponentViewModel by viewModels()
    private var adapter: VacancyAdapter? = null
    private var offerAdapter: OfferAdapter? = null

    private var binding: FragmentMainBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        mainViewModel.getVacancies()

        binding?.run {
            adapter = VacancyAdapter(::onClick)
            vacanciesRv.layoutManager = LinearLayoutManager(context)
            vacanciesRv.adapter = adapter

            offerAdapter = OfferAdapter()
            offerRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            offerRv.adapter = offerAdapter
        }
        observerVacancies()
        observerOffers()
    }

    private fun observerVacancies() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                mainViewModel.vacancyList.onEach { vacancies ->
                    binding?.let {
                        val list: MutableList<GeneralModel> = mutableListOf()
                        if(vacancies == null || vacancies.size <= 3) {
                            adapter?.submitList(list)
                            return@onEach
                        }
                        for (i in 0..2) {
                            list.add(vacancies[i].toVacancyModel())
                        }
                        list.add(GeneralModel.ButtonModel(vacancies.size - 3))
                        adapter?.submitList(list)
                    }
                }.collect()
            }
        }
    }

    fun onClick() {
        parentFragmentManager
            .beginTransaction()
            .replace(com.google.android.material.R.id.container, AllFragment())
            .commit()
    }

    private fun observerOffers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                mainViewModel.offerList.onEach { offers ->
                    binding?.let {
                        offerAdapter?.submitList(offers)
                    }
                }.collect()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        binding = null
    }
}

