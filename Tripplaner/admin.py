from django.contrib import admin
from .models import *
# Register your models here.
admin.site.register(Account)
admin.site.register(Trip)
admin.site.register(Expense)
admin.site.register(Share)