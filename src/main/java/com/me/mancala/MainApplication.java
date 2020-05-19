package com.me.mancala;

import com.me.mancala.resources.GameResource;
import com.me.mancala.resources.MoveResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jersey.jackson.JsonProcessingExceptionMapper;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class MainApplication extends Application<MainConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public String getName() {
        return "Mancala";
    }

    @Override
    public void initialize(Bootstrap<MainConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle("/public/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/public/js", "/js", null, "js"));
    }

    @Override
    public void run(MainConfiguration configuration,
                    Environment environment) {
        environment.jersey().register(new GameResource());
        environment.jersey().register(new MoveResource());
        environment.jersey().register(new JsonProcessingExceptionMapper(true));

    }

}