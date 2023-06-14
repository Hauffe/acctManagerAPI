package com.example.acctmanagerapi.core.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Transfer {
    private Balance origin;
    private Balance destination;


    public void processTransfer(Integer amount) {
        origin.withdraw(amount);
        destination.deposit(amount);
    }

}
