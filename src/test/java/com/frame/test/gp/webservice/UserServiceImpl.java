package com.frame.test.gp.webservice;


import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author Administrator
 * @CREATE 2017/7/30 21:25
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public List<User> getUsers() {
		return null;
	}

	@Override
	public Response delete(int id) {
		return null;
	}
}
