package com.riwi.examen_Rigo.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = " Rigoberto de Jesus Miranda Nuñez ",
                        url = "",
                        email = "rigoberto.miranda1503@gmail.com "),
                license = @License(name = "fleetnav", url = "https://github.com/fleetnav/fleetNav-microservices  LICENSE"),
                title = "Documentación: Reto 1: Talento B",
                version = "1.0",
                description = "En esta documentación de la API proporciona información detallada acerca de los endpoint requerido por el reto técnico propuesto por talento B"))
public class waggerConfig {


}
