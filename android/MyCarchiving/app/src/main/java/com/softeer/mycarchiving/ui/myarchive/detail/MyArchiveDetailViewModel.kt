package com.softeer.mycarchiving.ui.myarchive.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.softeer.mycarchiving.model.makingcar.SelectOptionUiModel
import com.softeer.mycarchiving.model.makingcar.SubSelectOptionUiModel
import com.softeer.mycarchiving.ui.myarchive.KEY_MYARCHIVE_DETAIL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MyArchiveDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
):ViewModel() {

    val screenIndex = savedStateHandle.getStateFlow(KEY_MYARCHIVE_DETAIL, -1)

    val carName = MutableStateFlow("팰리세이드")

    val carModel = MutableStateFlow("Le Blanc")

    val comment = MutableStateFlow("승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.")

    val selectOptions = MutableStateFlow(
        listOf(
            SelectOptionUiModel(
                id = "",
                name = "컴포트 2",
                price = 1090000,
                imageUrl = "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/roa.jpg",
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
                    ),
                    SubSelectOptionUiModel(
                        name = "메탈 도어스커프",
                        imageUrl = "",
                        description = "자동차를 타고 내리는 도어의 무늩ㅁ 하부를 보호하는 도어 스커프 부분을 메탈로 만들어 차체를 보호하고 메탈 디자인으로 고급스러운 감성을 전달합니다."
                    ),
                    SubSelectOptionUiModel(
                        name = "3열 파워폴딩시트",
                        imageUrl = "",
                        description = "러기지 사이드에 있는 버튼으로 3열 시트를 접었다 펼 수 있으며, 2열 시트도 조작할 수 있어 화물 적재시나 3열 이용시 사용자의 편의성을 높였습니다."
                    ),
                    SubSelectOptionUiModel(
                        name = "3열 열선시트",
                        imageUrl = "",
                        description = "시동이 걸린 상태에서 해당 좌석 히터 스위치를 누르면 강약조절 표시등이 켜져 사용 중임을 나타내고 해당 좌석이 따뜻해집니다."
                    ),
                    SubSelectOptionUiModel(
                        name = "헤드업 디스플레이",
                        imageUrl = "",
                        description = "주요 주행 정보를 전면 윈드실드에 표시하며 밝기가 최적화되어 주간에도 시인성이 뛰어납니다."
                    )
                ),
                tags = listOf("편리해요😉", "이것만 있으면 나도 주차고수🚘")
            ),
            SelectOptionUiModel(
                id = "",
                name = "듀얼 와이드 선루프",
                price = 890000,
                imageUrl = "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/dualwidesunroof.jpg",
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
                ),
            ),
            SelectOptionUiModel(
                id = "",
                name = "현대스마트센스 Ⅰ",
                price = 2900000,
                imageUrl = "https://88hey-bucket.s3.amazonaws.com/88hey/select-option/fca2.jpg",
                tags = listOf(
                    "대형견도 문제 없어요",
                    "가족들도 좋은 옵션👨‍👩‍👧‍👦"
                ),
            )
        )
    )

}