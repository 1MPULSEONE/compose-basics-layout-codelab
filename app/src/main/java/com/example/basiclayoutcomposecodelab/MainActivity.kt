package com.example.basiclayoutcomposecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiclayoutcomposecodelab.models.AlignYourBodyModel
import com.example.basiclayoutcomposecodelab.models.FavoriteCollectionCardModel
import com.example.basiclayoutcomposecodelab.repository.AlignYourBodyElementRepository
import com.example.basiclayoutcomposecodelab.repository.FavoriteCollectionCardRepository
import com.example.basiclayoutcomposecodelab.ui.theme.BasicLayoutComposeCodelabTheme
import com.example.basiclayoutcomposecodelab.ui.theme.rust600
import com.example.basiclayoutcomposecodelab.ui.theme.taupe100

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutComposeCodelabTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    color = taupe100
                ) {
                    HomeScreen()
                }
            }
        }
    }

    @Composable
    fun SearchBar(modifier: Modifier) {
        TextField(
            value = "",
            onValueChange = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
            ),
            placeholder = {
                Text(text = stringResource(R.string.search))
            },
            modifier = modifier
                .heightIn(56.dp)
                .fillMaxWidth(),
        )
    }

    @Composable
    fun AlignYourBodyElement(
        modifier: Modifier = Modifier,
        @DrawableRes drawable: Int,
        @StringRes text: Int,
    ) {
        Column(
            modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(text), modifier = Modifier.paddingFromBaseline(
                    top = 24.dp, bottom = 8.dp
                ), color = rust600
            )
        }
    }

    @Composable
    fun FavoriteCollectionCard(
        @DrawableRes drawable: Int, @StringRes string: Int, modifier: Modifier = Modifier
    ) {
        Surface(
            shape = MaterialTheme.shapes.small, modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(192.dp)
            ) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(56.dp)
                )
                Text(
                    text = stringResource(string), modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }

    @Composable
    fun AlignYourBodyRow(
        modifier: Modifier = Modifier, alignYourBodyData: List<AlignYourBodyModel>
    ) {
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
        ) {
            items(alignYourBodyData) { item ->
                AlignYourBodyElement(Modifier, item.imageId, item.text)
            }
        }
    }

    @Composable
    fun FavoriteCollectionsGrid(
        modifier: Modifier = Modifier, favoriteCollectionData: List<FavoriteCollectionCardModel>
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.height(120.dp)
        ) {
            items(favoriteCollectionData) { item ->
                FavoriteCollectionCard(item.imageId, item.text)
            }
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewSearchBar() {
        SearchBar(modifier = Modifier)
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewAlignYourBodyElement() {
        BasicLayoutComposeCodelabTheme {
            AlignYourBodyElement(
                modifier = Modifier.padding(8.dp),
                text = R.string.inversions,
                drawable = R.drawable.ab1_inversions,
            )
        }
    }

    @Composable
    fun HomeSection(
        @StringRes titleId: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
    ) {
        Column(modifier) {
            Text(
                stringResource(titleId).uppercase(),
                style = MaterialTheme.typography.h6,
                color = rust600,
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                    .padding(horizontal = 16.dp)
            )
            content()
        }
    }

    @Composable
    fun HomeScreen(modifier: Modifier = Modifier) {
        Column(
            modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
        ) {
            SearchBar(Modifier.padding(horizontal = 16.dp))
            HomeSection(R.string.align_your_body) {
                AlignYourBodyRow(
                    Modifier, AlignYourBodyElementRepository().getAllAlignYourBodyElements()
                )
            }
            HomeSection(R.string.favorite_collections) {
                FavoriteCollectionsGrid(
                    Modifier, FavoriteCollectionCardRepository().getAllFavoriteCollectionCards()
                )
            }
            Spacer(Modifier.height(16.dp))
        }
    }

    @Composable
    private fun BLCCBottomNavigation(modifier: Modifier = Modifier) {
        BottomNavigation(
            modifier,
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.home).uppercase())
                },
                selected = true,
                onClick = {}
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text(stringResource(R.string.profile).uppercase())
                },
                selected = false,
                onClick = {}
            )
        }
    }


    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewFavoriteCollectionCard() {
        FavoriteCollectionCard(
            R.drawable.fc2_nature_meditations, R.string.nature_meditations, Modifier.padding(8.dp)
        )
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewAlignYourBodyRow() {
        AlignYourBodyRow(Modifier, AlignYourBodyElementRepository().getAllAlignYourBodyElements())
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewFavoriteCollectionsGrid() {
        FavoriteCollectionsGrid(
            Modifier,
            FavoriteCollectionCardRepository().getAllFavoriteCollectionCards(),
        )
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewHomeSection() {
        HomeSection(R.string.align_your_body) {
            BasicLayoutComposeCodelabTheme {
                AlignYourBodyRow(
                    Modifier, AlignYourBodyElementRepository().getAllAlignYourBodyElements()
                )
            }
        }
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewHomeScreen() {
        HomeScreen()
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewBLCCBottomNavigation() {
        BasicLayoutComposeCodelabTheme {
            BLCCBottomNavigation(Modifier.padding(top = 24.dp))
        }
    }
}




