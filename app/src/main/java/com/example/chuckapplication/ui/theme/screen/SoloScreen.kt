package com.example.chuckapplication.ui.theme.screen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chuckapplication.R
import com.example.chuckapplication.ui.theme.ChuckApplicationTheme

@Composable
fun SoloScreen(chuckViewModel: ChuckViewModel) {
    Column(
        Modifier
            .background(Color.LightGray)
            .padding(20.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        ClickableImage(onClick = { chuckViewModel.loadData() })
        Text(
            text = chuckViewModel.chucktext.value,
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
    }
}
@Composable
fun ClickableImage(onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.kicker),
        contentDescription = "highKick!!",
        contentScale = ContentScale.Crop,
        alpha = 0.5F,
        modifier = Modifier
            .size(150.dp)
            .clickable(onClick = onClick)

    )


}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    ChuckApplicationTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            SoloScreen(chuckViewModel = ChuckViewModel())
        }
    }
}

