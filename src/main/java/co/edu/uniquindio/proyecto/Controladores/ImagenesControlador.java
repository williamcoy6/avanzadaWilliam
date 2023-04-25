package co.edu.uniquindio.proyecto.Controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.CloudinaryServicio;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor

public class ImagenesControlador {

    private final CloudinaryServicio cloudinaryServicio;


    @PostMapping("/subir")
    public ResponseEntity<MensajeDTO>subirImagen(@RequestParam("file")MultipartFile file) throws  Exception {
        File imagen =cloudinaryServicio.convertir(file);
        Map respuesta= (Map) cloudinaryServicio.subirImagen(imagen, "proyecto");
        return ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK, false, respuesta));

    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<MensajeDTO> eliminarImagen(@PathVariable String id ) throws  Exception {
         Map respuesta = (Map) cloudinaryServicio.eliminarImagen(id);
         return (ResponseEntity.status(HttpStatus.OK).body(new MensajeDTO(HttpStatus.OK,false,respuesta)));

    }



}
