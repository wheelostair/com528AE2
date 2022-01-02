# Use Cases and Feature list

## Feature list
1. Sessions with Users with specified roles
- Anonymous - hasn't logged in
- Admin - Able to add remove and modify items in the catlogue, view and modify users and orders. 
- Customer - created an account and may create orders - info saved such as card details, but not CVV. 
- Deactivated - an account which is deactivated
2. Purchasing goods
- Items added to basket
- Requests customers bank card deatails
3. Dectrementing stock
- When purchased the available stock levels decrease.
4. Orders and transactions stored in the system.
- Users see their own, admin can see them all. 
5. Interacts with bank 
- check card details and if fund available and moves money if possible
6. Log failed transactions
- users should be able to see their failed bank transaction and re-try
7. Database stores all data using a DAO/Object realtional mapping framework
- Info created during a session is retained for further sessions. 



## Use Cases

### User/Admin login
|                        |      |
| :---                   | :--- |
| **Description:**       | User/Admin Login to system    
| **Actors:**            | User or Admin
| **Preconditions:**     | No User logged in
| **Postconditions:**    | User/ Admin logged in
| **Flow:**              | Click on login area, moved to login page, enter login details
| **Alternative Flows:** | 
| **Exceptions:**        | Error if wrong username or password is entered.
| **Requirements:**      | Account must already be live and created to work.
### User Saves card details
|                        |      |
| :---                   | :--- |
| **Description:**       | Customer saves Card details on user setup page     
| **Actors:**            | Customer
| **Preconditions:**     | Customer card details not saved
| **Postconditions:**    | Customer card deatils saved
| **Flow:**              | Customer enters card details on user entry page
| **Alternative Flows:** | 
| **Exceptions:**        | 
| **Requirements:**      | Customers Card details shows on Basket page when trying to pay. 
### Choosing Items for basket
|                        |      |
| :---                   | :--- |
| **Description:**       | Customer choosing items for basket
| **Actors:**            | Customer
| **Preconditions:**     | Customer wants to buy goods
| **Postconditions:**    | Customer has good in basket and is ready to enter CVV (as Card details saved)
| **Flow:**              | customer chooses items and add them to the basket, goes to basket page and is ready to pay.
| **Alternative Flows:** | 
| **Exceptions:**        | 
| **Requirements:**      | customers card rejected
### Purcahsing Items
|                        |      |
| :---                   | :--- |
| **Description:**       | Items paid for, Money transfered to/from bank and stock decremented    
| **Actors:**            | customer
| **Preconditions:**     | Items in basket
| **Postconditions:**    | Itmes paid for, bank transfer completed and stock decresed
| **Flow:**              | Customer enteres CVV and clicks purchase
| **Alternative Flows:** | 
| **Exceptions:**        | customer enters wrong details
| **Requirements:**      | available money in bank, available stock
### Customer/Admin Views orders
|                        |      |
| :---                   | :--- |
| **Description:**       | Customer/Admin can view previous transactions
| **Actors:**            | Customer / Admin
| **Preconditions:**     | Order taken place
| **Postconditions:**    | Order available for view
| **Flow:**              | customer clicks on my orders page and views previous orders
| **Alternative Flows:** | Admin clicks on my orders page and can view all orders 
| **Exceptions:**        | not logged in
| **Requirements:**      | Customer or admin logged in and order completed
### View Failed transactions
|                        |      |
| :---                   | :--- |
| **Description:**       | Customer can view failed transaction     
| **Actors:**            | Customer
| **Preconditions:**     | Transaction already failed
| **Postconditions:**    | Able to view failed transaction log
| **Flow:**              | customer is shown log of failed transaction when failure happens
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

