package hilti.controller;

import hilti.model.Audio;
import hilti.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yangli on 8/29/16.
 */
@RestController
public class AudioController {
    public static final String FILE_PATH = "/Users/yangli/JavaWorkSpace/HiltiBffServer/audio/";
    @Autowired
    private AudioService audioService;

    @RequestMapping(value = "/audio/upload", method = RequestMethod.POST)
    public ResponseEntity createAudio(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            System.out.println("Upload file is missing");
            return new ResponseEntity<Audio>(HttpStatus.BAD_REQUEST);
        }
        try {
            Audio audio = new Audio();
            audio.setName(file.getOriginalFilename());
            audio.setPattern(file.getContentType());
            audio.setCategroy("message");
            audio.setSize(file.getSize());
            audio.setContent(file.getBytes());
            byte[] bytes = file.getBytes();
            File tempFile = new File(FILE_PATH + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(tempFile));
            stream.write(bytes);
            stream.close();
            audioService.saveAudio(audio);
            file.transferTo(tempFile);
            return new ResponseEntity<Integer>(audio.getId(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<Audio>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<Audio>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/audio/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Audio> getAudio(@PathVariable("id") int id) {
        System.out.println("Fetching Audio with id " + id);
        Audio audio = audioService.getAudio(id);
        if (audio == null) {
            System.out.println("Audio with id " + id + " not found");
            return new ResponseEntity<Audio>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Audio>(audio, HttpStatus.OK);
    }
}
