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
        <a href="/client.jar" id="download-button" class="btn-large waves-effect waves-light blue">Download GUI Client</a>
        <p class="grey-text">This client is printing to System.out for messages and listening on System.in, when receiving messages</p>
        <a href="/service-client.jar" id="download-button" class="btn-large waves-effect waves-light blue">Download Service Client</a>
        <p class="grey-text">This service client is printing messages to System.out and listening for firstly username, then message on System.in, when talking to GUI's</p>
    </div>
    <br>
    <br>
  </div>
</div>

<%@ include file="templates/fragments/footer.jsp"%>

<%@ include file="templates/fragments/scripts.jsp" %>

</body>
</html>

