import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GUI extends JFrame {
	private JLabel label;
	private JButton button;
	private JMenuBar menubar;

	public GUI() {
		String[] menuTitles = {"buying", "maint", "persons", "luggage", "safety", "eval"};
		String[][] subTitles = {
			{"vhigh", "high", "med", "low"},
			{"vhigh", "high", "med", "low"},
			{"2", "3", "4", "5more"},
			{"2", "4", "more"},
			{"small", "med", "big"},
			{"low", "med", "high"},
			{"unacc", "acc", "good", "vgood"},
		};
		String[] logicalMenuTitles = {"論理和", "論理積"};

		label = new JLabel("Swingnの世界へようこそ!");
		add(label, BorderLayout.NORTH);

		button = new JButton("OK");
		button.addActionListener(new ButtonActionListener());
		add(button, BorderLayout.SOUTH);

		menubar = new JMenuBar();
		JMenu menu = new JMenu("項目");
		for (int n = 0; n < menuTitles.length; n++) {
			JMenu subMenu = new JMenu(menuTitles[n]);
			for (int i = 0; i < subTitles[n].length; i++) {
				JMenuItem item = new JMenuItem(subTitles[n][i]);
				item.addActionListener(new MenuActionListener(menuTitles[n]));
				subMenu.add(item);
			}
			menu.add(subMenu);
		}
		menubar.add(menu);

		menu = new JMenu("論理演算");
		for (int n = 0; n < logicalMenuTitles.length; n++) {
			JMenuItem item = new JMenuItem(logicalMenuTitles[n]);
			item.addActionListener(new LogicalMenuActionListener());
			menu.add(item);
		}
		menubar.add(menu);

		setJMenuBar(menubar);

		ArcGraphics arcG = new ArcGraphics();
		add(arcG, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 200);
		setSize(300, 300);
		setVisible(true);

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
		private String title;

		MenuActionListener(String title) {
			this.title = title;
		}

		public void actionPerformed(ActionEvent e) {
			label.setText(title + " : " + e.getActionCommand() + "のメニューが選択されました！");
		}
	}

	class LogicalMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			label.setText(e.getActionCommand());
		}
	}

	public static void main(String[] args) {
		GUI frame = new GUI();
	}
}

