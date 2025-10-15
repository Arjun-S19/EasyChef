package com.example.easychef.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a single ingredient item within a user's pantry.
 *
 * This data class is used for serializing to and from the JSON array stored
 * in the `user_profiles` table's `pantry` column.
 *
 * @property ingredientId The unique identifier for the ingredient.
 * @property name The display name of the ingredient.
 * @property quantity The amount of the ingredient the user has.
 * @property unit The unit of measurement for the quantity (e.g., "g", "piece").
 */
@Serializable
data class PantryItem(
    @SerialName("ingredient_id")
    val ingredientId: String,
    val name: String,
    val quantity: Double,
    val unit: String
)