package io.getarrays.server.Resource;

import io.getarrays.server.Enumeration.Status;
import io.getarrays.server.Model.Response;
import io.getarrays.server.Model.Server;
import io.getarrays.server.Service.Implementation.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.nio.file.Files.readAllBytes;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImplementation serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("servers", serverService.list(30)))
                .message("Servers Retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("server", server))
                .message(server.getStatus() == Status.SERVER_UP? "Ping Success" : "Ping Failed")
                .status(OK)
                .statusCode(OK.value())
                .build()

        );
    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server){
        return ResponseEntity
                .ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("server", serverService.create(server)))
                .message("Server Created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("server", serverService.get(id)))
                .message("Server Retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id){
//        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("deleted", serverService.delete(id)))
                .message("Server Deleted")
                .status(OK)
                .statusCode(OK.value())
                .build()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateServer(@RequestBody @Valid Server server){
        return ResponseEntity
                .ok(Response.builder()
                        .timeStamp(now())
                        .data(Map.of("server", serverService.update(server)))
                        .message("Server Updated")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
                );
    }

    @GetMapping(path = "/image/{imageName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("imageName") String imageName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")
                + "/Downloads/image/" + imageName));
    }

//    @GetMapping(path = "/image/{imageName}", produces = IMAGE_PNG_VALUE)
//    public String getServerImage(@PathVariable("imageName") String imageName) throws IOException {
//        return imageName;
//    }

}
