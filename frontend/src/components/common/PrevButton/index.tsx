import * as style from './style';

interface PrevButtonProps {
  width: string;
  height: string;
  onClick: () => void;
}

export function PrevButton({ width, height, onClick }: PrevButtonProps) {
  return (
    <style.Container width={+width} height={+height} onClick={() => onClick()}>
      <svg xmlns="http://www.w3.org/2000/svg" width={width} height={height} viewBox="0 0 48 48" fill="none">
        <g clipPath="url(#clip0_577_11601)">
          <path
            d="M31 36L19 24L31 12"
            stroke="#002C5F"
            strokeOpacity="0.6"
            strokeLinecap="round"
            strokeLinejoin="round"
          />
        </g>
        <defs>
          <clipPath id="clip0_577_11601">
            <rect width="48" height="48" fill="white" />
          </clipPath>
        </defs>
      </svg>
    </style.Container>
  );
}
