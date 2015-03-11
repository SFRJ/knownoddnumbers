package adapters;

import ports.SystemB;

import javax.ws.rs.core.Response;

import static javax.ws.rs.client.ClientBuilder.newClient;

public class SystemBAdapter implements SystemB {

    @Override
    public String _456() {
        Response response = newClient().target("http://localhost:9997/").request().get();
        return response.readEntity(String.class);
    }
}
