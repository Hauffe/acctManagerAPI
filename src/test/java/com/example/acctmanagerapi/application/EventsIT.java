package com.example.acctmanagerapi.application;

import com.example.acctmanagerapi.core.models.Balance;
import com.example.acctmanagerapi.core.models.Event;
import com.example.acctmanagerapi.core.models.Transfer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventsIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Order(1)
    public void testReset() {
        ResponseEntity<Void> response = testRestTemplate.postForEntity("/reset", null, Void.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    public void testGetBalance_NonExistingAccount() {
        ResponseEntity<Object> response = testRestTemplate.getForEntity("/balance?account_id=1234", Object.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(0, response.getBody());
    }

    @Test
    @Order(3)
    public void testCreateAccountWithInitialBalance() {
        Event depositEvent = new Event("deposit", null, "100", 10);
        ResponseEntity<Transfer> response = testRestTemplate.postForEntity("/event", depositEvent, Transfer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("100", response.getBody().getDestination().getId());
        assertEquals(10, response.getBody().getDestination().getBalance());
    }

    @Test
    @Order(4)
    public void testDepositIntoExistingAccount() {
        Event depositEvent = new Event("deposit", null, "100", 10);
        ResponseEntity<Transfer> response = testRestTemplate.postForEntity("/event", depositEvent, Transfer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("100", response.getBody().getDestination().getId());
        assertEquals(20, response.getBody().getDestination().getBalance());
    }

    @Test
    @Order(5)
    public void testGetBalance_ExistingAccount() {
        ResponseEntity<Integer> response = testRestTemplate.getForEntity("/balance?account_id=100", Integer.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(20, response.getBody());
    }

    @Test
    @Order(6)
    public void testWithdrawFromNonExistingAccount() {
        Event withdrawEvent = new Event("withdraw", null, "200", 10);
        ResponseEntity<Integer> response = testRestTemplate.postForEntity("/event", withdrawEvent, Integer.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(0, response.getBody());
    }

    @Test
    @Order(7)
    public void testWithdrawFromExistingAccount() {
        Event withdrawEvent = new Event("withdraw",  "100", null,5);
        ResponseEntity<Transfer> response = testRestTemplate.postForEntity("/event", withdrawEvent, Transfer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("100", response.getBody().getOrigin().getId());
        assertEquals(15, response.getBody().getOrigin().getBalance());
    }

    @Test
    @Order(8)
    public void testTransferFromExistingAccount() {
        Event transferEvent = new Event("transfer", "100", "300", 15);
        ResponseEntity<Transfer> response = testRestTemplate.postForEntity("/event", transferEvent, Transfer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("100", response.getBody().getOrigin().getId());
        assertEquals(0, response.getBody().getOrigin().getBalance());
        assertEquals("300", response.getBody().getDestination().getId());
        assertEquals(15, response.getBody().getDestination().getBalance());
    }

    @Test
    @Order(9)
    public void testTransferFromNonExistingAccount() {
        Event transferEvent = new Event("transfer", "200", "300", 15);
        ResponseEntity<Integer> response = testRestTemplate.postForEntity("/event", transferEvent, Integer.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(0, response.getBody());
    }

}
