import datetime
from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate


app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = "postgresql://chat:chat@postgres:5432/chat"
db = SQLAlchemy(app)
migrate = Migrate(app, db)


class Chat(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    message = db.Column(db.String())
    user = db.Column(db.String())
    time = db.Column(db.DateTime())

    def __init__(self, id, message, user, time):
        self.id = id
        self.message = message
        self.user = user
        self.time = time


@app.route("/", methods=["POST","GET"])
def chat_home():
    if request.method == 'POST':
        length = Chat.query.count()
        message = request.form['message']
        user = request.form['user']
        time = datetime.datetime.now()
        db.session.add(Chat(length+1, message, user, time))
        db.session.commit()
        return render_template('Chat.html', query = Chat.query.all())
    return render_template("Chat.html", query = Chat.query.all())