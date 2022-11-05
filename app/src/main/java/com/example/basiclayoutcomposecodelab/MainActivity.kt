package com.example.basiclayoutcomposecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.example.basiclayoutcomposecodelab.ui.theme.BasicLayoutComposeCodelabTheme

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
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
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
                text = stringResource(text),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.paddingFromBaseline(
                    top = 24.dp, bottom = 8.dp
                )
            )
        }
    }

    @Composable
    fun FavoriteCollectionCard(
        @DrawableRes drawable: Int,
        @StringRes string: Int,
        modifier: Modifier = Modifier
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = modifier
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.width(192.dp)
            ) {
                Image(
                    painter = painterResource(drawable),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(56.dp)
                )
                Text(
                    text = stringResource(string),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
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
        AlignYourBodyElement(Modifier, R.drawable.ab1_inversions, R.string.inversions)
    }

    @Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
    @Composable
    fun PreviewFavoriteCollectionCard() {
        FavoriteCollectionCard(
            R.drawable.fc2_nature_meditations,
            R.string.nature_meditations,
            Modifier.padding(8.dp)
        )
    }
}

