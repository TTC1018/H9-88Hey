import { MyCarList } from '@/components/archiving/MyCarList';
import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';

import * as style from './style';

const archivingMockData = {
  data: {
    myarchivings: [
      {
        id: '1',
        model: '팰리세이드',
        trim: 'Le Blanc',
        trimOptions: ['디젤 2.2', '4WD', '7인승'],
        lastModifiedDate: '2023-07-19',
        selectedOptions: [
          {
            name: '컴포트 ||',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
          },
          {
            name: '듀얼 와이드 선루프',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
          },
        ],
      },
      {
        id: '2',
        model: '팰리세이드',
        trim: 'Le Blanc',
        trimOptions: ['디젤 2.2', '2WD', '7인승'],
        lastModifiedDate: '2023-07-15',
        selectedOptions: [
          {
            name: '주차보조 시스템',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
          },
          {
            name: '적외선 무릎워머',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
          },
          {
            name: '적외선 무릎워머',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
          },
          {
            name: '적외선 무릎워머',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
          },
          {
            name: '적외선 무릎워머',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
          },
        ],
      },
    ],
  },
};

const tempArchivingMockData = {
  data: {
    myarchivings: [
      {
        id: '1',
        model: '팰리세이드',
        trim: 'Le Blanc',
        trimOptions: ['디젤 2.2', '4WD', '7인승'],
        lastModifiedDate: '2023-07-19',
        selectedOptions: [
          {
            name: '컴포트 ||',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/P7V/002.png',
          },
          {
            name: '듀얼 와이드 선루프',
            imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/P7V/002.png',
          },
        ],
      },
      {
        id: '2',
        model: '팰리세이드',
        trim: 'Le Blanc',
        trimOptions: ['디젤 2.2', '2WD', '7인승'],
        lastModifiedDate: '2023-07-15',
        selectedOptions: [],
      },
    ],
  },
};

export function MyArchiving() {
  const data = archivingMockData.data.myarchivings;
  const tempData = tempArchivingMockData.data.myarchivings;
  return (
    <>
      <ArchivingHeader />
      <ArchivingNavigation />
      <style.Container>
        <style.Wrapper>
          <style.TitleText>내가 만든 차량 목록</style.TitleText>
          <style.Division />
          <style.MyCarBox>
            {tempData.map(data => (
              <MyCarList
                key={data.id}
                isSaved={false}
                model={data.model}
                trim={data.trim}
                trimOptions={data.trimOptions}
                lastModifiedDate={data.lastModifiedDate}
                selectedOptions={data.selectedOptions}
              />
            ))}
            {data.map(data => (
              <MyCarList
                key={data.id}
                isSaved={true}
                model={data.model}
                trim={data.trim}
                trimOptions={data.trimOptions}
                lastModifiedDate={data.lastModifiedDate}
                selectedOptions={data.selectedOptions}
              />
            ))}
          </style.MyCarBox>
        </style.Wrapper>
      </style.Container>
    </>
  );
}
