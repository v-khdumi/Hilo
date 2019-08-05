<%@ page import="io.hilo.ApplicationService" %>
<% def applicationService = grailsApplication.classLoader.loadClass('io.hilo.ApplicationService').newInstance()
%>
${raw(applicationService.getBlogHeader())}
	
	<style type="text/css">
		.post-content{
			height:300px;
			overflow:hidden;
		}
	</style>

	<g:if test="${flash.message}">
		<div class="alert alert-info" role="status">${flash.message}</div>
	</g:if>
	
	<g:each in="${posts}" var="post">
		<h1>${post.title}</h1>
		<div class="post-content">${raw(post.content.substring(209))}... <g:link action="view" id="${post.id}">Read More</g:link></div>
	</g:each>	


	<script type="text/javascript">
		$(".post-content").collapse()
	</script>

${raw(applicationService.getBlogFooter())}