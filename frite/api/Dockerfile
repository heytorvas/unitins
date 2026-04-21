# change {project} for your project's name

FROM python:3.9
ENV PYTHONUNBUFFERED=1

RUN apt-get update
RUN apt-get install ffmpeg libsm6 libxext6  -y

RUN mkdir /usr/src/frite/
WORKDIR /usr/src/frite/api

COPY ./requirements.txt .
RUN pip install -r requirements.txt

EXPOSE 8000