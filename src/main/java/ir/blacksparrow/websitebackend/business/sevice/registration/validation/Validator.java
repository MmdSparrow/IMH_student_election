package ir.blacksparrow.websitebackend.business.sevice.registration.validation;

import org.springframework.stereotype.Component;

@Component
public class Validator implements IValidator{
    @Override
    public boolean checkEmail(String emailAddress) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        return emailAddress.matches(regex);
    }
}
