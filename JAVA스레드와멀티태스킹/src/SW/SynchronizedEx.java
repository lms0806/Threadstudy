/* 제목 : 공유 집계판 사레를 코딩
 * 학번 : 201611321
 * 이름 : 임민수
 */
package SW;

public class SynchronizedEx {
	public static void main(String[] args) {
		SharedBoard board = new SharedBoard();
		Thread th1 = new StudentThread("kitae", board);
		Thread th2 = new StudentThread("hyosoo", board);
		th1.start();
		th2.start();
	}
}

class SharedBoard {
	private int sum = 0; //집계판의 합

	synchronized public void add() {
		int n = sum;
		Thread.yield(); //현재 실행 중인 스레드 양보
		n += 10; 
		sum = n; //10 증가한 값을 집계합에 기록
		System.out.println(Thread.currentThread().getName() + " : " + sum);
	}

	public int getSum() {
		return sum;
	}
}

class StudentThread extends Thread {
	private SharedBoard board; //집계판의 주소

	public StudentThread(String name, SharedBoard board) {
		super(name);
		this.board = board;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			board.add();
		}
	}
}
//sum값은 그대로 유지하되 앞부분이 달라지게 하도록 스레드를 2개를 실행시키는 프로그램