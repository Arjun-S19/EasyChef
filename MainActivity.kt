package com.example.easychef

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.easychef.ui.theme.EasyChefTheme

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        setContent {
            EasyChefTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun fetchRecipes(): Thread{
        return Thread{
            //API endpoint
            val url = URL("https://api.spoonacular.com/recipes/random?number=10&apiKey=YOUR_API_KEY")

            val connection = HttpURLConnection = url.openConnection() as HttpURLConnection

            //Request GET Method
            connection.requestMethod = "GET"

            //Response code
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val inputStream = connection.inputStream
                //Read and print the response data
                val reader : BufferedReader = BufferedReader(InputStreamReader(inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }

                //close connections
                reader.close()
                inputStream.close()
                connection.disconnect()

                //JSON response via Gson
                val jsonResponse = response.toString()
                val gson = Gson()
                val recipeResponse = gson.fromJson(jsonResponse, RecipeResponse::class.java)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EasyChefTheme {
        Greeting("Android")
    }
}