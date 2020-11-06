/* 제목 : 13-3 깜박이는 문자열을 가진 레이블 만들기
 * 학번 : 201611321
 * 이름 : 임민수
 */
package SW;

import java.awt.*;

import javax.swing.*;

class FlickeringLabel extends JLabel implements Runnable{
	private long delay;
	
	public FlickeringLabel(String text, long delay) {
		super(text);
		this.delay = delay;
		setOpaque(true);
		Thread th = new Thread(this);
		th.start();
	}
	
	@Override
	public void run() {
		int n = 0;
		while(true) {
			if(n == 0) {
				setBackground(Color.YELLOW);
			}
			else {
				setBackground(Color.GREEN);
			}
			
			if(n == 0) {
				n = 1;
			}
			else {
				n = 0;
			}//n을 0과 1로 반복시키면서 배경색을 바꾼다.
			
			try {
				Thread.sleep(delay);
			}
			catch(InterruptedException e) {
				return;
			}//입력한 숫자맞큼 초 세기
		}
	}
}
public class FlickeringLabelEx extends JFrame{
	public FlickeringLabelEx() {
		setTitle("FlickeringLabelEx 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		FlickeringLabel fLabel = new FlickeringLabel("깜박", 500);//"깜박"이라는 글자가 0.5초마다 배경색이 바뀜
		
		JLabel label = new JLabel("안깜박");
		
		FlickeringLabel fLable2 = new FlickeringLabel("여기도 깜박",300);//"여기도 깜박"이라는 글자가 0.3초마다 배경색이 바뀜
		
		c.add(fLabel);
		c.add(label);
		c.add(fLable2);
		
		setSize(300,150);
		setVisible(true);
	}
	public static void main(String[] args) {
		new FlickeringLabelEx();
	}
}
//"깜박", "여기도 깜박" 이 부분의 딜레이를 다르게 줘서 배경색을 바꾸는 프로그램