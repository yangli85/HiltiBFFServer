package hilti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import hilti.model.Image;
import java.io.IOException;
import hilti.service.ImageService;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by yangli on 8/29/16.
 */
@RestController
public class ImageController {
    public static final String FILE_PATH = "/Users/yangli/JavaWorkSpace/HiltiBffServer/audio/";
    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    public ResponseEntity createImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("Upload file is missing");
            return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
        }
        try{
            Image image = new Image();
            image.setCategroy("TestImage");
            image.setContent(file.getBytes());
            image.setSize(file.getSize());
            image.setPattern(file.getContentType());
            image.setName(file.getOriginalFilename());
            BufferedImage img = ImageIO.read(file.getInputStream());
            image.setHeight(img.getHeight());
            image.setWidth(img.getWidth());
            imageService.saveImage(image);
            byte[] bytes = file.getBytes();
            File tempFile = new File(FILE_PATH + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(tempFile));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity<Integer>(image.getId(), HttpStatus.OK);
        } catch(IOException e){
            return new ResponseEntity<Image>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/image/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Image> getImage(@PathVariable("id") int id) {
        System.out.println("Fetching Image with id " + id);
        Image image = imageService.getImage(id);
        if (image == null) {
            System.out.println("Image with id " + id + " not found");
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Image>(image, HttpStatus.OK);
    }
}
