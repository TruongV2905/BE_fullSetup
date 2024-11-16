package com.example.setup.model.request;

import com.example.setup.entity.Account;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailDetail {
    Account receiver;
    String subject;
    String link;
}
