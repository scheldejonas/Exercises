<%--
  Created by IntelliJ IDEA.
  User: scheldejonas
  Date: 17/02/17
  Time: 09:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <%@ include file="templates/fragments/headelements.jsp" %>
  <title>Chat | Velkommen</title>
</head>

<body>

<%@ include file="templates/fragments/navigation.jsp"%>

<div class="section no-pad-bot" id="index-banner">
  <div class="container">
    <br>
    <br>
    <h1 class="header center grey-text">Chat</h1>
    <div class="row center">
      <h5 class="header col s12 light">Velkommen til det nye chat.</h5>
    </div>
    <div class="row center">
<<<<<<< Updated upstream:CA/CA1/chat-web-server-onepage/web/index.jsp
        <a href="/chat/client.jar" id="download-button" class="btn-large waves-effect waves-light blue">Download GUI Client</a>
        <p class="grey-text">This client is printing to System.out for messages and listening on System.in, when receiving messages</p>
        <a href="/chat/service-client.jar" id="download-button" class="btn-large waves-effect waves-light blue">Download Service Client</a>
        <p class="grey-text">This service client is printing messages to System.out and listening for firstly username, then message on System.in, when talking to GUI's</p>
=======
        <a href="/library/chat-client.jar" id="download-button" class="btn-large waves-effect waves-light blue">Download Console UI Client</a>
        <p class="grey-text">This client makes you communicate with any chat server on a given ip and port. in Console, very handy</p>
        <a href="/library/chat-server.jar" id="download-button" class="btn-large waves-effect waves-light blue">Download Chat Server</a>
        <p class="grey-text">This is the chat server, which can be run only from the live server at the moment, if you are interested in trying being host, please contact me on jonas@schelde.info</p>
>>>>>>> Stashed changes:CA/CA1/chat-web-server-onepage/chat-onepage/out/index.jsp
    </div>
    <br>
    <br>
  </div>
</div>

<%@ include file="templates/fragments/footer.jsp"%>

<%@ include file="templates/fragments/scripts.jsp" %>

</body>
</html>

