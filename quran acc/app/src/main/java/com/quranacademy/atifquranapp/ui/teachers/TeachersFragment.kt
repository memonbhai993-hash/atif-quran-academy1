package com.quranacademy.atifquranapp.ui.teachers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.quranacademy.atifquranapp.databinding.FragmentTeachersBinding
import com.quranacademy.atifquranapp.ui.home.HomeTeacherAdapter

/**
 * Teachers Fragment - Displays all verified teachers
 */
class TeachersFragment : Fragment() {

    private var _binding: FragmentTeachersBinding? = null
    private val binding get() = _binding!!

    private lateinit var teacherAdapter: HomeTeacherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeachersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadTeachers()
    }

    private fun setupRecyclerView() {
        teacherAdapter = HomeTeacherAdapter { teacher ->
            val bundle = Bundle().apply {
                putString("teacherId", teacher.id)
            }
            findNavController().navigate(
                com.quranacademy.atifquranapp.R.id.teacherDetailFragment,
                bundle
            )
        }
        binding.rvTeachers.adapter = teacherAdapter
    }

    private fun loadTeachers() {
        // TODO: Load teachers from repository
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
