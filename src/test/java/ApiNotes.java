public class ApiNotes {

    /*
    API: Application Programming Interface
         APIs do not have user interface
    APIs are used to communicate with other applications
    APIs accepts xml or Json data format for the requests
    APIs produce xml or Json data format for the responses
    Json format is similar to Map, and xml format is similar to HTML.
    We use RestAssured Library.
    RestAssured Library is a library in Java and able to use many data format such as xml, json...etc.
    It is very common for API.
    When we create an API we will need endpoints.

    API can do =>
            1- insert
            2- update
            3- delete
            4- read
               operations on DataBase.
               For all different insert/update/delete/read operations, we will need different URLs(EndPoints).

    Why do we need multiple URLs?
        Because we may have different insert operations (students, teachers, grades...etc)

    In Api testing, we create different test scenarios for every endpoint, and we test them if Api works as expected.
    As an API automation tester, I check all endpoints if they are working as expected.

    How do I understand expected condition?
        Answer: Documentation(Swagger)
        When a developer create an Api, in Swagger documentation, developer defines the conditions such as HTTP requests methods, URLs...etc.
        As a tester, I create my test cases according to the swagger documentation. I have details and all the endpoints. I test them first
        manually and I type automation scripts.

    HTTP Request Methods:
        1- GET Method       : It is for reading data from database (Read --> R in CRUD)
                              To be able to use GET Method we need just "endpoint"
        2- POST Method      : It is for creating new data in database (Create --> C in CRUD)
                              To be able to send POST Request we need "endpoint" + "Response body"
        3- PUT Method       : It is for fully update in database (Update --> U in CRUD)
                              To be able to send PUT Request we need "endpoint" + "Fully Response body"
        4- PATCH Method     : It is for partially update in database (Update --> U in CRUD)
                              To be able to send PATCH Request we need "endpoint" + "Partial Response body"
        5- DELETE Method    : It is for deleting data from database (Delete --> D in CRUD)

     HTTP Status Codes:
     1XX    : It means the request is received and the process is continuing
     2XX    : It means the action was successfully received; understood; and accepted.
     3XX    : It means further actions must be taken to complete the request. (check your request if you missed something)
     4XX    : It means the request contains incorrect syntax or cannot be fulfilled.
     5XX    : It means server is down

     Note : In API testing, status code must be tested first. If the status code 2XX then we can test the details.

     Manual Testing ==> Tool Postman (download Postman)
     Postman is a User Interface/application, and it is used to test APIs manually.
     REST ASSURED LIBRARY   : It is a library in Java. It is used to test endpoints in automation.

    Cookies    : When we visit a website/application, it may use cookies to get our information like IP, username etc.
                If we accept cookies, it will crate a local area in our local computer which we do not see and store our data here.
                So our next visit in the same website will be faster because the website goes and take this data from there.

    APIs can also use cookies.

    Authorization   : Security level. Some APIs use just username and password => Based Authentication
                      But some APIs uses more complicated authorizations. => Role Based Authentication

    JsonPath Expressions :
    $ ==>> the root element, all the data
    $.store.book ==>  we get only book data
    $.store.book[1] ==> index 1 , we will get first Json data
    $.store.book[2] ==> index 2 , we will get second Json data (1,2 Positive indexes will give you the element from the beginning)
    $.store.book[-1] ==> index 1 , we will get last Json data (-1,-2 Negative indexes will give you the element from the end)
    $.store.book[1,3] ==> we will get multiple (first and third) Json data
    $.store.book[2].price ==> we will get the value of price key of the second book (data)


    ************API Challenge**********************************
    The biggest challenge in API  testing is data types
    Because Api's using json data
    Java use Object, Primitive, Maps...etc
    Solution : Deserialization.
    De-Serialization means you will get json data and convert it to Java object.

    In API testing we are using Rest Assured Library. Rest Assured Library is in Java.
    Api's use json data. If I use Java, data must be understood by Java.
    So, I have to convert json data to data type which Java uses. ==>> De-Serialization

    Notes   :
    1)  Java uses Objects and Primitives as data type, API uses XML, Json, etc.
        Java and API are using different data types, but they should communicate with each other.
        Communication is impossible with different data types.

        We have 2 options:
    i)  Convert Json to Java Object  ==>> De-Serialization(mostly we do De-Serialization)
    ii) Convert Java Object to Json ==>> Serialization

       For Serialization and De-Serialization, we have 2 options:
       a)GSON           : Google created it. By using Gson we can don Serialization adn De-Serialization
       b)Object Mapper  : More popular
     ************



     ***********Authentication & Authorization ****************
     Authentication : The process of verifying who a user is.
     Authorization  : The process of verifying what the user has access to. It means the right to have access to Database or Server.

     To learn how to get token we need to read the Swagger documentation.













     */




























}
