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
    @SerialName(SerializedKeys.INGREDIENT_ID)
    val ingredientId: String,
    @SerialName(SerializedKeys.NAME)
    val name: String,
    @SerialName(SerializedKeys.QUANTITY)
    val quantity: Double,
    @SerialName(SerializedKeys.UNIT)
    val unit: String
) {
    /**
     * Contains the string constants for the serialized JSON keys.
     * This prevents naming differences in the codebase.
     */
    companion object {
        object SerializedKeys {
            const val INGREDIENT_ID = "ingredient_id"
            const val NAME = "name"
            const val QUANTITY = "quantity"
            const val UNIT = "unit"
        }
    }
}