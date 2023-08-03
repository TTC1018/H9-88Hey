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
        <style.Text>{title}</style.Text>
        <style.Text>{price}</style.Text>
        <style.ButtonBox>
          <style.Button>추가하기</style.Button>
        </style.ButtonBox>
      </style.DescriptionWrapper>
    </style.OptionCard>
  );
}
