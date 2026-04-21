from examining.serializers import ExaminingSerializer
from examining.models import Examining
from django.shortcuts import render
from rest_framework.viewsets import ModelViewSet

# Create your views here.
class ExaminingViewSet(ModelViewSet):
    queryset = Examining.objects.all()
    serializer_class = ExaminingSerializer