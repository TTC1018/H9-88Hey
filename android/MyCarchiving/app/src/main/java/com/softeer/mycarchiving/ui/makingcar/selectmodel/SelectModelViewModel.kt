package com.softeer.mycarchiving.ui.makingcar.selectmodel

import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.makingcar.ModelFeatureUiModel
import com.softeer.mycarchiving.model.makingcar.SelectModelUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SelectModelViewModel @Inject constructor() : ViewModel() {

    private val _carImageUrls = MutableStateFlow(
        listOf(
            "https://s3-alpha-sig.figma.com/img/b1e4/4ff8/4a67e0e269d23eba81065f6ff874bf7f?Expires=1692576000&Signature=NCyMGxlqN5e6aNUjeWymOG5SmQx~cXD9IpXbj7QdamgBzKRkjaXs5RPKmO0a5meBTZhTnqa6qsHnHoGxyLoF77y3e-OqiYSR5W1Y-xH1mRKOodw3EZQZRziN4L8kpD~7eJcaXXOzNE2fLxa1ldfb4T8wYTLcJgwxp~yFiRwO3F5Q0E7iGWr19Y9yvktJDD6c2l90q~FIodMozknCUeID~o6S7OpFZGLxhjupkuXTzJaSeUBkecHyDOTKmIS3TarpuYqeA6XkUdHN2u0IE2hirXGR1viOC~oQmGKG6ot6Ftz-MxjE1fCX7fqjyhey42TdUUHv4n0sgIXgxlyYfYRm8Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            "https://s3-alpha-sig.figma.com/img/8393/0344/a45c56d116498f699d90049279310d50?Expires=1692576000&Signature=bz2eyfq~SFxnY~qyFccPifFVpuERyaS3aFV00a-5-Ft6CV7ksMY48q1Xy8UjvbDbE950sUtmt~cvZqxY2E9IEyTEOZAevw05TcJRnZp20snXxw8KYNWOlHlFpsgWE-3tKJHu6Xk2vynfUMCZqbUsQNh-RCl5anxbxz7z-Q52uoF3IbwCHQczQ5-DvBg5lVYUjzr5NzCbgVEuiDe57gncTCqPbdcTACMkF0nU4eyUeyv7GMSSiHrQsf88XqgDU7UdMMkmBYABqU-I3PZv57l-~2Ufj0WfFye~B2787NEF~BkTZD8wXsVswpVf6OcAJgHmCkmpvGKNG2wUPPvWuY~8Wg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            "https://s3-alpha-sig.figma.com/img/a51b/d824/fe75154fd970d0f8f7d1ab551b7db0fd?Expires=1692576000&Signature=TBrCPtaiBAML1BU-YokJVxnMbw7KGed3-oZLNZAXGeYoiO1hGlABFzPGwI3cMff05fRit1NKZcGk8KszvDlzw8g20i0vt2rxbRI92hoVBBA-4sBq33BHg2F4NB~RaGHaOpXgL50pkWWqCpBx-VFuvlnl1IM4nwk9KOwyiSjdxHojOpWj0e8pBhQS68fF35sGp057xRWSbBUWqQcoQGTkMDnP~A63uxmST7aBWlKXvoJgUT7uB-hhjyCfUIDahhpwZi2~JiTr-fk5zO9cWlq7UWFfrkeCaNvtOei4tXXr9C3qkx6JJtHGEdvYSanu-6Ulk9cdkWLNU3OJXuTI3l1Txw__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
            "https://s3-alpha-sig.figma.com/img/75ef/6abc/22a9a8adca019b55c0a6d16fe5b7b816?Expires=1692576000&Signature=ZNZl3GVIk0fVcJQ8A1CRxXsp1CrdGtPLIpyDKrFRBEX4kSFUCB1QdIxT8F~sHgN0NFkgS49iusUN2Lg90c6UGRQ8o2T48IuvAGwBPnjPdsOM7QZFvkmJfOXvKb4JsczXoJPSLvpdWwYah0GSJxus2Wl8WKnvuCHLDvFeRKN9jw5R7nJ7rhs0MpiNLvLcJdioJv8O5ObnA9feTbVWnZOhf3NZ5fcvdl8XoNIIPjicNXN-xJJlteI8t4ObQx3IMW5sSO7YnADg11rnMCcJxdKktIj6sRTbw1Vb1haOqmNtlHh7XITToodqTZp8GA4jZLAn5ASb3m~FqD-P63nXSUFFoA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        )
    )
    val carImageUrls: StateFlow<List<String>> = _carImageUrls
    val focusedImageIndex = MutableStateFlow(0)

    // 예시 리스트
    private val modelFeatures = listOf(
        ModelFeatureUiModel(
            name = "20인치\n알로이 휠",
            imageUrl = ""
        ),
        ModelFeatureUiModel(
            name = "서라운드 뷰\n모니터",
            imageUrl = ""
        ),
        ModelFeatureUiModel(
            name = "클러스터\n(12.3인치 컬러 LCD)",
            imageUrl = ""
        )
    )

    private val _carModels = MutableStateFlow(
        listOf(
            SelectModelUiModel(
                name = "Le Blanc",
                price = 47720000,
                features = modelFeatures
            ),
            SelectModelUiModel(
                name = "Exclusive",
                price = 40440000,
                features = modelFeatures
            ),
            SelectModelUiModel(
                name = "Prestige",
                price = 47720000,
                features = modelFeatures
            ),
            SelectModelUiModel(
                name = "Calligraphy",
                price = 47720000,
                features = modelFeatures
            )
        )
    )
    val carModels: StateFlow<List<SelectModelUiModel>> = _carModels

    fun onCarImageClick(index: Int) {
        focusedImageIndex.value = index
    }
}