/* 제목 : 13-7 wait(), notify()를 이용한 바 채우기
 * 학번 : 201611321
 * 이름 : 임민수
 */
package SW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyLabel extends JLabel {
	int barSize = 0; // 바의 크기
	int maxBarSize;

	public MyLabel(int maxBarSize) {
		this.maxBarSize = maxBarSize;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.MAGENTA);
		
		int width = (int) (((double) (this.getWidth())) / maxBarSize * barSize);
		
		if (width == 0) {
			return;
		}
		
		g.fillRect(0, 0, width, this.getHeight());
	}//맨처음 바 그리기

	synchronized void fill() {
		if (barSize == maxBarSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		barSize++;
		repaint(); // 바 다시 그리기
		notify();
	}//아무키나 입력시 바그리기

	synchronized void consume() {
		if (barSize == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				return;
			}
		}
		
		barSize--;
		repaint(); //바 다시 그리기
		notify();
	}//바 길이가 0이면 멈추고 시간이 지나면 바 크기 줄이기
}

class ConsumerThread extends Thread {
	MyLabel bar;

	public ConsumerThread(MyLabel bar) {
		this.bar = bar;
	}

	public void run() {
		while (true) {
			try {
				sleep(200);
				bar.consume();
			} catch (InterruptedException e) {
				return;
			}
		}
	}
}

public class TabAndThreadEx extends JFrame {
	MyLabel bar = new MyLabel(100);

	public TabAndThreadEx(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		
		bar.setBackground(Color.ORANGE);
		bar.setOpaque(true);
		bar.setLocation(20, 50);
		bar.setSize(300, 20);
		c.add(bar);
		
		c.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				bar.fill();
			}
		});//키보드 인식이 들어오면 바 그리기
		
		setSize(350, 200);
		setVisible(true);
		
		c.requestFocus();
		ConsumerThread th = new ConsumerThread(bar);
		
		th.start(); //스레드 시작
	}

	public static void main(String[] args) {
		new TabAndThreadEx("아무키나 빨리 눌러 바 채우기");
	}
}
//몇몇 게임들에서 찾아볼 수 있는 "아무키를 연타하세요"와 비슷한 프로그램
//아무키나 입력시 화면속의 바가 채워지다가 시간이 지나면 바의 크기가 줄어드는 프로그램