package net.mrkaan.weatherapp.views.allweatherdata

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import net.mrkaan.weatherapp.ui.theme.WeatherAppTheme
import net.mrkaan.weatherapp.util.request.Data
import net.mrkaan.weatherapp.views.CommonComposable
import net.mrkaan.weatherapp.views.detailed.DetailedWeatherDataActivity

class AllWeatherDataActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme {
                intent.extras?.let { bundle ->
                    bundle.get("allData")?.let { data ->
                        if (data is List<*> && data.isNotEmpty() && data[0] is Data) {
                            LazyVerticalGrid(
                                cells = GridCells.Fixed(2),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                items(data.size) {
                                    CommonComposable.SmallForecast(
                                        data = data[it] as Data,
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .width(100.dp)
                                            .height(150.dp),
                                        nextClick = {},
                                        onClick = { pos ->
                                            val extras = bundleOf(
                                                Pair("detailed", data[pos]),
                                                Pair("city_name", intent.extras?.get("city_name"))
                                            )
                                            val intent =
                                                Intent(
                                                    this@AllWeatherDataActivity,
                                                    DetailedWeatherDataActivity::class.java
                                                )
                                            intent.putExtras(extras)
                                            startActivity(intent)
                                        },
                                        pos = it
                                    )
                                }
                            }
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