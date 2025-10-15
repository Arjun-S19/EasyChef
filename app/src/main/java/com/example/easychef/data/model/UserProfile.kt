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
    @SerialName("user_id")
    val userId: String,
    @SerialName("user_name")
    val userName: String?,
    val pantry: List<PantryItem>,
    val diet: String?,
    val cuisines: List<String>,
    @SerialName("updated_date")
    val updatedDate: String?
)