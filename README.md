#**Gateway Test Project**

##**How to run**

***Run the script file run.sh in the project.***

Ensure that ports 3306 and 8080 are not in use. This file will run the build for the project and will install the docker-compose.

##**How to test**

***A Postman collection sent in the email to test the project.***

There is 5 endpoint to test:

* add gateway "/gateway"
* get gateway "/gateway/{id}"
* get all gateways "/gateway"
* add a device "/gateway/{id}/device"
* delete device "/gateway/device/{id}"




