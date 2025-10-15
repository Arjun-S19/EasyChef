package com.example.easychef.di

import com.example.easychef.BuildConfig
import com.example.easychef.domain.AuthRepository
import com.example.easychef.domain.UserProfileRepository
import com.example.easychef.data.repository.SupabaseAuthRepository
import com.example.easychef.data.repository.SupabaseUserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton

/**
 * Hilt module that provides all Supabase-related dependencies for the application.
 *
 * This module is installed in the SingletonComponent, meaning all dependencies
 * provided here will have a single instance throughout the app's lifecycle.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SupabaseModule {

    /**
     * Binds the AuthRepository interface to its Supabase implementation.
     *
     * This tells Hilt that whenever a component requests an [AuthRepository],
     * it should provide an instance of [SupabaseAuthRepository].
     *
     * @param impl The concrete implementation of the repository.
     * @return An instance satisfying the [AuthRepository] contract.
     */
    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: SupabaseAuthRepository): AuthRepository

    /**
     * Binds the UserProfileRepository interface to its Supabase implementation.
     *
     * This tells Hilt that whenever a component requests a [UserProfileRepository],
     * it should provide an instance of [SupabaseUserProfileRepository]. This is a
     * more efficient way of providing an implementation for an interface
     * compared to using @Provides.
     *
     * @param impl The concrete implementation of the repository.
     * @return An instance satisfying the [UserProfileRepository] contract.
     */
    @Binds
    @Singleton
    abstract fun bindUserProfileRepository(impl: SupabaseUserProfileRepository): UserProfileRepository

    /**
     * Companion object to hold the @Provides functions, which create instances
     * of classes from external libraries that Hilt doesn't know how to build.
     */
    companion object {

        /**
         * Provides a singleton instance of the main [SupabaseClient].
         *
         * This is the core client that manages all modules (Auth, Postgrest, etc.).
         * It's configured with the project URL and anon key from the BuildConfig.
         *
         * @return A fully configured [SupabaseClient].
         */
        @Provides
        @Singleton
        fun provideSupabaseClient(): SupabaseClient {
            return createSupabaseClient(
                supabaseUrl = BuildConfig.SUPABASE_URL,
                supabaseKey = BuildConfig.SUPABASE_ANON_KEY
            ) {
                // Installs the authentication module
                install(Auth)
                // Installs the database module
                install(Postgrest)
                // Sets OkHttp as the networking engine, recommended for Android
                httpEngine = OkHttp.create()
            }
        }
    }
}