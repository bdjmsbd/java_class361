package account;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 가계부를 관리하는 프로그램을 작성하세요.
		// accountbook
		// 메뉴
		// 1. 내역 입력
		// 2. 내역 수정
		// 3. 내역 삭제
		// 4. 조회
		// 5. 종료
		// 메뉴 선택 :

		// <내역 입력>
		// 날짜 : 2024-06-10
		// 수입/지출 : `수입
		// 분류 (월급, 용돈, 부수입): `월급
		// 금액 : `3000000
		// 내용 : `6월 월급

		// <내역 수정>
		// 날짜 : `20024-06-10
		// 1. 20024-06-10 | 수입 | 월급 | 3000000 | 6월 월급
		// ..
		// 내역 선택 : `1
		// 날짜 : `2024-06-10
		// 수입/지출 : `수입
		// 분류 (월급, 용돈, 부수입): 월급
		// 금액 : 2800000
		// 내용 : 6월 월급
		// [수정 완료]

		// <내역 삭제>
		// 날짜 : `20024-06-10
		// 1. 20024-06-10 | 수입 | 월급 | 2800000 | 6월 월급
		// ..
		// 내역 선택 : `1
		// [내역이 삭제되었습니다.]

		// <내역 조회>
		// (날짜순으로 정렬)
		// 20024-06-10 | 수입 | 월급 | 2800000 | 6월 월급


		AccountBookManager am = new AccountBookManager();
		am.run();

	}

}
