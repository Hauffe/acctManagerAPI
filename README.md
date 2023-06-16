# 📁 Account Manager API

This Spring boot application is responsible for performing the basic operations of a bank account and transfers.

# 🛠️ How to run

Please follow the instructions to run this application:

* Used Maven 3.9.1 and JDK 19

Maven build:

        mvn clean
        mvn install

To run the integration tests alone, please run:

        mvn verify

Go to the `\acctManagerAPI\target` directory and find the `acctManagerAPI-0.0.1-SNAPSHOT.jar` file built, then run:

        java -jar .\acctManagerAPI-0.0.1-SNAPSHOT.jar


Now, if everithing works, please check the `/reset` endpoint at:

        http://localhost:8080/reset

You may see the following text:
    
        OK

#  How it works

### Endpoints:

* ### Reset state before starting tests

      POST /reset

      200 OK


--
* ### Get balance for non-existing account

      GET /balance?account_id=1234

      404 0


--
* ### Create account with initial balance

      POST /event {"type":"deposit", "destination":"100", "amount":10}

      201 {"destination": {"id":"100", "balance":10}}


--
* ### Deposit into existing account

      POST /event {"type":"deposit", "destination":"100", "amount":10}

      201 {"destination": {"id":"100", "balance":20}}


--
* ### Get balance for existing account

      GET /balance?account_id=100

      200 20

--
* ### Withdraw from non-existing account

      POST /event {"type":"withdraw", "origin":"200", "amount":10}

      404 0

--
* ### Withdraw from existing account

      POST /event {"type":"withdraw", "origin":"100", "amount":5}

      201 {"origin": {"id":"100", "balance":15}}

--
* ### Transfer from existing account

      POST /event {"type":"transfer", "origin":"100", "amount":15, "destination":"300"}

      201 {"origin": {"id":"100", "balance":0}, "destination": {"id":"300", "balance":15}}

--
* ### Transfer from non-existing account

      POST /event {"type":"transfer", "origin":"200", "amount":15, "destination":"300"}

      404 0

