<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.inventor.list.label.username" path="username"/>
	<acme:input-textbox code="any.inventor.list.label.company" path="company"/>
	<acme:input-textbox code="any.inventor.form.label.name" path="name"/>
	<acme:input-textbox code="any.inventor.form.label.surname" path="surname"/>
	<acme:input-textbox code="any.inventor.form.label.email" path="email"/>
	<acme:input-textarea code="any.inventor.list.label.statement" path="statement"/>
	<acme:input-url code="any.inventor.list.label.link" path="link"/>			
</acme:form>