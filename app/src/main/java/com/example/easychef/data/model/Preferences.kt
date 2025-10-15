package com.example.easychef.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a user's dietary and cuisine preferences.
 *
 * This data class is used to group related user settings for easier
 * management and passing between different parts of the application.
 *
 * @property diet A string describing the user's dietary preference.
 * @property cuisines A list of strings for the user's favorite cuisines.
 */
@Serializable
data class Preferences(
    @SerialName(SerializedKeys.DIET)
    val diet: String?,
    @SerialName(SerializedKeys.CUISINES)
    val cuisines: List<String>
) {
    /**
     * Contains the string constants for the serialized JSON keys.
     */
    companion object {
        object SerializedKeys {
            const val DIET = "diet"
            const val CUISINES = "cuisines"
        }
    }
}