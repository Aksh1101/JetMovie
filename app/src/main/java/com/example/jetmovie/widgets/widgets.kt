package com.example.jetmovie.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.jetmovie.Model.Movie
import com.example.jetmovie.Model.getMovies

@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {}){
    var expanded by  remember{
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            //.height(130.dp) as i don't need the fixed height
            .clickable {
                onItemClick(movie.id)
            },
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp)
                , shape = RectangleShape,
                shadowElevation = 4.dp){

                Image(painter = rememberAsyncImagePainter(model = movie.images[0]
                ),
                    contentDescription ="Movie Poster")
                //Icon(imageVector = Icons.Default.Face, contentDescription ="Movie Image" )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style =  MaterialTheme.typography.headlineSmall)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.bodySmall)
                
                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White,
                                fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light)){
                                append(movie.plot)
                            }
                        },modifier = Modifier.padding(6.dp))

                        Divider(modifier = Modifier.padding(3.dp), thickness = 1.dp , color = Color.Black)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.bodySmall)

                    }
                }


                Icon(imageVector = if (expanded)Icons.Default.KeyboardArrowUp
                    else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "ArrowDropDown" ,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.Black
                )
            }

        }


    }
}
