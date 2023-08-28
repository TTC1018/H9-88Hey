package com.softeer.mycarchiving.enums

import com.softeer.mycarchiving.R

enum class ArchiveSearchPage(val titleTextId: Int, val needBack: Boolean) {
    SEARCH_CONDITION(titleTextId = R.string.archive_search_condition, needBack = false),
    SET_CAR(titleTextId = R.string.archive_search_set_car, needBack = true),
    SET_OPTION(titleTextId = R.string.archive_search_set_option, needBack = true),
}