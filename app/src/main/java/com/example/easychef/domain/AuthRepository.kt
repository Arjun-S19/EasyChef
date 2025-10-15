package com.example.easychef.domain

import java.util.UUID

/**
 * Defines the contract for all authentication-related operations in the application.
 *
 * This interface acts as the boundary for the domain layer, ensuring that the
 * core application logic is decoupled from the specific implementation of the
 * authentication service (e.g., Supabase, Firebase, etc.).
 */
interface AuthRepository {

    /**
     * Attempts to sign in a user with their email and password.
     *
     * **Usage:**
     * ```kotlin
     * val wasSuccessful = authRepository.signInWithEmail("user@example.com", "password123")
     * if (wasSuccessful) {
     * // Navigate to home screen
     * } else {
     * // Show error message
     * }
     * ```
     * @param email The user's email address.
     * @param password The user's password.
     * @return `true` if the sign-in was successful, `false` otherwise.
     */
    suspend fun signInWithEmail(email: String, password: String): Boolean

    /**
     * Attempts to create a new user account with an email and password.
     *
     * **Usage:**
     * ```kotlin
     * val didSignUp = authRepository.signUpWithEmail("newuser@example.com", "strongPassword")
     * if (didSignUp) {
     * // Inform user to check their email for confirmation
     * } else {
     * // Show sign-up error
     * }
     * ```
     * @param email The new user's email address.
     * @param password The new user's chosen password.
     * @return `true` if the sign-up was successful, `false` otherwise.
     */
    suspend fun signUpWithEmail(email: String, password: String): Boolean

    /**
     * Signs out the currently authenticated user.
     *
     * This function will clear the user's session locally.
     *
     * **Usage:**
     * ```kotlin
     * authRepository.signOut()
     * // Navigate back to the login screen
     * ```
     */
    suspend fun signOut()

    /**
     * Retrieves the unique identifier of the currently authenticated user.
     *
     * **Usage:**
     * ```kotlin
     * val userId = authRepository.currentUser()
     * if (userId != null) {
     * // User is logged in, proceed with fetching user-specific data
     * } else {
     * // No user is logged in
     * }
     * ```
     * @return A [UUID] object representing the logged-in user's unique ID, or `null` if no user is authenticated.
     */
    suspend fun currentUser(): UUID?
}