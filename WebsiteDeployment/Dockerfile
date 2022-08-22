FROM python:3.8.5-slim

WORKDIR /code

COPY . .

RUN pip install --upgrade pip
RUN pip install -r requirements.txt


CMD gunicorn app:app --bind 0.0.0.0:8000


# gunicorn is a WSGI (Web Server Gateway Interface), also can be described as "reverse proxy". 
# makes it possible for server to interact with application
