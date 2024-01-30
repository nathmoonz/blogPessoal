package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.responses.ApiResponse;

@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Blog Pessoal")
						.description("Projeto Blog Pessoal desenvolvido na turma 70 da Generation Brasil")
						.version("v.0.0.1")
						.license(new License()
								.name("Nathalia Vales")
								.url("https://github.com/nathmoonz"))
						.contact(new Contact()
								.name("Nathalia Vales")
								.url("https://www.linkedin.com/in/nathalia-vales-1b1904234/")
								.email("nathalia.smvales@gmail.com")))
						.externalDocs(new ExternalDocumentation()
								.description("GitHub")
								.url("https://github.com/nathmoonz/blogPessoal"));	
	}
	
	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}
	
	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
}
