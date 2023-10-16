package notadomain.aeras.service;

import java.util.Base64;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;
import notadomain.aeras.exception.InvalidCredentialsException;
import notadomain.aeras.exception.InvalidTokenException;
import notadomain.aeras.model.User;
import notadomain.aeras.repo.UserRepo;
import notadomain.aeras.util.RepoFactory;
import notadomain.aeras.util.Security;

public class UserServiceImpl implements UserService {
	private UserRepo repo = RepoFactory.getUserRepo();
	
	/**
	 * TODO Figure out key storage fr fr
	 * Storing my keys here???? Probably not safe, but I don't know how to do it safely
	 */
	private String keyStr ="Qd2ZtDBas7eVH6tm5ti+eUskmoBPMmhhc+x+dt9c2oU=";
	private SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(keyStr));

	
	@Override
	public boolean addUser(String username, String password) {
		String salt = Security.generateSalt();
		
		String pwHash = Security.generatePasswordHash(password, salt);
		
		User takenUsernameOrNull = repo.getUserByUsername(username);
		if(takenUsernameOrNull!=null) {
			return false;
		}
		User user = new User();
		user.setUsername(username);
		user.setPasswordhash(pwHash);
		user.setSalt(salt);
		
		repo.addUserToDatabase(user);
		return true;
	}
	
	@Override
	public String logInUser(String username, String password) throws InvalidCredentialsException{
		User userOfNameOrNull = repo.getUserByUsername(username);
		
		if(userOfNameOrNull != null) {
			String pwHash = Security.generatePasswordHash(password, userOfNameOrNull.getSalt());
			if(pwHash.equals(userOfNameOrNull.getPasswordhash())) {
				return Security.generateJwt("" + userOfNameOrNull.getId(), key);
			}
		}
		throw new InvalidCredentialsException();
	}
	
	@Override
	public String verifyUserAndGetUsername(String token) {
		int userId = 0;
		String username;
		try {
			userId = Integer.parseInt(Security.verifyJwt(token, key));
			username = repo.getUserById(userId).getUsername();
		} catch(InvalidTokenException e) {
			System.out.println(e.getLocalizedMessage());
			username = "";
		}
		
		
		return username;
	}

	@Override
	public boolean changePassword(String oldpw, String newpw, String token) throws InvalidCredentialsException, InvalidTokenException{
		try {
			String subject = Security.verifyJwt(token, key);
			if(!subject.isEmpty()) {
				User user = repo.getUserById(Integer.parseInt(subject));
				if(Security.generatePasswordHash(oldpw, user.getSalt()).equals(user.getPasswordhash())) {
					String newsalt = Security.generateSalt();
					user.setPasswordhash(Security.generatePasswordHash(newpw, newsalt));
					user.setSalt(newsalt);
					repo.updateUser(user);
					return true;
				}
				throw new InvalidCredentialsException("Wrong password");
			}
		} catch (NumberFormatException e) {
			throw new InvalidTokenException(e);
		} catch (InvalidTokenException e) {
			throw e;
		}
		return false;
	}
	
	@Override
	public boolean deleteUser(String token) {
		int userId = 0;
		try {
			userId = Integer.parseInt(Security.verifyJwt(token, key));
			User user = repo.getUserById(userId);
			repo.deleteUser(user);
			return true;
		} catch(InvalidTokenException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return false;
	}
}
