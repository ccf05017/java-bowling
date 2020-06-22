# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step1. Todo List
- [X] QnAService 삭제 리팩토링
- [X] 질문 삭제 시 데이터의 상태를 삭제 상태로 변경한다.
    - [X] 로그인 사용자와 질문한 사람이 다른 경우 예외 발생
    - [X] Answers 일급컬렉션에 삭제 메시지 전달
        - [X] 일급 컬렉션에 속한 모든 질문을 한번에 삭제할 수 있다.
        - [X] 답변이 한개라도 있는 경우 삭제 시도 시 예외 발생(기본)
            - [X] 답변이 있으나, 답변자가 모두 질문자인 경우 삭제 가능(예외)
    - [X] Answer 객체에서 삭제 처리할 수 있도록 리팩토링
- [X] QnAService 이력 저장 리팩토링
    - [X] 질문 삭제 시 이력이 남아야 한다.
        - [X] 질문 삭제 메서드 실행 시 삭제된 질문 이력을 반환한다.
        - [X] 답변 삭제 메서드 실행 시 삭제된 답변 이력을 반환한다.
        - [X] 답변 일급 컬렉션에서 삭제 메서드 실행 시 삭제된 답변 이력 컬렉션을 반환한다. 
        - [X] 질문 삭제 시 삭제된 모든 내용이 포함된 이력 컬렉션을 반환한다.
        - [X] 서비스 레이어에서 반환된 컬렉션을 저장한다.

## ~~Step2. Todo List~~ => `전부 폐기`
- 폐기 사유
    - 현재 구조는 객체를 선언한 이후 내부 상태를 변경하는 방식으로 설계 + 관심사 분리가 제대로 되어 있지 않음
    - 위 구조 상태에서는 볼링게임 진행을 마치 프레임이 자체적으로 진행하는 것처럼 되면서 설계가 어려움
    - 단순한 구조(생성자 적극 활용)로 변경 진행
    - + 마지막 프레임 요구사항을 제대로 파악 못했음
- Player
    - [X] 이름, Frame을 속성으로 갖는다.
    - [X] 첫 투구를 진행할 수 있다.
    - [X] 현재 진행중인 프레임의 숫자를 알 수 있다.
    - [ ] 투구해서 볼링 게임을 진행할 수 있다. (투구 후 현재 진행한 Frame의 상태를 확인할 수 있다.)
        - [X] 1 ~ 9 프레임에서 스트라이크로 종료 시 다음 Frame의 투구를 진행할 수 있다.
        - [X] 1 ~ 9 프레임에서 스트라이크가 아닌 경우 두번의 투구 후 다음 Frame을 진행 할 수 있다.
        - [ ] 투구 후 FrameStatus가 아니라 현재까지의 FrameResult 컬렉션을 내보내도록 리팩토링
        - [ ] 마지막 프레임(10 프레임)의 마지막 투구를 완료한 후에는 더이상 투구할 수 없다.
- Frame(interface)
    - 인터페이스로 추출
    - [X] 현재 프레임의 상태를 계산해서 FrameStatus 일급 컬렉션을 반환할 수 있음
    - [X] 현재 프레임이 마지막 프레임인지 알려줄 수 있음
    - [X] 현재 프레임의 마무리 여부를 알려줄 수 있음
- NormalFrame
    - [X] FrameResult, 다음 프레임(nextNormalFrame)을 속성으로 갖는다.
    - [X] 첫번째 투구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.
    - [X] 두번째 투구를 실행할 수 있다.
        - [X] 현재 FrameResult가 완료됐다면 실행할 수 없다.
    - [X] 현재 프레임에서 다음 프레임을 생성할 수 있다.
        - [X] FrameResult가 온전하지 않은 경우 다음 프레임으로 넘어갈 수 없다.
    - [X] 해당 프레임이 마지막 프레임인지 알려줄 수 있다.
    - [X] 현재 Frame의 상태를 알려줄 수 있다.
