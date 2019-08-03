
<%@ page import="io.hilo.BlogPost" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="io.hilo.ApplicationService" %>
<% def applicationService = grailsApplication.classLoader.loadClass('io.hilo.ApplicationService').newInstance()
%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<title>Hilo : Posts</title>
	</head>
	<body>
	
		<g:if test="${posts}">
			<div id="list-page" class="content scaffold-list" role="main">

				<h2 class="floatleft">Posts</h2>
			
				<g:link controller="page" action="create" class="btn btn-primary pull-right">New Blog Post</g:link>
			
				<br class="clear"/>
				
				<g:if test="${flash.message}">
					<div class="alert alert-info" role="status">${flash.message}</div>
				</g:if>
				
				
				<br style="clear:both">
				
				<table class="table">
					<thead>
						<tr>
							<g:sortableColumn property="id" title="${message(code: 'id', default: 'Id')}" />
						
							<g:sortableColumn property="title" title="${message(code: 'title', default: 'Title')}" />

							<th><g:message code="layout"/></th>
							
							<th></th>
						
						</tr>
					</thead>
					<tbody>
					<g:each in="${posts}" status="i" var="post">
						<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
							<td><g:link action="edit" id="${post.id}">${post.id}</g:link></td>

							<td><g:link action="edit" id="${post.id}">${post.title}</g:link></td>
						
							<td><g:link controller="layout" action="edit" id="${post.layout.id}">${post.layout.name}</g:link></td>
						
							<td><g:link action="edit" id="${post.id}" class=""><g:message code="edit"/></g:link></td>
						
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${postsTotal}" />
				</div>
			</div>
		</g:if>
		<g:else>
			<div class="alert alert-info">No posts created yet. <g:link action="create">Create First Blog Post</g:link></div>
		</g:else>
	</body>
</html>
