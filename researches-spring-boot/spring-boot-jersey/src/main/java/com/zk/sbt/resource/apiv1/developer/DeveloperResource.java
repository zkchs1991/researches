package com.zk.sbt.resource.apiv1.developer;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by zk_chs on 17/2/12.
 */
@Path("users")
@Component
public class DeveloperResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkEnableEdit (@QueryParam("imageId") Long imageId){
        return Response.ok().build();
    }

}
