package com.frame.test.gp.webservice;

import javax.jws.WebService;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.ws.Response;
import java.util.List;

/**
 * @author Administrator
 * @CREATE 2017/7/30 21:22
 */
@WebService
@Path(value = "/users/")
public interface UserService {
	@GET
	@Path("/") //http://ip:port/users
	List<User> getUsers();

	@DELETE
	@Path("{id}")
	Response delete(int id);

}
