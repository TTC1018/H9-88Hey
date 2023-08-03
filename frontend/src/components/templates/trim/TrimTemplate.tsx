import { TrimCard } from '@components/common/TrimCard';
import { MyCarImageBox } from '@components/common/MyCarImageBox';
import { MyCarDescription } from '@components/common/MyCarDescription';

export function TrimTemplate() {
  return (
    <div>
      <TrimCard
        isActive={true}
        title="디젤 2.2"
        price={'1,480,000'}
        description="기존 8인승 시트(1열 2명, 2열 3명, 3열 3명)에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다."
        hasEngineInfo={true}
        power={'202/3,800PS/rpm'}
        torque={'45.0/1,750~2,750kgf-m/rpm'}
      />
      <MyCarImageBox
        selectedIndex={1}
        images={[
          'https://www.hyundai.com/contents/vr360/LX06/interior/I49/img-interior.png',
          'https://www.hyundai.com/contents/spec/guide/lx_diesel2.2_s.jpg',
        ]}
        hasOption={true}
        onClick={() => {}}
      />
      <MyCarDescription
        title="문라이트 펄 블루"
        price="0"
        hasTag={true}
        tags={['어린이👶', '이것만 있으면 나도 주차고수🚘', '편리해요😉', '대형견도 문제 없어요🐶', '가성비가 좋아요']}
      />
    </div>
  );
}
