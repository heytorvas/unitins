from django.db import models

from api.models import Base

from palographic.models import Palographic

# Create your models here.
class Analysis(Base):
    palographic = models.ForeignKey(Palographic, related_name='analysis_palographic', on_delete=models.CASCADE)
    productivity = models.IntegerField()
    nor = models.FloatField()
    income = models.CharField(max_length=40)
    distance_between_palos = models.FloatField()
    palos_size = models.FloatField()
    impulsiveness = models.IntegerField()
    difference_sum = models.IntegerField()

    class Meta:
        verbose_name = 'analysis'

    def __str__(self):
        return f'Analysis: {self.id}, {self.created_at}, {self.updated_at}, {self.palographic}, {self.productivity}, {self.nor}, {self.income}, {self.distance_between_palos}, {self.palos_size}, {self.impulsiveness}, {self.difference_sum}'
