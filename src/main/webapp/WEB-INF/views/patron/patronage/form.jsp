<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">

	<acme:input-select code="patron.patronage.form.label.status" path="status">
		<acme:input-option code="patron.patronage.form.label.proposed" value="PROPOSED" selected="${ status == 'PROPOSED' }"/>
		<acme:input-option code="patron.patronage.form.label.accepted" value="ACCEPTED" selected="${ status == 'ACCEPTED' }"/>
		<acme:input-option code="patron.patronage.form.label.denied" value="DENIED" selected="${ status == 'DENIED' }"/>
	</acme:input-select>
	
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>	
	<acme:input-textbox code="patron.patronage.form.label.legalStuff" path="legalStuff"/>	
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.creationTime" path="creationTime"/>
	<acme:input-moment code="patron.patronage.form.label.startTime" path="startTime"/>
	<acme:input-moment code="patron.patronage.form.label.endTime" path="endTime"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	
	<h2><acme:message code="patron.patronage.message.inventor"/></h2>
	
	
	<acme:input-textbox code="patron.patronage.form.label.inventor.name" path="name"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.surname" path="surname"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.email" path="email"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.company" path="company"/>
	
	
</acme:form>
