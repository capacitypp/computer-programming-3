import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimpleGUI2 extends JFrame {
	private JLabel label;
	private JButton button;
	private JMenuBar menubar;

	public SimpleGUI2() {
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
		SimpleGUI2 frame = new SimpleGUI2();
	}
}

