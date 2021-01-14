"""Server URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from Tripplaner import views
from django.conf.urls import url

urlpatterns = [
    path('admin/', admin.site.urls),
    url(r'^login', views.Login, name='login'),
    #http://192.168.1.3:8080/login?u=fit18&p=fit18
    url(r'^signup', views.Signup, name='signup'),
    #http://192.168.1.3:8080/signup?u=nguyenhoangquan&p=nguyenhoangquan
    url(r'^trip', views.getTripUser, name="trip"),
    #http://192.168.1.3:8080/trip?id=1
    url(r'^addtrip', views.addTrip, name="addtrip"),
    #http://192.168.1.3:8080/addtrip?idu=3&name=a&bud=2000000&start=12&end=13&dep=HCM
    url(r'^expense', views.getExpenseTrip, name="expense"),
    #http://192.168.1.3:8080/expense?t=1
    url(r'^addexpense', views.addExpense, name="addexpense")
    #http://192.168.1.3:8080/addexpense?idt=1&name=an&cost=200000&type=2
]
