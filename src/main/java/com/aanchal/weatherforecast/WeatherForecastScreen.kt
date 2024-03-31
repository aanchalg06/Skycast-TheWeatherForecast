package com.aanchal.weatherforecast

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aanchal.weatherforecast.DataManager.WeatherVM


@Composable
fun WeatherForecastScreen(
    countryList: Map<String, Pair<Double, Double>>,
    viewModel: WeatherVM,
    context: Context,
    navController: NavController
) {
    var date by remember {
        mutableStateOf("")
    }

    var latitude by remember {
        mutableStateOf(0.0)
    }
    var longitude by remember {
        mutableStateOf(0.0)
    }
    var countryName by remember {
        mutableStateOf("")
    }

    var errorMessage by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    )  {
        Column {
            Row(

            ){
                Text(
                    textAlign = TextAlign.Center,
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                        ) { append("\n") }
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xff726ff9),
                                fontSize = 50.sp
                            )
                        ) { append("SkyCast:\n") }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontSize = 24.sp
                            )
                        ) { append("The") }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontSize = 32.sp
                            )
                        ) { append(" ") }
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontSize = 24.sp
                            )
                        ) { append("WeatherForecast") }
                    },
                    modifier = Modifier
                        .offset(
                            x = 50.dp,
                            y = 10.dp
                        )
                        .requiredWidth(width = 283.dp)
                        .requiredHeight(height = 124.dp)
                )

                Spacer(modifier = Modifier.padding(16.dp))

                Button(
                    onClick = { navController.navigate("downloadpage") },
                    modifier = Modifier
                        .offset(

                        )) {
                    Text(text = "DD", color = Color.White) // Set text color to white
                }



            }


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(100.dp)
                    .padding(20.dp),
                value = countryName,
                onValueChange = {
                    countryName = it
                    errorMessage = null
                },
                label = { Text("Enter Country Name") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(100.dp)
                    .padding(20.dp),
                value = date,
                onValueChange = { date = it },
                label = { Text("Enter Date") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(100.dp)
                    .padding(20.dp),
                onClick = {
                    if (countryList.containsKey(countryName)) {
                        val (latitude, longitude) = countryList[countryName] ?: Pair(0.0, 0.0)
                        viewModel.updateDataset(latitude, longitude, date, context,countryList,countryName)
                        if(viewModel.checkDB.value){
                            errorMessage= "Net required and Database is not loaded!!"
                        }
                        else{
                            errorMessage=""
                        }

                    } else {
                        errorMessage = "Invalid country name"
                    }
                }
            ) {
                Text("Submit")
            }

            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(color = Color(0xffbbb8e5))
            )
            {
                LazyColumn()
                {
                    item{
                        Text(text = "\n")
                        Text(
                            text = "Minimum and Maximum Temperatures-",
                            modifier = Modifier.padding(18.dp),
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp
                        )

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .width(100.dp)
                                .padding(20.dp)
                        ) {
                            Text(text = "Min: ${String.format("%.2f", viewModel.min.value)}",modifier = Modifier.padding(18.dp))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .width(100.dp)
                                .padding(20.dp)
                        ) {
                            Text(text = "Max:  ${String.format("%.2f",viewModel.max.value)}",modifier = Modifier.padding(18.dp))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
    }


}

