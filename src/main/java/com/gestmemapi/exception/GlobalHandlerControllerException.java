package com.gestmemapi.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = {"com.ProjectDevelopper.Authentication"} )
public class GlobalHandlerControllerException extends ResponseEntityExceptionHandler{

    // initialisation globale
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		//Vous pouvez intialiser n'importe quelle donnée ici

	}
	
    // creation d'un objet modèl global
	@ModelAttribute
    public void globalAttributes(Model model) {
		model.addAttribute("technicalError", "Une erreur technique est survenue !");
    }
	
    // interception des execptions dites globales
    @ExceptionHandler(Exception.class)//toutes les autres erreurs non gérées par le service sont interceptées ici
    public ResponseEntity<BusinessResourceExceptionDTO> unknowError(HttpServletRequest req, Exception ex) {
        BusinessResourceExceptionDTO response = new BusinessResourceExceptionDTO();
        response.setErrorCode("Technical Error");
        response.setErrorMessage(ex.getMessage());
        response.setRequestURL(req.getRequestURL().toString()); 
        return new ResponseEntity<BusinessResourceExceptionDTO>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // classe de persistance des messages d'erreur
    @ExceptionHandler(BusinessResourceException.class)
    public ResponseEntity<BusinessResourceExceptionDTO> businessResourceError(HttpServletRequest req, BusinessResourceException ex) {
        BusinessResourceExceptionDTO businessResourceExceptionDTO = new BusinessResourceExceptionDTO();
        businessResourceExceptionDTO.setStatus(ex.getStatus());
        businessResourceExceptionDTO.setErrorCode(ex.getErrorCode());
        businessResourceExceptionDTO.setErrorMessage(ex.getMessage());
        businessResourceExceptionDTO.setRequestURL(req.getRequestURL().toString()); 
        return new ResponseEntity<BusinessResourceExceptionDTO>(businessResourceExceptionDTO, ex.getStatus());
    }
}