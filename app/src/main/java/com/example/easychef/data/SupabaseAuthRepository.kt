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
 * This class is responsible for making the actual API calls to Supabase for
 * all authentication-related tasks. It is injected with a SupabaseClient
 * instance provided by Hilt.
 *
 * @property supabaseClient The main Supabase client provided by Hilt.
 */
class SupabaseAuthRepository @Inject constructor(
    private val supabaseClient: SupabaseClient
) : AuthRepository {

    /**
     * {@inheritDoc}
     * This implementation calls the Supabase `signInWith` function via the auth module.
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
     * {@inheritDoc}
     * This implementation calls the Supabase `signUpWith` function via the auth module.
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
     * {@inheritDoc}
     * This implementation calls the Supabase `signOut` function to clear the session.
     */
    override suspend fun signOut() {
        try {
            supabaseClient.auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * {@inheritDoc}
     * This implementation retrieves the current user session from the auth client
     * and converts the user ID string to a UUID.
     */
    override suspend fun currentUser(): UUID? {
        val user = supabaseClient.auth.currentUserOrNull()
        return user?.id?.let { UUID.fromString(it) }
    }
}