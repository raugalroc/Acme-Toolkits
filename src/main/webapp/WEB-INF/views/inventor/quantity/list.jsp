<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.list.label.invention.code" path="invention.code" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.invention.name" path="invention.name" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.invention.technology" path="invention.technology" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.invention.retailPrice" path="invention.retailPrice" width="20%"/>	
	<acme:list-column code="inventor.quantity.list.label.numberOfQuantity" path="numberOfQuantity" width="20%"/>
</acme:list>

<acme:button test="${showCreate}" code="inventor.quantity.list.button.create" action="/inventor/quantity/create?masterId=${masterId}"/>
