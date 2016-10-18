import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame {
	private JLabel labelQuery;
	private JButton button;
	private JMenuBar menubar;
	private ArcGraphics arcG;

	private ArrayList<String> query;
	private JRadioButtonMenuItem[] radioButtons;
	private Result result;

	public GUI() {
		query = new ArrayList<String>();
		result = null;

		String[] menuTitles = {"buying", "maint", "doors", "persons", "luggage", "safety", "eval"};
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

		labelQuery = new JLabel("query : ");
		add(labelQuery, BorderLayout.NORTH);

		button = new JButton("実行");
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

		arcG = new ArcGraphics();
		add(arcG, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(400, 200);
		setSize(300, 300);
		setVisible(true);

	}

	class ArcGraphics extends JPanel {
		private Color col[] = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN};
		private String itemTitle[] = {"unacc", "acc", "good", "vgood"};

		public ArcGraphics() {
			setSize(300, 250);
		}

		public void paint(Graphics g) {
			if (result == null)
				return;
			int sum = 0;
			for (String key : itemTitle)
				sum += result.get(key);
			int[] degs = new int[itemTitle.length];
			for (int i = 0; i < degs.length; i++)
				degs[i] = result.get(itemTitle[i]) * 360 / sum;
			sum = 0;
			for (int deg : degs)
				sum += deg;
			degs[degs.length - 1] += 360 - sum;
			int offset = 0, deg;
			for (int i = 0; i < degs.length; i++) {
				g.setColor(col[i]);
				deg = degs[i];
				g.fillArc(75, 50, 200, 200, offset, deg);
				g.drawString(itemTitle[i], 10, 50 + 20 * i);
				offset += deg;
			}
		}
	}

	class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Car car = new Car("car.csv");
			result = car.analyze(query.toArray(new String[0]));
			arcG.updateUI();
		}
	}

	class MenuActionListener implements ActionListener {
		private String title;

		MenuActionListener(String title) {
			this.title = title;
		}

		public void actionPerformed(ActionEvent e) {
			String string = title + "=" + e.getActionCommand();
			labelQuery.setText(string);
			if (query.size() != 0)
				query.add((getLogicalMenuIndex() == 0) ? "or" : "and");
			query.add(string);

			string = "query : ";
			for (String str : query)
				string += str + " ";
			labelQuery.setText(string);
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
			radioButtons[0].setSelected(true);
			labelQuery.setText("query : ");
			result = null;
			arcG.updateUI();
		}
	}

	public static void main(String[] args) {
		GUI frame = new GUI();
	}
}

