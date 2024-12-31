package org.khasanof.batch52.config.flow.processor;

import lombok.extern.slf4j.Slf4j;
import org.khasanof.batch52.model.PersonDTO;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author Nurislom
 * @see org.khasanof.batch52.config.flow.processor
 * @since 12/31/2024 10:31 PM
 */
@Slf4j
public class PersonItemProcessor implements ItemProcessor<PersonDTO, PersonDTO> {

    /**
     *
     * @param person
     * @return
     * @throws Exception
     */
    @Override
    public PersonDTO process(PersonDTO person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final PersonDTO transformedPerson = new PersonDTO(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
