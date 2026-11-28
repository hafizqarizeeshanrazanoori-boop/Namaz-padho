package com.namazpadho.app

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onDone: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(2000)
        onDone()
    }
    Image(
        painter = painterResource(R.drawable.splash),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LoginScreen(lang: Int, onLang: (Int) -> Unit, onGo: (String, String) -> Unit) {
    var first by remember { mutableStateOf("") }
    var last by remember { mutableStateOf("") }
    var open by remember { mutableStateOf(false) }
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        focusedBorderColor = C.Gold,
        unfocusedBorderColor = C.Bright,
        cursorColor = C.Gold,
        focusedPlaceholderColor = Color(0xFFBBD9B0),
        unfocusedPlaceholderColor = Color(0xFFBBD9B0)
    )
    Box(
        Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(C.Deep, C.Green, C.Deep)))
    ) {
        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(48.dp))
            Text("NAMAZ", color = Color.White, fontSize = 52.sp, fontWeight = FontWeight.Bold)
            Text("PADHO", color = C.Gold, fontSize = 52.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(24.dp))
            Box(
                Modifier.clip(RoundedCornerShape(40.dp))
                    .border(2.dp, C.Gold, RoundedCornerShape(40.dp))
                    .padding(horizontal = 32.dp, vertical = 12.dp)
            ) {
                Text(L.t("assalamu", lang), color = Color.White, fontSize = 22.sp)
            }
            Spacer(Modifier.height(32.dp))
            OutlinedTextField(
                value = first,
                onValueChange = { first = it },
                singleLine = true,
                placeholder = { Text(L.t("first_name", lang)) },
                shape = RoundedCornerShape(40.dp),
                colors = fieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(14.dp))
            OutlinedTextField(
                value = last,
                onValueChange = { last = it },
                singleLine = true,
                placeholder = { Text(L.t("last_name", lang)) },
                shape = RoundedCornerShape(40.dp),
                colors = fieldColors,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(14.dp))
            Box(Modifier.fillMaxWidth()) {
                Row(
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(40.dp))
                        .border(1.dp, C.Bright, RoundedCornerShape(40.dp))
                        .clickable { open = true }
                        .padding(horizontal = 20.dp, vertical = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        L.languages[lang],
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text("v", color = Color.White, fontSize = 18.sp)
                }
                DropdownMenu(expanded = open, onDismissRequest = { open = false }) {
                    L.languages.forEachIndexed { i, name ->
                        DropdownMenuItem(
                            text = { Text(name) },
                            onClick = {
                                onLang(i)
                                open = false
                            }
                        )
                    }
                }
            }
            Spacer(Modifier.height(32.dp))
            Box(
                Modifier.align(Alignment.End)
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(C.Green)
                    .border(3.dp, C.Gold, CircleShape)
                    .clickable(enabled = first.isNotBlank()) { onGo(first.trim(), last.trim()) },
                contentAlignment = Alignment.Center
            ) {
                Text(">", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun PersonIcon() {
    Canvas(
        Modifier.size(36.dp).clip(CircleShape).border(1.5.dp, Color(0xFF9EE6B0), CircleShape)
    ) {
        drawCircle(
            Color.White,
            radius = size.minDimension * 0.17f,
            center = Offset(size.width / 2, size.height * 0.38f)
        )
        drawCircle(
            Color.White,
            radius = size.minDimension * 0.3f,
            center = Offset(size.width / 2, size.height * 0.95f)
        )
    }
}

@Composable
fun HomeScreen(lang: Int, first: String) {
    var tab by remember { mutableStateOf(0) }
    val keys = listOf("home", "calling_alarm", "omr_calendar", "islamic_calc", "nearby_masjid")
    val icons = listOf("🏠", "⏰", "📅", "🧮", "📍")
    val title = if (tab == 1) L.t("my_calling_alarm", lang) else L.t(keys[tab], lang)
    Column(
        Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(C.Deep, C.Green, C.Deep)))
    ) {
        Row(
            Modifier.fillMaxWidth()
                .padding(12.dp)
                .clip(RoundedCornerShape(40.dp))
                .border(2.dp, C.Bright, RoundedCornerShape(40.dp))
                .background(Color(0xFF021A08))
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (tab == 0) {
                PersonIcon()
            } else {
                Box(
                    Modifier.size(36.dp).clickable { tab = 0 },
                    contentAlignment = Alignment.Center
                ) {
                    Text("<", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            Text(
                if (tab == 0) "NAMAZ PADHO" else title,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            if (tab == 0) {
                Box(
                    Modifier.size(36.dp).clip(CircleShape).border(1.5.dp, C.Bright, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(":", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Spacer(Modifier.size(36.dp))
            }
        }
        Box(Modifier.weight(1f)) {
            if (tab == 0) HomeContent(lang, first) else PlaceholderPage(lang)
        }
        Row(
            Modifier.fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(36.dp))
                .border(2.dp, C.Bright, RoundedCornerShape(36.dp))
                .background(Color(0xFF021A08))
                .padding(vertical = 8.dp)
        ) {
            keys.forEachIndexed { i, k ->
                Column(
                    Modifier.weight(1f).clickable { tab = i }.padding(vertical = 6.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(icons[i], fontSize = 22.sp)
                    Text(
                        L.t(k, lang),
                        color = if (tab == i) C.Gold else Color.White,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Composable
fun HomeContent(lang: Int, first: String) {
    val date = remember { SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH).format(Date()) }
    val rows = listOf(
        Triple("fajr", "04:55 AM", "06:15 AM"),
        Triple("zohr", "12:02 PM", "03:45 PM"),
        Triple("asr", "03:45 PM", "05:52 PM"),
        Triple("maghrib", "05:52 PM", "07:10 PM"),
        Triple("isha", "07:10 PM", "04:00 AM")
    )
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier.fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(topStart = 90.dp, topEnd = 90.dp))
                .background(Brush.verticalGradient(listOf(Color(0xFF1B6B2A), C.Deep))),
            contentAlignment = Alignment.Center
        ) {
            Text("🕌", fontSize = 70.sp)
        }
        Spacer(Modifier.height(12.dp))
        Box(
            Modifier.clip(RoundedCornerShape(40.dp))
                .border(2.dp, C.Gold, RoundedCornerShape(40.dp))
                .padding(horizontal = 28.dp, vertical = 10.dp)
        ) {
            val hello = L.t("assalamu", lang) + if (first.isNotBlank()) ", " + first else ""
            Text(hello, color = Color.White, fontSize = 18.sp, textAlign = TextAlign.Center)
        }
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(30.dp))
                .border(2.dp, C.Bright, RoundedCornerShape(30.dp))
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                L.t("today", lang) + ", " + date,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Text("New Delhi", color = Color.White, fontSize = 16.sp)
        }
        Spacer(Modifier.height(12.dp))
        Text(
            L.t("namaz_timetable", lang),
            color = C.Gold,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        Column(
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .border(2.dp, C.Bright, RoundedCornerShape(24.dp))
                .background(Color(0xFF03260E))
                .padding(12.dp)
        ) {
            Row(Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                Text(
                    L.t("name", lang), color = C.Gold, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1.2f), textAlign = TextAlign.Center
                )
                Text(
                    L.t("start", lang), color = C.Gold, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f), textAlign = TextAlign.Center
                )
                Text(
                    L.t("end", lang), color = C.Gold, fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f), textAlign = TextAlign.Center
                )
            }
            rows.forEach { (k, s, e) ->
                Row(
                    Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        L.t(k, lang), color = Color.White, fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1.2f), textAlign = TextAlign.Center
                    )
                    Text(
                        s, color = Color.White, fontSize = 17.sp,
                        modifier = Modifier.weight(1f), textAlign = TextAlign.Center
                    )
                    Text(
                        e, color = C.Gold, fontSize = 17.sp,
                        modifier = Modifier.weight(1f), textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun PlaceholderPage(lang: Int) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            L.t("coming_soon", lang),
            color = C.Gold,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
