import { useState } from 'react';

import { MyCarList } from '@/components/archiving/MyCarList';
import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';
import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as style from './style';
import MyCarNavigation from '@/components/archiving/MyCarNavigation';

const archivingMockData = {
  data: {
    myarchivings: [
      {
        id: 1,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: true,
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
        id: 2,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: true,
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
        id: 3,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: true,
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
        id: 13,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: false,
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
        id: 14,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: false,
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
        id: 15,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: false,
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
        id: 16,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: false,
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
        id: 17,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: false,
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
        id: 18,
        model: '팰리세이드',
        trim: 'Le Blanc',
        isSaved: false,
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
    ],
  },
};

export function MyArchiving() {
  const tempData = tempArchivingMockData.data.myarchivings;
  const data = archivingMockData.data.myarchivings;
  const allData = [...tempData, ...data];

  const [page, setPage] = useState(0);
  const rangeArray = Array.from({ length: 4 }, (_, index) => index + page * 4);

  return (
    <>
      <ArchivingHeader />
      <ArchivingNavigation />
      <style.Container>
        <style.Wrapper>
          <MyCarNavigation />
          <style.MyCarMain>
            <PrevButton
              width="60"
              height="60"
              onClick={() => {
                setPage(prev => prev - 1);
              }}
              isShow={page !== 0}
            />
            <style.MyCarBox>
              {rangeArray.map(index =>
                index < allData.length ? (
                  <MyCarList
                    key={allData[index].id}
                    isSaved={allData[index].isSaved}
                    model={allData[index].model}
                    trim={allData[index].trim}
                    trimOptions={allData[index].trimOptions}
                    lastModifiedDate={allData[index].lastModifiedDate}
                    selectedOptions={allData[index].selectedOptions}
                  />
                ) : (
                  <style.EmptyBox key={index}></style.EmptyBox>
                )
              )}
            </style.MyCarBox>
            <NextButton
              width="60"
              height="60"
              onClick={() => {
                setPage(prev => prev + 1);
              }}
              isShow={page !== Math.floor(allData.length < 1 ? 0 : Math.floor((allData.length - 1) / 4))}
            />
          </style.MyCarMain>
        </style.Wrapper>
      </style.Container>
    </>
  );
}
