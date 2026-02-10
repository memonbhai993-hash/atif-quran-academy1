package com.quranacademy.atifquranapp.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quranacademy.atifquranapp.databinding.FragmentRegistrationBinding
import com.quranacademy.atifquranapp.util.Constants
import com.quranacademy.atifquranapp.util.openUrl

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnSubmit.setOnClickListener {
            submitRegistration()
        }

        binding.txtGoogleForm.setOnClickListener {
            openGoogleForm()
        }
    }

    private fun submitRegistration() {
        // TODO: Validate and submit registration
    }

    private fun openGoogleForm() {
        requireContext().openUrl(Constants.STUDENT_REGISTRATION_FORM_URL)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
