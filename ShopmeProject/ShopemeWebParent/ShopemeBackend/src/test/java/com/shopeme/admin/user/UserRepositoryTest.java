package com.shopeme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TestEntityManager entityManger;

	@Test
	public void createUser() {
		Role role = entityManger.find(Role.class, 1);
		User userJames = new User("james@gmail.com", "james@321", "James", "Roy");
		userJames.addRole(role);
		User savedUser = userRepo.save(userJames);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void createUserForTwoRoles() {
		User userRavi = new User("ravi@gmail.com", "ravi@321", "ravi", "mehta");
		Role roleEditor = new Role(3);
		Role roleAssistance = new Role(5);
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistance);
		User savedUser = userRepo.save(userRavi);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void listAllUsers() {
		Iterable<User> users = userRepo.findAll();

		users.forEach(user -> System.out.println(user));
	}
	@Test
	public void findUserById() {
		Optional<User> userJames = userRepo.findById(1);
		System.out.println(userJames);
		assertThat(userJames).isNotNull();
	}
	
	@Test
	public void updateUser() {
		User user = userRepo.findById(1).get();
		user.setEnabled(true);
		user.setEmail("james123@gmail.com");
		userRepo.save(user);
	}
	
	
	@Test
	public void updateUserRole() {
		User userRavi = userRepo.findById(2).get();
		Role roleEditor=new Role(3);
		Role roleSalesPerson=new Role(5);
		userRavi.getRoles().remove(roleEditor);
		userRavi.addRole(roleSalesPerson);
	}
	
	
	
	@Test
	public void deleteUser() {
		Integer id=2;
		userRepo.deleteById(id);
	}
}
