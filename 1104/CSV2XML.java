import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import org.json.*;

public class CSV2XML {
	static String keys[] = {"buying", "maint", "doors", "persons", "luggage", "safety", "eval"};
	static String evalLabel[] = {"unacc", "acc", "good", "vgood"};
	public Document createDoc(ArrayList<ArrayList<String>> retrieved) throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Element root = doc.createElement("CarList");
		doc.appendChild(root);

		for (String label : evalLabel) {
			Element group = doc.createElement("Group");
			group.setAttribute("eval", label);
			for (ArrayList<String> list : retrieved) {
				if (!list.get(list.size() - 1).equals(label))
					continue;
				Element car = doc.createElement("Car");
				for (int i = 0; i < list.size() - 1; i++)
					car.setAttribute(keys[i], list.get(i));
				group.appendChild(car);
			}
			root.appendChild(group);
		}
		return doc;
	}

	public void saveXML(String fileName, Document doc) {
		try {
			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");
			tf.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4");
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

	public void saveJSON(String fileName, ArrayList<ArrayList<String>> retrieved) throws IOException, JSONException {
		FileWriter fw = new FileWriter(new File(fileName));
		JSONWriter writer = new JSONWriter(fw);
		writer.object();
		for (String label : evalLabel) {
			writer.key(label);
			writer.array();
			for (ArrayList<String> list : retrieved) {
				if (!list.get(list.size() - 1).equals(label))
					continue;
				writer.object();
				for (int i = 0; i < list.size() - 1; i++) {
					writer.key(keys[i]);
					writer.value(list.get(i));
				}
				writer.endObject();
			}
			writer.endArray();
		}
		writer.endObject();
		fw.flush();

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

		Car car = new Car("car.csv");
		ArrayList<ArrayList<String>> retrieved = car.analyze(query, logic);
		try {
			Document doc = xml.createDoc(retrieved);
			xml.saveXML("car.xml", doc);
			xml.saveJSON("car.json", retrieved);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

