var express = require('express')
var fs = require("fs");
var path = require("path");
var app = express()
app.set('json spaces', 2);
var persons = [];
var personsConst = [];
var availableData = null;

//Simple JSON server that simulates members entering and/or leaving the "list"

fs.readFile(path.join(__dirname, "data.json"), (err, data) => {
  if (err) {
    throw new Error("Could not load data: " + err);
  }
  persons = JSON.parse(data);
})
fs.readFile(path.join(__dirname, "data.json"), (err, data) => {
  if (err) {
    throw new Error("Could not load data: " + err);
  }
  personsConst = JSON.parse(data);
})

fs.readFile(path.join(__dirname, "availableData.json"), (err, data) => {
  if (err) {
    throw new Error("Could not load data: " + err);
  }
  availableData = JSON.parse(data);
})

function randomizeData() {
  const random = Math.floor(Math.random() * 3);
  const indexToTouch = Math.floor(Math.random() * persons.length);
  //console.log("Items in array: " + persons.length);
  if (random <= 1) {
    if (persons.length > 2) {
      availableData.push(persons[indexToTouch]); //Reuse data
      persons.splice(indexToTouch, 1);
    }
  }
  else {
    if (persons.length < 8) {
      const indexToTouch = Math.floor(Math.random() * availableData.length);
      persons.push(availableData[indexToTouch]);
      availableData.splice(indexToTouch, 1);
    }
  }

}

app.use("/", (req, res, next) => {
  res.set("Access-Control-Allow-Origin", "*");
  next();
});

app.get('/api/persons', function (req, res) {
  res.json(personsConst);
})

app.get('/api/persons_changing', function (req, res) {
  res.json(persons);
})

app.listen(4567, () => {
  console.log(`
#########################################
## REST Server listening on port 4567 ###
## Test in a browser using:           ###
## http://localhost:4567/api/persons  ###
#########################################`);

  setInterval(randomizeData, 1500);
}
);

