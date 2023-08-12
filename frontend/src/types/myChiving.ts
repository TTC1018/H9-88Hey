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
