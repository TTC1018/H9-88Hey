import { MyChivingDataProps } from '@/types/myChiving';
import { useFetch } from '@/hooks/useFetch';

import { MyCarList } from '@/components/MyChiving/MyCarList';
import { NoDataInfo } from '@/components/MyChiving/NoDataInfo';

import * as Styled from './style';

const savedInitialData = {
  myarchivings: [
    {
      id: 111,
      model: '',
      trim: '',
      isSaved: true,
      trimOptions: [''],
      lastModifiedDate: '',
      selectedOptions: [
        {
          name: '',
          imageUrl: '',
        },
      ],
    },
  ],
};

const tempInitialData = {
  myarchivings: [
    {
      id: 222,
      model: '',
      trim: '',
      isSaved: false,
      trimOptions: [''],
      lastModifiedDate: '',
      selectedOptions: [
        {
          name: '',
          imageUrl: '',
        },
      ],
    },
  ],
};

export function MySavedCar() {
  const { data: tempData } = useFetch<MyChivingDataProps>({ defaultValue: tempInitialData, url: '/mychiving/temp' });
  const { data: savedData } = useFetch<MyChivingDataProps>({ defaultValue: savedInitialData, url: '/mychiving' });
  const allData = [...tempData.myarchivings, ...savedData.myarchivings];

  return (
    <Styled.Contianer>
      {allData.length > 0 ? (
        <Styled.MyCarBox>
          {allData.map(value => (
            <MyCarList
              key={value.id}
              isSaved={value.isSaved}
              model={value.model}
              trim={value.trim}
              trimOptions={value.trimOptions}
              lastModifiedDate={value.lastModifiedDate}
              selectedOptions={value.selectedOptions}
            />
          ))}
        </Styled.MyCarBox>
      ) : (
        <NoDataInfo infoText="내 차 목록에 저장한 차량이 없어요" buttonText="내 차 만들기" toPath="/trim" />
      )}
    </Styled.Contianer>
  );
}
