import * as Styled from './style';

export function AutoSavingLogo() {
  return (
    <Styled.Container>
      <svg width="10" height="10" viewBox="0 0 10 10" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path
          d="M2.34835 7.65165C3.02697 8.33027 3.96447 8.75 5 8.75C7.07107 8.75 8.75 7.07107 8.75 5C8.75 2.92893 7.07107 1.25 5 1.25C3.96447 1.25 3.02697 1.66973 2.34835 2.34835C2.00293 2.69377 1.25 3.54167 1.25 3.54167"
          stroke="#545454"
          strokeLinecap="round"
          strokeLinejoin="round"
        />
        <path d="M1.25 1.6665V3.5415H3.125" stroke="#545454" strokeLinecap="round" strokeLinejoin="round" />
      </svg>
    </Styled.Container>
  );
}
