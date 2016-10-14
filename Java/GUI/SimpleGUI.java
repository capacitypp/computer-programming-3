import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;

public class SimpleGUI extends JFrame {
    private JLabel label;
    private JButton button;
    private JMenuBar menubar;
	
    public SimpleGUI () {
        //コンポーネントの作成
        label = new JLabel("Swing の世界へようこそ！"); // テキストを表示する GUI 部品
        button = new JButton("OK"); // ボタンを表示する GUI 部品
        String[] menuTitle = {"項目１", "項目２", "項目３"}; // メニューの表示項目
        String[] subTitle = {"詳細１", "詳細２"}; // サブメニューの表示項目
        menubar = new JMenuBar(); // メニューバーの生成
        JMenu menu = new JMenu("メニュー"); // メニューの生成
        for (int n=0; n<menuTitle.length;n++) {
            // menu.add(new JMenuItem(menuTitle[n])); // メニュー項目の登録
            JMenu subMenu = new JMenu(menuTitle[n]);
            for(int s=0; s<subTitle.length; s++) {
                JMenuItem item = new JMenuItem(subTitle[s]);
                subMenu.add(item); // サブメニュー項目の登録
                item.addActionListener(new MenuActionListener()); // 各アイテムにリスナを登録
            }
            menu.add(subMenu); // サブメニューの登録
        }
        menubar.add(menu); // メニューをメニューバーに追加
        
        add(label, BorderLayout.NORTH); // ラベルをコンテナに追加
        add(button, BorderLayout.SOUTH); // ボタンをコンテナに追加
        add(new RadioButtonSelector(), BorderLayout.CENTER); // ウインドウの中央に配置
        
        setJMenuBar(menubar); // メニューバーをコンテナに追加
        // JFrame に対する諸設定
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ウィンドウが閉じられた際の処理
        setLocation (400, 200); // 表示位置
        setSize (300, 300); // ウィンドウの大きさ
        setVisible(true); // 可視化する
        
        // コンストラクタの末尾において、以下の様にボタン用の応答機能（リスナ）を登録する
        button.addActionListener(new ButtonActionListener());
        // コンストラクタの末尾において、以下の様にメニュー用の応答機能（リスナ）を登録する
        /*menu = menubar.getMenu(0);; // メニューの取得
        for (int i=0; i < menu.getItemCount(); i++) {
          JMenuItem item = menu.getItem(i);
          item.addActionListener(new MenuActionListener()); // メニューの各アイテムにリスナを登録
        }*/
        
        // add(new ArcGraphics(), BorderLayout.CENTER); // ウインドウの中央に配置
    }
    // ボタン用のリスナクラスを作成する
    class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("ボタンが押されました！"); // label は private 変数だが参照できる
        }
    }
   
    // メニュー用のリスナクラスを作成する
    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText(e.getActionCommand() + "のメニューが選択されました！"); // メニュー項目名を表示
        }
    }
    
    class ArcGraphics extends JPanel { // JPanel を継承する
        private Color col [] = {Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW}; // 描画色
        private String itemTitle[] = {"項目１","項目２","項目３","項目４","項目５","項目６"}; // 項目名
        
        public ArcGraphics () {
            setSize(300, 250); // このパネルの大きさを指定
        }
        
        public void paint (Graphics g) {
            int offset = 0, deg = 30; // ３０度刻みの弧を描く
            for (int i = 0; i < itemTitle.length; i++) {
                g.setColor(col[i]); // 色の設定
                g.fillArc(75, 50, 200, 200, offset, deg); // 左上隅のx,y座標、弧の幅と高さ、開始角度、展開角度
                g.drawString(itemTitle[i], 10, 50+20*i); // 項目名を表示
                offset += deg; // 開始角度を更新
            }
        }
    }

    class RadioButtonSelector extends JPanel { // ラジオボタンによる条件設定用のパネル構築例クラス
    private ButtonGroup group;

    public RadioButtonSelector () {
        setLayout(new GridLayout(1,0)); // １行に収める格子状配置
        group = new ButtonGroup();
        String ItemsLabel[] = {"項目１", "項目２","項目３"};
        MenuActionListener maListener = new MenuActionListener();
        setLayout(new GridLayout(0,1));
        add(new JLabel("メニュー"));
        for (int n=0; n<ItemsLabel.length;n++) {
            JRadioButton button = new JRadioButton(ItemsLabel[n]);
            if (n==0)
                button.setSelected(true); // デフォルトとして選択されるボタン
            button.addActionListener(maListener); // リスナを登録
            add(button);
            group.add(button); // ボタングループに登録
        }
    }
    //リスナクラス
    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand(); // このメニューが選択された際の項目名を取得
            System.out.println(action);
        }
    }
}
    
   public static void main(String[] args) {
       SimpleGUI frame = new SimpleGUI (); // フレームの生成
   }
}