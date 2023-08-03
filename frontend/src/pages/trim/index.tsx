import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { TrimTemplate } from '@/components/templates/TrimTemplate';

const mockCards = [
  {
    title: 'Le Blanc(르블랑)',
    price: 47720000,
    carImages: [
      'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/001.png',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F8c343c%2F574691FAF8211C9B0062C36B47DAB069D716EF12D7C7683B36_3JA1&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F54f09c%2F5F68383D23E12EC9239B7385B37186C4B250A0F83043A92FA6_36OF&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Ffa4cc2%2F4AD940F9C9E82ABEFF650A58DBDD430D3AA22E10CBA3BCD93D_3QMO&scode=media',
    ],
    optionImages: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Exclusive',
    price: 47720000,
    carImages: [
      'https://www.hyundai.com/contents/vr360/LX06/exterior/R2T/001.png',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F8c343c%2F574691FAF8211C9B0062C36B47DAB069D716EF12D7C7683B36_3JA1&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F54f09c%2F5F68383D23E12EC9239B7385B37186C4B250A0F83043A92FA6_36OF&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Ffa4cc2%2F4AD940F9C9E82ABEFF650A58DBDD430D3AA22E10CBA3BCD93D_3QMO&scode=media',
    ],
    optionImages: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Prestige',
    price: 47720000,
    carImages: [
      'https://www.hyundai.com/contents/vr360/LX06/exterior/D2S/001.png',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F8c343c%2F574691FAF8211C9B0062C36B47DAB069D716EF12D7C7683B36_3JA1&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F54f09c%2F5F68383D23E12EC9239B7385B37186C4B250A0F83043A92FA6_36OF&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Ffa4cc2%2F4AD940F9C9E82ABEFF650A58DBDD430D3AA22E10CBA3BCD93D_3QMO&scode=media',
    ],
    optionImages: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Calligraphy',
    price: 47720000,
    carImages: [
      'https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/001.png',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F8c343c%2F574691FAF8211C9B0062C36B47DAB069D716EF12D7C7683B36_3JA1&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F54f09c%2F5F68383D23E12EC9239B7385B37186C4B250A0F83043A92FA6_36OF&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Ffa4cc2%2F4AD940F9C9E82ABEFF650A58DBDD430D3AA22E10CBA3BCD93D_3QMO&scode=media',
    ],
    optionImages: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
];

export function Trim() {
  const [selectedIndex, handleClick] = useSelectIndex();
  const [selectedImageIndex, handleImageClick] = useSelectIndex();

  const handleCardClick = (index: number) => () => {
    handleClick(index)();
    handleImageClick(0)();
  };

  return (
    <TrimTemplate
      selectedIndex={selectedIndex}
      selectedImageIndex={selectedImageIndex}
      optionCards={mockCards}
      onCardClick={handleCardClick}
      onImageClick={handleImageClick}
    />
  );
}
