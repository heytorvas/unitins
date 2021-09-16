from rest_framework import serializers

from palographic.serializers import PalographicSerializer
from analysis.models import Analysis

class AnalysisSerializer(serializers.ModelSerializer):

    class Meta:
        model = Analysis
        fields = (
            'id',
            'palographic',
            'productivity',
            'nor',
            'income',
            'distance_between_palos',
            'palos_size',
            'impulsiveness',
            'difference_sum',
            'created_at',
            'updated_at'
        )

    def to_representation(self, instance):
        response = super().to_representation(instance)
        response['palographic'] = PalographicSerializer(instance.palographic).data
        return response