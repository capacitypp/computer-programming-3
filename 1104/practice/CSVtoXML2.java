import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;

public class CSVtoXML2 {
	private Document doc = null;
	private Element root = null;
	private ArrayList<String> colname;

	public CSVtoXML2(String rootName) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		doc = db.newDocument();
		root = doc.createElement(rootName);
		doc.appendChild(root);
	}

	public void createTitle(BufferedReader br) throws IOException {
		colname = new ArrayList<String>();
		String line = br.readLine();
		StringTokenizer stt = new StringTokenizer(line, ",");
		while (stt.hasMoreTokens()) {
			colname.add(stt.nextToken());
		}
	}

	public void convert2XML(BufferedReader br, String elementName) throws DOMException, IOException {
		String line;
		Element elm = null;
		while ((line = br.readLine()) != null) {
			StringTokenizer std = new StringTokenizer(line, ",");
			Element car = doc.createElement(elementName);
			root.appendChild(car);
			for (int i = 0; i < colname.size(); i++) {
				if (i == 0) {
					elm = doc.createElement(colname.get(i));
					Text txt = doc.createTextNode(std.nextToken());
					elm.appendChild(txt);
					car.appendChild(elm);
				} else {
					car.setAttribute(colname.get(i), std.nextToken());
				}
			}
		}
	}

	public void outputFile(String outFileName) throws TransformerException {
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.transform(new DOMSource(doc), new StreamResult(outFileName));
		System.out.println(outFileName + "に出力しました。");
	}

	public static void main(String[] args) {
		try {
			CSVtoXML2 converter = new CSVtoXML2("車リスト");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("sample.csv"), "UTF-8"));
			converter.createTitle(br);
			converter.convert2XML(br, "車");
			br.close();
			converter.outputFile("result.xml");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

