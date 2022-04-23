<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.invention.list.label.code" path="code" width="5%"/>
	<acme:list-column code="inventor.invention.list.label.name" path="name" width="15%"/>
	<acme:list-column code="inventor.invention.list.label.draftMode" path="published" width="5%"/>
	<acme:list-column code="inventor.invention.list.label.technology" path="technology" width="15%"/>
	<acme:list-column code="inventor.invention.list.label.retailPrice" path="retailPrice" width="60%"/>
</acme:list>