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
....
Added diseas table
diseas can accept 3 values diseas name, diseas status and diseas reports file.
diseas table will have total four columns(d_id,d_Name,active_status,report_file).
...
Used Thymleaf for the Ui tamplates
now user can login by running the url name localhost:8080/mh/login
new user can signup on the application by above given url there they will find a sign up button 
after clicking on signup button you'll get the user registation form.
after logging on application you can see the full user list of the application and also we can modify that.
