package com.library.service;

import com.library.domain.Loan;
import com.library.domain.Role;
import com.library.domain.User;
import com.library.domain.enums.RoleType;
import com.library.dto.UserDTO;
import com.library.dto.mapper.UserMapper;
import com.library.dto.requests.AdminUpdateUserRequest;
import com.library.dto.requests.RegisterRequest;
import com.library.dto.requests.UserUpdateRequest;
import com.library.dto.response.UserLoansResponse;
import com.library.exception.BadRequestException;
import com.library.exception.ConflictException;
import com.library.dto.UserCreateDTO;
import com.library.exception.ResourceNotFoundException;
import com.library.exception.message.ErrorMessage;
import com.library.repository.LoanRepository;
import com.library.repository.RoleRepository;
import com.library.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private LoanRepository loanRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;


    public UserCreateDTO createUser(UserCreateDTO userCreateDTO) {

        boolean emailExist = userRepository.existsByEmail(userCreateDTO.getEmail());
        User user = userMapper.userCreateDTOToUser(userCreateDTO);

        if (emailExist){
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, user.getEmail()));
        }

        Role ro = new Role();
        ro.setName(roleRepository.findById(userCreateDTO.getRoleId()).get().getName());

        Set<Role> roles = new HashSet<>();
        roles.add(ro);

        userRepository.save(user);

        userCreateDTO.setId(user.getId());

        return userCreateDTO;

    }

    public UserDTO register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, request.getEmail()));
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Role role = roleRepository.findByName(RoleType.ROLE_MEMBER).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_MEMBER.name())));


        Set<Role> roles = new HashSet<>();
        roles.add(role);
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setPassword(encodedPassword);// security eklendikten sonra hashlenmiş password set edilecek
        user.setBirthDate(request.getBirthDate());
        user.setCreateDate(request.getCreateDate());
        user.setPhone(request.getPhone());
        user.setResetPasswordCode(request.getResetPasswordCode());
        user.setRoles(roles);
     /*   UserDTO userDTO= userMapper.registerToUserDTO(request);
        userDTO.setRoles(roles);
        User user = userMapper.userDTOToUser(userDTO);*/
        userRepository.save(user);
        UserDTO userDTO = userMapper.userToUserDTO(user);
        return userDTO;

    }

    @Transactional(readOnly = true)
    public Page<UserDTO> getUserPage(Optional<String> query, Pageable pageable) {

        Page<UserDTO> dtoPage = null;

            dtoPage = userRepository.findUsersQueryOptionalSearchWithPage(query, pageable);
            return dtoPage;
    //  dtoPage = userRepository.findUserWithPage(pageable);
    //    return dtoPage;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.map(users);
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, id)));
        return userMapper.userToUserDTO(user);
    }

    public UserDTO removeById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, id)));
       List<Loan> loans = loanRepository.getUserLoans(id);

        for (Loan each : loans) {
            if (each.equals(null)){
                throw new BadRequestException("User can not deleted");
            }
        }
        if (user.getBuiltIn()) {
            throw new BadRequestException(String.format(ErrorMessage.CANT_PROCESS__WITH_BUILT_IN_TRUE_USER));
        }
        userRepository.deleteById(id);
        return userMapper.userToUserDTO(user);
    }

    public UserDTO updateUserByAdminOrStaff(Long id, Long idLogin, AdminUpdateUserRequest adminUpdateUserRequest) {

        boolean emailExist = userRepository.existsByEmail(adminUpdateUserRequest.getEmail());

        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, id)));

        User userLogin = userRepository.findById(idLogin).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, idLogin)));

        if (user.getBuiltIn()) {
            throw new BadRequestException(ErrorMessage.CANT_PROCESS__WITH_BUILT_IN_TRUE_USER);
        }

        Set<Role> userRoles = user.getRoles();
        RoleType updateRoleName = userRoles.stream().findFirst().get().getName();

        Set<Role> loginUserRoles = userLogin.getRoles();
        RoleType loginUserRole = loginUserRoles.stream().findFirst().get().getName();

        if (loginUserRole.equals(RoleType.ROLE_STAFF) && updateRoleName.equals(RoleType.ROLE_ADMIN)) {
            throw new BadRequestException(ErrorMessage.STAFF_DOESNT_PROCESS_ABOUT_ADMIN);
        }
        if (loginUserRole.equals(RoleType.ROLE_STAFF) && updateRoleName.equals(RoleType.ROLE_STAFF)) {
            throw new BadRequestException(ErrorMessage.STAFF_DOESNT_PROCESS_ABOUT_OTHER_STAFF);
        }
        if (emailExist && !adminUpdateUserRequest.getEmail().equals(user.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, user.getEmail()));
        }
        if (adminUpdateUserRequest.getPassword() == null) {
            adminUpdateUserRequest.setPassword(user.getPassword());
        } else {
            String encodedPassword = passwordEncoder.encode(adminUpdateUserRequest.getPassword());
            adminUpdateUserRequest.setPassword(encodedPassword);
        }

        Set<String> userStrRoles = adminUpdateUserRequest.getRoles();
        Set<Role> roles = convertRoles(userStrRoles);

        User updateUser = userMapper.adminUpdateUserRequest(adminUpdateUserRequest);

        updateUser.setId(user.getId());
        updateUser.setRoles(roles);

        userRepository.save(updateUser);

        return userMapper.userToUserDTO(updateUser);
    }

    private Set<Role> convertRoles(Set<String> userStrRoles) {

        Set<Role> roles = new HashSet<>();

        if (userStrRoles == null) {
            Role userRole = roleRepository.findByName(RoleType.ROLE_MEMBER).orElseThrow(() ->
                    new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_MEMBER.name())));
            roles.add(userRole);
        } else {
            userStrRoles.forEach(role -> {
                switch (role) {
                    case "Administrator":
                        Role adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN).orElseThrow(() ->
                                new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_ADMIN.name())));
                        roles.add(adminRole);
                        break;
                    case "Staff":
                        Role staffRole = roleRepository.findByName(RoleType.ROLE_STAFF).orElseThrow(() ->
                                new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_STAFF.name())));
                        roles.add(staffRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(RoleType.ROLE_MEMBER).orElseThrow(() ->
                                new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE, RoleType.ROLE_MEMBER.name())));
                        roles.add(userRole);
                }
            });
        }
        return roles;
    }

    public UserDTO updateUser(Long id, UserUpdateRequest request) {
        boolean existEmail = userRepository.existsByEmail(request.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ROLE_NOT_FOUND_MESSAGE));

        if (user.getBuiltIn()) {
            throw new BadRequestException(ErrorMessage.CANT_PROCESS__WITH_BUILT_IN_TRUE_USER);
        }

        if (existEmail && !request.getEmail().equals(user.getEmail())) {
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST_MESSAGE, user.getEmail()));
        }

        User updateUser = userMapper.updateUserBySelf(request);
        updateUser.setCreateDate(user.getCreateDate());
        updateUser.setBuiltIn(user.getBuiltIn());
        updateUser.setScore(user.getScore());
        updateUser.setPassword(user.getPassword());
        updateUser.setId(user.getId());
        updateUser.setRoles(user.getRoles());

        userRepository.save(updateUser);
        return userMapper.userToUserDTO(updateUser);
    }

    public void updatePassword(Long id, UpdatePasswordRequest passwordRequest) {

        Optional<User> optUser = userRepository.findById(id);
        User user = optUser.get();

        if (user.getBuiltIn()) {
            throw new BadRequestException(ErrorMessage.CANT_PROCESS__WITH_BUILT_IN_TRUE_USER);
        }
        if (!passwordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException(ErrorMessage.PASSWORD_DOESNT_MATCH);
        }

        String encodedPassword = passwordEncoder.encode(passwordRequest.getNewPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public Page<UserLoansResponse> getUserLoans(Long id, Pageable pageable) {

        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE, id)));

        Page<UserLoansResponse> authUserLoans = loanRepository.getAuthUserLoans(id, pageable);

        if (authUserLoans.isEmpty()) {
            throw new ResourceNotFoundException(String.format(ErrorMessage.LOAN_NOT_FOUND_MESSAGE, id));
        }

        return authUserLoans;
    }
}
