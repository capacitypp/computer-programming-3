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

		/* メニューに項目を登録する */
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

		/* メニューに論理演算の種類を登録する */
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

		/* メニューにリセット・書込・読込を登録する */
		menu = new JMenu("操作");
		JMenuItem item = new JMenuItem("リセット");
		item.addActionListener(new ResetMenuActionListener());
		menu.add(item);
		item = new JMenuItem("書込");
		item.addActionListener(new ActionListener(){
			private JFrame frame;
			@Override
			public void actionPerformed(ActionEvent e) {
				if (result == null)
					return;
				JFileChooser filechooser = new JFileChooser();

				/* 書き込み対象のファイルを選択する */
				int selected = filechooser.showSaveDialog(frame);
				if (selected != JFileChooser.APPROVE_OPTION)
					return;

				Result.write(result, filechooser.getSelectedFile().getAbsolutePath());
			}
			public ActionListener setFrame(JFrame frame) {
				this.frame = frame;
				return this;
			}
		}.setFrame(this));
		menu.add(item);
		item = new JMenuItem("読込");
		item.addActionListener(new ActionListener(){
			private JFrame frame;
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();

				/* 読み込み対象のファイルを選択する */
				int selected = filechooser.showOpenDialog(frame);
				if (selected != JFileChooser.APPROVE_OPTION)
					return;

				labelQuery.setText("query : ");
				result = Result.read(filechooser.getSelectedFile().getAbsolutePath());
				arcG.updateUI();
			}
			public ActionListener setFrame(JFrame frame) {
				this.frame = frame;
				return this;
			}
		}.setFrame(this));
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

		/* 円グラフの描画処理 */
		public void paint(Graphics g) {
			if (result == null)
				return;
			int sum = 0;
			for (String key : itemTitle)
				sum += result.get(key);
			int[] degs = new int[itemTitle.length];
			/* 検索結果を角度に変換する */
			for (int i = 0; i < degs.length; i++)
				degs[i] = result.get(itemTitle[i]) * 360 / sum;
			sum = 0;
			for (int deg : degs)
				sum += deg;
			/* int型の切り捨て誤差を補正する */
			degs[degs.length - 1] += 360 - sum;
			int offset = 0, deg;
			/* 円グラフを描画する */
			for (int i = 0; i < degs.length; i++) {
				g.setColor(col[i]);
				deg = degs[i];
				g.fillArc(75, 50, 200, 200, offset, deg);
				/* 各色の意味を文字列として表示する */
				g.drawString(itemTitle[i], 10, 50 + 20 * i);
				offset += deg;
			}
		}
	}

	class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/* 検索する */
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
			/* 検索文字列を更新する */
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
		/* 論理和・論理積のどちらが選択されているかを、0か1で反す */
		for (int i = 0; i < radioButtons.length; i++)
			if (radioButtons[i].isSelected())
				return i;
		return 0;
	}

	class LogicalMenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/* 一つも条件を設定していない状態では、論理積を選択できないようにする */
			if ((query.size() == 0) && (getLogicalMenuIndex() != 0))
				radioButtons[0].setSelected(true);
		}
	}

	class ResetMenuActionListener implements ActionListener {
		/* リセット処理 */
		public void actionPerformed(ActionEvent e) {
			/* 検索条件をリセットする */
			query = new ArrayList<String>();
			/* 論理和を選択する */
			radioButtons[0].setSelected(true);
			/* 表示している検索条件をリセットする */
			labelQuery.setText("query : ");
			/* 検索結果をnullに設定（描画処理のため） */
			result = null;
			/* 円グラフを再描画 */
			arcG.updateUI();
		}
	}

	public static void main(String[] args) {
		GUI frame = new GUI();
	}
}

