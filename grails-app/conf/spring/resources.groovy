// Place your Spring DSL code here
import io.hilo.exception.BaseException
import io.hilo.handlers.HiloAuthenticationSuccessHandler
import io.hilo.common.CommonUtilities

beans = {
	commonUtilities(CommonUtilities)
	exceptionHandler(io.hilo.exception.BaseException) {
	    exceptionMappings = ['java.lang.Exception': '/error']
	}
	authenticationSuccessHandler(HiloAuthenticationSuccessHandler) {
		//https://groggyman.com/2015/04/05/custom-authentication-success-handler-with-grails-and-spring-security/
        requestCache = ref('requestCache')
        redirectStrategy = ref('redirectStrategy')
	}
}
