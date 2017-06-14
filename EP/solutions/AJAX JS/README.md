# AJAX JS

The task description [here](https://github.com/scheldejonas/Exercises/blob/master/EP/exam-preparation_AJAX_JS.pdf)

## General part

- Elaborate on how JSON or XML supports communication between subsystems, even when the subsystems are implemented on different platforms. 

JSON or XML supports communication between subsystems, by being requested from the client, with JavaScript code.

XML stand for Extensible markup language, and has the same syntax with tags as html has, but the difference is xml has no limitations as to what you can name the tags. So for that reason xml tags can be named your collection names and inside those collections named tags you can have a xml tag for each entity instance, with it property fields as further xml tags inside.

JSON stands for JavaScript Object Notation and is directly possible to use in the syntax as JavaScript objects.

Basicly you use the Content-Type HTTP Header, do describe it for example is JSON you are sending in your HTTP Post request, to the server.

- Explain the topics: AJAX and Same Origin Policy, and different ways to work around it 

To make this possible asynchronus while a user is browser an already recieved webpage, we are using JavaScript with the object XMLHttpRequest.

The thing about this request, is that it is not possible to do on another sub domain, port or protocol type, then the domain currently in.

This is called the Same Origin Policy

The way to get around this would be by creating an web proxy connection, on your server, that makes a XMLHttpRequest get content by XML or JSON, through your own server as a tunnel.

Other ways to get around this can also be achived with JSONP, which stands for JSON with Padding. This is way to use public accessable JavaScript files on other sub domains, ports and protocol types. This way is ussualy used for CDN getting of frameworks like JQuery JavaScript files.

The third way it can be achieved, is by CORS, which stands for "Cross-Origin Resource Sharing", the way to make it happen is through a header property "Acces-Control-Allow-Origin" containing my servers
url.

## Practical part

1. Hook up an event handler on the map, get the id, perform an AJAX request to fetch the JSON-data from the link given above and update the GUI using the JSON returned as sketched above.
   1. **line 29-40 in** **country-popups.js** (On all countries in the svg map there is an id with the country name. That same name exactly can be fetched data about on a REST service).
   2. **line 6-25 in country-popups.js** (The fetch is being done and updating the popup with information on the side.)


2. For the previous task it was possible to obtain data right from *restcountries.eu *via an AJAX call made from within your Browser (as sketched to the right). Use Chrome Developer tools to explain (with focus on the Same Origin Policy) why this is possible.
   1. ![alt tag](http://schelde.info/wp-content/uploads/2017/03/scheldeinfo_sp4-billeder-768x427.png)

      These 3 headers:

      "Acces-Control-Allow-Origin: *"
      "Acces-Control-Allow-Headers: Accept, X-Requested-With"
      "Acces-Control-Allow-Methods: GET"

      Here the headers is the proof in the response the server is accepting
      CORS - "Cross-Origin Resource Sharing"

      The server (restcountries.eu) is accepting through it's backend to
      respond AJAX calls, and therefore makes it possible to call for JSON
      information from this server.

      To implement it on backend, there is different methods according to
      your server setup.


3. Let's assume restcountries.eu had not allowed Cross Origin Calls. Design a Web Proxy Solution (using a plain Servlet or JAX-RS) where your browser will send the request to your proxy who should forward the request on to the remote server and send back the received response.
   1. **CountryRessource** (Method just for string building what is coming back to from the country service, receiving the country as Query parameter.)
   2. **App Files in webapp public area**