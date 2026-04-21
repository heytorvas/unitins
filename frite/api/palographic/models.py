from django.db import models

from api.models import Base
from examining.models import Examining
from psychologist.models import Psychologist

# Create your models here.
class Palographic(Base):
    examining = models.ForeignKey(Examining, related_name='palographic_examining', on_delete=models.CASCADE)
    psychologist = models.ForeignKey(Psychologist, related_name='palographic_psychologist', on_delete=models.CASCADE)
    test_date = models.DateField()
    schooling = models.CharField(max_length=30)
    test = models.ImageField(upload_to='images/', blank=True, null=True)

    class Meta:
        verbose_name = 'palographic'

    def __str__(self):
        return f'Palographic: {self.id}, {self.created_at}, {self.updated_at}, {self.examining}, {self.psychologist}, {self.test_date}, {self.schooling}, {self.test}'