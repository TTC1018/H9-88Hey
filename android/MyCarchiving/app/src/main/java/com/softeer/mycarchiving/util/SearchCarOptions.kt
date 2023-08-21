package com.softeer.mycarchiving.util

import com.softeer.mycarchiving.model.archiving.SearchOption
import com.softeer.mycarchiving.model.archiving.SearchOptionUiModel

object SearchCarOptions {
    private val ecoFriendlyCars = SearchOptionUiModel(
        category = "수소 / 전기차",
        options = listOf(
            SearchOption(name = "넥쏘"),
            SearchOption(name = "디 올 뉴 코나 Electric"),
            SearchOption(name = "아이오닉 6"),
            SearchOption(name = "포터 I Electric"),
            SearchOption(name = "포터 II Electric"),
            SearchOption(name = "포터 II Electric 특장차")
        )
    )

    private val sedanCars = SearchOptionUiModel(
        category = "승용차",
        options = listOf(
            SearchOption(name = "쏘나타 디 엣지"),
            SearchOption(name = "쏘나타 디 엣지 Hybrid"),
            SearchOption(name = "더 뉴 아반떼"),
            SearchOption(name = "더 뉴 아반떼 Hybrid"),
            SearchOption(name = "디 올 뉴 그랜저"),
            SearchOption(name = "디 올 뉴 그랜저 Hybrid")
        )
    )

    private val suvCars = SearchOptionUiModel(
        category = "SUV",
        options = listOf(
            SearchOption(name = "팰리세이드", isSelect = true),
            SearchOption(name = "베뉴"),
            SearchOption(name = "디 올 뉴 코나"),
            SearchOption(name = "디 올 뉴 코나 Hybrid"),
            SearchOption(name = "투싼"),
            SearchOption(name = "투싼 Hybrid")
        )
    )

    val searchAbleCars = listOf(ecoFriendlyCars, sedanCars, suvCars)
}