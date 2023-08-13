import * as Styled from './style';

interface Props {
  onClick: () => void;
}
export function SlidePrevButton({ onClick }: Props) {
  return (
    <Styled.Container
      onClick={onClick}
      xmlns="http://www.w3.org/2000/svg"
      width="48"
      height="48"
      viewBox="0 0 48 48"
      fill="none"
    >
      <g clipPath="url(#clip0_374_22851)">
        <path d="M31 36L19 24L31 12" stroke="#545454" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round" />
      </g>
      <defs>
        <clipPath id="clip0_374_22851">
          <rect width="48" height="48" fill="white" />
        </clipPath>
      </defs>
    </Styled.Container>
  );
}
