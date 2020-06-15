# 볼링 게임 점수판
## 진행 방법
* 볼링 게임 점수판 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step1. Todo List
- [X] 질문 삭제 시 데이터의 상태를 삭제 상태로 변경한다.
    - [X] 로그인 사용자와 질문한 사람이 다른 경우 예외 발생
    - [X] Answers 일급컬렉션에 삭제 메시지 전달
        - [X] 일급 컬렉션에 속한 모든 질문을 한번에 삭제할 수 있다.
        - [X] 답변이 한개라도 있는 경우 삭제 시도 시 예외 발생(기본)
            - [X] 답변이 있으나, 답변자가 모두 질문자인 경우 삭제 가능(예외)
    - [X] Answer 객체에서 삭제 처리할 수 있도록 리팩토링
- [ ] 질문 삭제 시 이력이 남아야 한다.
- [ ] 답변 삭제 시 이력이 남아야 한다.
