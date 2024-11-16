package com.example.setup.mapper;

import com.example.setup.entity.Account;
import com.example.setup.model.request.RegisterRequest;
import com.example.setup.model.response.AccountResponse;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toAccount(RegisterRequest request);

    AccountResponse toAccountResponse(Account account);
}
