# CA1 | Solution | Jonas Schelde

[Here](https://github.com/CphBusCosSem3/Exercises/tree/master/CA/CA1) is the teachers assignment repo

## Chat protocol

#### Design choices

- We have written the protocol in an interface firstly, to make sure what kinds of options you would have in this protocol.
- Also to have a way to overview, what is possible in terms of method, on the client, as to the service.
- Then of course there is just different kinds of implementations on the server and the client.
- The design is created with top-down approach, in the way; There is a server setup and a client setup.
- To this top-down structure design, each setup, both server and client, is seperated with a kind of industri standard package pattern named like this;
- Here is our Data modal:

![alt tag](images/chat_server_domain_model.png)

1. Web (here usually the .jsp files or other html files is placed to be used before responding to the user)
2. Controller (directs the requests and controls the threads overview)
3. Service (services the entities or the handles the data through the protocol for example, to be directed back to the controller or directly to be persisted for longer saving in the db, through dao)
4. Dao (this controls data acces to the database, keeping all data sent and recieved from database persistent)
5. Database (the relational table base with data saved)

Therefore threads is controlled as a seperate thread named ClientThread in the service layer. This i believe is simple for other developers to understand and build on top of.

## Chat server

We have with the same mentioned structure above used it to create the client.

In the client we did the same structure and of course used the same ChatProtocol interface.

The different part on client to notice is the view layer (package) where the part of making it able to be communicated with, through 
keyboard scanning in console. This is therefore the NON-GUI client.

The Swing GUI client, is actually made in design, but not implemented yet to the controller, but this should be extremly simple and 
straight forward, if nescessary at exam.

## Web server

And to end this project, the demands were to let this client and server be available on the web through a web server with https.

it is therefore online and working on this link:

- [online web server with chat client and server for download](https://77.66.48.34)

---
