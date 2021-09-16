from rest_framework import serializers

from examining.serializers import ExaminingSerializer
from psychologist.serializers import PsychologistSerializer

from palographic.models import Palographic

class PalographicSerializer(serializers.ModelSerializer):
    # examining = ExaminingSerializer(read_only=True)
    # psychologist = PsychologistSerializer(read_only=True)

    class Meta:
        model = Palographic
        fields = (
            'id',
            'examining',
            'psychologist',
            'test_date',
            'schooling',
            'test',
            'created_at',
            'updated_at'
        )

    def to_representation(self, instance):
        response = super().to_representation(instance)
        response['examining'] = ExaminingSerializer(instance.examining).data
        response['psychologist'] = PsychologistSerializer(instance.psychologist).data
        return response