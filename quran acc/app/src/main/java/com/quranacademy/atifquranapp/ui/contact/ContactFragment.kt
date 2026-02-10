package com.quranacademy.atifquranapp.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quranacademy.atifquranapp.databinding.FragmentContactBinding
import com.quranacademy.atifquranapp.util.Constants
import com.quranacademy.atifquranapp.util.openDialer
import com.quranacademy.atifquranapp.util.openEmail
import com.quranacademy.atifquranapp.util.openWhatsApp

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.cardWhatsapp.setOnClickListener {
            requireContext().openWhatsApp(Constants.CONTACT_WHATSAPP)
        }

        binding.cardCall.setOnClickListener {
            requireContext().openDialer(Constants.CONTACT_PHONE)
        }

        binding.cardEmail.setOnClickListener {
            requireContext().openEmail(Constants.CONTACT_EMAIL)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
