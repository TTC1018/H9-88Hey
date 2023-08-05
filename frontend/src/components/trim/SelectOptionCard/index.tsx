import * as style from './style';

interface SelectOptionCardProps {
  isActive: boolean;
  title: string;
  price: number;
  images: string[];
}
export function SelectOptionCard({ isActive, title, price, images }: SelectOptionCardProps) {
  return (
    <style.Container isActive={isActive}>
      <style.Title>{title}</style.Title>
      <style.Line isActive={isActive} />
      <style.ImageWrapper>
        {images.map(image => (
          <style.Image key={image} src={image} />
        ))}
      </style.ImageWrapper>
      <style.Line isActive={isActive} />
      <style.Price>{price.toLocaleString()} 원</style.Price>
    </style.Container>
  );
}
