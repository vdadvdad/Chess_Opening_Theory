package com.example.openingtheory

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.openingtheory.Logic.CourseCard

@Composable
fun CourseList(){
    var courseCards = ArrayList<CourseCard>()
    courseCards.add(CourseCard(route = "training", imageId = R.drawable.stafford_gambit))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(ScrollState(1)),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        courseCards.forEach{ item -> item.showCard() }
    }
}