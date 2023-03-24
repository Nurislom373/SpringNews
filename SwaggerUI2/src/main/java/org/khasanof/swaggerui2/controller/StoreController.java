package org.khasanof.swaggerui2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.khasanof.swaggerui2.domain.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Author: Nurislom
 * <br/>
 * Date: 3/21/2023
 * <br/>
 * Time: 9:40 PM
 * <br/>
 * Package: org.khasanof.swaggerui2.controller
 */
@RestController
@RequestMapping(value = "/store/*")
@Tag(name = "Store APIs", description = "It is a long established fact that a reader")
public class StoreController {

    @Operation(summary = "Create New Store", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully Created", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Store.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            })
    })
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Store> create(@Valid @RequestBody Store entity) {
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Store", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Store.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            }),
            @ApiResponse(responseCode = "404", description = "Store Not Found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            })
    })
    @RequestMapping(value = "update", method = RequestMethod.PUT)
    public ResponseEntity<Store> update(@Valid @RequestBody Store entity) {
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Operation(summary = "Delete Store", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            }),
            @ApiResponse(responseCode = "404", description = "Store Not Found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            })
    })
    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<>("Successfully Deleted - Store", HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get Store", description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Get", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Store.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            }),
            @ApiResponse(responseCode = "404", description = "Store Not Found", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RuntimeException.class))
            })
    })
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Store> get(@PathVariable Long id) {
        return new ResponseEntity<>(new Store(id, "Store", "khasanof373@gmail.com",
                20, "The point of using Lorem Ipsum is that it"), HttpStatus.OK);
    }

}
