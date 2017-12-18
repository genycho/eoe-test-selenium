@interface
Feature: 서비스포탈로그인
  Scenario: 정상로그인
    Given eoe 서비스 포탈에 접속한다
    When 로그인아이디 "service-portal", 비밀번호 "secret"로 로그인한다
    Then 로그인 성공 메시지가 표시된다 
    Then 사용자목록조회페이지가표시된다
  
  Scenario: 존재하지않는아이디  
    Given eoe 서비스 포탈에 접속한다
    When 로그인아이디 "not_exist_id", 비밀번호 "secret"로 로그인한다
    Then 에러메시지"Bad Credential"이 표시된다 
    Then 로그인페이지가 계속 표시된다
    
  Scenario: 맞지않는비밀번호
    Given eoe 서비스 포탈에 접속한다
    When 로그인아이디 "service-portal", 비밀번호 "wrongpw!"로 로그인한다
    Then 에러메시지"Bad Credential"이 표시된다 
    Then 로그인페이지가 계속 표시된다

Scenario: 비밀번호데이터드리븐
    Given eoe 서비스 포탈에 접속한다
    When 로그인아이디 "<userid>", 비밀번호 "<userpw>"로 로그인한다
    Then 에러메시지"Bad Credential"이 표시된다 
    Then 로그인페이지가 계속 표시된다
