import { MyCarProps } from './trim';

export interface OptionDataProps {
  selectOptions: OptionProps[];
}

export interface OptionProps {
  id: number;
  name: string;
  additionalPrice: number;
  imageURL: string;
  tags: string[];
  subOptions: SubOptionProps[];
}

export interface SubOptionProps {
  id: number;
  name: string;
  imageURL: string;
  description: string;
}

export interface OptionCardDataProps {
  id: number;
  index: number;
  name: string;
  additionalPrice: number;
  imageURL: string;
  subOptionNames: string[];
}

export interface DefaultOptionDataProps {
  defaultOptions: DefaultOptionProps[];
}

export interface DefaultOptionProps {
  category: string;
  subOptions: SubOptionProps[];
}

export interface DefaultSubOptionProps {
  name: string;
  imageURL: string;
}

export interface DefaultOptionCardDataProps {
  name: string;
  imageURL: string;
  description?: string;
}

export interface OptionContextProviderProps {
  trim: MyCarProps;
  addOption: ({ name, price }: OptionContextProps) => void;
  removeOption: (name: string) => void;
}

interface OptionContextProps {
  name: string;
  price: number;
}
