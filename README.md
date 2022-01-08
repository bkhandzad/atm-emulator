# atm-emulator
An automated teller machine emulator
ATM is a part of our life activity, which helps us in day transactions and business. An automated teller machine (ATM) is a computerized telecommunications instrument that provides the clients of a financial institution with access to financial transactions in a public space without the need for a cashier, human clerk or bank teller.
At this time, the ATM provides the people good services especially the people can get money at any time. We need the ATM system because not all the bank branches are open all days of the week, and some of the customers may not in a situation, they can visit the bank every time, and they want to withdraw money or deposit money for emergency cases. 
# Running project
1. to run with docker just excute "docker-compose up -d --build" in project folder
2. otherwise run Bank service (it will insert some dummy data)  
3. run ATM service (it will login to bank service)  
4. open browser (http://localhost:8082/atm/swagger-ui/index.html)
    1. first you should validate card number (E.g. 1459833336354632)  
    2. second you should authenticate previous step card number (E.g. for above card use 8521 )  
    3. now you can use card transaction services 
    4. if running not from docker update atmservice project application.properties , change service urls from bankservice to localhost
