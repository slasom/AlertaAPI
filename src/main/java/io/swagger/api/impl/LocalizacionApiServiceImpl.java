package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;
import java.util.ArrayList;
import io.swagger.model.Alerta;


import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-17T17:57:23.078Z[GMT]")

public class LocalizacionApiServiceImpl extends LocalizacionApiService {
    ArrayList<Alerta> localizaciones = new ArrayList<>();
    
    @Override
    public Response localizacionPost(Alerta body, SecurityContext securityContext) throws NotFoundException {
         //Check Objetc Alerta
        if(body.getIdPersona() == null)
            return Response.status(405).entity("Falta idPersona").build();
        if(body.getFecha() == null)
            return Response.status(405).entity("Falta fecha").build();
        if(body.getHora() == null)
            return Response.status(405).entity("Falta hora").build();
        if(body.getLocalizacion() == null)
            return Response.status(405).entity("Falta localizacion").build();
        if(body.getLocalizacion().getLatitud() == null)
            return Response.status(405).entity("Falta localizacion-latitud").build();
        if(body.getLocalizacion().getLongitud() == null)
            return Response.status(405).entity("Falta localizacion-longitud").build();
        
        localizaciones.add(body);
        System.out.println("Add Localizacion");
            
        return Response.status(201).entity("La localizazion de la persona con ID:" + body.getIdPersona() + " ha sido guardada con exito!").build();}
    
}