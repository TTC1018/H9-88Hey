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

// export interface OptionDetailsProps {
//   title: string;
//   description: string;
// }

// export interface SelectOptionCardInfoProps {
//   image: string;
//   title: string;
//   price: number;
//   descriptions: string[];
// }

// export interface DefaultOptionCardInfoProps {
//   image: string;
//   text: string;
//   subtext: string;
//   description: string;
// }
