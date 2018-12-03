package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import java.util.Map;
import java.util.List;

//Imports necesarios
import java.sql.*;
import java.text.*; 
import java.util.Date; 

import io.swagger.api.NotFoundException;
import io.swagger.api.basededatos.ConexionBD;

import java.io.InputStream;
import java.util.ArrayList;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.java.JavaJerseyServerCodegen", date = "2018-11-17T17:57:23.078Z[GMT]")

public class AlertaApiServiceImpl extends AlertaApiService {
    
    ArrayList<AlertaBateria> alertasBateria = new ArrayList<>();
    ArrayList<Alerta> alertasCaidas = new ArrayList<>();
    ArrayList<Alerta> alertasLocalizacion = new ArrayList<>();
    
    @Override
    public Response alertaBateriaPost(AlertaBateria body, SecurityContext securityContext) throws NotFoundException {
        if(body.getIdPersona() == null)
            return Response.status(405).entity("Falta idPersona").build();
        if(body.getFecha() == null)
            return Response.status(405).entity("Falta fecha").build();
        if(body.getHora() == null)
            return Response.status(405).entity("Falta hora").build();
        if(body.getNivelBateria() == null)
            return Response.status(405).entity("Falta nivel batería").build();
        
        System.out.println("Add AlertaBateria");
        alertasBateria.add(body);
        
        return Response.status(201).entity("La alerta de bateria perteneciente a ID:" + body.getIdPersona() + " ha sido guardada con exito!").build();
    
    }
    
    @Override
    public Response alertaCaidaPost(Alerta body, SecurityContext securityContext) throws NotFoundException {
        //Generar tiempo respuesta
        float t_respuesta=0;
        float startTime = System.currentTimeMillis();
        
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
            
       //Conexion a la base de datos
        ConexionBD bd= new ConexionBD();
        bd.Conexion();
        
        
        //Generar el fecha_hora
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date();
        String fecha_hora=dateFormat.format(date);
        
        
        System.out.println("Add AlertaCaida");
        System.out.println(dateFormat.format(date)); //2016-11-16 12:08:43
        
        alertasCaidas.add(body);
        
        t_respuesta=(System.currentTimeMillis()-startTime);
        //bd.insertarCaida(t_respuesta, fecha_hora, false);
        
        return Response.status(201).entity("La alerta de caida perteneciente a ID:" + body.getIdPersona() + " ha sido guardada con exito!").build();
    
    }
    
    @Override
    public Response alertaLocalizacionPost(Alerta body, SecurityContext securityContext) throws NotFoundException {
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
            
        System.out.println("Add AlertaLocalizacion");
        alertasLocalizacion.add(body);
            
        return Response.status(201).entity("La alerta de localizazion perteneciente a ID:" + body.getIdPersona() + " ha sido guardada con exito!").build();
    }
    
}

