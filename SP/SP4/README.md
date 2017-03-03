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

#### Practical Part

[See the JS project here](https://github.com/scheldejonas/Exercises/tree/master/SP/SP4/solution-four-exam-preparation-ajax-js)

###### Explain with Chrome Dev Tool CORS

![alt tag](http://schelde.info/wp-content/uploads/2017/03/scheldeinfo_sp4-billeder-768x427.png)

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

###### Web Proxy

[See the project here](https://github.com/scheldejonas/Exercises/tree/master/SP/SP4/solution-four-exam-preparation-web-proxy)

The shell is created, the proxy of the JSON content is missing implimentation.

## #5 Exam-prepartion TestAndMocking

[Here is the description of the task](https://github.com/scheldejonas/Exercises/blob/master/SP/SP4/_task%20description%20as%20from%20teachers/testVoidReturnSem3.pdf)

#### General part

###### Explain fundamental software testing

First we have to actually establish understanding, that test can be done
both after but also before writing the actual code.

The benefits about test first, is to make you kind of design
what object model, structure and classes you actually plan making.

The benefits compared to debugging after is also substantial.
The pattern difference is really, that you are setting out the coverage
in code actually, with the test code, so you know where to pick
the code / implementations, and get the green light.

The test after makes you forced to spend a lot of time debugging and some
times not getting to write the actual test, so that future
developers can read that test, with that, get the understanding of what is
requirements of situtations for this code to cover.

There is an experienced difference when you as developer has been
writing test-first code for a while. You start to become
a bit wrong or strange, when there is not that line of
green light to fulfill after.

The test after is there, in terms of agile development, not
really going more forward. But it all depends on your setup of
course.

###### Explain basics about writing JUnit tests

The basics is that you ofcourse know, the IDE or runner, actually can compile and run
in the 2 different moodes. TestCompile(For tests) and Compile(For production running).

When you are writing the tests, you are actually able to just
head on and start with the test methods with test annotiations.

I addition to that, you are able to go in and prepare instances or
test data for the test methods, you do that with some Before annotations.

When running the test code, you also need to consider that methods you call
could just have the wrong pointer also, so this is where After JUnit notation comes in
to make you able to verify that methods actually have been run, for the test to
pass.

###### Explain about strategies/frameworks to unit test code with complex dependencies

JUnit is a Java Test Framework, but when you backend server environment app, starts to
become more and more complex with dependicies from f.x. maven, it get's
more interesting to up your test framework to.

There is a test library Mockito, that makes it easy for you to "mock" an
example of you class for example.

You can here with the Mock Annotation of a say dao instance, create a test
ready dao class, when you want to test it, you want to do it, in an test ready instance
of you class that uses this dao class.

There the annotation InjectMocks, are the instance the mocks will be initialized inside.

So you are easily able to setup parts or areas of your model environment.

The thing about dependencies when you are testing, is also you shall
not test the core framework functions. Those are not you responsibility.
It is to cover the business logic for example or app specific usage of the dependency
you have used.

###### Explain the topics State Verification vs. Behavior Verification

The key difference here is how we verify that a method or chain of methods did the right thing in its interaction.

With state verification we do this by asserts against the warehouse's state.

Mocks use behavior verification, where we instead check to see if the method made the correct calls on the chained methods.

We do this check by telling the mock what to expect during setup and asking the mock to verify itself during verification.

Only the method is checked using asserts, and if the method doesn't change the state of the method is that there's no asserts at all.

###### Explain testers "just love" the dependency injection pattern

Wikipedia explains it just as a way to inject dependencies while running
the code.

First of all it is of course important to note, that Dependency injection is not
a framework, it is a design pattern for test.

But the reason the love it, is the ability to have the dependencies just available
is it makes it a lot easier to setup the whole test to compile first and
then after compile done, the dependencies can be used for example your bean objects to config
or dao objects, easy in TestRuntime.

#### Practical part

[See this project]()