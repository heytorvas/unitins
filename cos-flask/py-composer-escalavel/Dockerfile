FROM python:2.7
COPY ./ /usr/src/app
WORKDIR /usr/src/app
RUN pip install flask
RUN pip install elastic-apm[flask]
CMD ["python", "simples.py"]