package com.sahu.carousel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.sahu.carousel.ui.theme.CarouselTheme
import com.sahu.foundation.horizontalScroll
import com.sahu.foundation.rememberCarouselScrollState
import com.sahu.foundation.verticalScroll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarouselTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    var state by remember { mutableStateOf(0) }
                    val titles = listOf("Horizontal Scroll", "Horizontal Lazy", "Vertical Scroll", "Vertical Lazy")
                    Column {
                        ScrollableTabRow(selectedTabIndex = state) {
                            titles.forEachIndexed { index, title ->
                                Tab(selected = state == index,
                                    onClick = { state = index }) {
                                    Text(title, modifier = Modifier.padding(16.dp))
                                }
                            }
                        }
                        when (state) {
                            0 -> HorizontalScrollStateExample(Modifier.fillMaxHeight())
                            1 -> HorizontalLazyStateExample(Modifier.fillMaxHeight())
                            2 -> VerticalScrollStateExample(Modifier.fillMaxHeight())
                            3 -> VerticalLazyStateExample(Modifier.fillMaxHeight())
                        }
                    }
                }
            }
        }
    }
}

@Preview("Horizontal scroll Carousel with Modifier.horizontalScroll", showBackground = true)
@Composable
fun HorizontalScrollStateExample(
    @PreviewParameter(PreviewModifier::class)
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Using Scroll State", style = MaterialTheme.typography.h5)
        val scrollState = rememberCarouselScrollState()
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState),
        ) {
            repeat(20) {
                Box(
                    modifier = Modifier.size(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Item $it")
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "←←←")
            Carousel(
                state = scrollState,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            )
            Text(text = "→→→")
        }
    }
}

@Preview("Horizontal scroll Carousel with LazyList", showBackground = true)
@Composable
fun HorizontalLazyStateExample(
    @PreviewParameter(PreviewModifier::class)
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Using Lazy List State", style = MaterialTheme.typography.h5)
        val scrollState = rememberLazyListState()
        LazyRow(
            state = scrollState,
        ) {
            repeat(20) {
                item {
                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Item $it")
                    }
                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "←←←")
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Carousel(state = scrollState)
            }
            Text(text = "→→→")
        }
    }
}

@Preview("Vertical scroll Carousel with Modifier.verticalScroll", showBackground = true)
@Composable
fun VerticalScrollStateExample(
    @PreviewParameter(PreviewModifier::class)
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Using Scroll State", style = MaterialTheme.typography.h5)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val scrollState = rememberCarouselScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(20) {
                    Box(
                        modifier = Modifier.size(100.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Item $it")
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "↑\n↑↑\n↑↑↑", textAlign = TextAlign.Center)
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Carousel(
                        state = scrollState,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(4.dp, 120.dp)
                    )
                }
                Text(text = "↓↓↓\n↓↓\n↓", textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview("Vertical scroll Carousel with LazyList", showBackground = true)
@Composable
fun VerticalLazyStateExample(
    @PreviewParameter(PreviewModifier::class)
    modifier: Modifier,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(16.dp)
    ) {
        Text(text = "Using Lazy List State", style = MaterialTheme.typography.h5)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val scrollState = rememberLazyListState()
            LazyColumn(
                state = scrollState,
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                repeat(20) {
                    item {
                        Box(
                            modifier = Modifier.size(100.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Item $it")
                        }
                    }
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "↑\n↑↑\n↑↑↑", textAlign = TextAlign.Center)
                Carousel(
                    state = scrollState,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(4.dp, 120.dp)
                        .weight(1f)
                )
                Text(text = "↓↓↓\n↓↓\n↓", textAlign = TextAlign.Center)
            }
        }
    }
}