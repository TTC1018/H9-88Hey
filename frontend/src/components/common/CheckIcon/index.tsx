import * as style from './style';

interface CheckIconProps {
  isInnerColorIcon: boolean;
}

export function CheckIcon({ isInnerColorIcon }: CheckIconProps) {
  return (
    <style.Container isInnerColorIcon={isInnerColorIcon}>
      <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 22 22" fill="none">
        <circle cx="11" cy="11" r="11" fill="#00AAD2" />
        <path
          d="M6 11.5L9.33333 15L16 8"
          stroke="#F6F3F2"
          strokeWidth="1.8"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
      </svg>
    </style.Container>
  );
}
