package acme.features.any.userAccount;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService  implements AbstractShowService<Any, UserAccount>{
	
	@Autowired
	protected AnyUserAccountRepository repository;
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		return true;
	}

	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;
		UserAccount result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneUserAccountById(id);
		result.getRoles().forEach(r -> {});
		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		final List<UserRole> roles=new ArrayList<>(entity.getRoles());
		roles.sort(Comparator.comparing(UserRole::getAuthorityName).reversed());
		String rolesSt="";
		
		model.setAttribute("name", entity.getIdentity().getName());
		model.setAttribute("surname", entity.getIdentity().getSurname());
		model.setAttribute("email", entity.getIdentity().getEmail());

		boolean firstIteration=true;
		
		for (final UserRole r: roles) {
			if (firstIteration==true) {
				rolesSt=r.getAuthorityName();
				firstIteration=false;
			}else {
				rolesSt=rolesSt+", "+r.getAuthorityName();
			}
		}
		model.setAttribute("roles", rolesSt);
		
	}
}
