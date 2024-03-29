package com.aanchal.weatherforecast

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.aanchal.weatherforecast.DataManager.WeatherVM


@Composable
fun DownloadScreen(
    countryList: Map<String, Pair<Double, Double>>,
    viewModel: WeatherVM,
    context: Context,
    navController: NavHostController
) {

    // Example list of items
    viewModel.getDatabase(viewModel.list,countryList)
//    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    Column() {
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

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xffbbb8e5))
        )
        {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                viewModel.list.forEachIndexed { index, item ->
                    item {
                        Card(
                            modifier = Modifier.padding(16.dp).width(300.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xff726fe2),
                                contentColor = Color.Black
                            ),
                        ) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(fontWeight = FontWeight.Bold)
                                    ) {
                                        append("Latitude ")
                                    }
                                    append(item.first.first.toString())
                                },
                                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(fontWeight = FontWeight.Bold)
                                    ) {
                                        append("Longitude: ")
                                    }
                                    append(item.first.second.toString())
                                },
                                modifier = Modifier.padding(top = 5.dp, start = 16.dp, end = 16.dp)
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(fontWeight = FontWeight.Bold)
                                    ) {
                                        append("Date: ")
                                    }
                                    append(item.second)
                                },
                                modifier = Modifier.padding(top = 5.dp, start = 16.dp, end = 16.dp)
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(fontWeight = FontWeight.Bold)
                                    ) {
                                        append("Min. Temperature: ")
                                    }
                                    append(item.third.first.toString())
                                },
                                modifier = Modifier.padding(top = 5.dp, start = 16.dp, end = 16.dp)
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(fontWeight = FontWeight.Bold)
                                    ) {
                                        append("Max. Temperature: ")
                                    }
                                    append(item.third.second.toString())
                                },
                                modifier = Modifier.padding(top = 5.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                            )

                        }
                    }
                }
            }
        }
    }

    // Update the current index to display the next card

}

//@Preview(showBackground=true)
//@Composable
//fun Screen(){
//    DownloadScreen(viewModel, context, navController)
//
//}
//


