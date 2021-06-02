<h3 align="center">MCB Software Engineering Test Part2</h3>

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/DaAnkleBreaker24/SoftEngTest_Backend.git
   ```
3. Go in Part1 folder via command prompt
   ```
   execute 'mvn spring-boot:run' 
    ```
   to build and start directly or 
   ```
   execute 'mvn clean package springboot:repackage'
   ```
   then 
     ```
   execute 'java -jar target\part1-1.0.0.jar'
   ```
   to start via runnable jar
   
## Usage

<p>1. go to <strong>http://localhost:8080/swagger-ui.html</strong> to access swagger</p>
<p>2. Use the Authentication service to authenticate and get a token<p>
  use below body object
 ```
   {
  "password": "string",
  "username": "string"
   }
   ```
  <p>3. Click on <strong>Authorise<strong/> button and add to as follows </p>
     ```
  Bearer *space* GeneratedToken
   ```
    
    Example:
    ```
    Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjQwNjQ5Mjg1LCJpYXQiOjE2MjI2NDkyODV9.-bgZliiPrAS7jTh7M9ZnXDghwS4Qu8HmCG28nXT7Y79Ai7y_HiK5NKE5omgl6Al8VSsCY-fBYDUEPI1cDC0D1w
    ```
    <p> Use any webservice available </p>
