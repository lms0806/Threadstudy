/* 제목 : 13-2 Runnable 인터페이스를 이용하여 1초 단위로 출력하는 타이머 스레드 만들기
 * 학번 : 201611321
 * 이름 : 임민수
 */
package SW;

import java.awt.*;

import javax.swing.*;

class TimerRunnable implements Runnable{
	private JLabel timerLabel;
	
	public TimerRunnable(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}
	
	@Override
	public void run() {
		int n = 0;
		while(true) {
			timerLabel.setText(Integer.toString(n));//라벨에 숫자 대입
			n++;//1초마다 숫자 증가
			
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException e) {
				return;
			}//1초 세기
		}
	}
}

public class RunnableTimerEx extends JFrame{
	public RunnableTimerEx() {
		setTitle("Runnable를 상속받은 타이머 스레드 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));//라벨 폰트 설정
		c.add(timerLabel);
		
		TimerRunnable runnable = new TimerRunnable(timerLabel);
		Thread th = new Thread(runnable);
		
		setSize(300,170);
		setVisible(true);
		
		th.start();//쓰레드 시작
	}
	public static void main(String[] args) {
		new RunnableTimerEx();
	}
}
