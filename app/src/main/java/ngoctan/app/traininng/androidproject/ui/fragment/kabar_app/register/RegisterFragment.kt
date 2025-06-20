package ngoctan.app.traininng.androidproject.ui.fragment.kabar_app.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ngoctan.traininng.androidproject.R
import ngoctan.traininng.androidproject.databinding.FragmentRegisterKabarBinding

@AndroidEntryPoint
class RegisterFragment: Fragment() {
    private lateinit var binding: FragmentRegisterKabarBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterKabarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = Firebase.auth
        binding.register.setOnClickListener {
            val edtUsername = binding.edtUsername.text.toString().trim()
            val edtPassword = binding.edtPassword.text.toString().trim()
            registerViewModel.register(edtUsername, edtPassword)
        }

        /*binding.login.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_register_to_fragment_login)
        }*/

        initObserve()

    }

    fun initObserve() {
        lifecycleScope.launch {
            registerViewModel.uiState.collect { registerState ->
                registerState.registerStatus?.let { status ->
                    if (status)
                        Toast.makeText(requireContext(), "Registered Successfully!", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(requireContext(), "Registration Failed: ${registerState.errorMessage}", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}