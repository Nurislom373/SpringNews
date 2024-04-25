package org.khasanof;

import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/25/2024 9:48 AM
 */
@Component
public class SpringValidatorRegistry implements InitializingBean {

    private final ApplicationContext applicationContext;
    private final Set<Validator> validators = new HashSet<>();

    public SpringValidatorRegistry(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Set<Validator> validators() {
        return this.validators;
    }

    public void initBinder(WebDataBinder binder) {
        if (binder.getTarget() == null) {
            return;
        }
        BindingResult bindingResult = binder.getBindingResult();
        for (Validator validator : validators) {
            supportValidate(binder.getTarget(), validator, bindingResult);
        }
    }

    private void supportValidate(Object target, Validator validator, BindingResult bindingResult) {
        if (validator.supports(target.getClass())) {
            validator.validate(target, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("bindingResult");
        }
    }

    @Override
    public void afterPropertiesSet() {
        validators.addAll(getValidators());
    }

    private Collection<Validator> getValidators() {
        return applicationContext.getBeansOfType(Validator.class)
                .values();
    }
}
