# Designing an Application Layer Protocol (On top of TCP)

##### Descripotion

We know that, if a browser sends information like below, to a server:
The Server can, along with many other things, detect:

- The mime types the browser will accept in the answer,
- The preferred language for the reply
- Lookup a session object that belongs to this request (state on top of a stateless protocol)
- Detect the browser etc.

This is possible, because both ends have agreed on a shared protocol (HTTP), so both know how to interpret the speci c header values.

If you read the full HTTP speci ca on you could build your own Browser, or your own Server that could interoperate with all exis ng Web Servers or Browsers.

If this is possible for something as complex as HTTP, it must be possible for us to do a simple chat protocol that (running on top of TCP) would allow us to:

- Allow clients to connect (we don't care about login, and assume that all users have a unique username)
- Can provide clients with real  me info about all users online (constantly updated when new cha ers connect or
online cha ers disconnect)
- Allow clients to send a message to a:
o single user (Lars)
o several users (Lars, Jens, Jan) o all users
Re ect about this
- How would you design a protocol that could implement this behaviour?
- What would be the  rst "protocol thing" sent between the two parts (by which end)?
- Which "protocol thing" could the server use to report about all current online users?
- Which "protocol thing" could a client use send a message in the ways described above?
- Which "protocol thing" could the server use to pass on a message to clients, including both the sender and
message?
- Which "protocol thing" could a client use to indicate it would disconnect.

Think about a "protocol thing" as a way to send a text string with both a key (token), as for example Cache-Control (key) and max-age=0 (value) in the HTTP-example above, and a value.

Order will probably ma er (not all protocol messages can be sent at all  mes). This will be a statefull protocol.

We will discuss this today, and design a protocol that all servers and clients will have to implement.

#### TASK

#### 1) TCP Time Server
- TASK: *Write a TCP time server which should listen on a port and an network interface given as
command line arguments.  (Hint: ``.bind()`` method)*
- TASK: *Time should be returned in the format: ``Tue Sep 02 16:00:34 UTC 2014``.*
- TASK: *Test your server using a telnet client*
- LEARNED: Check TimeServer.java
- LEARNED: Respons from terminal with "telnet localhost 8080" worked just fine.

#### 2) TCP Time Client
- TASK: *Write you own client, which can connect to your TCP time server and print the time.*
- LEARNED: Stoppet here and went to task 3 before trying again.

#### 3) Echo server with extended protocol
- TASK: *Echo server basic here: [jonas forked repo](https://github.com/scheldejonas/week1-concurrency)*
- TASK: *This package contains two classes: An ``EchoClient`` and a ``EchoServer``. The server listens for connections forever,
and when a connection is established, it listens to the input and simply echoes the same back to the client.*
- TASK: *The client connects to the server and writes a string. When it writes it waits for a reply, prints it and quits.*
- LEARNED: Check EchoClient.java and EchoServer.java

Expand the echo server we did together in the class with the following behaviour.

| Message sent from client to server | Reply from server |
| ---- | ---- |
| UPPER#Hello World | HELLO WORLD |
| LOWER#Hello World | hello world |
| REVERSE#abcd | Dcba |
| TRANSLATE#hund | dog |

If the server receives any message that does not fit the patterns given above, it should 
close the connection.

#### 4) Extend the Echo Server with the following functionality:
- TASK: 1. The server should be able to handle many clients simultaneously
- TASK: 2. When a client sends a message to the server, the server should echo the 
message to all connected clients
- TASK: 3. Provide the Client with an Observer based design, to let users subscribe 
for events (incoming data).  The solution must use a separate Thread to 
read incoming data in order to avoid blocking problems.
- TASK: 4. Provide the client with a GUI.
- LEARNED: I am missing ideas, how to fit threads together with this server?
- LEARNED: Stopped and went to next assignment

Just add a few hardcode words in a map on the server. Return ``#NOT_FOUND`` for a 
word not found.