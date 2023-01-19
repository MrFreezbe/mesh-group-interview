package ru.meshgroup.interview.service;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.meshgroup.interview.domain.EmailData;
import ru.meshgroup.interview.domain.PhoneData;
import ru.meshgroup.interview.domain.User;
import ru.meshgroup.interview.exception.EntitiesNotFoundException;
import ru.meshgroup.interview.mapper.UserMapper;
import ru.meshgroup.interview.model.UserDto;
import ru.meshgroup.interview.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Page<User> searchUsers(LocalDate dateOfBirth, String phone, String name, String email, Pageable pageable) {
        return userRepository.findAll(searchUsers(dateOfBirth, phone, name, email), pageable);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailData_EmailAndPassword(email, password)
                .orElseThrow(() -> new EntitiesNotFoundException("User not found"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntitiesNotFoundException(User.class, id));
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        User user = getUserById(userId);

        user.setName(userDto.getName());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPassword(userDto.getPassword());

        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    private static Specification<User> searchUsers(LocalDate dateOfBirth, String phone, String name, String email) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (dateOfBirth != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dateOfBirth"), dateOfBirth));
            }
            if (phone != null) {
                Join<User, PhoneData> phoneData = root.join("phoneData", JoinType.LEFT);
                predicates.add(builder.equal(phoneData.get("phone"), phone));
            }
            if (name != null) {
                predicates.add(builder.like(root.get("name"), name + "%"));
            }
            if (email != null) {
                Join<User, EmailData> emailData = root.join("emailData", JoinType.LEFT);
                predicates.add(builder.equal(emailData.get("email"), email));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
