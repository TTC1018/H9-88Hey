package com.softeer.mycarchiving.ui.component


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.medium16
import com.softeer.mycarchiving.ui.theme.regular12
import com.softeer.mycarchiving.util.toPriceString


@Composable
fun SelectedOptionThumbnail(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    GlideImage(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp)),
        imageModel = { imageUrl },
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


// TODO 슬래시를 기준으로 글자가 구분되어야 할듯
@Composable
fun SelectedOptionDescription(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        style = regular12,
        text = text
    )
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
    SelectedOptionDescription(text = "후석 승객 알림 / 메탈 리어범퍼스텝 / 메탈 도어스커프 / 3열 파워폴딩시트 / 3열 열선시트 / 헤드업 디스플레이")
}

@Preview(widthDp = 68, heightDp = 82)
@Composable
fun PreviewSelectedOptionThumbnail() {
    SelectedOptionThumbnail(
        imageUrl = null
    )
}