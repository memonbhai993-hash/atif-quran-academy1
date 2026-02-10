package com.quranacademy.atifquranapp.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp

/**
 * Data class representing a Payment
 */
data class Payment(
    @DocumentId
    val id: String = "",

    val studentId: String = "",
    val studentName: String = "",
    val courseId: String = "",
    val courseName: String = "",
    val amount: Double = 0.0,
    val paymentMethod: String = "",
    val transactionId: String = "",
    val paymentProofUrl: String = "",
    val paymentStatus: PaymentVerificationStatus = PaymentVerificationStatus.PENDING,
    val month: String = "", // e.g., "February 2026"
    val notes: String = "",

    @ServerTimestamp
    val paymentDate: Timestamp? = null,

    @ServerTimestamp
    val createdAt: Timestamp? = null,

    @ServerTimestamp
    val updatedAt: Timestamp? = null
)

/**
 * Enum representing payment verification status
 */
enum class PaymentVerificationStatus {
    PENDING,
    VERIFIED,
    REJECTED
}
