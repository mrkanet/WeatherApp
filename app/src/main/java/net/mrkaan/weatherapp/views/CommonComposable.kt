package net.mrkaan.weatherapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.mrkaan.weatherapp.R
import net.mrkaan.weatherapp.util.ImageClass
import net.mrkaan.weatherapp.util.request.Data
import net.mrkaan.weatherapp.views.main.getDate

/**
 * Commonly used composables are in this class and they can be reached from anywhere.
 * This is like a common ui library.
  */
class CommonComposable {
    companion object {

        @Composable
        fun Loading() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier =
                    Modifier
                        .height(100.dp)
                        .width(100.dp)
                )
            }
        }

        @Composable
        fun Error(message: String) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier =
                    Modifier
                        .height(100.dp)
                        .width(100.dp),
                    painter = painterResource(
                        id = R.drawable.ic_baseline_error_outline_24
                    ),
                    contentDescription = "Error"
                )
                Text(
                    text = message,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        }


        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun SmallForecast(
            data: Data,
            modifier: Modifier = Modifier
                .padding(4.dp)
                .width(100.dp)
                .height(150.dp),
            nextClick: () -> Unit,
            onClick: (pos: Int) -> Unit,
            pos: Int
        ) {
            Card(
                modifier = modifier,
            ) {
                if (data.datetime == "") {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_navigate_next_24),
                        contentDescription = "All weather data",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                nextClick.invoke()
                            }
                    )
                } else {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clickable { onClick.invoke(pos) },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(text = getDate(data.datetime))
                        Image(
                            painter = painterResource(id = ImageClass.getImage(data.weather.icon)),
                            contentDescription = data.weather.icon
                        )
                        Row {
                            Text(text = data.temp.toString(), fontSize = 22.sp)
                            Spacer(modifier = Modifier.padding(2.dp))
                            Text(text = "°C", fontSize = 16.sp)
                        }
                    }
                }
            }

        }
    }
}