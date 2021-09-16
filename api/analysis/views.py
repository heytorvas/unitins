from django.shortcuts import render

from rest_framework.viewsets import ModelViewSet

from analysis.models import Analysis
from analysis.serializers import AnalysisSerializer

# Create your views here.
class AnalysisViewSet(ModelViewSet):
    queryset = Analysis.objects.all()
    serializer_class = AnalysisSerializer