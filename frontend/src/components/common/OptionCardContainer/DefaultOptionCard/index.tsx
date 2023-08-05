import * as style from './style';

const categoryList = ['파워 트레인/성능', '지능형 안전 기술', '안전', '외관', '내장', '시트', '편의', '멀티미디어'];
const selectedOption = 1;

export function DefaultOptionCard() {
  return (
    <style.Container>
      <style.CategoryWrapper>
        {categoryList.map((category, index) => (
          <style.Category option={index + 1} selectedOption={selectedOption} key={category}>
            {category}
          </style.Category>
        ))}
      </style.CategoryWrapper>
      <style.OptionCardWrapper>
        {[1, 2, 3, 4, 5, 6].map(item => (
          <style.OptionCard key={item}>
            <style.Image src="/src/assets/leblanc.jpeg" />
            <style.TextWrapper>
              <style.Text>IGS 시스템</style.Text>
              <style.SubText>기본 포함</style.SubText>
            </style.TextWrapper>
          </style.OptionCard>
        ))}
      </style.OptionCardWrapper>
    </style.Container>
  );
}
