package com.quranacademy.atifquranapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.quranacademy.atifquranapp.R
import com.quranacademy.atifquranapp.databinding.FragmentHomeBinding
import com.quranacademy.atifquranapp.ui.components.FeatureAdapter

/**
 * Home Fragment - Main landing screen of the app
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var featureAdapter: FeatureAdapter
    private lateinit var courseAdapter: HomeCourseAdapter
    private lateinit var teacherAdapter: HomeTeacherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupRecyclerViews()
        setupClickListeners()
        loadData()
    }

    private fun setupViews() {
        // Setup features RecyclerView
        featureAdapter = FeatureAdapter()
        binding.rvFeatures.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = featureAdapter
        }

        // Setup courses RecyclerView
        courseAdapter = HomeCourseAdapter { course ->
            navigateToCourseDetail(course.id)
        }
        binding.rvFeaturedCourses.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = courseAdapter
        }

        // Setup teachers RecyclerView
        teacherAdapter = HomeTeacherAdapter { teacher ->
            navigateToTeacherDetail(teacher.id)
        }
        binding.rvTopTeachers.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = teacherAdapter
        }
    }

    private fun setupRecyclerViews() {
        // Load features
        featureAdapter.submitList(getFeaturesList())
    }

    private fun setupClickListeners() {
        binding.btnEnrollNow.setOnClickListener {
            navigateToRegistration()
        }

        binding.btnViewCourses.setOnClickListener {
            findNavController().navigate(R.id.navigation_courses)
        }

        binding.txtViewAllCourses.setOnClickListener {
            findNavController().navigate(R.id.navigation_courses)
        }

        binding.txtViewAllTeachers.setOnClickListener {
            findNavController().navigate(R.id.navigation_teachers)
        }
    }

    private fun loadData() {
        // Show loading state
        binding.progressCourses.visibility = View.VISIBLE
        binding.progressTeachers.visibility = View.VISIBLE

        // TODO: Load data from repository
        // For now, showing empty state
        binding.progressCourses.visibility = View.GONE
        binding.progressTeachers.visibility = View.GONE
    }

    private fun getFeaturesList(): List<FeatureItem> {
        return listOf(
            FeatureItem(R.drawable.ic_verified, getString(R.string.home_feature_verified)),
            FeatureItem(R.drawable.ic_class, getString(R.string.home_feature_online)),
            FeatureItem(R.drawable.ic_courses, getString(R.string.home_feature_flexible)),
            FeatureItem(R.drawable.ic_quran, getString(R.string.home_feature_affordable))
        )
    }

    private fun navigateToRegistration() {
        findNavController().navigate(R.id.registrationFragment)
    }

    private fun navigateToCourseDetail(courseId: String) {
        val bundle = Bundle().apply {
            putString("courseId", courseId)
        }
        findNavController().navigate(R.id.courseDetailFragment, bundle)
    }

    private fun navigateToTeacherDetail(teacherId: String) {
        val bundle = Bundle().apply {
            putString("teacherId", teacherId)
        }
        findNavController().navigate(R.id.teacherDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/**
 * Data class for feature item
 */
data class FeatureItem(
    val iconResId: Int,
    val title: String
)
