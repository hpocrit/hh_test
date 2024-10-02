package ru.kpfu.itis.gabdullina.hh.list.ui.screen.all

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
import ru.kpfu.itis.gabdullina.hh.list.databinding.FragmentAllBinding
import ru.kpfu.itis.gabdullina.hh.list.ui.adapter.VacancyAdapter
import ru.kpfu.itis.gabdullina.hh.list.ui.mapper.toVacancyModel
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.MainComponentViewModel
import ru.kpfu.itis.gabdullina.hh.list.ui.screen.main.MainFragment
import javax.inject.Inject

class AllFragment: Fragment(R.layout.fragment_all) {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: AllViewModel by viewModels { factory }
    private val componentViewModel: MainComponentViewModel by viewModels()
    private var adapter: VacancyAdapter? = null

    private var binding: FragmentAllBinding? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.mainComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllBinding.bind(view)

        viewModel.getVacancies()

        binding?.run {
            adapter = VacancyAdapter(::onClick)
            vacanciesRv.layoutManager = LinearLayoutManager(context)
            vacanciesRv.adapter = adapter

            searchTv.setOnClickListener {
                onClick()
            }
        }
        observerVacancies()
    }

    private fun observerVacancies() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                viewModel.vacancyList.onEach { vacancies ->
                    binding?.let {
                        adapter?.submitList(vacancies?.map { it.toVacancyModel() })
                    }
                }.collect()
            }
        }
    }

    fun onClick() {
        parentFragmentManager
            .beginTransaction()
            .replace(com.google.android.material.R.id.container, MainFragment())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
        binding = null
    }
}
