package net.mrkaan.weatherapp.views

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import net.mrkaan.weatherapp.ui.theme.WeatherAppTheme
import net.mrkaan.weatherapp.util.ImageClass
import net.mrkaan.weatherapp.util.request.Data
import net.mrkaan.weatherapp.util.request.ResponseModel
import net.mrkaan.weatherapp.util.request.Weather
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity(), LocationListener {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var locationManager: LocationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLoading()

        requestPermissions(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            100
        )

        setObservers()
    }

    private fun showLoading() {
        setContent {
            Loading()
        }
    }

    private fun initViews() {
        setContent {
            viewModel.weatherData.value?.let { MainPreview(it) }
                ?: MainPreviewWithDummyData()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 100) {
            for (i in grantResults) {
                if (i != 0) {
                    return
                }
            }
            setLocationListener()
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }

    private fun setLocationListener() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, this)
        }
    }

    private fun setObservers() {
        viewModel.showToast.observe(this) {
            Toast.makeText(
                this@MainActivity,
                "Request failed try again later",
                Toast.LENGTH_LONG
            ).show()
        }

        viewModel.weatherData.observe(this) { initViews() }
    }

    override fun onLocationChanged(loc: Location) {
        //Location data is only needed for first time, that's why removing listener
        viewModel.location.postValue(loc)
        viewModel.request(loc.latitude.toString(), loc.longitude.toString())
        locationManager.removeUpdates(this)
    }


}

@Composable
fun MainPreview(model: ResponseModel) {
    WeatherAppTheme {
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
                            text = model.city_name,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = getDate(model.data[0].datetime),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                }
                SetImage(imgName = model.data[0].weather.icon)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = model.data[0].temp.toString(), fontSize = 32.sp)
                    Spacer(modifier = Modifier.padding(2.dp))
                    Text(text = "°C", fontSize = 22.sp)
                }
                Row {
                    val smallForecast = model.data.subList(1, model.data.size)
                    LazyRow(
                        contentPadding = PaddingValues(
                            horizontal = 0.dp,
                            vertical = 8.dp
                        )
                    ) {
                        items(smallForecast, itemContent = { SmallForecast(data = it) })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreviewWithDummyData() {
    val exampleData =
        ResponseModel(
            "Bear Mountain", "US", listOf(
                Data("2022-09-16", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-17", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-16", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-17", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-16", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-17", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-16", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-17", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-16", 18.7, Weather(-1, "c02d", "c02d")),
                Data("2022-09-17", 18.7, Weather(-1, "c02d", "c02d"))
            ), 39.2201, -80.1588, "WV", "America/New_York"
        )
    MainPreview(model = exampleData)
}

fun getDate(dateTime: String): String =
    LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        .format(DateTimeFormatter.ofPattern("EEE, dd LLL")).toString()

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier =
            Modifier
                .height(100.dp)
                .width(100.dp)
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun SetImage(imgName: String) {
    if (ImageClass.getImage(imgName) != -1) {
        Image(
            painter = painterResource(id = ImageClass.getImage(imgName)),
            contentDescription = imgName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 100.dp,
                    top = 0.dp,
                    end = 100.dp,
                    bottom = 0.dp
                ),
            contentScale = ContentScale.FillWidth
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallForecast(data: Data) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .width(100.dp)
            .height(150.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
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