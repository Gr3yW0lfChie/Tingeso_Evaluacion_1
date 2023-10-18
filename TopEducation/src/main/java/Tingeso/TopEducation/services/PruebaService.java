package Tingeso.TopEducation.services;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import Tingeso.TopEducation.entities.PruebaEntity;
import Tingeso.TopEducation.repositories.PruebaRepository;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class PruebaService {

	@Autowired
	private PruebaRepository pruebaRepository;
	private final Logger logg = LoggerFactory.getLogger(PruebaService.class);




	//----------------------------------------------------------------------------------------------------------
	//Busqueda
	public ArrayList<PruebaEntity> obtenerPruebas() {
		return (ArrayList<PruebaEntity>) pruebaRepository.findAll();
	}

	public Optional<PruebaEntity> obtenerPruebaPorId(Long id){
		return pruebaRepository.findById(id);
	}

	//----------------------------------------------------------------------------------------------------------
	//Crear
	public PruebaEntity crearPrueba(PruebaEntity pruebaEntity){
		return pruebaRepository.save(pruebaEntity);
	}

	public void guardarPruebaDB(String rutAlumno, LocalDate fechaPrueba, Integer puntaje){
		PruebaEntity pruebaEntity = new PruebaEntity();
		pruebaEntity.setAlumno(rutAlumno);
		pruebaEntity.setFechaExamen(fechaPrueba);
		pruebaEntity.setPuntaje(puntaje);
		crearPrueba(pruebaEntity);
	}


	//----------------------------------------------------------------------------------------------------------
	//Eliminar
	/*
	public void eliminarPrueba(Long id){
		pruebaRepository.deleteById(id);
	}
	*/

	@Generated
	public String guardar(MultipartFile file){
		String filename = file.getOriginalFilename();
		if(filename != null){
			if(!file.isEmpty()){
				try{
					byte [] bytes = file.getBytes();
					Path path  = Paths.get(file.getOriginalFilename());
					Files.write(path, bytes);
					logg.info("Archivo guardado");
				}
				catch (IOException e){
					logg.error("ERROR", e);
				}
			}
			return "Archivo guardado correctamente";
		}
		else{
			return "No se pudo guardar el archivo";
		}
	}



	@Generated
	public void leerCsv(String direccion){
		String texto = "";
		BufferedReader bf = null;
		//pruebaRepository.deleteAll();
		try{
			bf = new BufferedReader(new FileReader(direccion));
			String temp = "";
			String bfRead;
			int count = 1;
			while((bfRead = bf.readLine()) != null){
				if (count == 1){
					count = 0;
				}
				else{
					String[] datos = bfRead.split(";");
					if (datos.length >= 3) {
						String rut = datos[0];
						LocalDate fecha = LocalDate.parse(datos[1]);
						Integer puntaje = Integer.parseInt(datos[2]);

						guardarPruebaDB(rut, fecha, puntaje);
					} else {
						System.out.println("No se pudo guardar la prueba");
					}

					temp = temp + "\n" + bfRead;
				}
			}
			texto = temp;
			System.out.println("Archivo leido exitosamente");
		}catch(Exception e){
			System.err.println("No se encontro el archivo");
		}finally{
			if(bf != null){
				try{
					bf.close();
				}catch(IOException e){
					logg.error("ERROR", e);
				}
			}
		}
	}





}
