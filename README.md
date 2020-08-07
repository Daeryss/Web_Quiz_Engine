Description

Currently, your service allows creating new quizzes, but there may be problems if the client didn't provide all the quiz data. In such cases, the service will create an incorrect unsolvable quiz which is very frustrating for those who are trying to solve it.

At this stage, you should fix this so that the service does not accept incorrect quizzes. Another task is to make quizzes more interesting by supporting the arbitrary number of correct options (from zero to all). It means that to solve a quiz, the client needs to send all correct options at once, or zero if all options are wrong.

Here is a few resources where you can read how to validate data in the API:

Bean validation with Spring Boot
Spring Boot bean validation
There are only two modified operations for creating and solving quizzes. All other operations should not be changed or deleted.

Create a new quiz

To create a new quiz, the client needs to send a JSON as the request's body via POST to /api/quizzes. The JSON should contain the four fields:

title: a string, required;
text: a string, required;
options: an array of strings, required, should contain at least 2 items;
answer: an array of indexes of correct options, optional, since all options can be wrong.
Here is a new JSON quiz as an example:

{
  "title": "Coffee drinks",
  "text": "Select only coffee drinks.",
  "options": ["Americano","Tea","Cappuccino","Sprite"],
  "answer": [0,2]
}
The answer equals [0,2] corresponds to the first and the third item from the options array ("Americano" and "Cappuccino").

The server response is a JSON with four fields: id, title, text and options. Here is an example:

{
  "id": 1,
  "title": "Coffee drinks",
  "text": "Select only coffee drinks.",
  "options": ["Americano","Tea","Cappuccino","Sprite"]
}
The id field is a generated unique integer identifier for the quiz. Also, the response may or may not include the answer field depending on your wishes. This is not very important for this operation.

If the request JSON does not contain title or text, or they are empty strings (""), then the server should respond with the 400 (Bad request) status code. If the number of options in the quiz is less than 2, the server returns the same status code.

Solving a quiz
To solve a quiz, the client sends the POST request to /api/quizzes/{id}/solve with a JSON that contains the indexes of all chosen options as the answer. This looks like a regular JSON object with key "answer" and value as the array: {"answer": [0,2]}. As before, indexes start from zero.

It is also possible to send an empty array [] since some quizzes may not have correct options.

The service returns a JSON with two fields: success (true or false) and feedback (just a string). There are three possible responses.

If the passed answer is correct:
{"success":true,"feedback":"Congratulations, you're right!"}
If the answer is incorrect:
{"success":false,"feedback":"Wrong answer! Please, try again."}
If the specified quiz does not exist, the server returns the 404 (Not found) status code.
You can write any other strings in the feedback field, but the names of fields and the true/false values must match this example.
