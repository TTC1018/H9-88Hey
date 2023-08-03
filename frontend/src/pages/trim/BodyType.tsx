import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { BodyTypeTemplate } from '@/components/templates/BodyTypeTemplate';

const mockData = [
  {
    title: '7인승',
    price: 0,
    description:
      '기존 8인승 시트(1열 2명, 2열 3명, 3열 3명)에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다.',
    images: [
      'https://www.hyundai.com/contents/spec/guide/lx_2wd_s.jpg',
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1p89A1Ayz5SUzrKfHgl0hph8xUyYAqRlJ5A&usqp=CAU',
    ],
  },
  {
    title: '8인승',
    price: 0,
    description: '1열 2명, 2열 3명, 3열 3명이 탑승할 수 있는 구조로, 많은 인원이 탑승할 수 있도록 배려하였습니다',
    images: [
      'https://www.hyundai.com/contents/spec/guide/lx_htrac_s.jpg',
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUATgC4Xv06TncWaHMALgAiS0n4SoTG8iZ4g&usqp=CAU',
    ],
  },
];

export function BodyType() {
  const [selectedIndex, handleClick] = useSelectIndex();
  const [selectedImageIndex, handleImageClick] = useSelectIndex();

  const handleCardClick = (index: number) => () => {
    handleClick(index)();
    handleImageClick(0)();
  };

  return (
    <BodyTypeTemplate
      selectedIndex={selectedIndex}
      selectedImageIndex={selectedImageIndex}
      selectOptionCards={mockData}
      onCardClick={handleCardClick}
      onImageClick={handleImageClick}
    />
  );
}
