package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.FragmentLoginKabarBinding

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginKabarBinding
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginKabarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_login_to_fragment_register)
        }

        binding.login.setOnClickListener {
            val edtUsername = binding.edtUsername.text.toString().trim()
            val edtPassword = binding.edtPassword.text.toString().trim()
            loginViewModel.login(edtUsername, edtPassword)
        }

        initObserve()

    }

    fun initObserve() {
        lifecycleScope.launch {
            loginViewModel.uiState.collect { loginState ->
                loginState.loginStatus?.let { status ->
                    if (status) {
                        Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_fragment_login_to_fragment_select_country)
                    } else
                        Toast.makeText(requireContext(), "Login failed: ${loginState.errorMessage}", Toast.LENGTH_LONG).show()
                    }

            }
        }
    }
}