package hu.lamsoft.xml.transformer.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hu.lamsoft.xml.transformer.service.transformer.TransformerService;
import hu.lamsoft.xml.transformer.service.transformer.impl.exception.TransformationException;
import hu.lamsoft.xml.transformer.web.controller.vo.TransformationRequestVO;

@Controller
@RequestMapping()
public class TransformationController {

	@Autowired
	private TransformerService transformerService;
	
	@RequestMapping(value = "transform", method = RequestMethod.POST)
	public String generate(@Valid TransformationRequestVO transformationRequest, BindingResult result, Model model) {
		String generatedContent = null;
		try {
			generatedContent = transformerService.transformXmlWithXsl(transformationRequest.getXml(), transformationRequest.getXslt());
		} catch (TransformationException e) {
			generatedContent = e.getMessage();
		}
		model.addAttribute("generatedContent", generatedContent);
		model.addAttribute("transformationRequest", transformationRequest);
		return "transformer";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String loadPage(Model model) {
		model.addAttribute("transformationRequest", new TransformationRequestVO());
		return "transformer";
	}
	
}
