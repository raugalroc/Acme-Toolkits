<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="inventor.quantity.form.label.invention.code" path="invention.code"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:input-textbox code="inventor.quantity.form.label.invention.code" path="invention.code" readonly="true"/>
		</jstl:otherwise>	
	</jstl:choose>
	<acme:input-integer code="inventor.quantity.form.label.numberOfQuantity" path="numberOfQuantity"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete') && published == false}">
			<acme:submit code="inventor.quantity.form.button.update" action="/inventor/quantity/update"/>
			<acme:submit code="inventor.quantity.form.button.delete" action="/inventor/quantity/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.quantity.form.button.create" action="/inventor/quantity/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>		
</acme:form>