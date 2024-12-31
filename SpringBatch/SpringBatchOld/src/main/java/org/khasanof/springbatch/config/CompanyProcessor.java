package org.khasanof.springbatch.config;

import org.khasanof.springbatch.entity.Company;
import org.springframework.batch.item.ItemProcessor;

public class CompanyProcessor implements ItemProcessor<Company, Company> {

    @Override
    public Company process(Company company) throws Exception {
        return company;
    }

}
