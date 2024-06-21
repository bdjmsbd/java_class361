package day18.homework;

import java.util.concurrent.ScheduledExecutorService;

public class Main {
	/* 일정을 관리하는 프로그램을 작성하세요.
	 * -----------------
	 * 쉬운 버전 : 한사람의 일정을 관리하는 프로그램
	 * -----------------
	 * 메뉴
	 * 1. 일정 추가
	 * 2. 일정 수정
	 * 3. 일정 삭제
	 * 4. 일정 확인
	 * 5. 프로그램 종료
	 * 메뉴 선택 : 1
	 * -----------------
	 * 날짜(yyyy-MM-dd hh:mm) : 2024-06-21 17:50
	 * 일정 : 퇴근
	 * 상세 : 없음
	 * -----------------
	 * 일정이 추가되었습니다.
	 * -----------------
	 * 메뉴
	 * 1. 일정 추가
	 * 2. 일정 수정
	 * 3. 일정 삭제
	 * 4. 일정 확인
	 * 5. 프로그램 종료
	 * 메뉴 선택 : 2
	 * -----------------
	 * 날짜(yyyy-MM-dd) : 2024-06-21
	 * -----------------
	 * 2024-06-21 일정 리스트
	 * -----------------
	 * 1. 2024-06-21 17:50 퇴근 없음
	 * -----------------
	 * 수정할 일정 선택 : 1
	 * -----------------
	 * 날짜(yyyy-MM-dd hh:mm) : 2024-06-21 17:51
	 * 일정 : 퇴근
	 * 상세 : 없음
	 * -----------------
	 * 수정이 완료 되었습니다.
	 * -----------------
	 * 메뉴
	 * 1. 일정 추가
	 * 2. 일정 수정
	 * 3. 일정 삭제
	 * 4. 일정 확인
	 * 5. 프로그램 종료
	 * 메뉴 선택 : 3
	 * -----------------
	 * 날짜(yyyy-MM-dd) : 2024-06-21
	 * -----------------
	 * 2024-06-21 일정 리스트
	 * -----------------
	 * 1. 2024-06-21 17:50 퇴근 없음
	 * -----------------
	 * 삭제할 일정 선택 : 1
	 * -----------------
	 * 삭제되었습니다.
	 * -----------------
	 * 메뉴
	 * 1. 일정 추가
	 * 2. 일정 수정
	 * 3. 일정 삭제
	 * 4. 일정 확인
	 * 5. 프로그램 종료
	 * 메뉴 선택 : 4
	 * -----------------
	 * 날짜(yyyy-MM-dd) : 2024-06-21
	 * -----------------
	 * 2024-06-21 일정 리스트
	 * -----------------
	 * 1. 2024-06-21 17:50 퇴근 없음
	 * -----------------
	 * 메뉴로 가시려면 엔터를 입력하세요.
	 * -----------------
	 * 메뉴
	 * 1. 일정 추가
	 * 2. 일정 수정
	 * 3. 일정 삭제
	 * 4. 일정 확인
	 * 5. 프로그램 종료
	 * 메뉴 선택 : 5
	 * -----------------
	 * 프로그램을 종료합니다.
	 * -----------------
	 * */
	
	/* 일정을 관리하는 프로그램을 작성하세요.
	 * -----------------
	 * 어려운 버전 : 여러 사람의 일정을 관리하는 프로그램
	 * -----------------
	 * 메인 메뉴
	 * 1. 회원 관리
	 * 2. 일정관리 
	 * 3. 프로그램 종료
	 * -----------------
	 * 메뉴 선택 : 1
	 * -----------------
	 * 회원 메뉴
	 * 1. 회원 추가
	 * 2. 회원 수정
	 * 3. 회원 삭제
	 * 4. 이전으로
	 * -----------------
	 * 메뉴 선택 : 1
	 * -----------------
	 * 추가할 회원 정보 입력
	 * 아이디 : abc123 
	 * 이름 : 홍길동
	 * -----------------
	 * 회원이 추가됐습니다.
	 * -----------------
	 * 회원 메뉴
	 * 1. 회원 추가
	 * 2. 회원 수정
	 * 3. 회원 삭제
	 * 4. 이전으로
	 * -----------------
	 * 메뉴 선택 : 2
	 * -----------------
	 * 아이디 : abc123
	 * -----------------
	 * 수정할 이름 : 고길동
	 * -----------------
	 * 회원 정보를 수정했습니다.
	 * -----------------
	 * 회원 메뉴
	 * 1. 회원 추가
	 * 2. 회원 수정
	 * 3. 회원 삭제
	 * 4. 이전으로
	 * -----------------
	 * 메뉴 선택 : 3
	 * -----------------
	 * 아이디 : abc123
	 * -----------------
	 * 회원 정보를 삭제했습니다.
	 *  -----------------
	 * 회원 메뉴
	 * 1. 회원 추가
	 * 2. 회원 수정
	 * 3. 회원 삭제
	 * 4. 이전으로
	 * -----------------
	 * 메뉴 선택 : 4
	 * -----------------
	 * 메인 메뉴
	 * 1. 회원 관리
	 * 2. 일정관리 
	 * 3. 프로그램 종료
	 * -----------------
	 * 메뉴 선택 : 2
	 * -----------------
	 * 아이디 : admin
	 * -----------------
	 * 등록되지 않은 회원입니다.
	 * -----------------
	 * 메인 메뉴
	 * 1. 회원 관리
	 * 2. 일정관리 
	 * 3. 프로그램 종료
	 * -----------------
	 * 메뉴 선택 : 2
	 * -----------------
	 * 아이디 : asd123
	 * -----------------
	 * 일정 메뉴
	 * 1. 일정 추가
	 * 2. 일정 수정
	 * 3. 일정 삭제
	 * 4. 일정 확인
	 * 5. 이전으로
	 * 메뉴 선택 : 3
	 * -----------------
	 * 쉬운 버전과 동일
	 * */
	public static void main(String[] args) {
	
		MemberScheduleManager ms = new MemberScheduleManager();
		ms.run();
	}

}
