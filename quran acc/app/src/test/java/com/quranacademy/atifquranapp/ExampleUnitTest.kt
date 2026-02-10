package com.quranacademy.atifquranapp

import com.quranacademy.atifquranapp.data.model.Course
import com.quranacademy.atifquranapp.data.model.CourseLevel
import com.quranacademy.atifquranapp.data.model.Teacher
import com.quranacademy.atifquranapp.data.model.VerificationStatus
import org.junit.Assert.*
import org.junit.Test

/**
 * Unit tests for data models
 */
class ExampleUnitTest {

    @Test
    fun course_defaultValues_areCorrect() {
        val course = Course()

        assertEquals("", course.id)
        assertEquals("", course.name)
        assertEquals("", course.description)
        assertEquals(CourseLevel.BEGINNER, course.level)
        assertEquals(0.0, course.feeMonthly, 0.01)
        assertTrue(course.isActive)
        assertFalse(course.isFeatured)
    }

    @Test
    fun teacher_defaultValues_areCorrect() {
        val teacher = Teacher()

        assertEquals("", teacher.id)
        assertEquals("", teacher.name)
        assertEquals("", teacher.email)
        assertFalse(teacher.isVerified)
        assertEquals(VerificationStatus.PENDING, teacher.verificationStatus)
        assertEquals(0f, teacher.rating, 0.01f)
        assertEquals(0, teacher.experience)
    }

    @Test
    fun course_withCustomValues_areCorrect() {
        val course = Course(
            id = "course_001",
            name = "Noorani Qaida",
            description = "Learn basic Quranic Arabic",
            feeMonthly = 25.0,
            level = CourseLevel.BEGINNER,
            isFeatured = true
        )

        assertEquals("course_001", course.id)
        assertEquals("Noorani Qaida", course.name)
        assertEquals(25.0, course.feeMonthly, 0.01)
        assertEquals(CourseLevel.BEGINNER, course.level)
        assertTrue(course.isFeatured)
    }

    @Test
    fun teacher_verificationStatus_isCorrect() {
        val pendingTeacher = Teacher(verificationStatus = VerificationStatus.PENDING)
        val approvedTeacher = Teacher(verificationStatus = VerificationStatus.APPROVED)
        val rejectedTeacher = Teacher(verificationStatus = VerificationStatus.REJECTED)

        assertEquals(VerificationStatus.PENDING, pendingTeacher.verificationStatus)
        assertEquals(VerificationStatus.APPROVED, approvedTeacher.verificationStatus)
        assertEquals(VerificationStatus.REJECTED, rejectedTeacher.verificationStatus)

        assertFalse(pendingTeacher.isVerified)
        assertTrue(approvedTeacher.isVerified)
        assertFalse(rejectedTeacher.isVerified)
    }

    @Test
    fun courseLevel_values_areComplete() {
        val levels = CourseLevel.values()

        assertEquals(3, levels.size)
        assertTrue(levels.contains(CourseLevel.BEGINNER))
        assertTrue(levels.contains(CourseLevel.INTERMEDIATE))
        assertTrue(levels.contains(CourseLevel.ADVANCED))
    }
}
