import { useSelectIndex } from '@hooks/useSelectedIndex';
import { TrimTemplate } from '@components/templates/TrimTemplate';

const mockCards = [
  {
    title: 'Le Blanc(르블랑)',
    price: 47720000,
    images: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Exclusive',
    price: 47720000,
    images: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Prestige',
    price: 47720000,
    images: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Calligraphy',
    price: 47720000,
    images: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
];

export function Trim() {
  const [selectedIndex, handleCardClick] = useSelectIndex();

  return <TrimTemplate selectedIndex={selectedIndex} optionCards={mockCards} onClick={handleCardClick} />;
}
