package com.example.easychef.data.repository

import com.example.easychef.data.model.PantryItem
import com.example.easychef.data.model.Preferences
import com.example.easychef.data.model.UserProfile
import com.example.easychef.domain.UserProfileRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import java.util.UUID
import javax.inject.Inject

/**
 * Implements the UserProfileRepository using the Supabase Postgrest client.
 * This class handles the database queries for the `user_profiles` table.
 *
 * @property supabaseClient The main Supabase client provided by Hilt.
 */
class SupabaseUserProfileRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) : UserProfileRepository {

    private val table = supabaseClient.postgrest["user_profiles"]

    /**
     * Fetches a user profile and decodes it into a [UserProfile] object.
     */
    override suspend fun getProfile(userId: UUID): UserProfile? {
        return try {
            table.select {
                filter { eq("user_id", userId.toString()) }
            }.decodeSingleOrNull<UserProfile>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    /**
     * Updates the `pantry` column for a specific user in the database.
     */
    override suspend fun updatePantry(userId: UUID, pantryData: List<PantryItem>): Boolean {
        return try {
            table.update({
                set("pantry", pantryData)
                set("updated_date", "now()")
            }) {
                filter { eq("user_id", userId.toString()) }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * Updates the `diet` and `cuisines` columns for a specific user.
     */
    override suspend fun updatePreferences(userId: UUID, preferences: Preferences): Boolean {
        return try {
            table.update({
                set("diet", preferences.diet)
                set("cuisines", preferences.cuisines)
                set("updated_date", "now()")
            }) {
                filter { eq("user_id", userId.toString()) }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}