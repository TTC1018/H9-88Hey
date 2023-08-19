package com.softeer.mycarchiving.navigation

import com.softeer.mycarchiving.R

enum class MainDestination(val route: String, val titleTextId: Int) {
    LOGIN("login_route", R.string.top_bar_login),
    ARCHIVING("archive_route", R.string.top_bar_archiving),
    MY_ARCHIVING("mycar_route", R.string.top_bar_mycar),
    MAKING_CAR("makecar_route", R.string.top_bar_making_car),
    DRIVER_COMMENT("driver_route", R.string.top_bar_driver_comment),
    CONSUMER_COMMENT("consumer_route", R.string.top_bar_consumer_comment)
}

enum class MakingCarDestinations(val route: String) {
    SELECT_MODEL("select_model"),
    SELECT_TRIM ("select_trim"),
    SELECT_COLOR( "select_color"),
    SELECT_OPTION( "select_option"),
    SELECT_COMPLETE("select_complete")
}

enum class ArchivingDestinations(val route: String, val needBottomBar: Boolean) {
    ARCHIVING_MAIN("archiving_main", false),
    ARCHIVING_DETAIL("archiving_detail", true)
}

enum class MyArchiveDestinations(val route: String) {
    MY_ARCHIVE_MADE("my_archive_made"),
    MY_ARCHIVE_SAVE("my_archive_save"),
    MY_ARCHIVE_DETAIL("my_archive_detail"),
}