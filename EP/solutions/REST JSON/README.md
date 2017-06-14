# REST JSON

The task description [here](https://github.com/scheldejonas/Exercises/blob/master/EP/exam-preparation_REST_JSON.pdf)

## General part

- Explain the term REST and the architectural Requirements that relates to REST 
  - **Representational state transfer** (**REST**) or **RESTful** [web services](https://en.wikipedia.org/wiki/Web_service) is one way of providing interoperability between computer systems on the [Internet](https://en.wikipedia.org/wiki/Internet). REST-compliant Web services allow requesting systems to access and manipulate textual representations of [Web resources](https://en.wikipedia.org/wiki/Web_resource) using a uniform and predefined set of [stateless](https://en.wikipedia.org/wiki/Stateless_protocol) operations. Other forms of Web service exist, which expose their own arbitrary sets of operations such as [WSDL](https://en.wikipedia.org/wiki/Web_Services_Description_Language) and [SOAP](https://en.wikipedia.org/wiki/SOAP).[[1\]](https://en.wikipedia.org/wiki/Representational_state_transfer#cite_note-1)
  - Architectural requirements:
    - Client-Server
    - Stateless
    - Cacheable
    - Layered system
    - Code on demand (optional)
    - Uniform interface
    - https://en.wikipedia.org/wiki/Representational_state_transfer#Architectural_constraints
- Elaborate on how JSON or XML supports communication between subsystems, even when the subsystems are implemented on different platforms. 
  - There is consesus on the data format, and therefore makes it transferable to other services or applications throught JSON or XML.
  - Because the dataformat is in a simple way of declaring arrays, objects, keys and values.
    - [] = Array
    - {} = Object
    - "key" : = key
    - : "value" = value

## Practical part

- Implement a class, which can provide random test data as sketched below

  - ```java
    String data = dataGenerator.getData(100,"fname,lname,street,city ");
    ```

  - should return a JSON array with 100 test data on the form: 

  - ```json
    [{"fname": "Bo", "lname":"Hansen", "street": "Lyngbyvej 26", "city": "Lyngby"},..] 
    ```

- Create a JAX-RS application, which should implement a REST service, that when called like this:

  - ```http
    GET: http:/……/api/addresses/100/fname,lname,city
    ```

  - should return a JSON array as sketched below: 

  - ```json
    [{"fname": "Bo", "lname" : "Hansen","city": "Lyngby"}, {..}, …]
    ```

- Create a simple web page which should, using whatever technology you prefer, render a table with test data fetched via the REST method implemented in step 2.

  - http://localhost:8080/templates/addresses.html
  - **TablePopulator.js in webapp folder**

- If you have time. Add a response header to the REST service that will solve the same origin policy problem, so client pages hosted on other servers can access the service.

  - **PersonFilter in config package**