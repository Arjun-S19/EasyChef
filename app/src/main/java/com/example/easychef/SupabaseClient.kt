package com.example.easychef

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.auth.Auth
import io.ktor.client.engine.okhttp.OkHttp

/**
 * Singleton object that initializes and provides a single shared instance
 * of the Supabase client for the entire app.
 *
 * Configuration:
 *  - Uses the Supabase project URL and anon key from BuildConfig
 *  - Uses OkHttp as the HTTP engine for Ktor
 *  - Installs Auth and PostgREST modules
 *
 * Usage:
 *  Access the client via `SupabaseClient.client`
 *  Example:
 *      val data = SupabaseClient.client.postgrest["table_name"].select()
 *
 * Notes:
 *  - The instance is created lazily; it is built only on first access.
 *  - Add other modules (Realtime, Storage, Functions) here if needed.
 */
object SupabaseClient {

    /** Lazily created Supabase client shared across the app */
    val client by lazy {
        createSupabaseClient(
            supabaseUrl = BuildConfig.SUPABASE_URL,
            supabaseKey = BuildConfig.SUPABASE_ANON_KEY
        ) {
            // OkHttp engine provides networking on Android
            httpEngine = OkHttp.create()

            // Auth enables user authentication
            install(Auth)

            // PostgREST enables database table queries
            install(Postgrest)
        }
    }
}
