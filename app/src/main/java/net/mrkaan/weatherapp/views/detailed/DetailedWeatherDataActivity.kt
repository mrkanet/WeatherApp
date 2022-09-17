package net.mrkaan.weatherapp.views.detailed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.mrkaan.weatherapp.ui.theme.WeatherAppTheme
import net.mrkaan.weatherapp.util.request.Data
import net.mrkaan.weatherapp.views.CommonComposable
import net.mrkaan.weatherapp.views.main.SetImage
import net.mrkaan.weatherapp.views.main.getDate

class DetailedWeatherDataActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                intent.extras?.let { bundle ->
                    val data = bundle.get("detailed")
                    val cityName = bundle.get("city_name") as String
                    data?.let {
                        if (it is Data) {
                            ShowDetailedForecast(data = it, cityName)
                        } else {
                            NoDataError()
                        }
                    } ?: NoDataError()
                } ?: NoDataError()
            }
        }
    }
}


@Composable
private fun NoDataError() = CommonComposable.Error("No data retrieved")

@Composable
private fun ShowDetailedForecast(data: Data, cityName: String) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = cityName,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = getDate(data.datetime),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
            SetImage(data.weather.icon)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp, 0.dp, 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = data.temp.toString(), fontSize = 32.sp)
                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "°C", fontSize = 22.sp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 4.dp, 0.dp, 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = data.weather.description,
                    fontSize = 13.sp,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 4.dp, 20.dp, 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = "Min: ${data.min_temp} °C")
                Text(text = "Max: ${data.max_temp} °C")
            }
        }
    }
}