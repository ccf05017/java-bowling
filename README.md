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

## Step2. Todo List
- Frame
    - [X] 투구 결과들(throwResults), 다음 프레임(nextFrame)을 속성으로 갖는다.
    - [ ] 현재 프레임에서 다음 프레임을 생성할 수 있다.
        - [X] ThrowResults를 입력받아서 생성할 수 있다.
        - [ ] 스트라이크를 처리해서 생성할 수 있다.
- Frames
    - [ ] Frame을 관리하는 일급 컬렉션
    - [ ] 관리하는 Frame은 최대 10개를 넘지 못한다. 
- ThrowResult
    - [X] 프레임에서 한 회에 던져서 맞춘 갯수를 나타낸다.
    - [X] 0 ~ 10 사이의 값을 갖는다.
    - [X] 두 개의 ThrowResult를 더할 수 있다.
    - [X] 비교 연산을 위해 Comparable 구현
- ThrowResults
    - [X] 프레임에서 던져서 맞춘 갯수들을 관리하는 일급 컬렉션이다.
    - [X] 최대 2회까지 존재한다. (현재는 일단 마지막 프레임은 고려하지 않는다.)
    - [X] 던져서 맞춘 핀의 갯수 총합은 0 ~ 10을 벗어날 수 없다.
    - [X] 스트라이크인 경우 값이 10인 ThrowResult 한개만 관리한다.
- FrameResult
    - [ ] 스트라이크, 스페어, 미스, 거터를 갖는 enum이다.
