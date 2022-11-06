import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyDomParser {

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try (InputStream is = MyDomParser.class.getClassLoader().getResourceAsStream("library.xml");) {
			DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(is);
			NodeList issuedList = doc.getElementsByTagName("Issued");
			for(int i=0;i<issuedList.getLength();i++) {
				Node p = issuedList.item(i);
				if(p.getNodeType()==Node.ELEMENT_NODE) {
					Element issued = (Element) p;
					String to = issued.getAttribute("to"); 
					System.out.println("Issued to " + to + " : ");
					NodeList Bkid = issued.getChildNodes();
					for(int j=0;j<Bkid.getLength();j++) {
						Node n = Bkid.item(j);  
						if(n.getNodeType()==Node.ELEMENT_NODE) {
							Element name = (Element) n; 
							System.out.println(name.getTagName() + " = " + name.getTextContent()); 
						}
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			System.out.println("parser config");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			System.out.println("SAX config");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO config");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
