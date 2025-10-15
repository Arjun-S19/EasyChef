package com.example.easychef.data.model

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
    val diet: String?,
    val cuisines: List<String>
)