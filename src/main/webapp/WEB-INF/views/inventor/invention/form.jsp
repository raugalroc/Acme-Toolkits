<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="inventor.invention.form.label.code" path="code"/>
	<acme:input-textbox code="inventor.invention.form.label.name" path="name"/>
	<acme:input-textbox code="inventor.invention.form.label.technology" path="technology"/>
	<acme:input-textarea code="inventor.invention.form.label.description" path="description"/>
	<acme:input-money code="inventor.invention.form.label.retailPrice" path="retailPrice"/>
	<acme:input-url code="inventor.invention.form.label.link" path="link"/>
</acme:form>