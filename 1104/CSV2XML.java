import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class CSV2XML {
	public Document createDoc(ArrayList retrieved) throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element root = doc.createElement("CarList");
		doc.appendChild(root);

		for (int i = 0; i < retrieved.size(); i++) {
			Element car = doc.createElement("Car");

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
			}
			catch (TransformerException te) {
				te.printStackTrace();
			}
		}
		catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		}

		System.out.println(fileName + "に出力しました。");
	}

	public static void main(String[] args) {
		CSV2XML xml = new CSV2XML();

		HashMap<String, String> query = new HashMap<String, String>();
		String cmd[] = args[0].split("=");
		String logic = null;
		query.put(cmd[0], cmd[1]);
		if (args.length > 2) {
			logic = args[1];
			String cmd2[] = args[2].split("=");
			query.put(cmd2[0], cmd2[1]);
		}

		ArrayList retrieved = null;
		Car car = new Car("car.csv");
		try {
			Document doc = xml.createDoc(retrieved);
			xml.saveXML("car.xml", doc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

