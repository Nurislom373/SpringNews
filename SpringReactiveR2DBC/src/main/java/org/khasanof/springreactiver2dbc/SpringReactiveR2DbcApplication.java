package org.khasanof.springreactiver2dbc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.*;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringReactiveR2DbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveR2DbcApplication.class, args);
	}

}

/*

// Controller Version

@RestController
@RequestMapping(value = "/laptop/*")
@RequiredArgsConstructor
class LaptopController {

	private final LaptopRepo laptopRepo;

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public Mono<Laptop> create(@RequestBody Laptop laptop) {
		return laptopRepo.save(laptop);
	}

}
*/

@Component
@RequiredArgsConstructor
class LaptopHandler {

	private final LaptopRepo repo;

	public Mono<ServerResponse> create(ServerRequest request) {
		return ServerResponse.ok().body(request.bodyToMono(Laptop.class)
				.flatMap(repo::save), Laptop.class);
	}

	public Mono<ServerResponse> get(ServerRequest request) {
		String id = request.pathVariable("id");
		return ServerResponse.ok().body(repo.findById(Long.valueOf(id)), Laptop.class);
	}

	public Mono<ServerResponse> getAll(ServerRequest request) {
		return ServerResponse.ok().body(repo.findAll(), Laptop.class);
	}

}

@Configuration
class RouterConfig {

	@Bean
	@RouterOperations(value = {
			@RouterOperation(
					path = "/laptop/{id}",
					produces = {
							MediaType.APPLICATION_JSON_VALUE
					},
					method = RequestMethod.GET,
					beanClass = LaptopHandler.class,
					beanMethod = "get",
					operation = @Operation(
							operationId = "get",
							responses = {
									@ApiResponse(
											responseCode = "200",
											description = "success",
											content = @Content(schema = @Schema(
													implementation = Laptop.class
											))
									),
									@ApiResponse(responseCode = "404", description = "laptop not found with given id")
							},
							parameters = {
									@Parameter(in = ParameterIn.PATH, name = "id")
							}
					)
			),
			@RouterOperation(
					path = "/laptop",
					produces = {
							MediaType.APPLICATION_JSON_VALUE
					},
					method = RequestMethod.GET,
					beanClass = LaptopHandler.class,
					beanMethod = "getAll",
					operation = @Operation(
							operationId = "getAll",
							responses = {
									@ApiResponse(
											responseCode = "200",
											description = "success",
											content = @Content(schema = @Schema(
													implementation = Laptop.class
											))
									)
							}
					)
			),
			@RouterOperation(
					path = "/laptop/create",
					produces = {
							MediaType.APPLICATION_JSON_VALUE
					},
					method = RequestMethod.POST,
					beanClass = LaptopHandler.class,
					beanMethod = "create",
					operation = @Operation(
							operationId = "create",
							responses = {
									@ApiResponse(
											responseCode = "200",
											description = "success",
											content = @Content(schema = @Schema(
													implementation = Laptop.class
											))
									)
							}
					)
			)
	})
	public RouterFunction<ServerResponse> routerFunction(LaptopHandler handler) {
		return RouterFunctions.route()
				.GET("/laptop/{id}", handler::get)
				.GET("/laptop", handler::getAll)
				.POST("/laptop/create", handler::create)
				.build();
	}

}

@Repository
interface LaptopRepo extends ReactiveCrudRepository<Laptop, Long> {

}

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
class Laptop {
	@Id
	private Long id;
	private String name;
	private Long price;
}
