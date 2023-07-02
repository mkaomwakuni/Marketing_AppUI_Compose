package dev.mkao.marketing_ui_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mkao.marketing_ui_compose.ui.theme.Marketing_UI_ComposeTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Marketing_UI_ComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Contents()
                }
            }
        }
    }


    @Composable
    fun Contents() {
        val taskList = listOf("Account Opening", "Receipt Processing", "Account Verification","Loan Processing")
        val taskFlows = listOf("Applicant History", "Mobile Banking", "Health Insurance","Sacco")

        Scaffold(
            backgroundColor = colorResource(id = R.color.seal),
            topBar = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1.dp)
                        .statusBarsPadding() // Add padding for status bar
                ) {
                    Spacer(modifier = Modifier.height(1.dp)) // Add space between status bar and app name
                    Text(
                        text = "Chama App", // App name
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(16.dp)) // Add space between status bar and app name
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column() {
                        Text(
                            text = "Hi Welcome Back",
                            fontSize = 16.sp,
                            color = Color.DarkGray
                        )
                        Text(
                            text = "Karisa Kaingu",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                    }
                    Box() {
                        Image(
                            painterResource(id = R.drawable.user),
                            contentDescription = "User Pic",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .size(55.dp)
                                .clip(RoundedCornerShape(50.dp))
                        )

                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(BorderStroke(4.dp, color = Color.Black))
                                .background(Color.Black)
                                .align(alignment = Alignment.BottomStart)
                        ) {

                            Text(
                                text = "2",
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                    }
                }
            },
            //Bottom navigation
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp)),
                    backgroundColor = colorResource(id = R.color.seal)
                ) {
                    BottomNavigationItem(
                        selected = true, onClick = {}, icon = {
                            val selected = true
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home Page" ,
                                tint = if (selected) Color.Black else Color.LightGray
                            )
                        })
                    BottomNavigationItem(
                        selected = false, onClick = { /*TODO*/ }, icon = {
                            Icon(
                                imageVector = Icons.Filled.AccountBox,
                                contentDescription = "Sacco"
                            )
                        })
                    BottomNavigationItem(
                        selected = false, onClick = { /*TODO*/ }, icon = {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "Transactions"
                            )
                        })
                    BottomNavigationItem(
                        selected = false, onClick = { /*TODO*/ }, icon = {
                            Icon(
                                imageVector = Icons.Filled.Create,
                                contentDescription = "Loans"
                            )
                        })

                }
            }


        )
        {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .padding(paddingValues = it)
            ) {
                //searchbar
                SearchBar()
                //Spacer
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "A Proven \nFinancial Based System ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 16.dp, bottom = 20.dp)
                )
                //RecyclerView
                LazyRow(modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    item {
                        ItemsRow()
                    }
                    //iterate items within
                    items(taskList){task->
                        TaskItem(task)
                    }
                }
                //Flow Section
                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    //heading

                    Row(verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    Text(
                        text = "Action Plan",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Contents menu",
                            tint = Color.DarkGray)
                    }
                }
                //see all text
                Text(
                    text = "Overview",
                    color = Color.Gray,
                    fontSize = 16.sp)
              }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)){
                    itemsIndexed(taskFlows){index, item ->
                        FlowItem(item)

                        if (index<item.lastIndex){
                            Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(top = 16.dp))
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun FlowItem(item: String) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
            //left column
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start) {
                Text(
                    text = item.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()},
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Text(text = "4 mins ago", color = Color.DarkGray, fontFamily = FontFamily.Serif, fontSize = 12.sp)
            }
            //right sect
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier
                .clip(CircleShape)
                .background(color = colorResource(id = R.color.Yellowstone))) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "Add Button",Modifier.padding(7.dp),
                    tint = Color.Black)
            }
        }
    }

    @Composable
    fun TaskItem(task: String) {

        Box(modifier = Modifier
            .width(200.dp)
            .background(color = Color.Transparent)) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.Center) {
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center) {
                Text(modifier = Modifier.padding(8.dp),
                    text = "Loans",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
                }
                Text(text = task, fontSize = 12.sp, color = Color.DarkGray)
            }
        }
    }

    @Composable
fun ItemsRow() {
    Box(modifier = Modifier
        .width(180.dp)
        .padding(start = 15.dp)
        .drawBehind {
            drawRoundRect(
                color = Color.Gray,
                cornerRadius = CornerRadius(8.dp.toPx()),
                style = Stroke(
                    width = 2f,
                    pathEffect = PathEffect.dashPathEffect(
                        floatArrayOf(5f, 5f),
                        0f
                    )
                )
            )
        },
    contentAlignment = Alignment.Center) {

        Column(modifier = Modifier
            .padding(10.dp), verticalArrangement = Arrangement.Center) {
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.Yellowstone)
            ), modifier = Modifier.clip(RoundedCornerShape(15.dp))
            ) {
                Text(
                    text = "Savings",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Text(text = "Grow Your Interest",
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            color = Color.DarkGray
            )
        }
     }

}

    @Composable
    fun SearchBar() {
        val textstate = remember {
            mutableStateOf(TextFieldValue())
        }
        //TextField Composable
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(18.dp)),
            value = textstate.value, onValueChange = { textstate.value = it },
            //icon - Leading Icon
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search icon", tint = Color.Gray)
            },

            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.DarkGray,
                backgroundColor = Color.White,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(text = "Try Find something?", color = Color.Black, fontSize = 16.sp)
            })
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Marketing_UI_ComposeTheme {
            Contents()
        }
    }
}