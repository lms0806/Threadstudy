/* 제목 : 13-5 타이머 스레드 강제 종료
 * 학번 : 201611321
 * 이름 : 임민수
 */

package SW;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class TimerRunnables implements Runnable{
	private JLabel timerLabel;
	
	public TimerRunnables(JLabel timerLabel) {
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
public class ThreadInterruptEx extends JFrame {
	private Thread th;
	public ThreadInterruptEx() {
		setTitle("hreadInterruptEx 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JLabel timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));//라벨 폰트 설정
		TimerRunnable runnable = new TimerRunnable(timerLabel);
		Thread th = new Thread(runnable);
		
		c.add(timerLabel);
		
		JButton btn = new JButton("kill Timer");
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				th.interrupt(); // 타이머 스레드 강제 종료
				JButton btn = (JButton)e.getSource();
				btn.setEnabled(false); // 버튼 비활성화
			}
		});//버튼 클릭시 interrupt를 줘서 정지
		
		c.add(btn);
		
		setSize(300,170);
		setVisible(true);
		
		th.start();//쓰레드 시작
	}
	public static void main(String[] args) {
		new ThreadInterruptEx();
	}
}
//1번예제 소스에 버튼을 추가해 버튼 클릭시 interrupt를 줘서 스레드를 정지시킨다.