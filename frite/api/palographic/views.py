from django.shortcuts import render

from rest_framework.viewsets import ModelViewSet
from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework.parsers import MultiPartParser, FormParser, FileUploadParser

from palographic.models import Palographic
from palographic.serializers import PalographicSerializer

from examining.serializers import ExaminingSerializer

from detection.detection import main

import requests, json

URL = 'http://localhost:8000/api/v1'

def get_token():
    data = {
        "email": "heytor@heytor.com",
        "password": "heytor"
    }
    r = requests.post(f'{URL}/login/', data=data)
    return r.json()['token']

def post_analysis(detection, palographic_id):
    token = get_token()
    headers = {
        "Content-type": "application/json",
        "Authorization": f"Bearer {token}"
    }
    data = {
        "palographic": int(palographic_id),
        "productivity": int(detection["productivity"]),
        "nor": float(detection["nor"]),
        "income": str(detection["income"]),
        "distance_between_palos": float(detection["distance_between_palos"]),
        "palos_size": float(detection["palos_size"]),
        "impulsiveness": int(detection["impulsiveness"]),
        "difference_sum": int(detection["difference_sum"])
    }
    r = requests.post(f'{URL}/analysis/', headers=headers, data=json.dumps(data))
    print(r)

# Create your views here.
class PalographicViewSet(ModelViewSet):
    queryset = Palographic.objects.all()
    serializer_class = PalographicSerializer

    parser_classes = (FormParser, MultiPartParser, FileUploadParser)

    def create(self, request):
        if "id" in request.POST:
            try:
                instance = Palographic.objects.get(pk=request.POST['id'])
                serializer = PalographicSerializer(
                    instance=instance,
                    data=request.data
                )
            except Palographic.DoesNotExist:
                serializer = PalographicSerializer(data=request.data)
        else:
            serializer = PalographicSerializer(data=request.data)

        serializer.is_valid(raise_exception=True)
        serializer.save()

        if int(Response.status_code) == 200:
            palographic = Palographic.objects.last()
            path_image = palographic.test
            palographic_id = palographic.id
            detection = main(path_image)
            post_analysis(detection, palographic_id)


        return Response(serializer.data)