- FinalFrame
    - 마지막 Frame은 진행과 결과 모두 특이하기 때문에 별도 관리
    - 실제로는 StrikeFrameResult와 NormalFrameResult가 조합된 특수 프레임
    - [X] FrameResult 두개 (FirstFrameResult, SecondFrameResult)를 속성으로 갖는다.
    - [X] 첫 투구가 Strike인 경우
        - [X] 두번째 투구도 Strike이면 해당 프레임 마무리 (총 2회 투구, StrikeFrameResult * 2)
        - [X] 두번째 투구가 Strike가 아니면 한번 더 진행 (총 3회 투구, StrikeFrameResult * 1, NormalFrameResult * 1)
    - [ ] 첫 투구가 Strike가 아닌 경우
        - [ ] 두번째 투구가 Spare 처리한 경우 한번 더 진행 (총 3회 투구)
            - [ ] 세번째 투구가 Strike인 경우 온전하게 마무리 (NormalFrameResult * 1, StrikeFrameResult)
            - [ ] 세번째 투구가 Strike가 아닌 경우 반푼이 NormalFrameResult로 마무리 (NormalFrameResult * 2, 하나는 반쪼가리)
        - [ ] 두번째 투구가 Spare 처리를 못한 경우(미스인 경우) 해당 프레임 마무리 (총 2회 투구, NormalFrameResult * 1)
    - [ ] 상황에 맞는 현재 프레임 상태를 알려줄 수 있다.(Final 요구사항 분석 및 구현 후 마지막에 구현)
- FrameResult(interface)
    - 프레임 결과에 대한 관심사를 다형성으로 처리하기 위해 인터페이스 구현
    - 프레임 결과는 스트라이크와 그 외로 구분할 수 있다.
    - [X] Strike 인지 판단 할 수 있다.
    - [X] 해당 Frame의 종료 여부를 알려줄 수 있다.
    - [X] 해당 프레임이 마지막 프레임인지 알려줄 수 있다.
    - [ ] 미스 여부를 알려줄 수 있다.
    - [X] 현재 프레임 상태를 알려줄 수 있다.
        - [X] FrameStatus 컬렉션을 반환하도록 리팩토링
    - [X] Strike
        - [X] strike 여부를 물어보면 true를 반환한다.
        - [X] 완료 여부를 물어보면 true를 반환한다.
        - [X] 언제나 현재 프레임 상태는 Strike이다.
        - [X] 상태 확인 시 Strike 상태를 가진 길이 1짜리 컬렉션 반환 (일급 컬렉션 활용)
    - [X] Normal
        - [X] 첫번째 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.
            - [X] 첫번째 맞춘 핀의 수는 0 ~ 9 사이의 값을 갖는다.
        - [X] 두번째 맞춘 핀의 수를 입력받을 수 있다.
            - [X] 첫번째 맞춘 핀의 수와 두번째 맞춘 핀의 수의 합이 10을 넘을 수 없다.
        - [X] 첫번째, 두번째 던져서 맞춘 핀의 수를 객체로 리팩토링
        - [X] 상황에 맞는 현재 프레임 상태를 알려줄 수 있다.
            - [X] 아직 진행중일 때(첫번째 투구만 진행됐을 때)의 상태를 알려줄 수 있다.
            - [X] 완료됐을 때(두번째 투구까지 진행됐을 때)의 상태를 알려줄 수 있다.
        - [X] 변경된 인터페이스에 맞게 리팩토링 진행
- FrameResultFactory
    - [X] 각 프레임의 첫번째 투구로 맞춘 핀의 수를 입력받아서 FrameResult를 생성할 수 있다.
        - [X] 입력값이 10이면 Strike를 반환한다.
        - [X] 입력값이 0 ~ 9 사이면 일반 결과를 반환한다.
        - [X] 입력값이 0 ~ 10 범위를 벗어날 수 없다.
    - [X] 마지막 프레임의 첫 투구로 맞춘 핀의 수를 입력 받아서 마지막 프레임 결과를 생성할 수 있다.
- NumberOfHitPin (<- ThrowResult)
    - [X] 프레임에서 한 회에 던져서 맞춘 갯수를 나타낸다.
    - [X] 0 ~ 10 사이의 값을 갖는다.
    - [X] 두 개의 ThrowResult를 더할 수 있다.
    - [X] 비교 연산을 위해 Comparable 구현
