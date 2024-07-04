package users.controller.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UserExceptionNotFoundMapper implements ExceptionMapper<UserNotFoundException> {

    @Override
    public Response toResponse(UserNotFoundException e) {
        return Response.status(Response.Status.FOUND.getStatusCode(), "User not found").build();
    }
}
