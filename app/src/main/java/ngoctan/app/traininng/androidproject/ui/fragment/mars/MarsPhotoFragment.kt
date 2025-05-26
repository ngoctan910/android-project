package ngoctan.app.traininng.androidproject.ui.fragment.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ngoctan.traininng.androidproject.databinding.FragmentMarsPhotoBinding

@AndroidEntryPoint
class MarsPhotoFragment: Fragment() {
    private lateinit var binding: FragmentMarsPhotoBinding
    private val marsViewModel by viewModels<MarsPhotoViewModel>()
    private lateinit var marsPhotoAdapter: MarsPhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarsPhotoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPhotos.apply {
            marsPhotoAdapter = MarsPhotoAdapter()
            adapter = marsPhotoAdapter
        }

        marsViewModel.fetchPhoto()

        lifecycleScope.launch {
            marsViewModel.uiState.collect { photoState ->
                marsPhotoAdapter.setData(photoState.photoList ?: listOf())
            }
        }

    }
}