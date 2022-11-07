package com.example.basiclayoutcomposecodelab.repository

import com.example.basiclayoutcomposecodelab.R
import com.example.basiclayoutcomposecodelab.models.AlignYourBodyModel

class AlignYourBodyElementRepository {
    fun getAllAlignYourBodyElements(): List<AlignYourBodyModel> {
        return listOf(
            AlignYourBodyModel(
                R.drawable.ab1_inversions,
                R.string.inversions
            ),
            AlignYourBodyModel(
                R.drawable.ab2_quick_yoga,
                R.string.quick_yoga
            ),
            AlignYourBodyModel(
                R.drawable.ab3_stretching,
                R.string.stretching
            ),
            AlignYourBodyModel(
                R.drawable.ab4_tabata,
                R.string.tabata
            ),
            AlignYourBodyModel(
                R.drawable.ab5_hiit,
                R.string.hiit
            ),
            AlignYourBodyModel(
                R.drawable.ab6_pre_natal_yoga,
                R.string.pre_natal_yoga
            )
        )
    }
}