- FrameStatus
    - 스트라이크, 거터, 미스, 일반 숫자 등의 상태를 표현하는 enum
    - [X] 숫자 한 개로 상태를 찾아서 반환할 수 있다. (스트라이크, 거터, 일반숫자)
    - [X] 숫자 두 개로 상태를 찾아서 반환할 수 있다. (스페어, 거터, 일반숫자 - 미스)
    - [X] 위의 기능들을 NumberOfHitPin을 전달받아서 처리하도록 리팩토링
- FrameStatuses
    - FrameStatus 일급 컬렉션
    - [X] FrameStatus 리스트를 전달받아서 객체를 생성할 수 있다.
    - [X] 사이즈가 4 이상인 컬렉션은 생성할 수 없다.   
- ThrowResults
    - 요구사항 재분석 결과 스트라이크 외에는 던질때마다 결과를 출력해야 함. (현재 객체와 관심사가 다름)
    - [X] 프레임에서 던져서 맞춘 갯수들을 관리하는 일급 컬렉션이다.
    - [X] 최대 2회까지 존재한다. (현재는 일단 마지막 프레임은 고려하지 않는다.)
    - [X] 던져서 맞춘 핀의 갯수 총합은 0 ~ 10을 벗어날 수 없다.
    - [X] 스트라이크인 경우 값이 10인 ThrowResult 한개만 관리한다.
    
## Step2. Todo List(new)
- NumberOfHitPin
    - 1회 투구로 맞춘 핀의 수를 나타낸다.
    - [X] 0 ~ 10 사이의 값으로 생성할 수 있다.
    - [X] 같은 타입끼리 더할 수 있다.
    - [X] 같은 타입끼리 대소 비교를 할 수 있다.
- FrameStatus (interface)
    - 해당 프레임의 결과를 나타낸다.
    - 현재 프레임 내에서 다음 단계로 진행 할 수 있는지 여부에 관심을 갖는다.
    - NumberOfHitPin들을 갖고 있으며, 해당 내용을 기반으로 점수를 계산할 수 있다.
    - 현재 상태를 기반으로 해당 프레임의 다음 투구 상황을 진행할 수 있다.
- NormalFrameStatus
    - NumberOfHitPin을 최대 두개 가질 수 있는 FrameResult의 구현체
    - [X] 초구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.
    - [X] 초구가 스트라이크인 경우 해당 프레임이 마무리된다.
    - [X] 초구가 스트라이크가 아닌 경우 1회 더 진행 후 프레임이 마무리된다.
- FinalFrameStatus
    - NumberOfHitPin을 최대 세개 가질 수 있는 FrameResult의 구현체
    - [X] 초구로 맞춘 핀의 수를 전달해서 객체를 생성할 수 있다.
    - [X] 초구가 스트라이크면 무조건 3회 투구 후 해당 프레임이 마무리된다.
    - [X] 초구가 스트라이크가 아닌 경우
        - [X] 스페어 처리 시 3회 투구 후 해당 프레임이 마무리된다.
        - [X] 스페어 처리 실패 시 2회 투구 후 해당 프레임이 마무리된다.
- Frame (interface)
    - 현재 프레임의 결과와 번호를 나타낸다
    - 현재 프레임에서 다음 프레임으로 진행 할 수 있는지 여부에 관심을 갖는다.
    - 현재 FrameResult를 기반으로 점수를 계산할 수 있다.
    - 현재 FrameResult 상태를 기반으로 다음 프레임을 진행할 수 있다.
- NormalFrame
    - 1 ~ 9 프레임을 나타내는 Frame 구현체
    - [X] 프레임 번호, NormalFrameStatus, 이전 프레임을 속성으로 갖는다.
    - [X] 현재 프레임이 마무리되지 않았을 때 현재 프레임을 진행할 수 있다.
        - [X] 마무리 된 프레임에 대해 다시 진행 시 예외 발생
    - [X] 현재 프레임이 마무리되면 다음 프레임으로 넘어갈 수 있다.
    - [ ] 현재 프레임 결과를 보여줄 수 있다.
- FinalFrame
    - 10 프레임을 나타내는 Frame 구현체

### 참고사항: 마지막 프레임 결과 계산
- 초구 스트라이크인 경우
    - 무조건 3회 던진다.
- 초구가 스트라이크 아닌 경우
    - 2구째에 미스나면 2회로 끝난다.
    - 그 외 무조건 3회 던진다.
