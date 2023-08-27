package com.softeer.mycarchiving.ui.myarchive

import android.os.Bundle
import androidx.navigation.NavType
import com.softeer.mycarchiving.model.myarchive.ArchiveFeedUiModel
import kotlinx.serialization.json.Json

class TempCarType: NavType<ArchiveFeedUiModel>(isNullableAllowed = true) {

    override fun get(bundle: Bundle, key: String): ArchiveFeedUiModel? {
        return bundle.getSerializable(key) as ArchiveFeedUiModel?
    }

    override fun parseValue(value: String): ArchiveFeedUiModel {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: ArchiveFeedUiModel) {
        bundle.putSerializable(key, value)
    }
}