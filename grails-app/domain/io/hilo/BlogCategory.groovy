package io.hilo

class BlogCategory {

	BlogCategory(){
		this.uuid = UUID.randomUUID().toString()
	}
	
	String uuid
	String name
	String description

	Date dateCreated
	Date lastUpdated

	static hasMany = [ posts : BlogPost ]
	
	static constraints = {
		uuid(nullable:true)
		name(unique:true)
		description(nullable:true,size:0..65535)
		id generator: 'sequence', params:[ sequence:'ID_BLOG_CATEGORY_PK_SEQ']
    }

}