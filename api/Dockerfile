# change {project} for your project's name

FROM python:3.9
ENV PYTHONUNBUFFERED=1

RUN mkdir /usr/src/frite/
WORKDIR /usr/src/frite/api

COPY ./requirements.txt .
RUN pip install -r requirements.txt

EXPOSE 8000