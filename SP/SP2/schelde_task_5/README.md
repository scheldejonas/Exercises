##### Server architecture and deployment

--- 

Describtion of the task and questions, it is in pdf [here](https://github.com/scheldejonas/Exercises/blob/master/SP/SP2/Exam-preparation-server.pdf)

###### General part

1. What is a virtual environment?

It is software running inside a environment software, running on hardware

A virtual environment is the platform for the hardware, running universaly in a environment
that can be updated undependently of asking for the virtual software to make changes.

Explained in other way like this:

A Virtual Environment is a tool to keep the dependencies required by different 
projects in separate places, by creating virtual Java environments for them.

2. Which benefits does a virtual environment give us?

It makes us able to be total undependent on environment or hardware running software.

To let us implement and get our system to work on a whole lot of IT Infrastructure environments

3. Explain why Java is running in a virtual environment and how it relates to Java’s 
motto: write once, run everywhere.

So it is possible to use it on any Server platform environment implemented.

It helps me with a lot more flexsibility.

4. Explain what load balancing means.

Load balancing is the concept of making distribution of incoming network traffic become
very efficient.

A load balancer acts as the “traffic cop” sitting in front of your servers. With that
it routes client requests from users to clients with the needed content in a way, which is
reliable for the performance of the server, meaning starvation issues, but also to safe
deliver content to all requests in the most efficient way.

5. Give an example of one load balancing technique.

An balancing technique could be Round Robin, which means requests are distributed across
the group of servers sequentially.

Another way could be to use "IP Hash", that use the IP address of the client to determine which
server receives the request.

And a small comment is to go for software load balancing instead of hardware load balancing,
with example using NGINX for this purpose.

--- 

##### Practical part

- 1 - Deploying a server



