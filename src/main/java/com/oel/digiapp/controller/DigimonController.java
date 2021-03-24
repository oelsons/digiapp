package com.oel.digiapp.controller;

import com.oel.digiapp.domain.Digimon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

@RestController
public class DigimonController {

    @Value("${apiUrl}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/all")
    public Digimon[] getAll(){
        return restTemplate.getForObject(apiUrl, Digimon[].class);
    }

    @GetMapping("/name/{name}")
    public Digimon[] getByName(@PathVariable String name){
        return restTemplate.getForObject(apiUrl.concat("/name/").concat(name), Digimon[].class);
    }

    @GetMapping(value = "/namecsv/{name}", produces = "text/csv")
    public ResponseEntity<StreamingResponseBody> getCsvByName(@PathVariable String name){
        Digimon[] response = restTemplate.getForObject(apiUrl.concat("/name/").concat(name), Digimon[].class);

        return getCSVResponseEntity(name, response);
    }

    @GetMapping("/level/{level}")
    public Digimon[] getByLevel(@PathVariable String level){
        return restTemplate.getForObject(apiUrl.concat("/level/").concat(level), Digimon[].class);
    }

    @GetMapping(value = "/levelcsv/{level}", produces = "text/csv")
    public ResponseEntity<StreamingResponseBody> getCsvByLevel(@PathVariable String level){
        Digimon[] response = restTemplate.getForObject(apiUrl.concat("/level/").concat(level), Digimon[].class);

        return getCSVResponseEntity(level, response);
    }

    private ResponseEntity<StreamingResponseBody> getCSVResponseEntity(String property, Digimon[] response) {
        StreamingResponseBody stream = output -> {
            Writer writer = new BufferedWriter(new OutputStreamWriter(output));
            writer.write("Name,IMG,Level" + "\n");
            for (Digimon digimon : response) {
                writer.write(digimon.getName() + "," + digimon.getImg() + "," + digimon.getLevel() + "\n");
                writer.flush();
            }
        };
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + property + ".csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(stream);
    }
}
