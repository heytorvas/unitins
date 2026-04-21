from django.contrib import admin

from palographic.models import Palographic

# Register your models here.
@admin.register(Palographic)
class ExaminingAdmin(admin.ModelAdmin):
    list_display = (
        'id',
        'examining',
        'psychologist',
        'test_date',
        'schooling',
        'test',
        'created_at',
        'updated_at'
    )