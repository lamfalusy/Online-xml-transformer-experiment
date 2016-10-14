<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
	<style>
		table.input-table {
			width: 100%;
		}
		
		.xml-column {
			width: 50%;
		}
		
		.xslt-column {
			width: 50%;
		}
		
		.input-table textarea {
			width: 100%;
			height: 600px;
		}
	</style>
</head>

<body>
	<h2>Hello World!</h2>
	
	<form:form method="POST" commandName="transformationRequest" action="transform">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div>${message}</div>
		
		<table class="input-table">
			<tr>
				<td class="xml-column">
					<div>XML</div>
					<form:textarea path="xml" />
				</td>
				<td class="xslt-column">
					<div>XSLT</div>
					<form:textarea path="xslt" />
				</td>
			</tr>
		</table>
		
		<div>
			<input type="submit" />
		</div>
	</form:form>
	
	<div>
		${generatedContent}
	</div>
	
</body>
</html>