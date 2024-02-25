package com.latam.companerosDeViajeAPI.controller.TravelGroup;

import com.latam.companerosDeViajeAPI.dto.auth.AuthResponseDto;
import com.latam.companerosDeViajeAPI.dto.auth.LoginRequestDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.*;
import com.latam.companerosDeViajeAPI.persistence.entities.TravelGroup.TravelGroup;
import com.latam.companerosDeViajeAPI.service.TravelGroup.TravelGroupServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("travel-group")
@SecurityRequirement(name = "bearer-key")
public class TravelGroupController {
    private TravelGroupServiceImp travelGroupServiceImp;

    public TravelGroupController(TravelGroupServiceImp travelGroupServiceImp) {
        this.travelGroupServiceImp = travelGroupServiceImp;
    }
    @Operation(summary = "US VIA-05 - Endpoint para la creación de grupos de viaje. \n" +
            "\n" +
            "URL: /travel-group/create\n" +
            "Campos Requeridos: \n" +
            "destination: Cadena de texto dónde colocar el destino de viaje. \n" +
            "departureDate: Cadena donde se coloca la fecha de partida con el siguiente formato: Año-mes-díaTHora:Minutos:Segundos. Ejemplo: \"2024-03-08T10:30:00\"\n" +
            "returnDate: Cadena donde se coloca la fecha de retorno con el siguiente formato: Año-mes-díaTHora:Minutos:Segundos. Ejemplo: \"2024-03-08T10:30:00\" \n" +
            "itinerary: Cadena de texto para colocar el itinerario del viaje \n" +
            "budget: Campo numérico donde se coloca el presupuesto estimado para el viaje en número, sin comillas y con decimales. Ejemplo 100000.00\n" +
            "interest: Arreglo o lista de intereses o características del viaje. Se colocan usando corchetes [] y en su interior el texto de cada interés entre paréntesis, separando cada uno por una coma (,). Ejemplo: [\"playas\", \"montañas\", \"deportes\", \"aventura\"]. \n" +
            "minimumNumberOfMembers: Campo numérico para colocar el número mínimo de miembros del viaje, con números enteros positivos.  \n" +
            "Body: \n" +
            "\n" +
            "{\n" +
            "    \"destination\": \"Carlos Paz\",\n" +
            "    \"departureDate\": \"2023-12-06T00:00:00\",\n" +
            "    \"returnDate\": \"2023-12-08T00:00:01\",\n" +
            "    \"itinerary\": \"Viaje de egresados, desde Puerto Deseado(Santa Cruz) a Carlos Paz. Un viaje recreativo, sin fiestas nocturnas. Con paseos para recorrer la naturaleza, realizar actividades al aire libre(tirolesa, natación, caminatas). \",\n" +
            "    \"budget\": 1000000.00,\n" +
            "    \"interests\": [\n" +
            "        \"Playas\",\n" +
            "        \"montañas\",\n" +
            "        \"deportes\",\n" +
            "        \"turismo urbano\"\n" +
            "    ],\n" +
            "    \"minimumNumberOfMembers\": 25\n" +
            "}\n" +
            "\n" +
            "Token de Autenticación: Requerido. \n" +
            "\n" +
            "Campos No Requeridos: No hay campos opcionales. \n" +
            "\n" +
            "Validaciones: \n" +
            "- Se valida que el usuario tenga un rol apropiado para crear grupos de viaje. \n" +
            "- Se valida que ningún dato esté vacío, nulo o ausente. Si hay errores en la carga de datos retorna un código 400, y un JSON que contiene el campo en que se encuentra el error(Field) y el error que se encontró. \n" +
            "- Se valida que la fecha de salida sea anterior a la fecha de retorno. \n" +
            "- Se valida que el presupuesto sea un número mayor a 0. \n" +
            "- Se valida que al menos uno de los intereses sea válido. Si ninguno lo es, se retorna un código 400 y un informe del error. \n" +
            "- Se eliminan los intereses duplicados de la lista. \n" +
            "- Se agregan únicamente los intereses válidaos dentro de la base de datos. Si un interés no es válido, éste se omite. \n" +
            "- Se valida que el número mínimo de miembros del grupo de viaje sea mayor a 0(cero). \n" +
            "\n" +
            "Respuesta Exitosa: \n" +
            "Código: 200 Ok. \n" +
            "Body: Información del grupo de viaje creado, incluyendo el usuario dueño del mismo. \n")
    @PostMapping(value = "create")
    public ResponseEntity<TravelGroupCreatedDto> createTravelGroup(@RequestBody TravelGroupDTO travelGroupDTO, HttpServletRequest request) {
        return ResponseEntity.ok(travelGroupServiceImp.createTravelGroup(travelGroupDTO, request));
    }
    @Operation(summary = "US VIA-04 - Endpoint para listar todos los grupos de viaje creados \n" +
            "\n" +
            "URL: /travel-group/find-travel-group \n" +
            "\n" +
            "\n" +
            "Campos Requeridos: Ninguno. \n" +
            "\n" +
            "Aclaración. En Swagger los campos optativos son considerados requeridos. En otras herramientas de testeo, si no se modifican los campos usa los valores por defecto. \n" +
            "\n" +
            "Campos Optativos: \n" +
            "\n" +
            "Parámetros: \n" +
            "\n" +
            "page : Número de página, comenzando en 0. Si no se coloca nada, el valor por defecto es 0. \n" +
            "\n" +
            "size : Cantidad de Grupos de viaje que desea recibir en cada página. Si no se coloca nada, el valor por defecto es 20. \n" +
            "\n" +
            "sort : Atributo con el cual se desea ordenar la lista recibida. Los parámetros posibles son id, destination, departureDate, returnDate, budget, itinerary, minimumNumberOfMembers. La lista puede ordenarse de modo ascendente, para lo cual hay que agregar \",ASC\" luego del valor del parámetro, o descendente, para lo cual hay que agregar \",DESC\" luego del valor del parámetro. Si no se coloca nada, el valor por defecto es id en orden ascendente. \n" +
            "\n" +
            "Ejemplo: /travel-group/find-travel-group?page=0&size=15&sort=id,DESC \n" +
            "En este ejemplo, se busca todos los Travel Group, mostrando la página 0(primera página), con 15 resultados como máximo en cada página, ordenados por el atributo id, en orden descendente. \n" +
            "\n" +
            "Aclaración: Para más información de la paginación y las páginas, revisar el apartado Respuesta. \n" +
            "\n" +
            "Token de Autenticación: Requerido. \n" +
            "\n" +
            "Validaciones: \n" +
            "\n" +
            "Este endpoint no posee validaciones. Cualquier usuario logeado, con cualquier rol, puede acceder a él. \n" +
            "\n" +
            "Respuesta Exitosa: \n" +
            "\n" +
            "Código: 200 OK.  \n" +
            "\n" +
            "Body: \n" +
            "El resultado de la consulta se retorna páginado, es decir que la lista total se divide en páginas, comenzando en la página 0. Cada página retorna una lista del objeto paginado, de acuerdo a los parámetros que se hayan seleccionado. \n" +
            "Retorna un objeto, que dentro del atributo content, retorna una lista de objetos Travel Group con sus datos. También retorna atributos adiscionales sobre la petición. \n" +
            "\n" +
            "TODO: Validaciones para que solo muestre los grupos de viaje que no hayan sido eliminados, ni sancionados, o cuyo viaje haya sido concretado. Estas validaciones dependen de que otras historias de usuario se concreten. ")
    @GetMapping(value="find-travel-group")
    public ResponseEntity<Page<TravelGroupInfoDto>> findTravelGroups(@PageableDefault(size = 20) Pageable pageable){
        return ResponseEntity.ok(travelGroupServiceImp.getTravelGroups(pageable).map(TravelGroupMapper::travelGroupToTravelGroupInfoDTO));
    }
    @Operation(summary = "US VIA-03 - Endpoint para que un usuario se agregue a un grupo de viaje\n" +
            "\n" +
            "URL: /travel-group/add-user\n" +
            "\n" +
            "\n" +
            "Campos Requeridos: \n" +
            "\n" +
            "Parametro \n" +
            "\n" +
            "groupId: campo numérico donde debe enviarse el id del grupo al que se desea unir el usuario. \n" +
            "\n" +
            "Ejemplo: \n" +
            "\n" +
            "http://localhost:8080/travel-group/add-user?groupId=15\n" +
            "\n" +
            "En este ejemplo, el usuario se agregará al Grupo de Viaje con id 15, si es que ese grupo existe y pasa las validaciones. \n" +
            "\n" +
            "Campos Optativos: Ninguno. \n" +
            " \n" +
            "Token de Autenticación: Requerido. \n" +
            "\n" +
            "Validaciones: \n" +
            "\n" +
            "- Se valida que la persona que intenta agregarse al grupo sea User. En caso de tener otro rol, por ejemplo Admin, retorna un error. Return: BadRequest: 400\n" +
            "- Que el id ingresado no esté vacío o sea nulo. Return: BadRequest: 400\n" +
            "- Que exista un grupo con el id ingresado por parámetro. Return: BadRequest: 400\n" +
            "- Que el usuario que quiere unirse no sea el dueño (owner) o creador del grupo: Return: BadRequest: 400\n" +
            "- Que el usuario que quiere unirse no se encuentre dentro del grupo de viajeros (travelers). Return: BadRequest: 400 \n" +
            "\n" +
            "\n" +
            "Respuesta Exitosa: \n" +
            "\n" +
            "Código: 200 OK. \n" +
            "\n" +
            "Body: \n" +
            "\n" +
            "En caso de éxito, retorna un objeto con la información actualizada del grupo de viaje, que incluye una lista de travelers donde figura el usuario ingresado. Para una doble comprobación, puede consultarse el endpoint para mostrar la lista de grupos de viaje y buscar el grupo con sus datos actualizados. " +
            "TODO: \n" +
            "- Agregar validaciones para que un usuario sancionado no pueda agregarse a un grupo de viaje, o que no pueda agregarse a grupos de viaje que ya partieron o que fueron sancionados o eliminados. Estas validaciones dependen de otras historias de usuario. ")
    @PostMapping(value="add-user")
    public ResponseEntity<TravelGroupInfoDto> findTravelGroups(@RequestParam Long groupId, HttpServletRequest request){
        return ResponseEntity.ok(travelGroupServiceImp.addUserToTravelGroup(groupId, request));
    }

    @PutMapping(value = "leave-travel-group")
    public ResponseEntity<TravelGroupInfoDto> leaveTravelGroup(@RequestParam Long groupId, HttpServletRequest request){
        return ResponseEntity.ok(travelGroupServiceImp.leaveTravelGroup(groupId, request));
    }
    @PatchMapping(value = "owner-traveler")
    public ResponseEntity<String> convertOwnerOnTraveler(){
        return ResponseEntity.ok(travelGroupServiceImp.convertOwnerOnTraveler());
    }
}
