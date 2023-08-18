interface TrimProps {
  id: number;
  name: string;
  price: number;
}
interface TrimOptionProps extends Omit<TrimProps, 'price'> {
  additionalPrice: number;
}
interface InteriorColorProps extends Omit<TrimProps, 'price'> {
  colorImageUrl: string;
}
interface ExteriorColorProps extends Pick<InteriorColorProps, 'name' | 'colorImageUrl'> {
  additionalPrice: number;
  carImageUrl: string;
}
export interface ArchivingProps {
  modelName: string;
  feedId: string;
  creationDate: string;
  isPurchase: boolean;
  review: string;
  tags: string[];
  totalPrice: number;
  trim: TrimProps;
  engine: TrimOptionProps;
  bodyType: TrimOptionProps;
  wheelDrive: TrimOptionProps;
  interiorColor: InteriorColorProps;
  exteriorColor: ExteriorColorProps;
  selectedOptions: SelectOptionsProps[];
}
export interface SelectOptionsProps {
  id: string;
  name: string;
  imageUrl: string;
  subOptions: string[];
  tags: string[];
  review: string;
  additionalPrice: number;
}
export interface ArchivingDataProps {
  archivings: ArchivingProps[];
  nextOffset: number;
}

interface ArchivingCarProps {
  id: string;
  name: string;
  category: string;
}
export interface ArchivingCarDataProps {
  selectOptions: ArchivingCarProps[];
}
