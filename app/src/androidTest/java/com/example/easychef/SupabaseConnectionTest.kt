package com.example.easychef

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Hilt-enabled integration test to verify that the Supabase client is correctly
 * configured and can query data from the backend.
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class SupabaseConnectionTest {

    @get:Rule // 2. Add the Hilt rule
    var hiltRule = HiltAndroidRule(this)

    @Inject // 3. Inject the SupabaseClient directly
    lateinit var client: SupabaseClient

    /**
     * Initializes Hilt's components before each test.
     */
    @Before
    fun setup() {
        hiltRule.inject() // 4. This line injects the dependencies
    }

    /**
     * Performs a read operation on `test_table` and asserts that
     * the first record contains name = "Hello World!".
     */
    @Test
    fun canSelectFromTestTable() {
        runBlocking {
            try {
                val rows = withTimeout(10_000) {
                    client.postgrest["test_table"].select().decodeList<TestItem>()
                }
                assert(rows.isNotEmpty())
                assertEquals("Hello World!", rows[0].name)
                println("Supabase connection test succeeded")
            } catch (t: Throwable) {
                // It's better to log the actual error for debugging
                t.printStackTrace()
                throw AssertionError("Supabase connection test failed.")
            }
        }
    }
}