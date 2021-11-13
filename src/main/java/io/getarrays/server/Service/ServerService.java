package io.getarrays.server.Service;

import io.getarrays.server.Model.Server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

public interface ServerService {
//    CRUD OPERATIONS
//    1. CREATING A SERVER METHOD
    Server create(Server server);

//    2. Listing All Servers and setting a limit
    Collection<Server> list(int Limit);

//    3 get Server By ID
    Server get(Long id);

//    4 update a Server
    Server update(Server server);

//    5. Delete a Server
    Boolean delete(Long id);

//    6. Pinging Server method
    Server ping(String ipAddress) throws IOException;


}
