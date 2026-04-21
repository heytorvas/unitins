from psychologist.models import Psychologist
from django.contrib import admin

# Register your models here.
@admin.register(Psychologist)
class ExaminingAdmin(admin.ModelAdmin):
    list_display = (
        'id',
        'name',
        'cpf',
        'birth_date',
        'email',
        'password',
        'created_at',
        'updated_at'
    )