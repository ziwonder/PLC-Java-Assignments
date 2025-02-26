# Property Managment System
## Overview 
This Java-based Property Management System allows users to manage apartments, supporting both owned and rented properties. The system provides functionalities such as listing apartments, adding new properties, deleting entries, and calculating statistics like mean costs and the oldest apartment
# Features
- Stores apartment data including ID, area, number of rooms, floor, construction year, and address.
- There are two types of apartments: Owned Apartments and Rented Apartments
- Provides a command-line interface for user interaction.
- Implements persistent storage using Java Object Serialization.
# Usage
- Compile a project
  ```bash
  javac *java
  ```
- Run a project
  ```bash
  java PropertyManagementClient <File> <Parameter>
  ```
\<File>: The name of the file used for serialization. If the file does not exist, it should be
created.

\<Parameter>: One of list, add, delete, count, meancosts, or oldest. Only one
parameter can be provided per call.
1. Parameter 'list':
  - Output all data for all apartments.
2. Parameter 'list <id>':
  - Output all data of a single apartment
3. Parameter 'add OA <id> <area> <rooms> <floor> <year_of_construction>
<postal_code> <street> <house_number> <apartment_number>
<operating_costs/m2> <reserve_fund/m2>':
  - Add an owned apartment persistently.
4. Parameter 'add RA <id> <area> <rooms> <floor> <year_of_construction>
<postal_code> <street> <house_number> <apartment_number> <rent/m2>
<tenants>':
  - Add a rented apartment persistently.
5. Parameter 'delete <id>':
  - Delete apartment.
6. Parameter 'count':
  - Calculate the total number of recorded apartments.
7. Parameter 'count <type>':
  - Calculate the total number of owned apartments/rented apartments.
8. Parameter 'meancosts':
  - Calculate the average monthly total costs of all apartments.
9. Parameter 'oldest':
  - Find the oldest apartment(s).
# Example Usage
To add an owned apartment.
Input:
```bash
  java PropertyManagementClient data.ser add OA 1 95 3 4 1898 1080 Florianigasse 42 20 1.55 0.45
```
Output:
```bash
Info: Apartment 1 added.
```
# Error Handling
The system validates inputs and throws meaningful error messages like:
- "Error: Invalid parameter."
- "Error: Invalid year of construction."
- "Error: Apartment already exists. (id=<id>)"
- "Error: Apartment not found. (id=<id>)"
# Dependencies
- Java 8+
- No external libraries requiered 

   
