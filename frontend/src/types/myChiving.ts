export interface SelectionOptionsProps {
  name: string;
  imageUrl: string;
}

export interface MyArchivingsProps {
  id: number;
  model: string;
  trim: string;
  isSaved: boolean;
  trimOptions: string[];
  lastModifiedDate: string;
  selectedOptions: SelectionOptionsProps[];
}

export interface MyChivingDataProps {
  myarchivings: MyArchivingsProps[];
}

export interface FeedMyChivingsProps {
  id: number;
  model: string;
  trim: string;
  isPurchase: boolean;
  trimOptions: string[];
  interiorColor: string;
  exteriorColor: string;
  creatrionDate: string;
  selectedOptions: SelectionOptionsProps[];
  review: string;
  tags: string[];
}

export interface FeedMyChivingDataProps {
  archivingsByUser: FeedMyChivingsProps[];
}
