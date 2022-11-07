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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicLayoutComposeCodelabTheme {}
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
        modifier: Modifier = Modifier,
        alignYourBodyData: List<AlignYourBodyModel>
    ) {
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        ) {
            items(alignYourBodyData) { item ->
                AlignYourBodyElement(Modifier, item.imageId, item.text)
            }
        }
    }

    @Composable
    fun FavoriteCollectionsGrid(
        modifier: Modifier = Modifier,
        favoriteCollectionData: List<FavoriteCollectionCardModel>
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
}

