package com.example.easychef.data

import com.example.easychef.domain.AuthRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import java.util.UUID
import javax.inject.Inject

/**
 * Implements the AuthRepository interface using the SupabaseClient.
 *
 * @property supabaseClient The main Supabase client provided by Hilt.
 */
class SupabaseAuthRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) : AuthRepository {

    /**
     * This implementation calls the Supabase `signInWith` function.
     */
    override suspend fun signInWithEmail(email: String, password: String): Boolean {
        return try {
            supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * This implementation calls the Supabase `signUpWith` function.
     */
    override suspend fun signUpWithEmail(email: String, password: String): Boolean {
        return try {
            supabaseClient.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * This implementation calls the Supabase `signOut` function.
     */
    override suspend fun signOut() {
        try {
            supabaseClient.auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * This implementation retrieves the current user session from the auth client.
     */
    override suspend fun currentUser(): UUID? {
        val user = supabaseClient.auth.currentUserOrNull()
        return user?.id?.let { UUID.fromString(it) }
    }
}