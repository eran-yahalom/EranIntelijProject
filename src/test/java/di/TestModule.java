package di;

import com.google.inject.AbstractModule;
import api.client.ReqresAPI;
import api.client.PetStoreSwaggerAPI;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {

        // bind API clients
        bind(ReqresAPI.class);
        bind(PetStoreSwaggerAPI.class);
    }
}