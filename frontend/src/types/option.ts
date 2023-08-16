import { MyCarProps } from './trim';

export interface OptionDataProps {
  selectOptions: OptionProps[];
}

export interface OptionProps {
  isAvailable?: boolean;
  id: number;
  name: string;
  additionalPrice: number;
  imageUrl: string;
  tags: string[];
  subOptions: SubOptionProps[];
}

export interface SubOptionProps {
  id: number;
  name: string;
  imageUrl: string;
  description: string;
}

export interface OptionCardDataProps {
  isAvailable?: boolean;
  id: number;
  index: number;
  name: string;
  additionalPrice: number;
  imageUrl: string;
  subOptionNames: string[];
}

export interface DefaultOptionDataProps {
  defaultOptions: DefaultOptionProps[];
}

export interface DefaultOptionProps {
  category: string;
  subOptions: SubOptionProps[];
}

export interface OptionContextProviderProps {
  trim: MyCarProps;
  addOption: ({ name, price }: OptionContextProps) => void;
  removeOption: (name: string) => void;
}

export interface OptionContextProps {
  name: string;
  price: number;
  imageUrl: string;
  subOptions: string[];
}
