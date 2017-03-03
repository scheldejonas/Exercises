# Studypoint Exercise 4

[Here is the task description](https://github.com/scheldejonas/Exercises/tree/master/SP/SP4/_task%20description%20as%20from%20teachers)

## #1 Exercises from Javascript Day-1

## #2 Exercises from: Javascript Day-2

## #3 Exercises from Day-3: (Ajax)

## #4 Exam-preparation AJAX JS

[Here is the task description](https://github.com/scheldejonas/Exercises/blob/master/SP/SP4/_task%20description%20as%20from%20teachers/exam-preparation_AJAX_JS.pdf)

#### General part

JSON or XML supports communication between subsystems, by being
requested from the client, with JavaScript code.

XML stand for Extensible markup language, and has the same syntax with
tags as html has, but the difference is xml has no limitations as to
what you can name the tags. So for that reason xml tags can be named
your collection names and inside those collections named tags you can
have a xml tag for each entity instance, with it property fields as
further xml tags inside.

JSON stands for JavaScript Object Notation and is directly possible to
use in the syntax as JavaScript objects.

Basicly you use the Content-Type HTTP Header, do describe it for example
is JSON you are sending in your HTTP Post request, to the server.

To make this possible asynchronus while a user is browser an already
recieved webpage, we are using JavaScript with the object XMLHttpRequest.

The thing about this request, is that it is not possible to do on another
sub domain, port or protocol type, then the domain currently in.

This is called the Same Origin Policy

The way to get around this would be by creating an web proxy connection,
on your server, that makes a XMLHttpRequest get content by XML or JSON,
through your own server as a tunnel.

Other ways to get around this can also be achived with JSONP, which
stands for JSON with Padding. This is way to use public accessable
JavaScript files on other sub domains, ports and protocol types. This
way is ussualy used for CDN getting of frameworks like JQuery
JavaScript files.

The third way it can be achieved, is by CORS, which stands for
"Cross-Origin Resource Sharing", the way to make it happen is through
a header property "Acces-Control-Allow-Origin" containing my servers
url.

##### Practical Part

[See this project here]()

## #5 Exam-prepartion TestAndMocking