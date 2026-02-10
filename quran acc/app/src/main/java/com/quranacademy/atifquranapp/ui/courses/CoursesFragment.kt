package com.quranacademy.atifquranapp.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.quranacademy.atifquranapp.data.model.Course
import com.quranacademy.atifquranapp.databinding.FragmentCoursesBinding
import com.quranacademy.atifquranapp.ui.home.HomeCourseAdapter

/**
 * Courses Fragment - Displays all available courses
 */
class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    private lateinit var courseAdapter: HomeCourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSwipeRefresh()
        loadCourses()
    }

    private fun setupRecyclerView() {
        courseAdapter = HomeCourseAdapter { course ->
            navigateToCourseDetail(course.id)
        }
        binding.rvCourses.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = courseAdapter
        }
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            loadCourses()
        }
    }

    private fun loadCourses() {
        binding.progressBar.visibility = View.VISIBLE
        binding.txtEmpty.visibility = View.GONE

        // TODO: Load courses from repository
        // For now, show empty state
        binding.progressBar.visibility = View.GONE
        binding.txtEmpty.visibility = View.VISIBLE
    }

    private fun navigateToCourseDetail(courseId: String) {
        val bundle = Bundle().apply {
            putString("courseId", courseId)
        }
        findNavController().navigate(com.quranacademy.atifquranapp.R.id.courseDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
