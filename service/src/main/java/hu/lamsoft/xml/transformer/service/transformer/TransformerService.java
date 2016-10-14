package hu.lamsoft.xml.transformer.service.transformer;

import hu.lamsoft.xml.transformer.service.transformer.impl.exception.TransformationException;

public interface TransformerService {

	String transformXmlWithXsl(String xml, String xslt) throws TransformationException;
	
}
