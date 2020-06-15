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
    - [ ] 로그인 사용자와 질문한 사람이 다른 경우 예외 발생
    - [ ] 답변이 있는 질문 삭제 시도 시 예외 발생
    - [ ] 답변자 중 한명이라도 질문자와 다른 경우 삭제 시도 시 예외 발생
- [ ] 질문 삭제 시 답변도 삭제되야 한다.
    - [ ] 질문자와 답변자가 다른 질문 삭제 시도 시 예외 발생
- [ ] 질문 삭제 시 이력이 남아야 한다.
- [ ] 답변 삭제 시 이력이 남아야 한다.
