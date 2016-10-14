package hu.lamsoft.xml.transformer.web.controller.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class TransformationRequestVO {

	@NotEmpty
	private String xml;
	@NotEmpty
	private String xslt;
	
	public TransformationRequestVO() {
		// Nothing to do here
	}
	
	public String getXml() {
		return xml;
	}
	
	public void setXml(String xml) {
		this.xml = xml;
	}
	
	public String getXslt() {
		return xslt;
	}
	
	public void setXslt(String xslt) {
		this.xslt = xslt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((xml == null) ? 0 : xml.hashCode());
		result = prime * result + ((xslt == null) ? 0 : xslt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransformationRequestVO other = (TransformationRequestVO) obj;
		if (xml == null) {
			if (other.xml != null)
				return false;
		} else if (!xml.equals(other.xml))
			return false;
		if (xslt == null) {
			if (other.xslt != null)
				return false;
		} else if (!xslt.equals(other.xslt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TransformationRequestVO [xml=" + xml + ", xslt=" + xslt + "]";
	}
	
}
