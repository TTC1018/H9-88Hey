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
  index: number;
  name: string;
  price: number;
  imageUrl: string;
  subOptionNames: string[];
}

export interface DefaultOptionCardDataProps {
  name: string;
  imageUrl: string;
}
