package com.smu.investimentos.auth.account.api.users;


import com.smu.investimentos.auth.account.api.services.UserService;
import com.smu.investimentos.auth.account.security.CanReadMyUser;
import com.smu.investimentos.auth.account.security.CanReadUsers;
import com.smu.investimentos.auth.account.security.CanWriteUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	@CanReadUsers
	public Page<UserResponse> findAll(Pageable pageable) {
		return userService.findAll(pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
//	@CanWriteUsers
	public UserResponse create(@RequestBody @Valid UserRequest userRequest) {
		return userService.createUserAndAccount(userRequest);
	}

	@GetMapping("/{id}")
	@CanReadMyUser
	public UserResponse findById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CanWriteUsers
	public UserResponse update(@PathVariable Long id,
	            @RequestBody UserUpdateRequest request) {

		return userService.update(id, request);
	}
}
