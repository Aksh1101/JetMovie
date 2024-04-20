package com.example.jetmovie.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetmovie.Model.Movie
import com.example.jetmovie.Model.getMovies
import com.example.jetmovie.navigation.MoviesScreens
import com.example.jetmovie.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController){
    Scaffold (topBar = { TopAppBar(title = { Text(text = "Movies") }, Modifier.padding(2.dp)
    )
    },){MainContent(navController = navController)
    }

}
@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
){
    Column(modifier =Modifier.padding(start = 12.dp, top = 75.dp)){
        LazyColumn {
            items(items = movieList){
                MovieRow(movie = it){ movie ->
                    navController.navigate(route = MoviesScreens.DetailScreen.name+"/$movie")
                    //Log.d("TAG","Main Content: $movie")

                }
            }
        }
    }
}