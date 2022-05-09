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

<acme:form>

	<jstl:if test="${status != 'PROPOSED'}">
		<acme:input-textbox code="inventor.patronage.form.label.status" path="status"/>
	</jstl:if>
	
	<jstl:if test="${status == 'PROPOSED'}">
		<acme:input-select code="inventor.patronage.form.label.editStatus" path="status">
			<acme:input-option code="PROPOSED" value="PROPOSED"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:input-option code="DENIED" value="DENIED"/>
		</acme:input-select>		
	</jstl:if>
	
	<acme:input-textbox code="inventor.patronage.form.label.legalStuff"
		path="legalStuff" />
	<acme:input-money code="inventor.patronage.form.label.budget"
		path="budget" />
	<acme:input-textbox code="inventor.patronage.form.label.patron"
		path="patron.userAccount.username" />

	<acme:input-moment code="inventor.patronage.form.label.creationTime"
		path="creationTime" />
	<acme:input-moment code="inventor.patronage.form.label.startTime"
		path="startTime" />
	<acme:input-moment code="inventor.patronage.form.label.endTime"
		path="endTime" />

	<acme:input-url code="inventor.patronage.form.label.link" path="link" />
	
	<acme:submit test="${acme:anyOf(command, 'show, update') && status == 'PROPOSED'}" code="inventor.patronage.form.button.update" action="/inventor/patronage/update"/>
	
</acme:form>