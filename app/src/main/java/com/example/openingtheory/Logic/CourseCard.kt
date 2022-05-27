package com.example.openingtheory.Logic

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.openingtheory.R

class CourseCard(route: String = "", imageId: Int = 0) {
    var route = route
    var imageId = imageId
    @Composable
    fun showCard(navController: NavController = rememberNavController()){
        Card(modifier = Modifier
            .clickable { navController.navigate(route) }
            .size(300.dp).clip(RoundedCornerShape(5.dp))){
            Image(painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(5.dp)))
        }
    }
}