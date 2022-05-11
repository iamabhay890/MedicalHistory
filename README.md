# MedicalHistory

connection with DB Done
user created(Tested with postman) 
Adding Signup, login functionality
Adding Forgot password functionality
Adding Edit profile functionality
Adding Logout functionality
Adding Add Prescription functionality


Added all the functionality of the patients 
Patients can add their medical history into the table.
created reference table master.
created patient_medicine table
established a OnetoMany relationship between patient and patient_medicine table
patient-medicine foreign key is "pid" of the patient table with the column name of patient_id

#application ruuning properties
DB portNo-3306
server runnning on 8080

#database details
using mysql version 5
database name - medical_history_api
tables - user, patient,patient_medicine, medicine(master table)
..
