package io.getarrays.server;

import io.getarrays.server.Enumeration.Status;
import io.getarrays.server.Model.Server;
import io.getarrays.server.Repository.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);


	}

@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			serverRepo.save(new Server(null,
					"192.168.1.160",
					"Ubuntu Linux",
					"16GB",
					"Personal Computer",
					"http://localhost:8080/server/image/server1.png",
					Status.SERVER_UP));

			serverRepo.save(new Server(null,
					"10.0.0.0",
					"Windows",
					"32GB",
					"Turntabl",
					"http://localhost:8080/server/image/server2.png",
					Status.SERVER_DOWN));

			serverRepo.save(new Server(null,
					"172.16.0.0",
					"Macintosh",
					"16GB",
					"Super Computer",
					"http://localhost:8080/server/image/server3.png",
					Status.SERVER_UP));

			serverRepo.save(new Server(null,
					"192.168.0.0",
					"Amalitech PC",
					"32GB",
					"Developer Machine",
					"http://localhost:8080/server/image/server4.png",
					Status.SERVER_UP));

			serverRepo.save(new Server(null,
					"192.158.1.169",
					"Sumsung",
					"16GB",
					"Gaming",
					"http://localhost:8080/server/image/server5.png",
					Status.SERVER_DOWN));

		};
	}


}
