import { ArchivingProps, SelectOptionsProps } from '@/types/archiving';

export interface OptionProps {
  id: string;
  name: string;
  imageUrl: string;
  subOptions: string[];
  additionalPrice: number;
}

interface ModelProps {
  id: number;
  name: string;
}

interface TrimProps {
  id: number;
  name: string;
  price: number;
}

interface CommonProps {
  id: number;
  name: string;
  additionalPrice: number;
}

interface InteriorColor {
  id: number;
  name: string;
  colorImageUrl: string;
}

interface ExteriorColorProps {
  id: number;
  name: string;
  carImageUrl: string;
  colorImageUrl: string;
  additionalPrice: number;
}

export interface MyChivingProps {
  myChivingId: number;
  lastModifiedDate: string;
  isSaved: boolean;
  totalPrice: number;
  model: ModelProps;
  trim?: TrimProps;
  engine?: CommonProps;
  bodyType?: CommonProps;
  wheelDrive?: CommonProps;
  interiorColor?: InteriorColor;
  exteriorColor?: ExteriorColorProps;
  selectedOptions?: OptionProps[];
}

export interface MyChivingDataProps {
  nextOffset: number;
  mychivings: MyChivingProps[];
}

export interface MyFeedProps {
  id: number;
  model: string;
  trim: string;
  isPurchase: boolean;
  trimOptions: string[];
  interiorColor: string;
  exteriorColor: string;
  creationDate: string;
  selectedOptions: SelectOptionsProps[];
  review: string;
  tags: string[];
  totalPrice: number;
}

export interface MyFeedDataProps {
  archivingsByUser: ArchivingProps[];
}
