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

		label = new JLabel("Swingnの世界へようこそ!");
		button = new JButton("OK");
		menubar = new JMenuBar();
		JMenu menu = new JMenu("項目");
		for (int n = 0; n < menuTitles.length; n++) {
			JMenu subMenu = new JMenu(menuTitles[n]);
			for (int i = 0; i < subTitles[n].length; i++) {
				JMenuItem item = new JMenuItem(subTitles[n][i]);
				subMenu.add(item);
			}
			menu.add(subMenu);
		}
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

