package tienda.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FirebaseStorageService {
    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);
    
    final String BucketName = "techshop-41b90.appspot.com";
    final String rutaSuperiorStorage = "techshop";
    final String rutaJsonFile = "firebase";
    final String archivoJsonFile = "techshop-41b90-firebase-adminsdk-tfhq1-55e1d6db0c.json";
            
}
