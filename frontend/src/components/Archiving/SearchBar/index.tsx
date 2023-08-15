import * as Styled from './style';

interface Props {
  selectedCar: string;
  cars: string[];
  onClick: (car: string) => void;
}
export function SearchBar({ selectedCar, cars, onClick }: Props) {
  return (
    <Styled.Container>
      {cars.map(car => (
        <Styled.Wrapper key={car} isActive={car === selectedCar} onClick={() => onClick(car)}>
          {car}
        </Styled.Wrapper>
      ))}
    </Styled.Container>
  );
}
