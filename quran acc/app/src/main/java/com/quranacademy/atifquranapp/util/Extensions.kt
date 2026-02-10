package com.quranacademy.atifquranapp.util

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * Extension functions for Context
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
            capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
}

fun Context.openUrl(url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    } catch (e: Exception) {
        showToast("Unable to open link")
    }
}

fun Context.openWhatsApp(phoneNumber: String, message: String = "") {
    try {
        val formattedNumber = phoneNumber.replace("+", "").replace(" ", "")
        val url = "https://wa.me/$formattedNumber?text=${Uri.encode(message)}"
        openUrl(url)
    } catch (e: Exception) {
        showToast("WhatsApp is not installed")
    }
}

fun Context.openDialer(phoneNumber: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        startActivity(intent)
    } catch (e: Exception) {
        showToast("Unable to open dialer")
    }
}

fun Context.openEmail(email: String, subject: String = "", body: String = "") {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }
        startActivity(intent)
    } catch (e: Exception) {
        showToast("Email app not found")
    }
}

/**
 * Extension functions for View
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visibleIf(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.showSnackbar(
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionText: String? = null,
    action: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, message, duration)
    if (actionText != null && action != null) {
        snackbar.setAction(actionText) { action() }
    }
    snackbar.show()
}

/**
 * Extension functions for Fragment
 */
fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message, duration)
}

fun Fragment.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(resId, duration)
}

fun Fragment.isNetworkAvailable(): Boolean {
    return requireContext().isNetworkAvailable()
}

/**
 * Extension functions for String
 */
fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPhone(): Boolean {
    return android.util.Patterns.PHONE.matcher(this).matches() && this.length >= 10
}

/**
 * Date formatting extensions
 */
fun Long.toFormattedDate(): String {
    val sdf = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return sdf.format(java.util.Date(this))
}

fun Long.toFormattedTime(): String {
    val sdf = java.text.SimpleDateFormat("HH:mm", java.util.Locale.getDefault())
    return sdf.format(java.util.Date(this))
}

fun Long.toFormattedDateTime(): String {
    val sdf = java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault())
    return sdf.format(java.util.Date(this))
}
