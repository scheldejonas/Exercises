# Server

The task description [here](https://github.com/scheldejonas/Exercises/blob/master/EP/Exam-preparation-server.pdf)

## General part

Write 2-5 lines of text for each bullet:

- What is a virtual environment?
  - It is software running inside a environment software, running on hardware
  - A virtual environment is the platform for the hardware, running universaly in a environment that can be updated undependently of asking for the virtual software to make changes.
  - Explained in other way like this: A Virtual Environment is a tool to keep the dependencies required by different projects in separate places, by creating virtual Java environments for them.
- Which benefits does a virtual environment give us?
  - It makes us able to be total undependent on environment or hardware running software.
  - To let us implement and get our system to work on a whole lot of IT Infrastructure environments
- Explain why Java is running in a virtual environment and how it relates to Java’s motto: write once, run everywhere.
  - So it is possible to use it on any Server platform environment implemented. It helps me with a lot more flexsibility.
- Explain what load balancing means.
  - Load balancing is the concept of making distribution of incoming network traffic become
    very efficient.
  - A load balancer acts as the “traffic cop” sitting in front of your servers. With that it routes client requests from users to clients with the needed content in a way, which is reliable for the performance of the server, meaning starvation issues, but also to safe deliver content to all requests in the most efficient way.
- Give an example of one load balancing technique.
  - An balancing technique could be Round Robin, which means requests are distributed across
    the group of servers sequentially.
  - Another way could be to use "IP Hash", that use the IP address of the client to determine which server receives the request.
  - Another way is least_time,  for each request, NGINX Plus selects the server with the lowest average latency and the least number of active connections, where the lowest average latency is calculated based on which of the following parameters is included on the `least_time` directive:
    - `header` – Time to receive the first byte from the server
    - `last_byte` – Time to receive the full response from the server
  - And a small comment is to go for software load balancing instead of hardware load balancing, with example using NGINX for this purpose.

## Practical part

For this exercise we will write a simple Java chat server and deploy it behind a Nginx proxy on a server.

- Make a server and your login credentials to log in to the server
  - ssh-keygen
  - Digital Ocean exam-server: http://37.139.24.159/

- Make a installation of Nginx listening on port 80
  - New User:  https://www.digitalocean.com/community/tutorials/initial-server-setup-with-debian-8

  - Nginx install: 

  - Locales: https://people.debian.org/~schultmc/locales.html & https://askubuntu.com/questions/162391/how-do-i-fix-my-locale-issue

    - ```
      locale-gen "en_US.UTF-8"
      export LANGUAGE=en_US.UTF-8
      export LANG=en_US.UTF-8
      export LC_ALL=en_US.UTF-8
      export LC_CTYPE=en_US.UTF-8
      locale

      ```

  - Nginx: https://www.digitalocean.com/community/tutorials/how-to-install-nginx-on-debian-8

- Install Java SDK 1.8

  - http://www.mkyong.com/java/how-to-install-oracle-jdk-8-on-debian/ 

- Make a installation of Tomcat listening on another port than 80 (preferrably 8080)

  - Tomcat8: https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-apache-tomcat-on-a-debian-server

- HTTP Server One HTTP POST method which accepts text from incoming requests (either by a URL parameter or from the request body) and inserts that text into a list on the server.

  - **receiveRandomText() in ServerResource** (Just saving the text to Database class)

- HTTP Server One HTTP GET method which returns the content of that list in the body of the HTTP response to the client.

  - **getRandomText in ServerResource** (Just gettting the full list as json)

- Now package the project to a .war file, ready to be deployed on your remote server.

  - **war Plugin in Maven**

- Demonstrate that you can access the server by putting content into the list with the HTTP POST method, and getting it out with the HTTP GET method.

  - http://178.62.221.177/

- Implement a reverse proxy in Nginx on port 80 that forwards all traffic to the Tomcat server on another port (probably 8080).

  - Tomcat8 Optimization: https://www.digitalocean.com/community/tutorials/how-to-optimize-your-tomcat-installation-on-ubuntu-14-04

- Demonstrate that you can still reach your HTTP server that you just deployed through Nginx on port 80.

  - Getting it on root uri: https://stackoverflow.com/questions/5328518/deploying-my-application-at-the-root-in-tomcat

