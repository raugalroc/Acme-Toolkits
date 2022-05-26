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

	<acme:input-select code="patron.patronage.form.label.status"
		path="status">
		<acme:input-option code="patron.patronage.form.label.proposed"
			value="PROPOSED" selected="${ status == 'PROPOSED' }" />
		<acme:input-option code="patron.patronage.form.label.accepted"
			value="ACCEPTED" selected="${ status == 'ACCEPTED' }" />
		<acme:input-option code="patron.patronage.form.label.denied"
			value="DENIED" selected="${ status == 'DENIED' }" />
	</acme:input-select>
	<acme:input-textbox code="patron.patronage.form.label.id"  path="id" readonly="true"/>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code" readonly="false"/>
	<acme:input-textbox code="patron.patronage.form.label.legalStuff"
		path="legalStuff" />
	<acme:input-money code="patron.patronage.form.label.budget"
		path="budget" />
	<jstl:choose>
		<jstl:when test="${published==true}">
			<acme:input-moment code="patron.patronage.form.label.creationTime"
			path="creationTime" />
		</jstl:when>
	</jstl:choose>
	
	<acme:input-moment code="patron.patronage.form.label.startTime"
		path="startTime" />
	<acme:input-moment code="patron.patronage.form.label.endTime"
		path="endTime" />
	<acme:input-url code="patron.patronage.form.label.link" path="link" />
	
	

	<jstl:choose>

		<jstl:when
			test="${acme:anyOf(command, 'create, update, delete, publish') && published==false}">
			<acme:input-select code="patron.patronage.form.label.select.inventor" path="inventor">
				<jstl:forEach items="${inventors}" var="optionInventor">
					<acme:input-option code="${optionInventor.userAccount.username}" value="${optionInventor.userAccount.username}"
										selected="${inventor.equals(optionInventor)}"/>
				</jstl:forEach>
			</acme:input-select>

		</jstl:when>
		<jstl:when test="${command=='show'}">
			<h2>
				<acme:message code="patron.patronage.message.inventor" />
			</h2>
			<acme:input-textbox code="patron.patronage.form.label.inventor.name"
				path="name" readonly="true"/>
			<acme:input-textbox
				code="patron.patronage.form.label.inventor.surname" path="surname" readonly="true"/>
			<acme:input-textbox code="patron.patronage.form.label.inventor.email"
				path="email" readonly="true"/>
			<acme:input-textbox
				code="patron.patronage.form.label.inventor.company" path="company" readonly="true"/>

		</jstl:when>

	</jstl:choose>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>		
	</jstl:choose>

</acme:form>