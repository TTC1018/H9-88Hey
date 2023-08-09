export interface OptionProps {
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

export interface OptionCardDataProps {
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

export interface OptionContextProviderProps {
  addOption: ({ name, price }: OptionContextProps) => void;
  removeOption: (name: string) => void;
}

interface OptionContextProps {
  name: string;
  price: number;
}
