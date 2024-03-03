package com.latam.companerosDeViajeAPI.controller.UserReport;

import com.latam.companerosDeViajeAPI.controller.exception.RestResponseEntityExceptionHandler;
import com.latam.companerosDeViajeAPI.dto.exceptions.ErrorResponseDto;
import com.latam.companerosDeViajeAPI.dto.userReport.ReportDto;
import com.latam.companerosDeViajeAPI.dto.userReport.UserReportDto;
import com.latam.companerosDeViajeAPI.service.UserReport.UserReportServiceImp;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user-report")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
@Tag(name = "User Report")
public class UserReportController {
    private UserReportServiceImp  userReportService;

 @Operation(
            summary="Endpoint para reportar a un traveler por su id de usuario",
            description = "Endpoint para reportar a un traveler por su id de usuario según la US-VIA-10. Este endpoint solo puede ser consultado por usuarios registrados y logueados, y requiere para su autenticación del ingreso del JWT que se obtiene al loguearse.",
            externalDocs = @ExternalDocumentation(url = "https://trello.com/c/b3lX9Pui", description = "Para mayor detalle pueden visitar el Trello, en la tarjeta de la US-VIA-10 BACK"),
            method = "POST",
            requestBody =@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Body requerido con dos parámetros. reportedTravelerId es requerido y se valida que exista un usuario con el id" +
                            "ingresado. message no es requerido, en caso de llegar vacío el mensaje del motivo por el cuál se denuncia al usuario" +
                            "este campo queda vacío.",
                    content = @Content(schema = @Schema(implementation = ReportDto.class))
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success. En caso de éxito, devuelve un  Json con los datos del reporte, incluyendo la información del usuario" +
                                    "reportado y el usuario que realiza el reporte, y el mensaje del reporte, que estará vacío si no se agrega ninguno, " +
                                    "además de un campo booleano llamado revisedComplaint para indicar si el reporte ya fue revisado(al realizar el reporte " +
                                    "se encontrará en false, al ser revisado por el administrador debería cambiar a true).",
                            content = @Content(schema = @Schema(implementation = UserReportDto.class,
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
    @PostMapping(value="send")
    public ResponseEntity<UserReportDto> reportUser(@RequestBody ReportDto reportDto , HttpServletRequest request){
        return ResponseEntity.ok(userReportService.reportUser(reportDto, request));
    }
    @Operation(
            summary = "Endpoint temporal para consultar los reportes",
            description = "Endpoint temporal para consultar todos los reportes y recibirlos como una lista. Solo requiere el JWT. "
    )
    @GetMapping(value = "reports")
    public ResponseEntity<List<UserReportDto>> getUserReports(){
        return ResponseEntity.ok(userReportService.getUserReports());
    }

}
