import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
	private JLabel label;
	private JButton button;
	private JMenuBar menubar;

	private ArrayList<String> query;
	private JRadioButtonMenuItem[] radioButtons;

	public GUI() {
		query = new ArrayList<String>();

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
		radioButtons = new JRadioButtonMenuItem[logicalMenuTitles.length];
		ButtonGroup group = new ButtonGroup();
		for (int n = 0; n < logicalMenuTitles.length; n++) {
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(logicalMenuTitles[n]);
			item.addActionListener(new LogicalMenuActionListener());
			if (n == 0)
				item.setSelected(true);
			menu.add(item);
			group.add(item);
			radioButtons[n] = item;
		}
		menubar.add(menu);

		menu = new JMenu("操作");
		JMenuItem item = new JMenuItem("リセット");
		item.addActionListener(new ResetMenuActionListener());
		menu.add(item);
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
			String string = title + "=" + e.getActionCommand();
			label.setText(string);
			if (query.size() != 0)
				query.add((getLogicalMenuIndex() == 0) ? "or" : "and");
			query.add(string);

			string = "";
			for (String str : query)
				string += str + " ";
			label.setText(string);
		}
	}

	private int getLogicalMenuIndex() {
		for (int i = 0; i < radioButtons.length; i++)
			if (radioButtons[i].isSelected())
				return i;
		return 0;
	}

	class LogicalMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ((query.size() == 0) && (getLogicalMenuIndex() != 0))
				radioButtons[0].setSelected(true);
		}
	}

	class ResetMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			query = new ArrayList<String>();
			label.setText("");
		}
	}

	public static void main(String[] args) {
		GUI frame = new GUI();
	}
}

