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
	<acme:input-textbox code="inventor.patronage-report.form.label.sequenceNumber" readonly="true" path="sequenceNumber"/>
	<acme:input-moment code="inventor.patronage-report.form.label.creationMoment" readonly="true" path="creationMoment"/>	
	<acme:input-textarea  code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>
	<acme:input-textbox code="inventor.patronage-report.form.label.patronage.code" readonly="true" path="patronage.code"/>
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="inventor.patronage-report.form.label.confirmation" path="confirmation"/>
	</jstl:if>
	
	<jstl:choose>	 
		<jstl:when test="${command == 'create'}">
			<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create?masterId=${patronageId}"/>
		</jstl:when>		
	</jstl:choose>
	
</acme:form>
