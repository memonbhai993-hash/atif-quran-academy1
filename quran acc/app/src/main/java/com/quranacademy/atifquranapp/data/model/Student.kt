package com.quranacademy.atifquranapp.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 * Data class representing a Student
 */
data class Student(
    @DocumentId
    val id: String = "",

    val studentName: String = "",
    val parentName: String = "",
    val age: Int = 0,
    val gender: Gender = Gender.MALE,
    val email: String = "",
    val phone: String = "",
    val country: String = "",
    val courseId: String = "",
    val preferredTiming: String = "",
    val paymentStatus: PaymentStatus = PaymentStatus.PENDING,
    val enrollmentDate: Timestamp? = null,

    @ServerTimestamp
    val createdAt: Timestamp? = null,

    @ServerTimestamp
    val updatedAt: Timestamp? = null
)

/**
 * Enum representing student gender
 */
enum class Gender {
    MALE,
    FEMALE
}

/**
 * Enum representing payment status
 */
enum class PaymentStatus {
    PENDING,
    VERIFIED,
    REJECTED
}
