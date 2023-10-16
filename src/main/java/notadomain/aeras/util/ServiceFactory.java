package notadomain.aeras.util;

import notadomain.aeras.service.UserService;
import notadomain.aeras.service.UserServiceImpl;

public class ServiceFactory {
	public static UserService getUserService() {
		return new UserServiceImpl();
	}

}
