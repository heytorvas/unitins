from django.db import models
from api.models import Person


# Create your models here.
class Examining(Person):
    city = models.CharField(max_length=30)
    state = models.CharField(max_length=2)
    gender = models.CharField(max_length=10)
    schooling = models.CharField(max_length=30)
    
    class Meta:
        verbose_name = 'examining'

    def __str__(self):
        return self.name