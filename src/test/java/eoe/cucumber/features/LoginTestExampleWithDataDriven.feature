@interface
Feature: 데이터드리븐예제_서비스포탈로그인
Scenario Outline: 로그인비밀번호데이터드리븐
	Given eoe 서비스 포탈에 접속한다
    When 로그인아이디 "<userid>", 비밀번호 "<userpw>"로 로그인한다
    Then 에러메시지"Bad Credential"이 표시된다 
    Then 로그인페이지가 계속 표시된다
Examples:
	|	userid				|	userpw					|
	|	dd_userid01	|	onlycharacter	|
	|	dd_userid02	|	123456789012	|
	|	dd_userid03	|	short						|


  
  
	