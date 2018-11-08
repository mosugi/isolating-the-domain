package example.infrastructure.datasource.user;

import example.domain.model.user.*;
import org.springframework.stereotype.Repository;

@Repository
public class UserDatasource implements UserRepository {
    UserMapper mapper;

    @Override
    public User findBy(UserIdentifier id) {
        User user = mapper.findBy(id);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public Users list() {
        return new Users(mapper.list());
    }

    @Override
    public User register(UserCandidate userCandidate) {
        UserIdentifier userId = new UserIdentifier(mapper.newUserIdentifier());
        mapper.registerUser(userId);
        updateName(userId, userCandidate.name());
        updateMailAddress(userId, userCandidate.mailAddress());
        updatePhoneNumber(userId, userCandidate.phoneNumber());
        return findBy(userId);
    }

    @Override
    public void updateName(UserIdentifier identifier, Name name) {
        Long nameId = mapper.newUserNameIdentifier();
        mapper.registerName(nameId, identifier, name);
        mapper.deleteNameMapper(identifier);
        mapper.registerNameMapper(identifier, nameId);
    }

    @Override
    public void updateMailAddress(UserIdentifier identifier, MailAddress mailAddress) {
        Long mailAddressId = mapper.newUserMailAddressIdentifier();
        mapper.registerMailAddress(mailAddressId, identifier, mailAddress);
        mapper.deleteMailAddressMapper(identifier);
        mapper.registerMailAddressMapper(identifier, mailAddressId);
    }

    @Override
    public void updatePhoneNumber(UserIdentifier identifier, PhoneNumber phoneNumber) {
        Long phoneNumberId = mapper.newUserPhoneNumberIdentifier();
        mapper.registerPhoneNumber(phoneNumberId, identifier, phoneNumber);
        mapper.deletePhoneNumberMapper(identifier);
        mapper.registerPhoneNumberMapper(identifier, phoneNumberId);
    }

    @Override
    public void delete(User user) {
        mapper.delete(user);
    }

    public UserDatasource(UserMapper mapper) {
        this.mapper = mapper;
    }
}
