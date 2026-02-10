package com.quranacademy.atifquranapp.util

/**
 * A wrapper class for handling different states of data
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    companion object {
        fun <T> success(data: T): Resource<T> = Success(data)
        fun <T> loading(data: T? = null): Resource<T> = Loading(data)
        fun <T> error(message: String, data: T? = null): Resource<T> = Error(message, data)
    }

    /**
     * Returns true if this is a Success instance
     */
    val isSuccess: Boolean
        get() = this is Success

    /**
     * Returns true if this is an Error instance
     */
    val isError: Boolean
        get() = this is Error

    /**
     * Returns true if this is a Loading instance
     */
    val isLoading: Boolean
        get() = this is Loading

    /**
     * Returns the data if available, or null otherwise
     */
    fun getDataOrNull(): T? = data

    /**
     * Returns the error message if available, or null otherwise
     */
    fun getMessageOrNull(): String? = message

    /**
     * Maps the data to another type
     */
    fun <R> map(transform: (T) -> R): Resource<R> {
        return when (this) {
            is Success -> Success(transform(data!!))
            is Loading -> Loading(data?.let(transform))
            is Error -> Error(message!!, data?.let(transform))
        }
    }

    /**
     * Executes the given block if this is a Success
     */
    inline fun onSuccess(action: (T) -> Unit): Resource<T> {
        if (this is Success) {
            data?.let(action)
        }
        return this
    }

    /**
     * Executes the given block if this is an Error
     */
    inline fun onError(action: (String) -> Unit): Resource<T> {
        if (this is Error) {
            message?.let(action)
        }
        return this
    }

    /**
     * Executes the given block if this is a Loading
     */
    inline fun onLoading(action: () -> Unit): Resource<T> {
        if (this is Loading) {
            action()
        }
        return this
    }
}
