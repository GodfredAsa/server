package io.getarrays.server.Service.Implementation;

import io.getarrays.server.Enumeration.Status;
import io.getarrays.server.Model.Server;
import io.getarrays.server.Repository.ServerRepo;
import io.getarrays.server.Service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Implementing the functionalities in the service
 */

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import static io.getarrays.server.Enumeration.Status.*;

@RequiredArgsConstructor // for dependency injection creates a constructor
@Service
@Transactional // enabling CRUD Operations
@Slf4j // enables printing in the console of the application
public class ServerServiceImplementation implements ServerService {
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving New Server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }
    private String setServerImageUrl() {
        String[] imageNames = {"serve1.png", "serve2.png", "serve3.png", "serve4.png", "serve5.png", "serve6.png"};

        return ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(6)])
                .toUriString();
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging Server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? SERVER_UP: SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int Limit) {
        log.info("Fetching All Servers");
        return serverRepo.findAll(PageRequest.of(0, 10)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching Server With ID: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleted Server with ID: {}", id);
         serverRepo.deleteById(id);
        return true;
    }


}
