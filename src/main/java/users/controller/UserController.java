package users.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import users.controller.entity.UserEntity;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import users.controller.service.UserService;

import java.util.UUID;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var users = userService.findAll(page, pageSize);
        return Response.ok(users).build();
    }

    @Path("/{id}")
    @GET
    public Response findUserById(@PathParam("id") UUID userId) {
        var user = this.userService.findById(userId);
        return Response.ok(user).build();
    }


    @POST
    //Annotation necessária para interagir com a base (post, put e delete) get não precisa.
    @Transactional
    public Response createUser(UserEntity userEntity) {
        var user = userService.createUser(userEntity);
        return Response.ok(user).build();
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public Response updateUser(@PathParam("id") UUID userId, UserEntity userEntity) {
        var user = userService.updateUser(userId, userEntity);
        return  Response.ok(user).build();
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public Response deleteUser(@PathParam("id") UUID userId) {
        userService.deleteById(userId);
        return Response.noContent().build();
    }

}

