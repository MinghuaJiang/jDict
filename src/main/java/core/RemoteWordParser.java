package core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class RemoteWordParser
{
    private static RemoteWordParser parser;

    private RemoteWordParser()
    {

    }

    public static RemoteWordParser getInstance()
    {
        if (parser == null)
        {
            parser = new RemoteWordParser();
        }
        return parser;
    }

    private String siteUrl = "http://dict.cn/";

    public List<String> getChineseExplain(String word) throws Exception
    {
        String urlStr = siteUrl + word;
        System.out.println(urlStr);
        String htmlContent = getHtmlContent(urlStr);
        // return htmlContent;
        return parseHtmlContent(htmlContent);
    }

    private String getHtmlContent(String urlString) throws Exception
    {
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try
        {
            URL url = new URL(urlString);
            connection = getHttpURLConnection(url);
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
            if (connection != null)
            {
                connection.disconnect();
            }
        }
    }

    private HttpURLConnection getHttpURLConnection(URL url) throws Exception
    {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        return connection;
    }

    public List<String> parseHtmlContent(String html) throws Exception
    {
        if (html.contains("dict-basic-ul"))
        {
            int firstIndex = html.indexOf("<ul class=\"dict-basic-ul\">");
            html = html.substring(firstIndex,
                    html.indexOf("</ul>", firstIndex) + 5);
            html =  html.replaceAll("<口>", "[口]");
        }
        else
        {
            return new ArrayList<String>(0);
        }
        List<String> list = new ArrayList<String>();
        try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
			        .newDocumentBuilder();
			InputSource source = new InputSource();
      
			source.setCharacterStream(new StringReader(html));
			Document document = documentBuilder.parse(source);
			NodeList li = document.getElementsByTagName("li");
			for (int i = 0; i < li.getLength(); i++)
			{
			    Node item = li.item(i);

			    StringBuilder sb = new StringBuilder();
			    NodeList desc = item.getChildNodes();
			    for (int j = 0; j < desc.getLength(); j++)
			    {
			        if (desc.item(j).getNodeName().equalsIgnoreCase("span"))
			        {
			            if(desc.item(j).hasChildNodes()){
			            	sb.append(desc.item(j).getFirstChild().getNodeValue());
			            }
			        }
			        if (desc.item(j).getNodeName().equalsIgnoreCase("strong"))
			        {
			        	if(desc.item(j).hasChildNodes()){
			        		sb.append(desc.item(j).getFirstChild().getNodeValue());
			        	}
			        }
			    }
			    list.add(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return list;
    }

    public static void main(String[] args) throws Exception
    {
        RemoteWordParser parser = RemoteWordParser.getInstance();
        System.out.println(parser.getChineseExplain("apple"));
    }
}
