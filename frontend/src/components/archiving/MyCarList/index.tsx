import { XButtonLogo } from '@/components/common/XButtonLogo';
import * as style from './style';

export function MyCarList() {
  return (
    <style.Container>
      <style.Wrapper>
        <style.InfoBox>
          <style.InfoText>
            <style.AlertStar>*</style.AlertStar>저장하지 않고 나간 차량이 있어요.
          </style.InfoText>
        </style.InfoBox>
        <style.MainBox>
          <style.Title>
            <style.TitleText>펠리세이드 Le Blanc </style.TitleText>
            <style.TrimText>디젤 2.2 / 4WD / 7인승</style.TrimText>
          </style.Title>
          <style.SubTitle>
            <style.subTitleText>23년 7월 19일 임시저장</style.subTitleText>
            <style.XButton>
              <XButtonLogo />
            </style.XButton>
          </style.SubTitle>
        </style.MainBox>
        <style.OptionBox>
          <style.OptionCard isLastCard={false} />
          <style.OptionCard isLastCard={false} />
          <style.OptionCard isLastCard={false} />
          <style.OptionCard isLastCard={true} />
        </style.OptionBox>
      </style.Wrapper>
    </style.Container>
  );
}
