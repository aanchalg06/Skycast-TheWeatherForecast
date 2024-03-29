package com.aanchal.weatherforecast

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aanchal.weatherforecast.DataManager.WeatherVM


@Composable
fun ForecastScreen(){






    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    )  {
        Column {
            Text(
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontSize = 12.sp)
                    ) {append("\n")}
                    withStyle(style = SpanStyle(
                        color = Color(0xff726ff9),
                        fontSize = 50.sp)) {append("SkyCast:\n")}
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontSize = 24.sp)) {append("The")}
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontSize = 32.sp)) {append(" ")}
                    withStyle(style = SpanStyle(
                        color = Color.Black,
                        fontSize = 24.sp)) {append("WeatherForecast")}},
                modifier = Modifier
                    .offset(
                        x = 45.dp,
                        y = 0.dp
                    )
                    .requiredWidth(width = 283.dp)
                    .requiredHeight(height = 124.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(100.dp)
                    .padding(20.dp),
                value = "IND",
                onValueChange = { it },
                label = { Text("Enter Country Name") }
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(100.dp)
                    .padding(20.dp),
                value = "2025-01-09",
                onValueChange = { it },
                label = { Text("Enter Date") }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .width(100.dp)
                    .padding(20.dp),
                onClick = {

                }
            ) {
                Text("Submit")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(color = Color(0xffbbb8e5)))

            {
                Column()
                {
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
                        Text(text = "Max:  0.1", modifier = Modifier.padding(18.dp))
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .width(100.dp)
                            .padding(20.dp)
                    ) {
                        Text(text = "Max:  0.1", modifier = Modifier.padding(18.dp))
                    }


                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }


}



@Preview(showBackground=true)
@Composable
fun Show(){
        ForecastScreen()

}

