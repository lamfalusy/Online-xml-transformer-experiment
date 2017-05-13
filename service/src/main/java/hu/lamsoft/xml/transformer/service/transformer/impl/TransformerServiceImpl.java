package hu.lamsoft.xml.transformer.service.transformer.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

import hu.lamsoft.xml.transformer.service.transformer.TransformerService;
import hu.lamsoft.xml.transformer.service.transformer.impl.exception.TransformationException;

@Service
public class TransformerServiceImpl implements TransformerService {

	@Override
	public String transformXmlWithXsl(String xml, String xslt) throws TransformationException {
		System.out.println("Start");
		String html = downloadHtml("http://www.foodnutritiontable.com/nutritions/");
		System.out.println("DownloadEnd");
		try {
            Tidy tidy = new Tidy();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.out.println("Pretty proint start");
            tidy.pprint(tidy.parseDOM(new ByteArrayInputStream(html.getBytes()), System.out), outputStream);
            System.out.println("Pretty proint end");
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(outputStream.toByteArray()));

            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            StreamSource stylesource = new StreamSource(new ByteArrayInputStream(xslt.getBytes()));
            Transformer transformer = tFactory.newTransformer(stylesource);

            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            return writer.toString();
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new TransformationException(e);
        }
	}

	private String downloadHtml(String urlString) {
	    try(InputStream inputStream = new URL(urlString).openStream()) {	        
	        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
	    } catch (Exception e) {
	    	throw new IllegalStateException(e);
	    }
	}
	
	
}
