package com.example.easychef

import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Integration test to verify that the Supabase client is correctly configured
 * and can query data from the backend.
 *
 * Requirements:
 *  - `SupabaseClientProvider` initialized with a valid URL and anon key
 *  - Supabase table `test_table` exists with columns `id` and `name`
 *  - RLS is disabled for this table
 *  - First row with name = "Hello World!" is present
 */
@RunWith(AndroidJUnit4::class)
class SupabaseConnectionTest {

    private lateinit var client: SupabaseClient

    /**
     * Initializes the shared Supabase client from the app provider.
     * Runs once before each test.
     */
    @Before
    fun setup() {
        client = SupabaseClientProvider.client
    }

    /**
     * Performs a read operation on `test_table` and asserts that
     * the first record contains name = "Hello World!".
     *
     * This confirms:
     *  - network access is available
     *  - Supabase client configuration is valid
     *  - PostgREST decoding works as expected
     */
    @Test
    fun canSelectFromTestTable()
    {
        // runBlocking is a simple way to run a suspend function in a test
        runBlocking {
            try {
                // Fetch the item from the test_table
                val rows = withTimeout(10_000) {
                    client.postgrest["test_table"].select().decodeList<TestItem>()
                }

                assert(rows.isNotEmpty())

                assertEquals("Hello World!", rows[0].name)

                println("Supabase connection test succeeded")

            } catch (_: Throwable) {
                throw AssertionError("Supabase connection test failed.")
            }
        }
    }

}
