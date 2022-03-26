package acme.features.userRole.userAccount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.entities.UserAccount;
import acme.framework.roles.UserRole;

@Controller
public class UserRoleUserAccountController extends AbstractController<UserRole, UserAccount>{

	@Autowired
	protected UserRoleUserAccountListService	listService;
	
	@Autowired
	protected UserRoleUserAccountShowService	showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}
	
	
}
