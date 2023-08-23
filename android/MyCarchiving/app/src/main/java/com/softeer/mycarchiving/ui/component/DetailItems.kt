package com.softeer.mycarchiving.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.theme.DarkGray
import com.softeer.mycarchiving.ui.theme.HyundaiLightSand
import com.softeer.mycarchiving.ui.theme.LightGray
import com.softeer.mycarchiving.ui.theme.PrimaryBlue
import com.softeer.mycarchiving.ui.theme.White
import com.softeer.mycarchiving.ui.theme.medium14
import com.softeer.mycarchiving.ui.theme.medium16
import com.softeer.mycarchiving.ui.theme.medium18
import com.softeer.mycarchiving.ui.theme.medium20
import com.softeer.mycarchiving.ui.theme.regular14
import com.softeer.mycarchiving.ui.theme.roundCorner
import com.softeer.mycarchiving.util.toPriceString

@Composable
fun DetailTextLabel(
    text: String
) {
    Text(text = text, style = medium14)
}

@Composable
fun DetailBanner(
    modifier: Modifier = Modifier,
    carImageUrl: String,
    model: String,
    trim: String,
    trimOptions: String,
    price: Int,
    exteriorColor: String,
    exteriorColorUrl: String,
    interiorColor: String,
    interiorColorUrl: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(374.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.bg_detail_car),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                model = "${carImageUrl}001.png",
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 27.dp, vertical = 6.dp)
            ) {
                Text(text = "$model $trim", style = medium18)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = trimOptions, style = regular14)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.price_won, price.toPriceString()),
                    style = medium14,
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(12.dp))
                Divider(thickness = 1.dp, color = DarkGray)
                Spacer(modifier = Modifier.height(20.dp))
                DetailColorInfoRow(
                    title = stringResource(id = R.string.summary_outer),
                    colorUrl = exteriorColorUrl,
                    colorName = exteriorColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                DetailColorInfoRow(
                    title = stringResource(id = R.string.summary_inner),
                    colorUrl = interiorColorUrl,
                    colorName = interiorColor
                )
            }
        }
    }
}

@Composable
fun DetailColorInfoRow(
    modifier: Modifier = Modifier,
    title: String,
    colorUrl: String,
    colorName: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, style = medium14)
        Spacer(modifier = Modifier.width(12.dp))
        AsyncImage(
            modifier = Modifier
                .width(16.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
            model = colorUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = colorName, style = regular14)
    }
}

@Composable
fun DetailReview(
    modifier: Modifier = Modifier,
    review: String?
) {
    review?.let {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .background(color = HyundaiLightSand, shape = roundCorner)
                .padding(horizontal = 17.dp, vertical = 12.dp),
            text = it,
            style = regular14,
            color = DarkGray
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailSelectedOption(
    modifier: Modifier = Modifier,
    optionImageUrl: String,
    isPackage: Boolean = false,
    optionNum: Int,
    optionName: String,
    subOptions: List<String>? = null,
    optionReview: String? = null,
    optionTags: List<String>? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(121.dp)
                    .clip(roundCorner),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(optionImageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SelectFillNumberCircle(
                    numberText = optionNum.toString().padStart(2, '0'),
                    isFill = isPackage
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(text = optionName, style = medium20)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Divider(thickness = 1.dp, color = LightGray)
            subOptions?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it.joinToString(separator = " | "),
                    style = medium16,
                    color = PrimaryBlue
                )
            }
            optionReview?.let {
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = it, style = regular14)
            }
            optionTags?.let { tags ->
                Spacer(modifier = Modifier.height(7.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    tags.forEach { tag ->
                        OptionTagChip(tagString = tag)
                    }
                }
            }
        }
        Divider(thickness = 6.dp, color = HyundaiLightSand)
    }
}

@Preview
@Composable
fun PreviewDetailBanner() {
    DetailBanner(
        model = "펠리세이드",
        trim = "Le Blanc",
        trimOptions = "디젤 2.2 / 4WD / 7인승",
        price = 47340000,
        exteriorColor = "어비스 블랙펄",
        exteriorColorUrl = "",
        interiorColor = "퀼팅 천연(블랙)",
        interiorColorUrl = "",
        carImageUrl = ""
    )
}

@Preview
@Composable
fun PreviewDetailReview() {
    DetailReview(review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.")
}

@Preview
@Composable
fun PreviewDetailSelectedOption() {
    DetailSelectedOption(
        optionImageUrl = "https://s3-alpha-sig.figma.com/img/39ad/898d/ab83c16d1ce02c7dde44ec1f2bc64e37?Expires=1693180800&Signature=SyPWLI-OjiYp4xloomMowu5aw1zS-0BEOOvfzwSJ1SCZDHW2A7FGH2urgegUKN12BdQnBU8L41mjALeuw2Mh9~pVkbsepy4O6tpLVpU60cEYf6xuHbXcIexv4-mGoA6OJ-03-n7crmArzLZZc6UcpeOKG3MzhfZjousGXnjMOus6rQvrI4OcjwMLJzbDLI0G-nlG7Sam9Rcsq78gkV5RaxPk25uUdFiixvU8Uvak8idriypar~8ICqsUb-r~IC07CbS2NLcsvn49mflf6~gZVlbJG1aDVmciHk8vOZz2Oc435luEYN7WFrcUmYYkSZxpjxSMXv7oamshyx4AV6CFog__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
        optionNum = 1,
        optionName = "컴포트 Ⅱ",
        subOptions = listOf("전방 충돌방지 보조", "내비게이션 기반 스마트 크루즈 컨트롤", "고속도로 주행보조 2"),
        optionReview = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다.",
        optionTags = listOf("어린이\uD83D\uDC76", "편리해요\uD83D\uDE09", "이것만 있으면 나도 주차고수\uD83D\uDE98")
    )
}