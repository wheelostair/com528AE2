# Use Cases and Feature list
## Feature list
1. Shop Owner Login
- Known code to start program
- Device setup - inital setup
2. Purchasing goods
- Check with bank card exists
- Check if funds exist
- Validate Transaction
- Error Message
3. Return of goods
- Check with bank card exists
- Validate Transaction
- Check if shop funds exist
4. Shop Owner Logout


## Use Cases
### Owner Login
|                        |      |
| :---                   | :--- |
| **Description:**       | Shop Owner Login to system    
| **Actors:**            | Shop Owner
| **Preconditions:**     | System not logged in
| **Postconditions:**    | System logged in
| **Flow:**              | Shop Owner logs in using known code
| **Alternative Flows:** | Error message if wrong code used
| **Exceptions:**        | not required if machine already logged in
| **Requirements:**      | If first time login ask shop owner to setup bank details/card for payments to go to
### Accepted transaction
|                        |      |
| :---                   | :--- |
| **Description:**       | Purchasing goods accepted     
| **Actors:**            | Shop Owner & Customer
| **Preconditions:**     | Customer wants to buy goods
| **Postconditions:**    | Customer has bought goods
| **Flow:**              | Shop owner enters details - accepted from bank 
| **Alternative Flows:** | 
| **Exceptions:**        | 
| **Requirements:**      | Customers card accepted
### Rejected Transaction
|                        |      |
| :---                   | :--- |
| **Description:**       | Purchasing goods rejected
| **Actors:**            | Shop Owner & Customer
| **Preconditions:**     | Customer wants to buy goods
| **Postconditions:**    | Unable to purchase goods
| **Flow:**              | Shop owner enters details - declined from bank 
| **Alternative Flows:** | 
| **Exceptions:**        | 
| **Requirements:**      | customers card rejected
### No Wifi/Internet
|                        |      |
| :---                   | :--- |
| **Description:**       | Lack of Wifi/Internet    
| **Actors:**            | Shop owner
| **Preconditions:**     | Unable to reach Wifi
| **Postconditions:**    | Error message stating no internet
| **Flow:**              | Unable to complete tranaction due to lack of wifi/internet
| **Alternative Flows:** | 
| **Exceptions:**        | Wifi/Internte Connected
| **Requirements:**      | no Internet signal
### Customer/Owner cancels transaction
|                        |      |
| :---                   | :--- |
| **Description:**       | Customer/Owner cancels transaction     
| **Actors:**            | Owner / Customer
| **Preconditions:**     | During Transaction
| **Postconditions:**    | Transaction cancelled
| **Flow:**              | During trasaction cancel button is pressed and stops actions
| **Alternative Flows:** | Cancel is pressed before transaction and starts again. 
| **Exceptions:**        | 
| **Requirements:**      | Stops transaction
### Refund of transation
|                        |      |
| :---                   | :--- |
| **Description:**       | Refund of transation     
| **Actors:**            | Owner / Customer
| **Preconditions:**     | Transaction already completed
| **Postconditions:**    | Transaction reversed
| **Flow:**              | Reversal of funds completed
| **Alternative Flows:** | Alternative Flows
| **Exceptions:**        | Execptions
| **Requirements:**      | Money transfered from owner to Customer
### Refund of transation failed
|                        |      |
| :---                   | :--- |
| **Description:**       | Refund of transation failed     
| **Actors:**            | Owner / Customer
| **Preconditions:**     | Transaction already completed
| **Postconditions:**    | Transaction reversal failed
| **Flow:**              | Reversal of funds not completed
| **Alternative Flows:** | Alternative Flows
| **Exceptions:**        | Execptions
| **Requirements:**      | Not enough Money in owner account to complete reversal
### Shop owner logout
|                        |      |
| :---                   | :--- |
| **Description:**       | Shop owner logout    
| **Actors:**            | Owner 
| **Preconditions:**     | Logged in to system
| **Postconditions:**    | Logged out of system
| **Flow:**              | From logged in to logged out of system
| **Alternative Flows:** | Alternative Flows
| **Exceptions:**        | Execptions
| **Requirements:**      | System is logged out after processed followed
