package notadomain.aeras.util;

import notadomain.aeras.repo.UserRepo;
import notadomain.aeras.repo.UserRepoImpl;

public class RepoFactory {
	public static UserRepo getUserRepo() {
		return new UserRepoImpl();
	}
}
