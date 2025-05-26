package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.select_country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.FragmentSelectCountryBinding

@AndroidEntryPoint
class SelectCountryFragment: Fragment() {
    private lateinit var binding: FragmentSelectCountryBinding
    private lateinit var selectCountryAdapter: SelectCountryAdapter
    private val selectCountryViewModel by viewModels<SelectCountryViewModel>()
    private val countryList = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectCountryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.apply {
            layoutManager = LinearLayoutManager(context)
            selectCountryAdapter = SelectCountryAdapter()
            adapter = selectCountryAdapter
        }

        selectCountryViewModel.fetchCountry()
        lifecycleScope.launch {
            selectCountryViewModel.uiState.collect { countryState ->
                selectCountryAdapter.submitList(countryState.countryList ?: listOf())
            }
        }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_select_country_to_fragment_home)
        }
    }



}