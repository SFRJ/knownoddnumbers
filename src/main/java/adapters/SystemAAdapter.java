package adapters;

import ports.SystemA;

import javax.ws.rs.core.Response;

import static javax.ws.rs.client.ClientBuilder.newClient;

public class SystemAAdapter implements SystemA {


    @Override
    public String _123() {
        Response response = newClient().target("http://localhost:9996/").request().get();
        return response.readEntity(String.class);
    }
}
