import * as Styled from './style';

interface Props {
  selectedCar: string;
  onClick: (car: string) => void;
  cars: string[];
}
export function SearchBar({ selectedCar, onClick, cars }: Props) {
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
