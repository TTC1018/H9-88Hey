import { useState } from 'react';
import * as Styled from './style';

interface XButtonProps {
  onClick: () => void;
}

export function XButton({ onClick }: XButtonProps) {
  const [isHover, setIsHover] = useState(false);
  function handleMouseEnter() {
    setIsHover(true);
  }
  function handleMouseLeave() {
    setIsHover(false);
  }
  return (
    <Styled.Container onClick={onClick} onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave}>
      <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" viewBox="0 0 22 22" fill="none">
        <path
          d="M11 21C16.5228 21 21 16.5228 21 11C21 5.47715 16.5228 1 11 1C5.47715 1 1 5.47715 1 11C1 16.5228 5.47715 21 11 21Z"
          stroke="#BEBEBE"
          fill={isHover ? '#545454' : ''}
          strokeLinejoin="round"
        />
        <path
          fillRule="evenodd"
          clipRule="evenodd"
          d="M13.8271 8.17186L8.17028 13.8287L13.8271 8.17186Z"
          fill="#BEBEBE"
        />
        <path
          d="M13.8271 8.17186L8.17028 13.8287"
          stroke="#BEBEBE"
          strokeWidth="2"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
        <path
          fillRule="evenodd"
          clipRule="evenodd"
          d="M8.17287 8.17186L13.8297 13.8287L8.17287 8.17186Z"
          fill="#BEBEBE"
        />
        <path
          d="M8.17287 8.17186L13.8297 13.8287"
          stroke="#BEBEBE"
          strokeWidth="2"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
      </svg>
    </Styled.Container>
  );
}
