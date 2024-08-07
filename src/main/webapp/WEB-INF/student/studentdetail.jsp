<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Detail</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
        }

        .container {
            display: flex;
            width: 80%;
            height: 80%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .sidebar {
            background-color: #007bff;
            padding: 40px;
            width: 25%;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
        }

        .sidebar h2 {
            margin-bottom: 20px;
        }

        .button-group {
            width: 100%;
            display: flex;
            flex-direction: column;
            gap: 10px;
            margin-top: 20px;
        }

        .button-group a {
            width: 100%;
        }

        .button-group button {
            width: 100%;
            padding: 10px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .reply-button {
            background-color: green;
            color: white;
        }

        .secondary {
            background-color: white;
            color: black;
            border: 1px solid #ccc;
        }

        .content {
            background-color: #ffffff;
            padding: 20px;
            width: 75%;
        }

        .form-group {
            margin-top: 10px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input,
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            margin-bottom: 20px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="sidebar">
        <h2>안녕하세요<br>학생 ${student.sid} 님</h2>
        <div class="button-group">
            <a href="/reply?id=${message.mno}"><button class="reply-button">Reply</button></a>
            <a href="/studentlist"><button type="button" class="secondary">List</button></a>
        </div>
    </div>
    <div class="content">
        <div class="form-group">
            <label for="title">TITLE</label>
            <input type="text" id="title" name="title" value="${message.title}" readonly>
        </div>
        <div class="form-group">
            <label for="sender">SENDER</label>
            <input type="text" id="sender" name="sender" value="${message.sender}" readonly>
        </div>
        <div class="form-group">
            <label for="date">DATE</label>
            <input type="text" id="date" name="date" value="${message.senddate}" readonly>
        </div>
        <div class="form-group">
            <label for="content">CONTENT</label>
            <textarea id="content" name="content" rows="10" readonly>${message.content}</textarea>
        </div>
    </div>
</div>
</body>
</html>
