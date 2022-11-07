package com.example.basiclayoutcomposecodelab.repository

import com.example.basiclayoutcomposecodelab.R
import com.example.basiclayoutcomposecodelab.models.FavoriteCollectionCardModel

class FavoriteCollectionCardRepository {
    fun getAllFavoriteCollectionCards(): List<FavoriteCollectionCardModel> {
        return listOf(
            FavoriteCollectionCardModel(
                R.drawable.fc1_short_mantras,
                R.string.short_mantras
            ),
            FavoriteCollectionCardModel(
                R.drawable.fc2_nature_meditations,
                R.string.nature_meditations
            ),
            FavoriteCollectionCardModel(
                R.drawable.fc3_stress_and_anxiety,
                R.string.stress_and_anxiety
            ),
            FavoriteCollectionCardModel(
                R.drawable.fc4_self_massage,
                R.string.self_message,
            ),
            FavoriteCollectionCardModel(
                R.drawable.fc5_overwhelmed,
                R.string.overwhelmed
            ),
            FavoriteCollectionCardModel(
                R.drawable.fc6_nightly_wind_down,
                R.string.nightly_wind_down
            ),
        )
    }
}