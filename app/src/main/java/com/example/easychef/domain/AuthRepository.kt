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
     * @param email The user's email address.
     * @param password The user's password.
     * @return `true` if the sign-in was successful, `false` otherwise.
     */
    suspend fun signInWithEmail(email: String, password: String): Boolean

    /**
     * Attempts to create a new user account with an email and password.
     *
     * @param email The new user's email address.
     * @param password The new user's chosen password.
     * @return `true` if the sign-up was successful, `false` otherwise.
     */
    suspend fun signUpWithEmail(email: String, password: String): Boolean

    /**
     * Signs out the currently authenticated user.
     *
     * This function will clear the user's session locally. It is a one-way
     * operation and does not return a value.
     */
    suspend fun signOut()

    /**
     * Retrieves the unique identifier of the currently authenticated user.
     *
     * @return The [UUID] of the current user if a session exists, otherwise `null`.
     */
    suspend fun currentUser(): UUID?
}