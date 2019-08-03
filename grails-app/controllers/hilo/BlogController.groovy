package hilo

import grails.plugin.springsecurity.annotation.Secured

import io.hilo.BlogPost
import io.hilo.Layout

class BlogController {


 	@Secured(['ROLE_ADMIN'])
	def index(){}


 	@Secured(['ROLE_ADMIN'])
	def list(){
    	params.max = 10
    	[ posts: BlogPost.list(params), postsTotal: BlogPost.count() ]
	}

 	@Secured(['ROLE_ADMIN'])
	def create(){
		def layouts = Layout.list()
		[ layouts: layouts ]
	}


 	@Secured(['ROLE_ADMIN'])
	def save(){
    	def postInstance = new BlogPost(params)
    	if (!postInstance.save(flush: true)) {
			def layouts = Layout.list()
			flash.message = "Something went wrong. Please try again"
    	    render(view: "create", model: [postInstance: postInstance, layouts: layouts ])
    	    return
    	}
    	
    	flash.message = "Successfully saved post"
    	redirect(action: "list")
	}


 	@Secured(['ROLE_ADMIN'])
	def edit(Long id){
		def postInstance = BlogPost.get(id)
		if(!postInstance){
			flash.message = "Unable to find blog post..."
			redirect(action: "list")
			return
		}
		def layouts = Layout.list()
		[ postInstance: postInstance, layouts: layouts ]
	}


 	@Secured(['ROLE_ADMIN'])
	def update(Long id){

		def postInstance = BlogPost.get(id)
		if(!postInstance){
			flash.message = "Unable to find blog post..."
			redirect(action: "list")
			return
		}

		postInstance.properties = params
        	
    	if (!postInstance.save(flush: true)) {
			flash.message = "Something went wrong. Please try again"
    	    render(view: "edit", model: [postInstance: postInstance])
    	    return
    	}
    	
    	flash.message = "Post sucessfully updated"
    	redirect(action: "list")
	}


 	@Secured(['ROLE_ADMIN'])
	def delete(Long id){
		def postInstance = BlogPost.get(id)
		if(!postInstance){
			flash.message = "Unable to find blog post..."
			redirect(action: "list")
			return
		}

		postInstance.delete(flush:true)
		flash.message = "Successfully deleted blog post."
		redirect(action: "list")
	}


 	@Secured(['permitAll'])
	def entries(){
    	params.max = 7
    	[ posts: BlogPost.list(params), postsTotal: BlogPost.count() ]
	}


 	@Secured(['permitAll'])
	def view(id){}


}