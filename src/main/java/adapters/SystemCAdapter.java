package adapters;

import ports.SystemC;

import javax.ws.rs.client.Entity;

import static javax.ws.rs.client.ClientBuilder.newClient;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

public class SystemCAdapter implements SystemC {

    @Override
    public void _saveData(String data) {
         newClient().target("http://localhost:9998/").request().post(Entity.entity(data, TEXT_PLAIN));
    }
}
