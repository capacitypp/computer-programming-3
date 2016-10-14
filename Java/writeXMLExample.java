
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

// TODO と書かれた部分にコードを追加する。

public class writeXMLExample {

	public Document createDoc(ArrayList retrieved) throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument(); // 文書を新規作成する
		Element root = doc.createElement("CarList"); // ルート要素を生成する
		doc.appendChild(root); // ルート要素を文章に追加する
		// TODO :
        	for (int i=0; i<retrieved.size(); i++) {
			Element car = doc.createElement("Car"); // Car の要素を生成する
			// TODO : TextNode の生成と要素への追加
		}
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

	public static void main(String[] args) { // throws ClassNotFoundException {
        	writeXMLExample wXML = new writeXMLExample ();
        
		HashMap<String, String> query = new HashMap<String, String>();
		String cmd[] = args[0].split("=");
		String logic = null;
		query.put(cmd[0], cmd[1]);
		if (args.length > 2) {
			logic = args[1];
			String cmd2[] = args[2].split("=");
			query.put(cmd2[0], cmd2[1]);
		}
        
        	ArrayList retrieved = null; // TODO: 検索結果を取得
		Car car = new Car("car.csv");
		try {
            		Document doc = wXML.createDoc(retrieved); // ResultSet から Document を生成
			wXML.saveXML("car.xml", doc); // Document を XML に変換して出力
		} catch (Exception e) {
			e.printStackTrace(); // クラスが発見されなかった場合
		}
	}
}
