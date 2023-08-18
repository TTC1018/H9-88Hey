package com.softeer.mycarchiving.ui.archiving.archivingdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.softeer.mycarchiving.R
import com.softeer.mycarchiving.ui.component.DetailBanner
import com.softeer.mycarchiving.ui.component.DetailReview
import com.softeer.mycarchiving.ui.component.DetailSelectedOption
import com.softeer.mycarchiving.ui.component.DetailTextLabel
import com.softeer.mycarchiving.ui.theme.White

@Composable
fun ArchiveDetailRoute(
    modifier: Modifier = Modifier
) {
    ArchiveDetailScreen(modifier = modifier)
}

@Composable
fun ArchiveDetailScreen(
    modifier: Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = White)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(27.dp))
            DetailTextLabel(text = stringResource(id = R.string.archive_summary_car_info))
            DetailBanner()
            Spacer(modifier = Modifier.height(23.dp))
            DetailTextLabel(text = stringResource(id = R.string.archive_general_review))
            Spacer(modifier = Modifier.height(16.dp))
            DetailReview(review = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.")
            Spacer(modifier = Modifier.height(23.dp))
            DetailTextLabel(text = stringResource(id = R.string.selected_option))
        }
        (0..4).forEach {
            DetailSelectedOption(
                optionImageUrl = "https://s3-alpha-sig.figma.com/img/39ad/898d/ab83c16d1ce02c7dde44ec1f2bc64e37?Expires=1693180800&Signature=SyPWLI-OjiYp4xloomMowu5aw1zS-0BEOOvfzwSJ1SCZDHW2A7FGH2urgegUKN12BdQnBU8L41mjALeuw2Mh9~pVkbsepy4O6tpLVpU60cEYf6xuHbXcIexv4-mGoA6OJ-03-n7crmArzLZZc6UcpeOKG3MzhfZjousGXnjMOus6rQvrI4OcjwMLJzbDLI0G-nlG7Sam9Rcsq78gkV5RaxPk25uUdFiixvU8Uvak8idriypar~8ICqsUb-r~IC07CbS2NLcsvn49mflf6~gZVlbJG1aDVmciHk8vOZz2Oc435luEYN7WFrcUmYYkSZxpjxSMXv7oamshyx4AV6CFog__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                optionNum = it + 1,
                optionName = "컴포트 Ⅱ",
                subOptions = listOf("전방 충돌방지 보조", "내비게이션 기반 스마트 크루즈 컨트롤", "고속도로 주행보조 2"),
                optionReview = "승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다.",
                optionTags = listOf("어린이\uD83D\uDC76", "편리해요\uD83D\uDE09", "이것만 있으면 나도 주차고수\uD83D\uDE98")
            )
        }
    }
}

@Preview
@Composable
fun PreviewArchiveDetailRoute() {
    ArchiveDetailRoute()
}