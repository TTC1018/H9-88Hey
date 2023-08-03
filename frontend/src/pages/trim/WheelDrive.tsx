import { useSelectIndex } from '@hooks/useSelectedIndex';
import { WheelDriveTemplate } from '@components/templates/WheelDriveTemplate';

const mockData = [
  {
    title: '2WD',
    price: 0,
    description:
      '엔진에서 전달되는 동력이 전/후륜 바퀴 중 한쪽으로만 전달되어 차량을 움직이는 방식입니다차체가 가벼워 연료 효율이 높습니다',
    image: 'https://www.hyundai.com/contents/spec/guide/lx_2wd_s.jpg',
  },
  {
    title: '4WD',
    price: 0,
    description:
      '전자식 상시 4륜 구동 시스템 입니다도로의 상황이나 주행 환경에 맞춰 전후륜 구동력을 자동배분하여 주행 안전성을 높여줍니다',
    image: 'https://www.hyundai.com/contents/spec/guide/lx_htrac_s.jpg',
  },
];

export function WheelDrive() {
  const [selectedIndex, handleCardClick] = useSelectIndex();

  return <WheelDriveTemplate selectedIndex={selectedIndex} optionCards={mockData} onClick={handleCardClick} />;
}
