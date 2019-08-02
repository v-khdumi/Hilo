package io.hilo

class BlogPost {
	
	BlogPost(){
		this.uuid = UUID.randomUUID().toString()
	}
	
	String uuid
	String title
	String content
	String meta
	
    static constraints = {
		uuid(nullable:true)
		title(nullable:false)
		content(nullable:false, size:0..65535)	
		meta(nullable:true)
		id generator: 'sequence', params:[sequence:'ID_COUNTRY_PK_SEQ']
    }

}