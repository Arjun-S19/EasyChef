package com.example.easychef

import android.os.Bundle
import android.util.Log
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
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.easychef.ui.theme.EasyChefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    //spoonacular API test:


    //API URL format:
    val spoonAPI = "https://api.spoonacular.com/recipes/complexSearch?query=pasta&diet=high-protein&cuisine=italian&intolerances=gluten&number=5&apiKey=250b5996fd1a4e98a1a051fb5719518a"
    /*
    ex) user has pasta, marinara sauce, ground beef, spinach,
    - want a high protein meal
    -italian cuisine
    -gluten free
     */


    val spoonApi2 = "https://api.spoonacular.com/recipes/complexSearch?cuisine=mexican&maxFat=25&maxCarbs=100&number=8&apiKey=250b5996fd1a4e98a1a051fb5719518a"
    /*
    ex)
    - ingredients: chicken, asparagus, salt, pepper, olive oil, rice
    -cuisine: mexican
    - high protein
    - low fat
    - low carbs
    - no dietary restriction
     */

    //request object:
    val spoonReqQueue: RequestQueue = Volley.newRequestQueue(this)

    val spoonRequest = JsonObjectRequest(Request.Method.GET, spoonAPI, null, { result ->
        Log.d("Spoonacular API Example", result.toString())
    }, { err ->
        Log.d("Volley Example", err.message.toString())
    })

    spoonReqQueue.add(spoonRequest)


    //request obj for recipe 2:
    val spoonRequest2 = JsonObjectRequest(Request.Method.GET, spoonApi2, null, { result ->
        Log.d("Spoonacular API Example", result.toString())
    }, { err ->
        Log.d("Volley Example", err.message.toString())
    })

    spoonReqQueue.add(spoonRequest2)

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