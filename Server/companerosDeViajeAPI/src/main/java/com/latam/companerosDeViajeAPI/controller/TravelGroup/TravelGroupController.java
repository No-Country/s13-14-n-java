package com.latam.companerosDeViajeAPI.controller.TravelGroup;

import com.latam.companerosDeViajeAPI.dto.auth.AuthResponseDto;
import com.latam.companerosDeViajeAPI.dto.auth.LoginRequestDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupCreatedDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupDTO;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupInfoDto;
import com.latam.companerosDeViajeAPI.dto.travelGroup.TravelGroupMapper;
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
    @Operation(summary = "Endpoint para la creación de grupos de viaje. \n" +
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

    @GetMapping(value="find-travel-group")
    public ResponseEntity<Page<TravelGroupInfoDto>> findTravelGroups(@PageableDefault(size = 20) Pageable pageable){
        return ResponseEntity.ok(travelGroupServiceImp.getTravelGroups(pageable).map(TravelGroupMapper::travelGroupToTravelGroupInfoDTO));
    }

    @PostMapping(value="add-user")
    public ResponseEntity<TravelGroupInfoDto> findTravelGroups(@RequestParam Long groupId, HttpServletRequest request){
        return ResponseEntity.ok(travelGroupServiceImp.addUserToTravelGroup(groupId, request));
    }
}
