package com.example.appsmoviles.ui.screens.user

import BottomBarUser
import ContentUser
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.appsmoviles.R

@Composable
fun HomeUser(){

    val navController = rememberNavController()

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarUser()
        },
        bottomBar = {
            BottomBarUser(
                navController = navController
            )
        }
    ) { padding ->
        ContentUser(
            navController = navController,
            padding = padding
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUser(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF9800), shape = RoundedCornerShape(2.dp))
            .padding(40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Surface(
            color = Color.Black,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = stringResource(R.string.txt_user),
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                fontSize = 18.sp
            )
        }
    }
}
