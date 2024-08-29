package com.example.movieapp.widgets

import android.net.Uri.Builder
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.ImageSource
import coil.request.ImageRequest
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies

@Preview
@Composable
fun movieRow(movie: Movie = getMovies()[0], onItemClick : (String) -> Unit ={}){

    var enabled by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        //.height(130.dp)
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start

        ){
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape ,
                tonalElevation = 4.dp,
                shadowElevation = 4.dp,

                )
            {
//               Image(painter =  rememberAsyncImagePainter(
//                   model = ImageRequest.Builder(LocalContext.current)
//                       .data(movie.images)
//                       .crossfade(true)
//                       .build(),
//               ), contentDescription = null)


                Image(painter = rememberAsyncImagePainter(model = movie.images[0]
                    ), contentDescription = "movie images")


            }
            Column(modifier = Modifier.padding(4.dp)) {

                Text(text = movie.title, style = MaterialTheme.typography.headlineMedium , fontWeight = FontWeight.Bold)
                Text(text = "Director: ${movie.title}", style = MaterialTheme.typography.titleSmall)
                Text(text = "Release: ${movie.year}" ,  style = MaterialTheme.typography.titleSmall)
                AnimatedVisibility(visible = enabled) {
                    Column {
                        Text(buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black,
                                fontSize = 13.sp)){
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(color = Color.LightGray,
                                fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            ){
                                append(movie.plot)
                            }
                        }, modifier = Modifier.padding(6.dp))
                        HorizontalDivider(modifier =Modifier.padding(3.dp) )
                        Text(text = "Director: ${movie.director}" , style= MaterialTheme.typography.titleSmall , fontWeight = FontWeight.W700)
                        Text(text = "Actor: ${movie.actors}" , style= MaterialTheme.typography.titleSmall,fontWeight = FontWeight.W700)
                        Text(text = "rating: ${movie.rating}" , style= MaterialTheme.typography.titleSmall,fontWeight = FontWeight.W700)

                    }


                }


                Icon(imageVector = if(enabled == false)
                    Icons.Filled.KeyboardArrowDown
                    else
                        Icons.Filled.KeyboardArrowUp,
                    contentDescription = "up and down",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            enabled = !enabled
                        },
                    tint = Color.DarkGray)

            }

        }
    }
}
