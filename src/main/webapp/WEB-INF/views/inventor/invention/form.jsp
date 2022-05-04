<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.invention.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.invention.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.invention.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.invention.form.label.description" path="description"/>
	<acme:input-select code="inventor.invention.form.label.inventionType" path="inventionType">
		<acme:input-option code="component" value="COMPONENT" selected="${inventionType == 'COMPONENT'}"/>
		<acme:input-option code="tool" value="TOOL" selected="${inventionType == 'TOOL' }"/>
	</acme:input-select>
	<acme:input-money code="inventor.invention.form.label.retailPrice" path="retailPrice"/>
	<acme:input-select code="inventor.invention.form.label.published" path="published">
		<acme:input-option code="true" value="true" selected="${published == true}"/>
		<acme:input-option code="false" value="false" selected="${published == false }"/>
	</acme:input-select>
	<acme:input-url code="inventor.invention.form.label.link" path="link"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="inventor.invention.form.button.update" action="/inventor/invention/update"/>
			<acme:submit code="inventor.invention.form.button.delete" action="/inventor/invention/delete"/>
			<acme:submit code="inventor.invention.form.button.publish" action="/inventor/invention/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.invention.form.button.create" action="/inventor/invention/create"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>