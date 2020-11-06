/* 제목 : 13-1 Thread를 상속받아 1초 단위의 타이머 만들기
 * 학번 : 201611321
 * 이름 : 임민수
 */
package SW;

import java.awt.*;
import javax.swing.*;

class TimerThread extends Thread{
	private JLabel timerLabel;
	
	public TimerThread(JLabel timerLabel) {
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

public class ThreadTimerEx extends JFrame{
	public ThreadTimerEx() {
		setTitle("Thread를 상속받은 타이머 스레드 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));//라벨 폰트 설정
		c.add(timerLabel);
		
		TimerThread th = new TimerThread(timerLabel);
		
		setSize(300,170);
		setVisible(true);
		
		th.start();//쓰레드 시작
	}
	public static void main(String[] args) {
		new ThreadTimerEx();
	}
}
//1초마다 숫자 1씩 증가시켜서 JFrame에 띄워서 보여주는 프로그램