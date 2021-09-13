from rest_framework import serializers
from examining.models import Examining

class ExaminingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Examining
        fields = (
            'id',
            'name',
            'cpf',
            'birth_date',
            'city',
            'state',
            'gender',
            'schooling',
            'created_at',
            'updated_at'
        )