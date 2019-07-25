package com.tgpuj.wsga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.www_wsga_com.xml.book.GetBookRequest;
import https.www_wsga_com.xml.book.GetBookResponse;

/*POJO que atiende peticiones SOAP*/

/*@EndPoint: Registrar la clase con Spring WS para procesar la solicitud de SOAP entrante*/

@Endpoint
public class BookEndpoint {
	private static final String NAMESPACE_URI = "https://www.wsga.com/xml/book";
 
	private BookRepository bookRepository;
 
	@Autowired
	public BookEndpoint(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	/*
	 * @PayloadRoot
	 * Ayuda a Spring WS a elegir el método del controlador basado en el espacio de nombres del mensaje y en la parte local
	 * @ResponsePayload
	 * Indica que el mensaje entrante se asignará al parámetro de solicitud del método.
	 * @RequestPayload
	 * Se utiliza para que Spring WS asigne el valor de retorno a la carga útil de respuesta.
	 * */
 
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookRequest")
	@ResponsePayload
	public GetBookResponse getCountry(@RequestPayload GetBookRequest request) {
		GetBookResponse response = new GetBookResponse();
		response.setBook(bookRepository.findBookById(request.getId()));
		return response;
	}
}