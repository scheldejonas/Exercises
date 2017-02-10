##### Part 1: deploying Server

---

Look questions in this Readme file [forked exercise repo](https://github.com/scheldejonas/week2-network/tree/master/ex/ex4)

Good Markdown syntax [sheet](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)

###### 1.1 - Creating a web application

Java Web application created in IntelliJ IDEA

Servlet created her [Servlet](https://github.com/scheldejonas/Exercises/blob/master/SP/SP2/schelde_task_3/src/controller/Servlet.java)

###### 1.2 - Installing Tomcat

Setup here - [http://77.66.48.34:8090/](http://77.66.48.34:8090/)

###### 1.3 - Deploying your web application

Here is the war file [war file repo](https://github.com/scheldejonas/Exercises/tree/master/SP/SP2/schelde_task_3/out/artifacts/schelde_task_3_war)

Here is the [live root url link](http://77.66.48.34:8090/schelde_task_3_war/).

Here is the Servlet method link [live /index link](http://77.66.48.34:8090/schelde_task_3_war/index)

###### 1.4 - Redirecting Nginx traffic

This is the setup running on port 8090, set on ```/etc/tomcat8/server.xml```

```
     Client
      |
      | HTTPS
      |
+--- Server ---+
|     |        |
|     | HTTP   |
|     |        |
|    Tomcat    |
|              |
+--------------+
```

Changed this:

```
location / {
                # First attempt to serve request as file, then
                # as directory, then fall back to displaying a 404.
                try_files $uri $uri/ =404;
        }
```

to this

```
 location / {
                # First attempt to serve request as file, then
                # as directory, then fall back to displaying a 404.
                # try_files $uri $uri/ =404;
                proxy_pass http://127.0.0.1:8090/schelde_task_3_war/index;
        }
```

Try out on [url with http port](http://77.66.48.34)

Proxy pass from server_ip -> http://127.0.0.1:8090/schelde_task_3_war/index

Done

###### 1.5 - Closing down tomcat

Added the parameter "address" for connection in tag, 

```
<Connector port="8090" address="localhost" protocol="HTTP/1.1"
               connectionTimeout="20000"
               URIEncoding="UTF-8"
               redirectPort="8453" />
```

restartet tomcat8 service.

Now sudo netstat -tulpn, shows tomcat isn't listening on 0.0.0.0

--- 

##### Part 2 - Virtualisation

###### 1 - What does virtualisation mean?

Virtualisation means the computer runs as a piece of software sharing physical hardware

###### 2 - How are you using virtualisation right now?

Digital Ocean Debain server, is running in a virtual environment, which means in
down to hardware, my server is sharing ressources with a lot of the other virtual servers
setup on digital ocean

###### 3 - Why is Java using virtualisation? And how does it help you?

So it is possible to use it on any Server platform environment implemented.

It helps me with a lot more flexsibility.

