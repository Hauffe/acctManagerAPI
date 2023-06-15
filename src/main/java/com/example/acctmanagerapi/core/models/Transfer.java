package com.example.acctmanagerapi.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transfer {

    private Balance origin;
    private Balance destination;


    public void processTransfer(Integer amount) {
        origin.withdraw(amount);
        destination.deposit(amount);
    }

}
