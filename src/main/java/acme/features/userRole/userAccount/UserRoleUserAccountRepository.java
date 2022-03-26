package acme.features.userRole.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
@Repository
public interface UserRoleUserAccountRepository {

	@Query("select u from UserAccount u where u.hasRole()")
	Collection<UserAccount> findAllUserAccounts();
	
}
