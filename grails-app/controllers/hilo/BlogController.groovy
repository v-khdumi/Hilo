package hilo

import grails.plugin.springsecurity.annotation.Secured

import io.hilo.BlogPost
import io.hilo.Layout

class BlogController {


 	@Secured(['permitAll'])
	def index(){
		def posts = BlogPost.list([max: 3, order: 'dateCreated'])
		[ posts: posts ]
	}


 	@Secured(['ROLE_ADMIN'])
	def list(){
    	params.max = 10
    	[ posts: BlogPost.list(params), postsTotal: BlogPost.count() ]
	}

 	@Secured(['ROLE_ADMIN'])
	def create(){
		def layouts = Layout.list()
		//def categories = BlogCategory.list()
		// if(!categories){
		// 	flash.message = "No blog categories found, you must create at least one category in order to continue."
		// 	redirect(action:"create_category")
		// 	return
		// }

		[ layouts: layouts ]
	}


 	@Secured(['ROLE_ADMIN'])
	def save(){
		println params

		redirect(action:"create")
		return
    	def postInstance = new BlogPost(params)
    	if (!postInstance.save(flush: true)) {
			def layouts = Layout.list()
			//def categories = BlogCategory.list()
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
		//def categories = BlogCategory.list()
		// if(!categories){
		// 	flash.message = "No blog categories found, you must create at least one category in order to continue."
		// 	redirect(action:"manage")
		// 	return
		// }

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
	def view(Long id){
		def postInstance = BlogPost.get(id)
		if(!postInstance){
			flash.message = "Unable to find blog post..."
			redirect(action: "list")
			return
		}
		[ postInstance: postInstance ]
	}



 // 	@Secured(['ROLE_ADMIN'])
	// def manage(){
 //    	params.max = 10
 //    	[ categories: BlogCategory.list(params), categoriesTotal: BlogCategory.count() ]
	// }


 // 	@Secured(['ROLE_ADMIN'])
	// def add_category(){
	// 	def category = new BlogCategory(params)
 //    	if (!category.save(flush: true)) {
	// 		flash.message = "Something went wrong. Please try again"
 //    	    render(view: "manange", model: [ category: category ])
 //    	    return
 //    	}
    	
 //    	flash.message = "Successfully saved blog category"
 //    	redirect(action: "manage")
	// }


 // 	@Secured(['ROLE_ADMIN'])
	// def remove_category(Long id){		
	// 	def categoryInstance = BlogCategory.get(id)
	// 	if(!categoryInstance){
	// 		flash.message = "Unable to find blog category..."
	// 		redirect(action: "manage")
	// 		return true
	// 	}

	// 	def categories = BlogCategory.list()

	// 	if(categories.size() == 1){
	// 		flash.message = "You need at least one additional category to continue."
	// 		redirect(action: "manage")
	// 		return true
	// 	}

	// 	categoryInstance.delete(flush:true)
	// 	flash.message = "Successfully deleted blog category."
	// 	redirect(action: "manage")
	// }



}