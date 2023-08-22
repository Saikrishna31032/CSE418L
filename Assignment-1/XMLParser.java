import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser 
{
    public static void main(String[] args) {
        try {
            File inputFile = new File("reservation.xml"); // Replace with your XML file path

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("reservation");

            String[][] tableData = new String[nodeList.getLength()][5];

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodeList.item(i);
                    String fullName = element.getElementsByTagName("fullName").item(0).getTextContent();
                    String flightNumber = element.getElementsByTagName("flightNumber").item(0).getTextContent();
                    String departureDate = element.getElementsByTagName("departureDate").item(0).getTextContent();
                    String seats = element.getElementsByTagName("seats").item(0).getTextContent();
                    String classType = element.getElementsByTagName("class").item(0).getTextContent();
                    tableData[i][0] = fullName;
                    tableData[i][1] = flightNumber;
                    tableData[i][2] = departureDate;
                    tableData[i][3] = seats;
                    tableData[i][4] = classType;

                }
            }
             generateHTMLTable(tableData,"table.html");
            System.out.println("HTML table generated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateHTMLTable(String[][] tableData, String outputFilePath) 
    {
       try 
        {
            
            FileWriter writer = new FileWriter(outputFilePath);

            writer.write("<html><body>");
            writer.write("<table border=\"1\">");

            // Generate table header
            writer.write("<tr>");
            writer.write("<th>fullName</th>");
            writer.write("<th>flightNumber</th>");
            writer.write("<th>departureDate</th>");
            writer.write("<th>seats</th>");
            writer.write("<th>classType</th>");
            writer.write("</tr>");

            
            // Generate table rows
            for (String[] row : tableData) 
            {
                writer.write("<tr>");
                for (String cell : row) 
                {
                    writer.write("<td>" + cell + "</td>");
                }
                writer.write("</tr>");
            }

            writer.write("</table>");
            writer.write("</body></html>");

            writer.close();
        } catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
