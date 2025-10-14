package com.example.easychef

import kotlinx.serialization.Serializable

/**
 * Data model representing a single row in the `test_table` on Supabase.
 */
@Serializable
data class TestItem(
    val id: Int,
    val name: String
)