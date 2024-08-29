package com.example.movieapp.Screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.movieRow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val newMovieList = getMovies().filter { movie->
        movie.id == movieId
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie", Modifier.padding(start = 90.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                ))
            Row (
            ){
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription ="Arrow Back" ,
                modifier = Modifier
                    .padding(top = 50.dp)
                    .clickable { navController.popBackStack() }
                    .size(40.dp))

                Spacer(modifier = Modifier.width(100.dp))
            }
        }
    ) { paddingValue->
        Surface(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(paddingValue)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
               movieRow(newMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Text(text = "MovieIamges")
                HorizontalScrollAbleImageView(newMovieList)
            }
        }
    }

}

@Composable
private fun HorizontalScrollAbleImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { image ->
            Card(
                Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = image),


                    contentDescription = "Images"
                )
            }
        }
    }
}
//Surface(modifier = Modifier
//.fillMaxWidth()
//.fillMaxHeight()
//) {
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = movieData.toString() , style = MaterialTheme.typography.headlineLarge)
//        Spacer(modifier = Modifier.height(20.dp))
//        Button(onClick = {  navController.popBackStack()}) {
//            Text(text = "Go Back")
//        }
//    }
//}
