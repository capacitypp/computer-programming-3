import java.awt.*;
import java.awt.Color;
import javax.swing.*;

public class SimpleGUI extends JFrame {
	private JLabel label;
	private JButton button;
	private JMenuBar menubar;

	public SimpleGUI() {
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
	}

	public static void main(String[] args) {
		SimpleGUI frame = new SimpleGUI();
	}
}

