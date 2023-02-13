package com.example.myfinalapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

class MainActivity : ComponentActivity() {
    lateinit var item1:Item
    lateinit var item2:Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setItens()
            Column(modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                GeneralCard(item1)
                GeneralCard(item2)
            }

        }
    }

    private fun setItens() {
        item1 = Item(
            1,
            "PETRO",
            "Petrobrás",
            "https://seeklogo.com/images/P/Petrobras-logo-03DABEE0AC-seeklogo.com.png)",
            "Ação",
            3.45F,4.5F,5F,8F)

        item2 = Item(
            2,
            "COMB",
            "Postos",
            "https://seeklogo.com/images/P/Petrobras-logo-03DABEE0AC-seeklogo.com.png)",
            "Ação",
            4.65F,2.4F,7F,-5F)
    }
}
@Composable
fun GeneralCard(item: Item) {
    val context = LocalContext.current
    var expandedState by remember { mutableStateOf(false)}
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .animateContentSize(animationSpec = tween(
                delayMillis = 300,
                easing = LinearOutSlowInEasing
            )),


        elevation = 16.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()

        ) {
            Text(
                text = item.number.toString(),
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Image(
                painter = painterResource(id = R.drawable.petrobras),
                contentDescription = "Image",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(100.dp)),
                contentScale = ContentScale.None
            )
//            AsyncImage(
//                model = item.img,
//                contentDescription = "Imagem da internet"
//            )
            Column() {
                Text(
                    text = item.abrev,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "See More",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),

                    modifier = Modifier.padding(4.dp)
                        .clickable {
                            expandedState = !expandedState
                        },
                    color = Color.Blue,
                )

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)) {
                Text(
                    text = "R$ " + item.actualBalance.toString(),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = item.dayBalance.toString() + " %",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(4.dp),
                    color = verifyColor(item.dayBalance)
                )
            }
        }
    }
    //
}

fun verifyColor(balance: Float): Color {
    if (balance < 0) {
        return Color.Red
    }
    else if(balance > 0) {
        return Color.Green
    }
    else return Color.Gray

}
