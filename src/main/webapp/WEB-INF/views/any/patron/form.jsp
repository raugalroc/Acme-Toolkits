
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.patron.list.label.username" path="username"/>
	<acme:input-textbox code="any.patron.list.label.company" path="company"/>
	<acme:input-textbox code="any.patron.form.label.name" path="name"/>
	<acme:input-textbox code="any.patron.form.label.surname" path="surname"/>
	<acme:input-textbox code="any.patron.form.label.email" path="email"/>
	<acme:input-textarea code="any.patron.list.label.statement" path="statement"/>
	<acme:input-url code="any.patron.list.label.link" path="link"/>			
</acme:form>