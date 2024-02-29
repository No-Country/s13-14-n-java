package com.latam.companerosDeViajeAPI.controller.TravelGroup;

import com.latam.companerosDeViajeAPI.controller.exception.RestResponseEntityExceptionHandler;
import com.latam.companerosDeViajeAPI.dto.exceptions.ErrorResponseDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.*;
import com.latam.companerosDeViajeAPI.service.TravelGroup.TravelGroupServiceImp;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("travel-group")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Travel Group")
public class TravelGroupController {
    private TravelGroupServiceImp travelGroupServiceImp;

    public TravelGroupController(TravelGroupServiceImp travelGroupServiceImp) {
        this.travelGroupServiceImp = travelGroupServiceImp;
    }
    @Operation(
            summary="Endpoint para crear grupos de viaje",
            description = "Endpoint para crear grupos de viaje según la US-VIA-05. Este endpoint solo puede ser consultado por usuarios registrados y logueados, y requiere para su autenticación del ingreso del JWT que se obtiene al loguearse.",
            externalDocs = @ExternalDocumentation(url = "https://trello.com/c/HKwglUkI", description = "Para mayor detalle pueden visitar el Trello, en la tarjeta de la US-VIA-05 BACK"),
            method = "POST",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Los elementos de este body son requeridos. departureDate no puede ser igual o posterior a returnDate. Destination y Itinareary no pueden estar vacíos. Budget y minimumNumberOfMembers deben ser números mayores que cero. La lista de intereses debe contener al menos un interés válido que se encuentre en la base de datos. ",
                    content = @Content(schema = @Schema(implementation = TravelGroupDTO.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success. En caso de éxito, devuelve un  Json con los datos del grupo de viaje(travel group) creado, incluídos los datos del dueño del grupo(owner).",
                            content = @Content(schema = @Schema(implementation = TravelGroupCreatedDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request. En caso de error en el ingreso de datos, devuelve, en la mayoría de los casos, un Json que contiene el campo del error y una descripción del mismo.",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden. En caso de no contar con los permisos necesarios o cuando existen excepciones no controladas devuelve un error de permisos.",
                            content = @Content(schema = @Schema(implementation = RestResponseEntityExceptionHandler.class
                            ))
                    )
            }
    )
    @PostMapping(value = "create")
    public ResponseEntity<TravelGroupCreatedDto> createTravelGroup(@RequestBody TravelGroupDTO travelGroupDTO, HttpServletRequest request) {
        return ResponseEntity.ok(travelGroupServiceImp.createTravelGroup(travelGroupDTO, request));
    }
    @Operation(

            summary="Endpoint para buscar en todos los grupos de viaje creados.",
            description = "Endpoint para buscar en todos los grupos de viaje creados según la US-VIA-04. Este endpoint solo puede ser consultado por usuarios registrados y logueados, y requiere para su autenticación del ingreso del JWT que se obtiene al loguearse.",
            externalDocs = @ExternalDocumentation(url = "https://trello.com/c/CGOIrYbI", description = "Para mayor detalle pueden visitar el Trello, en la tarjeta de la US-VIA-04 BACK"),
            method = "GET",
            parameters = {
                    @Parameter(name = "page", description = "Número de página, comenzando en 0. Si no se coloca nada, el valor por defecto es 0. " , example = "1"),
                    @Parameter(name = "size", description = "Cantidad de Grupos de viaje que desea recibir en cada página. Si no se coloca nada, el valor por defecto es 20.  " , example = "20"),
                    @Parameter(name = "sort", description = "Atributo con el cual se desea ordenar la lista recibida. Los parámetros posibles son id, destination, departureDate, returnDate, budget, itinerary, minimumNumberOfMembers. La lista puede ordenarse de modo ascendente, para lo cual hay que agregar \",ASC\" luego del valor del parámetro, o descendente, para lo cual hay que agregar \",DESC\" luego del valor del parámetro. Si no se coloca nada, el valor por defecto es id en orden ascendente.  " , example = "id"),
                    @Parameter(name = "destintation", description = "Opcional. SI se ingresa, se buscará los grupos de viaje cuyos destinos que coincidan exactamente con la palabra ingresada" , example = "Cuba"),
                    @Parameter(name = "departureDate", description = "Opcional. Si se ingresa, se buscará los grupos de viaje que tengan exactamente la fecha de salida ingresada" , example = "2024-05-02T00:00:00"),
                    @Parameter(name = "returnDate", description = "Opcional. Si se ingresa, se buscará los grupos de viaje que tengan exactamente la fecha de regreso ingresada" , example = "2024-05-02T00:00:00"),
                    @Parameter(name = "budget", description = "Opcional. Si se ingresa, se buscará los grupos de viaje que tengan exactamente el monto del presupuesto ingresado" , example = "5000"),
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success. En caso de éxito, el resultado de la consulta se retorna páginado, es decir que la lista total se divide en páginas, comenzando en la página 0. Cada página retorna una lista del objeto paginado, de acuerdo a los parámetros que se hayan seleccionado. \n" +
                                    "Retorna un objeto, que dentro del atributo content, retorna una lista de objetos Travel Group con sus datos. También retorna atributos extra sobre la petición. " +
                                    "Si no se encuentra ningún grupo con los parámetros ingresados, la lista retorna vaćía. Si no se colocan parámetros opcionales, el endpoint retorna todos los grupos de viaje de la base de datos. Los parámetros opcionales que no se van a utilizar no deben ingresarse en la consulta. " +
                                    "En caso de ingresarse, ya sea vacíos o nulos, la búsqueda se realizará con esos valores según corresponda" +
                                    "El atributo totalPages devuelve la cantidad de páginas en total que se encontraron. El atributo totalElements devuelve la cantidad total de travel grups(grupos de viaje" +
                                    ") encontrados con los parámetros ingresados"

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request. En caso de error en el ingreso de datos, devuelve, en la mayoría de los casos, un Json que contiene el campo del error y una descripción del mismo.",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden. En caso de no contar con los permisos necesarios o cuando existen excepciones no controladas devuelve un error de permisos.",
                            content = @Content(schema = @Schema(implementation = RestResponseEntityExceptionHandler.class
                            ))
                    )}

    )
    @GetMapping(value="find-travel-group")
    public ResponseEntity<Page<TravelGroupInfoDto>> findTravelGroups(@PageableDefault(size = 20) Pageable pageable
            , @RequestParam(required = false) String destination, @RequestParam(required = false) LocalDateTime departureDate
            , @RequestParam(required = false) LocalDateTime returnDate, @RequestParam(required = false) BigDecimal budget){
        return ResponseEntity.ok(travelGroupServiceImp.findTravelGroups(pageable, destination, departureDate, returnDate, budget)
                .map(TravelGroupMapper::travelGroupToTravelGroupInfoDTO));
    }
    @Operation(

            summary="Endpoint para agregar un usuario a un grupo de viaje creado.",
            description = "Endpoint para agregar un usuario a un grupo de viaje creado según la US-VIA-03. Este endpoint solo puede ser consultado por usuarios registrados y logueados, y requiere para su autenticación del ingreso del JWT que se obtiene al loguearse.",
            externalDocs = @ExternalDocumentation(url = "https://trello.com/c/UWVemdBR", description = "Para mayor detalle pueden visitar el Trello, en la tarjeta de la US-VIA-03 BACK"),
            method = "POST",
            parameters = {
                    @Parameter(name = "groupId", description = "Campo numérico donde debe enviarse el id del grupo al que se desea unir el usuario. Valida que el id ingresado no esté vacío o sea nulo, y que exista un número con dicho id" , example = "1"),
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success. En caso de éxito, retorna un objeto con la información actualizada del grupo de viaje, que incluye una lista de travelers donde figura el usuario ingresado. Para una doble comprobación, puede consultarse el endpoint para mostrar la lista de grupos de viaje y buscar el grupo con sus datos actualizados. ",
                            content = @Content(schema = @Schema(implementation = TravelGroupInfoDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request. En caso de error en el ingreso de datos, devuelve, en la mayoría de los casos, un Json que " +
                                    "contiene el campo del error y una descripción del mismo. Se valida que la persona que intenta agregarse al grupo" +
                                    " sea User. En caso de tener otro rol, por ejemplo Admin, retorna un error." +
                                    "Valida que el id ingresado no esté vacío o sea nulo y que exista un grupo con el id ingresado por parámetro." +
                                    "Además, valida que el usuario que quiere unirse no se encuentre dentro del grupo de viajeros (travelers). Return: BadRequest: 400 ",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden. En caso de no contar con los permisos necesarios o cuando existen excepciones no controladas devuelve un error de permisos.",
                            content = @Content(schema = @Schema(implementation = RestResponseEntityExceptionHandler.class
                            ))
                    )}

    )
    @PostMapping(value="add-user")
    public ResponseEntity<TravelGroupInfoDto> addUserToTravelGroup(@RequestParam Long groupId, HttpServletRequest request){
        return ResponseEntity.ok(travelGroupServiceImp.addUserToTravelGroup(groupId, request));
    }
    @Operation(
            summary="Endpoint para que un usuario pueda abandonar un grupo de viaje",
            description = "Endpoint para que un usuario pueda abandonar un grupo de viaje según la US-VIA-11. Este endpoint solo puede ser consultado por usuarios registrados y logueados, y requiere para su autenticación del ingreso del JWT que se obtiene al loguearse.",
            externalDocs = @ExternalDocumentation(url = "https://trello.com/c/1ECIlfYx", description = "Para mayor detalle pueden visitar el Trello, en la tarjeta de la US-VIA-11 BACK"),
            method = "PUT",
            parameters = {
                    @Parameter(name = "groupId", description = "Campo numérico donde debe enviarse el id del grupo al que se desea unir el usuario. Valida que el id ingresado no esté vacío o sea nulo, y que exista un número con dicho id. Además, valida que el usuario se encuentre unido al grupo que desea abandonar. En caso de tratarse del dueño del grupo(owner), de ser posible lo reemplaza por otro miembro del grupo de viaje" , example = "1"),
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success. En caso de éxito, retorna un objeto con la información actualizada del grupo de viaje, que incluye una lista de travelers donde no debería figurar el usuario que abandonó el grupo. Además, si el usuario era el owner(dueño) del grupo, y hay otros viajeros, uno de ellos será el nuevo owner del grupo de viaje. Para una doble comprobación, puede consultarse el endpoint para mostrar la lista de grupos de viaje y buscar el grupo con sus datos actualizados. ",
                            content = @Content(schema = @Schema(implementation = TravelGroupInfoDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request. En caso de error en el ingreso de datos, devuelve, en la mayoría de los casos, un Json que " +
                                    "contiene el campo del error y una descripción del mismo. " +
                                    "Valida que el id ingresado no esté vacío o sea nulo y que exista un grupo con el id ingresado por parámetro." +
                                    "Además, valida que el usuario que quiere abandonar el grupo se encuentre en la lista de viajeros(travelers)",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden. En caso de no contar con los permisos necesarios o cuando existen excepciones no controladas devuelve un error de permisos.",
                            content = @Content(schema = @Schema(implementation = RestResponseEntityExceptionHandler.class
                            ))
                    )}

    )
    @PutMapping(value = "leave-travel-group")
    public ResponseEntity<TravelGroupInfoDto> leaveTravelGroup(@RequestParam Long groupId, HttpServletRequest request){
        return ResponseEntity.ok(travelGroupServiceImp.leaveTravelGroup(groupId, request));
    }
    @Operation(
            hidden = true
    )
    @PatchMapping(value = "owner-traveler")
    public ResponseEntity<String> convertOwnerOnTraveler(){
        return ResponseEntity.ok(travelGroupServiceImp.convertOwnerOnTraveler());
    }
    @Operation(
            summary="Endpoint para que un usuario pueda editar la información de un grupo de viaje",
            description = "Endpoint para que un usuario pueda editar la información de un grupo de viaje según la US-VIA-06. Este endpoint solo puede ser consultado por usuarios registrados y logueados, y requiere para su autenticación del ingreso del JWT que se obtiene al loguearse.",
            externalDocs = @ExternalDocumentation(url = "https://trello.com/c/LNyFOqcm", description = "Para mayor detalle pueden visitar el Trello, en la tarjeta de la US-VIA-06 BACK"),
            method = "PUT",

            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "El body es requerido, pero cada campo del objeto es opcional. Solo se realizará la edición, si todos los campos ingresados pasan las validaciones, y únicamente se realizaran las modificaciones de los campos ingresados correctamente. El campo departureDate no puede ser igual o posterior a returnDate. Itinareary no pueden estar vacíos. Budget y minimumNumberOfMembers deben ser números mayores que cero. La lista de intereses debe contener al menos un interés válido que se encuentre en la base de datos. ",
                    content = @Content(schema = @Schema(implementation = UpdateTravelGroupInfoDto.class))
            ),
            parameters = {
                    @Parameter(name = "groupId", description = "Campo numérico donde debe enviarse el id del grupo que se desea editar. Valida que el id ingresado no esté vacío o sea nulo, y que exista un grupo con dicho id. Además, valida que el usuario sea el owner del grupo que desea editar." , example = "1"),
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success. En caso de éxito, retorna un objeto con la información actualizada del grupo de viaje. ",
                            content = @Content(schema = @Schema(implementation = TravelGroupInfoDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request. En caso de error en el ingreso de datos, devuelve, en la mayoría de los casos, un Json que " +
                                    "contiene el campo del error y una descripción del mismo. " +
                                    "Valida que el id ingresado no esté vacío o sea nulo y que exista un grupo con el id ingresado por parámetro." +
                                    "Además, valida que el usuario que desea modificar el grupo sea el owner. " +
                                    "En caso de modificar la fecha de partida(departureDate) y/o la fecha de retorno(returnDate), valida que la nueva combinación de fechas sea posible, y que la fecha de retorno no sea igual o anterior a la de partida. " +
                                    "En caso de ingresar el campo itinerary(itinerario), valida que el nuevo valor no esté vacío. " +
                                    "En caso de agregar el campo budget(presupuesto) y/o minimumNumberOfMembers(mínimo número de miembros), valida que el nuevo valor sea mayor a 0(cero). " +
                                    "En caso que se ingrese el campo interests, valida que la lista de intereses tenga al menos un interés válido de la base de datos, y aquellos intereses duplicados o inexistentes se ignorarán. ",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class,
                                    contentMediaType = MediaType.APPLICATION_JSON_VALUE
                            ))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden. En caso de no contar con los permisos necesarios o cuando existen excepciones no controladas devuelve un error de permisos.",
                            content = @Content(schema = @Schema(implementation = RestResponseEntityExceptionHandler.class
                            ))
                    )}

    )
    @PutMapping(value = "update-travel-group")
    public ResponseEntity<TravelGroupInfoDto> updateTravelGroup(@RequestParam Long groupId, HttpServletRequest request, @RequestBody UpdateTravelGroupInfoDto updateTravelGroupInfoDto){
        return ResponseEntity.ok(travelGroupServiceImp.updateTravelGroup(groupId, request, updateTravelGroupInfoDto));
    }
}
