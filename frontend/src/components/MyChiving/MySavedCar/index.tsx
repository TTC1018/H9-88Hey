import { Fragment, MouseEvent, useRef } from 'react';

import { useNavigate } from 'react-router-dom';

import { MyCarProps } from '@/types/trim';
import { MyChivingProps } from '@/types/myChiving';
import { OptionContextProps } from '@/types/option';
import { useModalContext } from '@/hooks/useModalContext';
import { useInfiniteFetch } from '@/hooks/useInfiniteFetch';
import { useInfiniteScroll } from '@/hooks/useInfiniteScroll';
import { useShowScrollButton } from '@/hooks/useShowScrollButton';
import { API_URL, ModalType, OPTION_CATEGORY, apiPath } from '@/constants';

import { MyCarList } from '@/components/MyChiving/MyCarList';
import { NoDataInfo } from '@/components/MyChiving/NoDataInfo';
import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/common/ModalPortal';
import { ReviewSkeleton } from '@/components/common/ReviewSkeleton';
import { ScrollTopButton } from '@/components/common/ScrollTopButton';

import * as Styled from './style';

type MatchPathType = Record<string, string>;

const matchPath: MatchPathType = {
  engine: '/trim/engine',
  bodyType: '/trim/body-type',
  wheelDrive: '/trim/wheel-drive',
  interiorColor: '/color',
  exteriorColor: '/color',
  selectOptions: '/option',
};

interface ClickEventDataProps {
  deleteText: string;
  moveText: string;
}

export function MySavedCar() {
  const { handleOpen } = useModalContext();
  const navigate = useNavigate();

  const { isShow, scrollToTop } = useShowScrollButton({ scrollY: 800, scrollTo: 0 });
  const fetchMoreElement = useRef<HTMLDivElement>(null);
  const intersecting = useInfiniteScroll(fetchMoreElement);
  const nextOffset = useRef(1);

  const {
    data: myChivings,
    isLoading,
    handleDelete,
  } = useInfiniteFetch<MyChivingProps>({
    key: 'myChivings',
    url: apiPath.mychiving(nextOffset.current, 8),
    intersecting,
    nextOffset,
    dependencies: [''],
    //method: 'GET',
  });

  const modalInfo = useRef({
    type: ModalType.CLOSE,
    contents: '',
    onClick: () => {},
  });

  const myCar: MyCarProps = {
    carType: { krName: '팰리세이드', enName: 'Palisade' },
    trim: { name: '', price: 0, id: 0 },
    engine: { name: '', additionalPrice: 0, id: 0 },
    bodyType: { name: '', additionalPrice: 0, id: 0 },
    wheelDrive: { name: '', additionalPrice: 0, id: 0 },
    exteriorColor: { name: '', colorImageUrl: '', additionalPrice: 0, id: 0 },
    interiorColor: { name: '', colorImageUrl: '', id: 1 },
    options: [],
    carImageUrl: '',
  };

  function handleNavigate(myChiving: MyChivingProps) {
    const { trim, engine, bodyType, wheelDrive, exteriorColor, interiorColor, selectOptions, carCode } = myChiving;
    const savedMyCar: MyCarProps = {
      ...myCar,
      trim: trim,
      engine: engine ?? myCar.engine,
      bodyType: bodyType ?? myCar.bodyType,
      wheelDrive: wheelDrive ?? myCar.wheelDrive,
      exteriorColor: exteriorColor ?? myCar.exteriorColor,
      interiorColor: interiorColor ?? myCar.interiorColor,
      options: selectOptions
        ? (selectOptions.map(props => {
            return {
              ...props,
              path: OPTION_CATEGORY[props.category],
            };
          }) as OptionContextProps[])
        : [],
      carImageUrl: exteriorColor ? exteriorColor.carImageUrl : '',
    };

    localStorage.setItem('myCar', JSON.stringify(savedMyCar));
    if (myChiving.isSaved) {
      navigate('/result', { state: savedMyCar });
    } else {
      const myChivingValues = Object.values(myChiving).slice(5);
      const myChivingKeys = Object.keys(myChiving).slice(5);

      const targetIndex = myChivingValues.findIndex(value => value === null);
      const lastIndex = myChivingValues.length - 1;
      const targetPath = myChivingKeys[targetIndex === -1 ? lastIndex : targetIndex];

      if (targetPath === 'selectOptions') {
        navigate(`${matchPath[targetPath]}?car_code=${carCode}`);
        localStorage.setItem('carCode', carCode || '');
        return;
      }

      navigate(matchPath[targetPath]);
    }
  }

  /** fe-dev pull 받고 수정 예정 */
  async function deleteMyChiving(url: string) {
    const response = await fetch(url, {
      method: 'DELETE',
      headers: {
        Authorization: `Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ`,
        credentials: 'same-origin',
      },
    });
    if (!response.ok) {
      throw new Error('DELETE 에러 발생');
    }
  }

  function handleDeleteList(myChiving: MyChivingProps) {
    deleteMyChiving(`${API_URL}/mychiving/${myChiving.myChivingId}?user_id=1234`);
    handleDelete(myChiving.myChivingId);
  }

  function handleClick(myChiving: MyChivingProps, data: ClickEventDataProps, event: MouseEvent<HTMLDivElement>) {
    const element = event.target as Element;
    const deleteButton = element.closest('button');

    if (deleteButton) {
      modalInfo.current = {
        type: ModalType.DELETE,
        contents: data.deleteText,
        onClick: () => handleDeleteList(myChiving),
      };
    } else {
      modalInfo.current = { type: ModalType.MOVE, contents: data.moveText, onClick: () => handleNavigate(myChiving) };
    }
    handleOpen();
  }

  return (
    <Fragment>
      <Styled.Contianer>
        {myChivings && myChivings.length === 0 ? (
          isLoading ? (
            <ReviewSkeleton />
          ) : (
            <NoDataInfo infoText="내 차 목록에 저장한 차량이 없어요" buttonText="내 차 만들기" toPath="/trim" />
          )
        ) : (
          <>
            <Styled.MyCarBox>
              {myChivings.map((data, index) => (
                <MyCarList key={index} myChiving={data} onClick={handleClick} />
              ))}
            </Styled.MyCarBox>
            {isLoading && (
              <Styled.Wrapper>
                <Styled.Loading />
                <Styled.Loading />
              </Styled.Wrapper>
            )}
          </>
        )}
        <div ref={fetchMoreElement}></div>
        {isShow && <ScrollTopButton onClick={scrollToTop} />}
      </Styled.Contianer>
      <ModalPortal>
        <PopupModal
          type={modalInfo.current.type}
          onClick={modalInfo.current.onClick}
          contents={modalInfo.current.contents}
        />
      </ModalPortal>
    </Fragment>
  );
}
