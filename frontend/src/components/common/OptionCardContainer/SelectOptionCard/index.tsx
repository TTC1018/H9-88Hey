import * as style from './style';

interface SelectOptionCardProps {
  selectedOption: number;
  images: string[];
}

interface OptionCardProps {
  option: number;
  selectedOption: number;
  image: string;
  title: string;
  price: string;
}

interface OptionCardHoverProps {
  descriptionList: string[];
}

export function SelectOptionCard({ selectedOption, images }: SelectOptionCardProps) {
  return (
    <>
      <style.Container>
        <OptionCard
          option={1}
          selectedOption={selectedOption}
          image={images[0]}
          title={'듀얼 와이드 선루프'}
          price={'+ 000,000 원'}
        />
        <OptionCard
          option={2}
          selectedOption={selectedOption}
          image={images[0]}
          title={'듀얼 와이드 선루프'}
          price={'+ 000,000 원'}
        />
        <OptionCard
          option={3}
          selectedOption={selectedOption}
          image={images[0]}
          title={'듀얼 와이드 선루프'}
          price={'+ 000,000 원'}
        />
        <OptionCard
          option={4}
          selectedOption={selectedOption}
          image={images[0]}
          title={'듀얼 와이드 선루프'}
          price={'+ 000,000 원'}
        />
        <OptionCard
          option={5}
          selectedOption={selectedOption}
          image={images[0]}
          title={'듀얼 와이드 선루프'}
          price={'+ 000,000 원'}
        />
        <OptionCard
          option={6}
          selectedOption={selectedOption}
          image={images[0]}
          title={'듀얼 와이드 선루프'}
          price={'+ 000,000 원'}
        />
      </style.Container>
    </>
  );
}

function OptionCard({ option, selectedOption, image, title, price }: OptionCardProps) {
  return (
    <style.OptionCard option={option} selectedOption={selectedOption}>
      <style.Image src={image} />
      <style.DescriptionWrapper>
        <style.Text option={option} selectedOption={selectedOption}>
          {title}
        </style.Text>
        <style.Text option={option} selectedOption={selectedOption}>
          {price}
        </style.Text>
        <style.ButtonBox>
          <style.Button option={option} selectedOption={selectedOption}>
            {option === selectedOption ? '추가 완료' : '추가하기'}
          </style.Button>
        </style.ButtonBox>
        {option === selectedOption && <style.Icon src="src/assets/icon_done.svg" />}
      </style.DescriptionWrapper>
      {option === 2 && (
        <OptionCardHover
          descriptionList={[
            '전방 충돌방지 보조(교차 차량/추월시 대향차/측방 접근차)',
            '내비게이션 기반 스마트 크루즈 컨트롤(진출입로)',
            '고속도로 주행 보조 2',
          ]}
        />
      )}
    </style.OptionCard>
  );
}

function OptionCardHover({ descriptionList }: OptionCardHoverProps) {
  return (
    <style.OptionCardHover>
      <style.DescriptionHoverWrapper>
        {descriptionList.map(description => (
          <style.DescriptionHover key={description}>{description}</style.DescriptionHover>
        ))}
      </style.DescriptionHoverWrapper>
    </style.OptionCardHover>
  );
}
