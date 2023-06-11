package udesc.dsw.droneseta.utils;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

public class FileSystemUtils {

    private static final String GALLERY_FOLDER = "./uploads/";

    public static String saveInGallery(MultipartFile originalFile, String imageName) throws Exception {
        String path = GALLERY_FOLDER + imageName;
        Path newFile = Paths.get(path);
        Files.createDirectories(newFile.getParent());

        byte[] content = originalFile.getBytes();
        Files.write(newFile, content);

        return newFile.toAbsolutePath().toString();
    }

    public static byte[] findFile(String location) {
        try {
            FileSystemResource file = new FileSystemResource(Paths.get(location));
            return file.getInputStream().readAllBytes();
        } catch (Exception e) {
            return null;
        }
    }

}