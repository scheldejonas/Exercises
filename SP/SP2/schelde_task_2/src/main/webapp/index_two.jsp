<%--
  Created by IntelliJ IDEA.
  User: scheldejonas
  Date: 09/02/2017
  Time: 16.22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Materialize Framework Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
    <!-- Our CSS -->
    <link rel="stylesheet" type="text/css" href="/assets/app.css">
    <title>The world third time</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col s12 ">
                <h1>Hello world! again gain</h1>
            </div>
        </div>
        <div class="divider"></div>
        <img src="/assets/images/billede-mangler.png"/>
        <div class="divider"></div>
        <div id="my_box"></div>
        <div class="divider"></div>
        <div class="row">
            <form method="get" action="#" class="col s12">
                <input type="hidden" name="hidden" value="12345678" />
                <div class="row">
                    <div class="input-field col s6">
                        <input id="first_name" name="firstName" type="text" class="validate" />
                        <label for="first_name">First Name</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="last_name" name="lastName" type="text" class="validate" />
                        <label for="last_name">Last Name</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12 ">
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <form method="post" action="#" class="col s12">
                <input type="hidden" name="hidden" value="12345678" />
                <div class="row">
                    <div class="input-field col s6">
                        <input id="first_name" name="firstName"  type="text" class="validate" />
                        <label for="first_name">First Name</label>
                    </div>
                    <div class="input-field col s6">
                        <input id="last_name" name="lastName" type="text" class="validate" />
                        <label for="last_name">Last Name</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col s12 ">
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit
                            <i class="material-icons right">send</i>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <%@include file="fragments/scripts.jsp"%>
</body>
</html>
