<%@ page import="io.hilo.Catalog" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="${message(code: 'catalog.label', default: 'Catalog')}" />
		<title>Create Catalog</title>
		<link rel="stylesheet" href="${resource(dir:'js/lib/ckeditor/4.4.0', file:'contents.css')}" />	
		<script type="text/javascript" src="${resource(dir:'js/lib/ckeditor/4.4.0/ckeditor.js')}"></script>
		<script type="text/javascript" src="${resource(dir:'js/lib/ckeditor/4.4.0/styles.js')}"></script>
	
		<link rel="stylesheet" href="${resource(dir:'css', file:'admin.css')}" />
	</head>
	<body>
		
		
		
		<div class="form-outer-container">
		
		
			<div class="form-container">
			
				<h2>Update Post
					<g:link controller="catalog" action="list" class="btn btn-default pull-right">Back to Posts</g:link>
					<br class="clear"/>
				</h1>
			
				<br class="clear"/>
			

			
				<div class="messages">
			
					<g:if test="${flash.message}">
						<div class="alert alert-info" role="status">${flash.message}</div>
					</g:if>
			
					<g:if test="${flash.error}">
						<div class="alert alert-danger" role="status">${flash.error}</div>
					</g:if>
					
					<g:hasErrors bean="${postInstance}">
						<div class="alert alert-danger">
							<ul>
								<g:eachError bean="${postInstance}" var="error">
									<li><g:message error="${error}"/></li>
								</g:eachError>
							</ul>
						</div>
					</g:hasErrors>
				
				</div>
				
				
				
			
				<g:form action="update" id="${postInstance.id}">
					<div class="form-row">
						<span class="form-label twohundred secondary">Title 
							<span class="information secondary block">Title must be unique</span>
						</span>
						<span class="input-container">
							<input name="title" type="text" class="form-control threefifty" value="${postInstance?.title}" id="title"/>
						</span>
						<br class="clear"/>
					</div>
					
						  

				
					<div class="form-row">
						<g:textArea class="form-control ckeditor" name="content" id="content" cols="40" rows="15" maxlength="65535" value="${postInstance?.content}"/>
						<br class="clear"/>
					</div>
				


			 		<div class="form-row">
			 			<span class="form-label twohundred secondary">Layout</span>
			 			<span class="input-container">
							<g:select name="layout.id"
									from="${layouts}"
									value="${postInstance?.layout?.id}"
									optionKey="id" 
									optionValue="name" 
									class="form-control threehundred"/>
			 			</span>
			 			<br class="clear"/>
			 		</div>
					
				
				
					<div class="buttons-container">
						<g:submitButton name="create" class="btn btn-primary" value="Update Post" />
					</div>
					
				</g:form>
				
			</div>
		</div>
		
		
		
	</body>
</html>
