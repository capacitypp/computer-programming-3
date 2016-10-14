import java.sql.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

// TODO と書かれた部分にコードを追加すれば、プログラムを完成できる。

public class SQL {
	private Statement statement = null;
	private Connection connection = null;
	private String titles[] = { "buying", "maint", "doors", "persons", "luggage", "safety" };
	private String evals[] = { "unacc", "acc", "good", "vgood" };

	public SQL() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC"); // クラスローダを用いた sqlite-JDBC ドライバの読み込み
		try {
			// データベースへの接続
			connection = DriverManager.getConnection("jdbc:sqlite:car.db"); // car.db は出力ファイル名
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			statement.executeUpdate("DROP TABLE IF EXISTS CarData"); // CarData というデータベースが既に存在していたら削除する
			statement.executeUpdate("CREATE TABLE CarData (buying STRING, maint STRING, doors STRING, persons STRING, luggage STRING, safety STRING, eval STRING)");
		} catch (SQLException e) {
			// データベースファイルが見つからなかった場合は "out of memory" メッセージが出力される
			e.printStackTrace();
		}
	}

	public void putCarData(Car cardata) { // Car の情報をSQLデータベースに登録する
		for (HashMap<String, String> map : cardata) {
			String command = "INSERT INTO CarData VALUES('";
			// TODO : command のステートメント文字列を追加する。
			// System.out.println(command); command の内容を画面表示して確認する
			try {
				statement.executeUpdate(command); // データベース更新の実行
			} catch (SQLException e) {
				e.printStackTrace(); // データの登録が失敗した場合
			}
		}
	}

	public ResultSet retrieve(HashMap<String, String> query, String logic) throws SQLException {
		String command = "SELECT * FROM CarData WHERE ";
		// TODO : command のステートメント文字列を追加する。
		return statement.executeQuery(command); // クエリ検索の実行
	}

	public Document createDoc(ResultSet rs) throws ParserConfigurationException, SQLException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument(); // 文書を新規作成する
		Element root = doc.createElement("CarList"); // ルート要素を生成する
		doc.appendChild(root); // ルート要素を文章に追加する
		// TODO : 
		while (rs.next()) {
			Element car = doc.createElement("Car"); // Car の要素を生成する
			// TODO : TextNode の生成と要素への追加
		}
		rs.close();
		return doc;
	}

	public void saveXML(String fileName, Document doc) {
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			try {
				tf.transform(new DOMSource(doc), new StreamResult(fileName));
			} catch (TransformerException te) {
				te.printStackTrace();
			}
		} catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		}

		System.out.println(fileName + " に出力しました。");
	}

	public void closeConnection() { // 接続を閉じる
		try {
			if (connection != null)
				connection.close(); // 接続のクローズ
		} catch (SQLException e) {
			e.printStackTrace(); // 接続のクローズが失敗した場合
		}
	}

	public static void main(String[] args) { // throws ClassNotFoundException {
		HashMap<String, String> query = new HashMap<String, String>();

		String cmd[] = args[0].split("=");
		String logic = null;
		query.put(cmd[0], cmd[1]);
		if (args.length > 2) {
			logic = args[1];
			String cmd2[] = args[2].split("=");
			query.put(cmd2[0], cmd2[1]);
		}
		Car car = new Car("car.csv");
		try {
			SQL sqlm = new SQL(); // ヘッダのみのSQLを生成
			sqlm.putCarData(car); // SQL に Car のデータを登録
			ResultSet rs = sqlm.retrieve(query, logic); // 条件を満たすレコードを取得
			Document doc = sqlm.createDoc(rs); // ResultSet から Document を生成
			sqlm.saveXML("car.xml", doc); // Document を XML に変換して出力
			sqlm.closeConnection();
		} catch (Exception e) {
			e.printStackTrace(); // クラスが発見されなかった場合
		}
	}
}
