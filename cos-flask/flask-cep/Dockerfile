FROM python:2.7
COPY ./ /usr/src/app
WORKDIR /usr/src/app
RUN pip install flask
RUN pip install psycopg2
EXPOSE 5000
CMD ["python", "app.py"]