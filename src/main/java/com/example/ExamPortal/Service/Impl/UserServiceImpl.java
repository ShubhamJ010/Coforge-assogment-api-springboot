package com.example.ExamPortal.Service.Impl;

import com.example.ExamPortal.Exception.UserExistsException;
import com.example.ExamPortal.Model.Address;
import com.example.ExamPortal.Model.User;
import com.example.ExamPortal.Repo.AddressRepo;
import com.example.ExamPortal.Repo.UserRepo;
import com.example.ExamPortal.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final AddressRepo addressRepo;


    @Override
    public List<User> getAllUser() throws Exception {
        return userRepo.findAll();
    }

    @Override
    public User AddUser(User user, Address address) throws Exception {
        if (user == null || address == null) {
            throw new IllegalArgumentException("User and address must not be null.");
        }
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new UserExistsException("User with the same email already exists.");
        }

        addressRepo.save(address);

        return userRepo.save(user);
    }
}
