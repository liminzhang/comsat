/*
 * COMSAT
 * Copyright (C) 2014, Parallel Universe Software Co. All rights reserved.
 *
 * This program and the accompanying materials are dual-licensed under
 * either the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation
 *
 *   or (per the licensee's choosing)
 *
 * under the terms of the GNU Lesser General Public License version 3.0
 * as published by the Free Software Foundation.
 */
package co.paralleluniverse.fibers.retrofit;

import co.paralleluniverse.fibers.SuspendExecution;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import javax.inject.Singleton;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/repos/{owner}/{repo}/contributors")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldApplication extends Application<Configuration> {
    @Override
    public void run(Configuration configuration,
            final Environment environment) throws ClassNotFoundException {
        environment.jersey().register(this);
    }

    @GET
    public List<Contributor> get(@PathParam("owner") String owner, @PathParam("repo") String repo) throws SuspendExecution {
        return Lists.newArrayList(new Contributor("foo", 10), new Contributor(owner, 20),
                new Contributor(repo, 30), new Contributor("bar", 40));
    }

    public static class Contributor {
        String login;
        int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }

        @JsonProperty
        public String getLogin() {
            return login;
        }

        @JsonProperty
        public int getContributions() {
            return contributions;
        }
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
    }
}
