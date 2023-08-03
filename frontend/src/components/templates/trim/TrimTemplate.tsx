import { SelectOptionImageBox } from '@components/common/SelectOptionImageBox';
import { SelectOptionDescription } from '@components/common/SelectOptionDescription';
import { SelectOptionDetail } from '@components/common/SelectOptionDetail';
import { OptionCardContainer } from '@components/common/OptionCardContainer';

export function TrimTemplate() {
  return (
    <>
      <SelectOptionImageBox image="src/assets/leblanc.jpeg" />
      <SelectOptionDescription
        title="컴포트 ||"
        price="690,000"
        tags={['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️']}
      />
      <SelectOptionDetail
        index={5}
        length={6}
        optionList={[
          {
            title: '헤드업 디스플레이',
            description:
              '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
          },
        ]}
      />
      <OptionCardContainer selectedCategory={1} selectedOption={1} images={['src/assets/leblanc.jpeg']} />
    </>
  );
}
