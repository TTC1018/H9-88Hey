export interface OptionProps {
  name: string;
  imageUrl: string;
}

export interface MyChivingProps {
  id: number;
  model: string;
  trim: string;
  isSaved: boolean;
  trimOptions: string[];
  lastModifiedDate: string;
  selectedOptions: OptionProps[];
}

export interface MyChivingDataProps {
  myarchivings: MyChivingProps[];
}

export interface MyFeedProps {
  id: number;
  model: string;
  trim: string;
  isPurchase: boolean;
  trimOptions: string[];
  interiorColor: string;
  exteriorColor: string;
  creatrionDate: string;
  selectedOptions: OptionProps[];
  review: string;
  tags: string[];
}

export interface MyFeedDataProps {
  archivingsByUser: MyFeedProps[];
}
