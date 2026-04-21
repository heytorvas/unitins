from django.contrib import admin
from examining.models import Examining
# Register your models here.

@admin.register(Examining)
class ExaminingAdmin(admin.ModelAdmin):
    list_display = (
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