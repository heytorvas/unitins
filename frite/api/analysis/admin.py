from django.contrib import admin

from analysis.models import Analysis

# Register your models here.
@admin.register(Analysis)
class AnalysisAdmin(admin.ModelAdmin):
    list_display = (
        'palographic',
        'productivity',
        'nor',
        'income',
        'distance_between_palos',
        'palos_size',
        'impulsiveness',
        'difference_sum'
    )