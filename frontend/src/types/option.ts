export interface SelectOptionProps {
  name: string;
  price: number;
  imageUrl: string;
  tags: string[];
  subOptions: SubOptionProps[];
}

export interface SubOptionProps {
  name: string;
  imageUrl: string;
  description: string;
}

export interface SelectOptionCardDataProps {
  name: string;
  price: number;
  imageUrl: string;
  subOptionNames: string[];
}
