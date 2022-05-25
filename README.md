# SpendTrackingApplication
This is a Spend Tracker Web Application. It is developed using Java, Spring JPA, Spring security, Json Web Token for security, MySQL &amp; MongoDb for Database.

# BluePrint
1. User Registration
2. User will have wallet
3. On Registration digital currency will be assigned to User
4. Using digital currency user can purchase any product listed on app
5. User purchase history should be persisted on mongodb --> MONGODB
6. Users Daily activity need to be captured (assume Login to Logout life cycle for a day)
7. Product/walled/user should be persisted on mysql
8. User Authentication/Authorization
9. Two types of roles : System_Admin and End_User
-------------------------------------
10. System_Admin
1. able perform Product Lifecycle
2. Able fetch Users data analytics(Top spends by user/Most product purchased etc)
3. Users Listing/Disable Users
4. Able to update digital currency that will be assigned to users
5. Able to update specific users wallet
6. Able to increase product quantity incase of out of stock
------------------------------
11. End Users
1. Register
2. Login
3. Able to update Profile
4. Able to view wallets
5. Able view all products according to category(max 5)
6. Purchase Product
7. deduct balance from walllet if minimum balance is not there throw proper error
8. able to view order/purchase history
-----------------------------------------

# Database Design

## User          
-> user_id (PK)
-> firstname
-> lastname
-> username
-> password
-> emailid
-> enabled

### Features 
1. register
2. login
3. Update profile
4. fetch wallet
5. show products with category(max 5)
6. purchase product(wallet update,product quantity update)
7. purchase history
8. disable user


## Product          
-> product_id (PK) , product_category_id (FK)
-> product_id
-> product_name
-> product_category_id

### Features 
1. add product
2. update product
3. delete product
4. get product


## Product Category   
-> product_category_id (PK)
-> product_category_id
-> prod_category_name

### Features 
1. show products by category


## User Wallet     
-> user_id (FK) , wallet_id (PK)
-> user_id
-> wallet_id
-> wallet_credit

### Features 
1. update wallet
2. check minimum balance
3. show wallet

