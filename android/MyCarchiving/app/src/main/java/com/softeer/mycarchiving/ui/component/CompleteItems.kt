package com.softeer.mycarchiving.ui.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.data.CarColorType
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.model.makingcar.ColorOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.HyundaiSand
import com.softeer.mycarchiving.ui.theme.LightGray
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.medium16
import com.softeer.mycarchiving.ui.theme.medium18
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.util.toPriceString


@Composable
fun SelectedOptionThumbnail(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    GlideImage(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
            .aspectRatio(4f / 5f)
            .clip(RoundedCornerShape(8.dp)),
        imageModel = { imageUrl },
        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
        previewPlaceholder = R.drawable.ic_launcher_background
    )
}

@Composable
fun SelectedOptionHeadText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        style = medium16,
        text = text
    )
}

@Composable
fun SelectedOptionPriceText(
    modifier: Modifier = Modifier,
    price: Int,
) {
    Text(
        modifier = modifier,
        style = medium16,
        text = price.toPriceString() + stringResource(id = R.string.make_car_price_tail),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun CompleteOptionPriceText(
    modifier: Modifier = Modifier,
    price: Int,
) {
    Text(
        modifier = modifier,
        style = regular14,
        text = stringResource(id = R.string.price_won, price.toPriceString()),
        textAlign = TextAlign.End
    )
}

// TODO 슬래시를 기준으로 글자가 구분되어야 할듯
@Composable
fun SelectedOptionSubs(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        style = regular12,
        text = text,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun SelectedOptionInfo(
    modifier: Modifier = Modifier,
    optionInfo: SelectOptionUiModel,
    thumbnailUrl: String?
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(max = 120.dp)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SelectedOptionThumbnail(imageUrl = thumbnailUrl)
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    SelectedOptionHeadText(text = optionInfo.name)
                    SelectedOptionPriceText(price = optionInfo.price)
                }
                SelectedOptionSubs(text = optionInfo.subOptions?.joinToString(" / ") { it.name } ?: "")
            }
        }
        OptionInfoDivider(thickness = 1.dp, color = LightGray)
    }
}

@Composable
fun CompleteCarTitleText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        style = medium18,
    )
}

@Composable
fun CompleteCarCard(
    modifier: Modifier = Modifier,
    carName: String,
    modelName: String,
    options: List<String>,
    colors: List<ColorOptionUiModel>,
    price: Int,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawRoundRect(
                    color = HyundaiLightSand,
                    cornerRadius = CornerRadius(32f)
                )
            }
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CompleteCarTitleText(text = "$carName $modelName")
        OptionRegular14Text(optionName = options.joinToString(" / "))
        CompleteOptionPriceText(modifier = Modifier.fillMaxWidth(), price = price)
        OptionInfoDivider(thickness = 1.dp, color = HyundaiSand)
        colors.forEach {
            CompleteColorInfoRow(
                category = it.category.type,
                imageUrl = it.imageUrl,
                colorName = it.optionName
            )
        }
    }
}

@Composable
fun CompleteColorInfoRow(
    modifier: Modifier = Modifier,
    category: String,
    imageUrl: String,
    colorName: String,
) {
    Row(
        modifier = modifier
            .heightIn(max = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(style = medium14, text = category)
        GlideImage(
            modifier = Modifier
                .height(16.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
            imageModel = { imageUrl },
            previewPlaceholder = R.drawable.ic_launcher_background,
        )
        Text(style = regular14, text = colorName)
    }
}

@Composable
fun CompleteBanner(
    modifier: Modifier = Modifier,
    carName: String,
    imageUrl: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        CompleteBannerBackground()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OptionHeadText(
                optionName = stringResource(
                    id = R.string.make_car_done_announce,
                    carName
                )
            )
            OptionRegular10Text(text = stringResource(id = R.string.make_car_done_description))
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
fun CompleteBannerBackground(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.8f)
            .padding(horizontal = 16.dp)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg_complete_car),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun PreviewSelectedOptionHeadText() {
    SelectedOptionHeadText(text = "컴포트 II")
}

@Preview
@Composable
fun PreviewSelectedOptionPriceText() {
    SelectedOptionPriceText(price = 1090000)
}

@Preview(widthDp = 250, heightDp = 50)
@Composable
fun PreviewSelectedOptionDescription() {
    SelectedOptionSubs(text = "후석 승객 알림 / 메탈 리어범퍼스텝 / 메탈 도어스커프 / 3열 파워폴딩시트 / 3열 열선시트 / 헤드업 디스플레이")
}

@Preview(widthDp = 68, heightDp = 82)
@Composable
fun PreviewSelectedOptionThumbnail() {
    SelectedOptionThumbnail(
        imageUrl = null
    )
}

@Preview
@Composable
fun PreviewSelectedOptionInfo() {
    SelectedOptionInfo(
        optionInfo = SelectOptionUiModel(
            id = "",
            name = "컴포트 2",
            price = 10900000,
            imageUrl = "",
            tags = listOf(
                "어린이🧒",
                "안전사고 예방🚨",
                "대형견도 문제 없어요🐶",
                "가족들도 좋은 옵션👨‍👩‍👧‍👦"
            ),
            subOptions = listOf(
                SubSelectOptionUiModel(
                    name = "후석 승객 알림",
                    imageUrl = "",
                    description = "초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다."
                ),
                SubSelectOptionUiModel(
                    name = "메탈 리어범퍼스텝",
                    imageUrl = "",
                    description = "러기지 룸 앞쪽 하단부를 메탈로 만들어 물건을 싣고 내릴 때나 사람이 올라갈 때 차체를 보호해줍니다."
                )
            )
        ),
        thumbnailUrl = ""
    )
}

@Preview
@Composable
fun PreviewCompleteCarTitleText() {
    CompleteCarTitleText(text = "팰리세이드 Le Blanc(르블랑)")
}

@Preview
@Composable
fun PreviewCompleteColorInfoRow() {
    CompleteColorInfoRow(
        category = "외장",
        imageUrl = "",
        colorName = "문라이트 블루펄"
    )
}

@Preview(widthDp = 409, heightDp = 251)
@Composable
fun PreviewCompleteBanner() {
    CompleteBanner(
        carName = "팰리세이드",
        imageUrl = ""
    )
}

@Preview
@Composable
fun PreviewCompleteCarCard() {
    CompleteCarCard(
        carName = "팰리세이드",
        modelName = "Le Blanc(르블랑)",
        options = listOf("디젤 2.2", "4WD", "7인승"),
        price = 47340000,
        colors = listOf(
            ColorOptionUiModel(
                id = 0,
                category = CarColorType.EXTERIOR,
                optionName = "문라이트 블루펄",
                imageUrl = "",
                price = 0,
                matchingColors = emptyList(),
                tags = emptyList()
            ),
            ColorOptionUiModel(
                id = 1,
                category = CarColorType.INTERIOR,
                optionName = "퀼팅 천연(블랙)",
                imageUrl = "",
                price = 0,
                matchingColors = emptyList(),
                tags = emptyList()
            )
        )
    )
}