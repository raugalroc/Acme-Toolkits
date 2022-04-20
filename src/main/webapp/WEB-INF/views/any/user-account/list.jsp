<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
		<acme:list-column code="any.user-account.username" path="username"/>
		<acme:list-column code="any.user-account.name" path="name"/>
		<acme:list-column code="any.user-account.surname" path="surname"/>
		<acme:list-column code="any.user-account.email" path="email"/>
		<acme:list-column code="any.user-account.roles" path="roles"/>
</acme:list>