from django.db import models

class Base(models.Model):
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    class Meta:
        abstract = True

class Person(Base):
    name = models.CharField(max_length=40)
    cpf = models.CharField(max_length=11)
    birth_date = models.DateField()
    
    class Meta:
        abstract = True
        