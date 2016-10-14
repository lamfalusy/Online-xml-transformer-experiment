package hu.lamsoft.xml.transformer.web.controller;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;

import hu.lamsoft.xml.transformer.web.controller.vo.TransformationRequestVO;

@Controller
@RequestMapping()
public class HelloController {

	@RequestMapping(value = "transform", method = RequestMethod.POST)
	public String generate(@Valid TransformationRequestVO transformationRequest, BindingResult result, Model model) {
		model.addAttribute("generatedContent", transform(transformationRequest));
		model.addAttribute("transformationRequest", transformationRequest);
		return "hello";
	}
	
	private String transform(TransformationRequestVO transformationRequest) {
		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new ByteArrayInputStream(transformationRequest.getXml().getBytes()));

            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            StreamSource stylesource = new StreamSource(new ByteArrayInputStream(transformationRequest.getXslt().getBytes()));
            Transformer transformer = tFactory.newTransformer(stylesource);

            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            return writer.toString();
        } catch (Exception e) {
        	e.printStackTrace();
        }
		return null;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String loadPage(Model model) {
		model.addAttribute("transformationRequest", new TransformationRequestVO());
		return "hello";
	}
	
}
