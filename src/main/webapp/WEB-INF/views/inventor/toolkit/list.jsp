<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.toolkit.list.label.code" path="code" width="20%"/>
	<acme:list-column code="inventor.toolkit.list.label.title" path="title" width="20%"/>
	<acme:list-column code="inventor.toolkit.list.label.published" path="published" width="20%"/>
	<acme:list-column code="inventor.toolkit.list.label.description" path="description" width="70%"/>
</acme:list>

<jstl:if test="${command == 'list-mine'}">
	<acme:button code="inventor.toolkit.list.button.create" action="/inventor/toolkit/create"/>
</jstl:if>	
