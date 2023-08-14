import * as Styled from './style';

interface Props {
  onClick: () => void;
}
export function SaveButton({ onClick }: Props) {
  return (
    <Styled.Container viewBox="0 0 52 52" fill="none" xmlns="http://www.w3.org/2000/svg">
      <circle cx="26" cy="26" r="26" fill="#E4DCD3" />
      <rect width="24" height="24" transform="translate(14 13)" fill="#E4DCD3" />
      <path
        d="M18 35L18 17C18 15.8954 18.8954 15 20 15H32C33.1046 15 34 15.8954 34 17V35L26 30.8636L18 35Z"
        stroke="white"
        strokeWidth="2"
        strokeLinejoin="round"
      />
    </Styled.Container>
  );
}
