package com.example.easychef.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user's complete profile data as stored in the database.
 *
 * This class models the structure of a row in the `user_profiles` table
 * and is used for deserializing the JSON response from Supabase.
 *
 * @property userId The user's unique identifier (UUID).
 * @property userName The user's display name.
 * @property pantry A list of [PantryItem] objects representing the user's pantry.
 * @property diet A string describing the user's dietary preference.
 * @property cuisines A list of strings for the user's favorite cuisines.
 * @property updatedDate The timestamp of the last profile update.
 */
@Serializable
data class UserProfile(
    @SerialName(SerializedKeys.USER_ID)
    val userId: String,
    @SerialName(SerializedKeys.USERNAME)
    val userName: String?,
    @SerialName(SerializedKeys.PANTRY)
    val pantry: List<PantryItem>,
    @SerialName(SerializedKeys.DIET)
    val diet: String?,
    @SerialName(SerializedKeys.CUISINES)
    val cuisines: List<String>,
    @SerialName(SerializedKeys.UPDATED_DATE)
    val updatedDate: String?
) {
    /**
     * Contains the string constants for the serialized JSON keys,
     * which correspond to the column names in the database table.
     */
    companion object {
        object SerializedKeys {
            const val USER_ID = "user_id"
            const val USERNAME = "user_name"
            const val PANTRY = "pantry"
            const val DIET = "diet"
            const val CUISINES = "cuisines"
            const val UPDATED_DATE = "updated_date"
        }
    }
}