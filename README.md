# Auto Repair Shop System

Yrgo. Backend programming Part 2. Assignment 2. Spring Data JPA.

## Run the system

1. Open terminal
2. Navigate to the directory where you want this project to be
3. Run git clone https://github.com/gussannra/auto_repair_shop.git
4. Run cd auto_repair_shop
5. Run the program:

- Linux/mac: ./run.sh
- Windows Powershell: .\run.bat
- Windows Command Prompt: run.bat

## Test the system 

### Create customer
POST
http://localhost:8080/customers?name=Bosse&phone=0701020304

### Get customer id by name 
GET
http://localhost:8080/customer-id?name=Bosse

### Create vehicle
POST
http://localhost:8080/vehicles?registrationNumber=ABC123&brand=Volvo&model=V60&productionYear=2020

### Get all customers and their vehicles
GET
http://localhost:8080/customers

### Get all vehicles
GET
http://localhost:8080/vehicles

### Get vehicles by brand
GET
http://localhost:8080/vehicles-by-brand?brand=Volvo

### Add vehicle to customer
POST
http://localhost:8080/addvehicletocustomer?customerId=1&vehicleId=1

### Update vehicle brand
PUT
http://localhost:8080/vehicles/1/brand?brand=Saab

### Get registration numbers and brands
GET
http://localhost:8080/vehicle-brand-info

### Get vehicles by customer id
GET
http://localhost:8080/vehicles-by-customerId?customerId=1
