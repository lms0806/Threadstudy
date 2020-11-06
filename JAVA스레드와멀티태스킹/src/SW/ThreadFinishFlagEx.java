/* 제목 : 13-6 flag를 이용한 스레드 강제 종료
 * 학번 : 201611321
 * 이름 : 임민수
 */
package SW;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class RandomThread extends Thread {
	private Container contentPane;
	private boolean flag = false; //flag를 통하여 스레드 종료 표시

	public RandomThread(Container contentPane) {
		this.contentPane = contentPane;
	}

	void finish() { //스레드 종료 명령을 flag에 표시
		flag = true;
	}

	@Override
	public void run() {
		while (true) {
			int x = ((int) (Math.random() * contentPane.getWidth()));
			int y = ((int) (Math.random() * contentPane.getHeight()));//JFrame의 크기 면적 가져오기
			
			JLabel label = new JLabel("Java");
			label.setSize(80, 30);
			label.setLocation(x, y);
			
			contentPane.add(label);
			contentPane.repaint();
			
			try {
				Thread.sleep(300);
				if (flag == true) {
					contentPane.removeAll();
					
					label = new JLabel("finish");
					label.setSize(80, 30);
					label.setLocation(100, 100);
					label.setForeground(Color.RED);
					contentPane.add(label);
					contentPane.repaint();
					
					return; //스레드 종료
				}
			} catch (InterruptedException e) {
				return;
			}//0.3초마다 Java라는 글자를 JFrame창에 띄움
		}
	}
}

public class ThreadFinishFlagEx extends JFrame {
	private RandomThread th;
	public ThreadFinishFlagEx() {
		setTitle("ThreadFinishFlagEx 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				th.finish(); //RandomThread 스레드 종료 명령
			}
		});
		
		setSize(300, 200);
		setVisible(true);
		
		th = new RandomThread(c); //스레드 생성
		th.start(); //스레드 동작시킴
	}

	public static void main(String[] args) {
		new ThreadFinishFlagEx();
	}
}
//"Java"라는 글자를 화면의 크기에 맞춰진 곳에 랜덤으로 띄워 표시한다.
//마우스로 클릭시 더이상 "Java"라는 글자가 띄워지지 않고 화면이 정리되고 "Finish"라는 글자가 뜨면서 끝나는 프로그램 