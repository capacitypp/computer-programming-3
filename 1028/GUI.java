import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame {
	private JLabel label;
	private JButton button;
	private JMenuBar menubar;

	public GUI() {
		label = new JLabel("Swingnの世界へようこそ!");
		button = new JButton("OK");
		String[] menuTitle = {"項目1", "項目2", "項目3"};
		menubar = new JMenuBar();
		JMenu menu = new JMenu("メニュー");
		for (int n = 0; n < menuTitle.length; n++)
			menu.add(new JMenuItem(menuTitle[n]));
		menubar.add(menu);

		add(label, BorderLayout.NORTH);
		add(button, BorderLayout.SOUTH);
		ArcGraphics arcG = new ArcGraphics();
		add(arcG, BorderLayout.CENTER);
		setJMenuBar(menubar);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 200);
		setSize(300, 300);
		setVisible(true);

		button.addActionListener(new ButtonActionListener());
		menu = menubar.getMenu(0);
		for (int i = 0; i < menu.getItemCount(); i++) {
			JMenuItem item = menu.getItem(i);
			item.addActionListener(new MenuActionListener());
		}
	}

	class ArcGraphics extends JPanel {
		private Color col[] = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW};
		private String itemTitle[] = {"項目1", "項目2", "項目3", "項目4", "項目5", "項目6"};

		public ArcGraphics() {
			setSize(300, 250);
		}

		public void paint(Graphics g) {
			int offset = 0, deg = 30;
			for (int i = 0; i < itemTitle.length; i++) {
				g.setColor(col[i]);
				g.fillArc(75, 50, 200, 200, offset, deg);
				g.drawString(itemTitle[i], 10, 50 + 20 * i);
				offset += deg;
			}
		}
	}

	class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			label.setText("ボタンが押されました！");
		}
	}

	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			label.setText(e.getActionCommand() + "のメニューが選択されました！");
		}
	}

	public static void main(String[] args) {
		GUI frame = new GUI();
	}
}

