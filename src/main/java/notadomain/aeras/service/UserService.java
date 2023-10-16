package notadomain.aeras.service;

import notadomain.aeras.exception.InvalidCredentialsException;
import notadomain.aeras.exception.InvalidTokenException;

public interface UserService {
	public boolean addUser(String username, String password);
	public String logInUser(String username, String password) throws InvalidCredentialsException;
	public String verifyUserAndGetUsername(String token);
	public boolean changePassword(String oldpw, String newpw, String token)throws InvalidCredentialsException, InvalidTokenException;
	public boolean deleteUser(String token);
}
