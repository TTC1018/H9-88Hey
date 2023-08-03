import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { EngineTemplate } from '@/components/templates/EngineTemplate';

const mockData = [
  {
    title: '디젤 2.2',
    price: 14800000,
    description: '높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다.',
    power: '202/3,800PS/rpm',
    torque: '45.0/1,750~2,750kgf-m/rpm',
    image: 'https://www.hyundai.com/contents/spec/guide/lx_diesel2.2_s.jpg',
  },
  {
    title: '가솔린 3.8',
    price: 14800000,
    description:
      '고마력의 우수한 가속 성능을 확보하여, 넉넉하고 안정감 있는 주행이 가능합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다.',
    power: '295/6,000PS/rpm',
    torque: '36.2/5,200kgf-m/rpm',
    image: 'https://www.hyundai.com/contents/spec/guide/lx_gasoline3.8_s.jpg',
  },
];

export function Engine() {
  const [selectedIndex, handleCardClick] = useSelectIndex();

  return <EngineTemplate selectedIndex={selectedIndex} optionCards={mockData} onClick={handleCardClick} />;
}
