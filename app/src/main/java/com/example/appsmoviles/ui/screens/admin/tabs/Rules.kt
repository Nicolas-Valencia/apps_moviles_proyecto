package com.example.appsmoviles.ui.screens.admin.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appsmoviles.R

@Composable
fun Rules() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F2F2))
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Título principal
            Text(
                text = stringResource(R.string.rules_title),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Criterios
            Text(
                text = stringResource(R.string.rules_criteria_title),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            BulletText(stringResource(R.string.rules_criteria_item1))
            BulletText(stringResource(R.string.rules_criteria_item2))
            BulletText(stringResource(R.string.rules_criteria_item3))
            BulletText(stringResource(R.string.rules_criteria_item4))
            BulletText(stringResource(R.string.rules_criteria_item5))

            Spacer(modifier = Modifier.height(24.dp))

            // Razones de rechazo
            Text(
                text = stringResource(R.string.rules_reject_title),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            BulletText(stringResource(R.string.rules_reject_item1))
            BulletText(stringResource(R.string.rules_reject_item2))
            BulletText(stringResource(R.string.rules_reject_item3))
            BulletText(stringResource(R.string.rules_reject_item4))
            BulletText(stringResource(R.string.rules_reject_item5))
        }
    }
}

@Composable
fun BulletText(text: String) {
    Row(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(text = "• ", fontSize = 16.sp)
        Text(text = text, fontSize = 16.sp)
    }
}
