package com.example.easychef.domain

import com.example.easychef.data.model.PantryItem
import com.example.easychef.data.model.UserProfile
import java.util.UUID

/**
 * Defines the contract for all user profile data operations.
 * This covers the "Pantry" and user preferences features of the app.
 */
interface UserProfileRepository {

    /**
     * Fetches the entire profile for a given user.
     *
     * **Usage:**
     * ```kotlin
     * val userProfile = userProfileRepository.getProfile(someUserId)
     * userProfile?.let {
     * println("User's name is ${it.name}")
     * }
     * ```
     * @param userId The unique UUID of the user.
     * @return A [UserProfile] object if found, or null if an error occurs.
     */
    suspend fun getProfile(userId: UUID): UserProfile?

    /**
     * Updates the user's pantry list.
     *
     * **Usage:**
     * ```kotlin
     * val newPantry = listOf(PantryItem("1", "Apple", 2.0, "pcs"))
     * val success = userProfileRepository.updatePantry(someUserId, newPantry)
     * if (success) {
     * // Pantry was updated
     * }
     * ```
     * @param userId The unique UUID of the user.
     * @param pantryData A list of [PantryItem] objects representing the new pantry.
     * @return `true` if the update was successful, `false` otherwise.
     */
    suspend fun updatePantry(userId: UUID, pantryData: List<PantryItem>): Boolean

    /**
     * Updates the user's diet and cuisine preferences.
     *
     * **Usage:**
     * ```kotlin
     * val success = userProfileRepository.updatePreferences(
     * userId = someUserId,
     * diet = "Vegetarian",
     * cuisines = listOf("Italian", "Mexican")
     * )
     * ```
     * @param userId The unique UUID of the user.
     * @param diet A string representing the user's diet.
     * @param cuisines A list of strings for the user's favorite cuisines.
     * @return `true` if the update was successful, `false` otherwise.
     */
    suspend fun updatePreferences(userId: UUID, diet: String, cuisines: List<String>): Boolean
}