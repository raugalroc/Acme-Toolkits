<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.dashboard.form.title.patronages"/>
</h2>
<table class="table table-sm" aria-describedby="patron.dashboard.form.label.number-by-status">
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.number-proposed"/>
		</th>
		<td>
			<acme:print value="${numberProposed}"/>
		</td>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.number-accepted"/>
		</th>
		<td>
			<acme:print value="${numberAccepted}"/>
		</td>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.number-denied"/>
		</th>
		<td>
			<acme:print value="${numberDenied}"/>
		</td>
	</tr>
</table>

<h3>
	<acme:message code="patron.dashboard.form.label.average.value"/>
</h3>
<table class="table table-sm" aria-describedby="patron.dashboard.form.label.average.value">
	<jstl:forEach var="entry" items="${averageBudget}">
		<tr>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.currency"/>
			</th>
			<td>
				<acme:print value="${entry.key.first}"/>
			</td>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.status"/>
			</th>
			<td>
				<acme:print value="${entry.key.second}"/>
			</td>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.average.value"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="patron.dashboard.form.label.deviation.value"/>
</h3>
<table class="table table-sm" aria-describedby="patron.dashboard.form.label.deviation.value">
	<jstl:forEach var="entry" items="${deviationBudget}">
		<tr>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.currency"/>
			</th>
			<td>
				<acme:print value="${entry.key.first}"/>
			</td>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.status"/>
			</th>
			<td>
				<acme:print value="${entry.key.second}"/>
			</td>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.deviation.value"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="patron.dashboard.form.label.minimum.value"/>
</h3>
<table class="table table-sm" aria-describedby="patron.dashboard.form.label.minimum.value">
	<jstl:forEach var="entry" items="${minimumBudget}">
		<tr>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.currency"/>
			</th>
			<td>
				<acme:print value="${entry.key.first}"/>
			</td>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.status"/>
			</th>
			<td>
				<acme:print value="${entry.key.second}"/>
			</td>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.minimum.value"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="patron.dashboard.form.label.maximum.value"/>
</h3>
<table class="table table-sm" aria-describedby="patron.dashboard.form.label.maximum.value">
	<jstl:forEach var="entry" items="${maximumBudget}">
		<tr>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.currency"/>
			</th>
			<td>
				<acme:print value="${entry.key.first}"/>
			</td>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.status"/>
			</th>
			<td>
				<acme:print value="${entry.key.second}"/>
			</td>
			<th scope="row">
				<acme:message code="patron.dashboard.form.label.maximum.value"/>
			</th>
			<td>
				<acme:print value="${entry.value}"/>
			</td>
		</tr>
	</jstl:forEach>
</table>