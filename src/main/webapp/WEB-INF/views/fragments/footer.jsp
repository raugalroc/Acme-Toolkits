<%--
- footer.jsp
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
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:footer-panel>
	<acme:footer-subpanel code="master.footer.title.about">
		<acme:footer-option icon="far fa-building" code="master.footer.label.company" action="/master/company"/>
		<acme:footer-option icon="far fa-file-code" code="master.footer.label.license" action="/master/license"/>		
	</acme:footer-subpanel>

	<acme:footer-subpanel code="master.footer.title.follow">
		<acme:message var="linkedinUrl" code="master.footer.url.linkedin"/>
		<acme:footer-option icon="fab fa-linkedin" code="master.footer.label.linked-in" action="${linkedinUrl}" newTab="true"/>
		<acme:message var="instagramUrl" code="master.footer.url.instagram"/>
		<acme:footer-option icon="fab fa-instagram" code="master.footer.label.instagram" action="${instagramUrl}" newTab="true"/>
	</acme:footer-subpanel>

	<acme:footer-subpanel code="master.footer.title.languages">
		<acme:footer-option icon="fa fa-language" code="master.footer.label.english" action="/?language=en"/>
		<acme:footer-option icon="fa fa-language" code="master.footer.label.spanish" action="/?language=es"/>
	</acme:footer-subpanel>

	<acme:footer-logo logo="images/logo.png">
		<acme:footer-copyright code="master.company.name"/>
	</acme:footer-logo>		

</acme:footer-panel>
