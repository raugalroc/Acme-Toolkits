<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.list.label.invention.code" path="invention.code" width="80%"/>	
	<acme:list-column code="inventor.quantity.list.label.numberOfQuantity" path="numberOfQuantity" width="20%"/>
</acme:list>

<acme:button test="${showCreate}" code="inventor.quantity.list.button.create" action="/inventor/quantity/create?masterId=${masterId}"/>
