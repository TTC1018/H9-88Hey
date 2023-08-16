export interface ArchivingProps {
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
export interface SelectOptionsProps {
  name: string;
  imageUrl: string;
  subOptions: string[];
  tags: string[];
  review: string;
}
export interface ArchivingDataProps {
  archivings: ArchivingProps[];
}

interface ArchivingCarProps {
  name: string;
  options: string[];
}
export interface ArchivingCarDataProps {
  archivingCars: ArchivingCarProps[];
}
